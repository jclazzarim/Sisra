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
import br.unioeste.sisra.persistencia.dao.ContaDao;
import br.unioeste.sisra.persistencia.factory.PostgresqlDaoFactory;
import br.unioeste.sisra.utils.DataUtils;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Charlinho
 */
public class ContaControle {
	public class Query{
		public final static String POR_MESA_E_EM_ABERTO = "POR_MESA_E_EM_ABERTO";
	}
	
     private IContaListener listener;

    public ContaControle(IContaListener listener) {
        this.listener = listener;

    }
    
    public static Conta contaTOAdapter(ContaTO to) throws Exception{
        Conta conta = new Conta();
        conta.setId(to.getId());
     //   conta.setHoraAbertura( new Timestamp(to.getHoraAbertura());
       // conta.setHoraFechamento(new Timestamp(DataUtils.converterStringParaData(to.getHoraFechamento()).getTime()));
        
        
        conta.setMesa(MesaControle.mesaTOAdapter(to.getMesaTO()));
        return conta;
    }

    public void gravar(Object retorno, boolean novo) throws Exception {
        ContaTO to = (ContaTO) retorno;
        //TODO precisamos criar uma validação de tadas dentro do validate
      //  new ContaValidacao().validar(retorno);

        Conta funcionario = contaTOAdapter(to);

        ContaDao funcionarioDao = PostgresqlDaoFactory.getDaoFactory().getContaDao();
        try {
            if (novo) {
                funcionarioDao.insert(funcionario, funcionario.getId());
            } else {
                funcionarioDao.update(funcionario.getId(), funcionario);
                
            }
        } catch (DaoException ex) {
            Logger.getLogger(ContaControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // ------------------------------------------------------------------------
    // BUSCAS
    // ------------------------------------------------------------------------
    public void buscarContasPorId(String text) throws ValidacaoException, Exception {


        ContaDao dao = PostgresqlDaoFactory.getDaoFactory().getContaDao();
        try {
            Conta[] funcionarios;
            
            if (text.trim().length() == 0) {
                funcionarios = dao.findAll();
            } else {
                Long idConta = new ContaValidacao().validaLong(text);
                funcionarios = dao.findWhereCodigoEquals(idConta);
            }
            
            for (Conta funcionario : funcionarios) {
                System.out.println("- " + funcionario.toString());
            }
            listener.exibirBusca(converterEntidadesEmTO(funcionarios));
        } catch (DaoException ex) {
            throw new Exception(ex);
        }
    }

    public void buscarContasPorNome(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void buscarContasPorCPF(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ContaTO bucarContaPorChave(String pk) throws Exception {
        ContaDao funcionarioDao = PostgresqlDaoFactory.getDaoFactory().getContaDao();

        Conta[] funcionarios = funcionarioDao.findWhereCodigoEquals(new ContaValidacao().validaLong(pk));
        if (funcionarios.length > 0) {
            return funcionarios[0].toTO();
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
            result[i++] = c.toTO();
        }

        return result;
    }

    public void excluirConta(String pk) throws Exception {
        ContaDao funcionarioDao = PostgresqlDaoFactory.getDaoFactory().getContaDao();
        funcionarioDao.delete(new ContaValidacao().validaLong(pk));
        listener.funcionarioExcluidoSucesso(pk);
    }
}
