/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.controle;

import br.unioeste.sisra.controle.validacao.ContaValidacao;
import br.unioeste.sisra.modelo.entidade.Conta;
import br.unioeste.sisra.modelo.execao.DaoException;
import br.unioeste.sisra.modelo.execao.ValidacaoException;
import br.unioeste.sisra.modelo.listener.IContaListener;
import br.unioeste.sisra.modelo.to.ContaTO;
import br.unioeste.sisra.modelo.to.MesaTO;
import br.unioeste.sisra.persistencia.dao.ContaDao;
import br.unioeste.sisra.persistencia.factory.PostgresqlDaoFactory;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Charlinho
 */
public class ContaControle {

    public class Query {

        public final static String POR_MESA_E_EM_ABERTO = "POR_MESA_E_EM_ABERTO";
    }
    private IContaListener listener;

    public ContaControle(IContaListener listener) {
        this.listener = listener;

    }

    public static Conta contaTOAdapter(ContaTO to) throws Exception {
        Conta conta = new Conta();
        conta.setId(to.getId());
        conta.setDescricao(to.getDescricao());
        conta.setHoraAbertura(to.getHoraAbertura());
        conta.setHoraFechamento(to.getHoraFechamento());

        conta.setMesa(MesaControle.mesaTOAdapter(to.getMesaTO()));
        conta.setTotal(new Double(to.getTotal()));
        return conta;
    }

    public void gravar(Object obj, boolean novo) throws Exception {
        ContaTO to = (ContaTO) obj;
        //TODO precisamos criar uma validação de tadas dentro do validate
        //  new ContaValidacao().validar(retorno);

        Conta conta = contaTOAdapter(to);

        ContaDao contaDao = PostgresqlDaoFactory.getDaoFactory().getContaDao();
        try {
            if (novo) {
                contaDao.insert(conta, conta.getId());
            } else {
                contaDao.update(conta.getId(), conta);
            }
        } catch (DaoException ex) {
            Logger.getLogger(ContaControle.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Atualiza se a mesa está ou não ocupada
        MesaControle mesaControle = new MesaControle(null);
        mesaControle.atualizarMesaOcupada(to.getMesaTO());
    }

    public void fecharConta(String pk) throws DaoException, ValidacaoException, Exception {

        ContaDao dao = PostgresqlDaoFactory.getDaoFactory().getContaDao();
        Conta[] contas;

        Long idConta = new ContaValidacao().validaLong(pk);
        contas = dao.findWhereCodigoEquals(idConta);

        Conta conta = contas[0];
        conta.setHoraFechamento(new Date());

        gravar(conta.toTO(), false);

    }

    // ------------------------------------------------------------------------
    // BUSCAS
    // ------------------------------------------------------------------------
    public void buscarContasPorId(String text) throws ValidacaoException, Exception {

        ContaDao dao = PostgresqlDaoFactory.getDaoFactory().getContaDao();
        try {
            Conta[] contas;

            if (text.trim().length() == 0) {
                contas = dao.findAll();
            } else {
                Long idConta = new ContaValidacao().validaLong(text);
                contas = dao.findWhereCodigoEquals(idConta);
            }

            for (Conta funcionario : contas) {
                System.out.println("- " + funcionario.toString());
            }
            listener.exibirBusca(converterEntidadesEmTO(contas));
        } catch (DaoException ex) {
            throw new Exception(ex);
        }
    }

    public void buscarContasAbertas() throws DaoException, Exception {
        ContaDao dao = PostgresqlDaoFactory.getDaoFactory().getContaDao();
        Conta[] contas;
        contas = dao.findAll();
        Conta[] retorno = new Conta[contas.length];

        for (int i = 0; i < retorno.length; i++) {
            Conta conta = contas[i];
            if (conta.getHoraFechamento() == null) {
                retorno[i] = conta;
            }
        }

        listener.exibirBusca(converterEntidadesEmTO(retorno));
    }

    public void buscarContasPorDescricao(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void buscarContasPorMesa(MesaTO mesa, boolean abertas) throws ValidacaoException, Exception {
        Long idMesa = mesa.getId();

        ContaDao dao = PostgresqlDaoFactory.getDaoFactory().getContaDao();

        Conta[] contas = dao.findWhereIdMesa(idMesa, abertas);

        for (Conta funcionario : contas) {
            System.out.println("- " + funcionario.toString());
        }
        listener.exibirBusca(converterEntidadesEmTO(contas));

    }

    public ContaTO bucarContaPorChave(String pk) throws Exception {
        ContaDao contaDao = PostgresqlDaoFactory.getDaoFactory().getContaDao();

        Conta[] contas = contaDao.findWhereCodigoEquals(new ContaValidacao().validaLong(pk));
        if (contas.length > 0) {
            return contas[0].toTO();
        } else {
            return new ContaTO();
        }
    }
    // ------------------------------------------------------------------------
    // OUTROS MÉTODOS
    // ------------------------------------------------------------------------

    private ContaTO[] converterEntidadesEmTO(Conta[] contas) throws Exception {
        ContaTO[] result = new ContaTO[contas.length];
        int i = 0;

        for (Conta c : contas) {
            if (c != null) {
                result[i++] = c.toTO();
            }
        }

        return result;
    }

    public void excluirConta(String pk) throws Exception {
        ContaDao funcionarioDao = PostgresqlDaoFactory.getDaoFactory().getContaDao();
        funcionarioDao.delete(new ContaValidacao().validaLong(pk));
        listener.excluidoSucesso(pk);
    }
}
