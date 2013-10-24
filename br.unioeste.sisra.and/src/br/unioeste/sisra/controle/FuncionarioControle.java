/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.controle;

import br.unioeste.sisra.controle.validacao.FuncionarioValidacao;
import br.unioeste.sisra.modelo.entidade.Funcionario;
import br.unioeste.sisra.modelo.execao.DaoException;
import br.unioeste.sisra.modelo.execao.ValidacaoException;
import br.unioeste.sisra.modelo.listener.IFuncionarioListener;
import br.unioeste.sisra.modelo.to.FuncionarioTO;
import br.unioeste.sisra.persistencia.dao.FuncionarioDao;
import br.unioeste.sisra.persistencia.factory.PostgresqlDaoFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author J.C
 */
public class FuncionarioControle {

    private IFuncionarioListener listener;

    public FuncionarioControle(IFuncionarioListener listener) {
        this.listener = listener;

    }

    public void gravar(Object retorno, boolean novo) throws Exception {
        FuncionarioTO to = (FuncionarioTO) retorno;
        //TODO precisamos criar uma validação de tadas dentro do validate
        new FuncionarioValidacao().validar(retorno);

        Funcionario funcionario = new Funcionario();
        funcionario.setId(to.getId());
        funcionario.setNome(to.getNome());
        funcionario.setSexo(to.getSexo());
        
        
        //conversão de datas
        funcionario.setDtNasc(to.getDtNascimento());
        funcionario.setDtContrata(to.getDtContratacao());
        if (to.getDtDemissao() != null)
            funcionario.setDtDemite(to.getDtDemissao());
        
        funcionario.setRg(to.getRg());
        funcionario.setRua(to.getRua());
        funcionario.setBairro(to.getBairro());
        funcionario.setCidade(to.getCidade());
        if (to.getNumero().trim().length() > 0) {
            funcionario.setNumResidencia(Integer.parseInt(to.getNumero()));
        }
        funcionario.setEstado(to.getEstado());
        funcionario.setPais(to.getPais());
        funcionario.setCpf(to.getCpf());


        FuncionarioDao funcionarioDao = PostgresqlDaoFactory.getDaoFactory().getFuncionarioDao();
        try {
            if (novo) {
                funcionarioDao.insert(funcionario, funcionario.getId());
            } else {
                funcionarioDao.update(funcionario.getId(), funcionario);
                
            }
        } catch (DaoException ex) {
            Logger.getLogger(FuncionarioControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // ------------------------------------------------------------------------
    // BUSCAS
    // ------------------------------------------------------------------------
    public void buscarFuncionariosPorId(String text) throws ValidacaoException, Exception {


        FuncionarioDao dao = PostgresqlDaoFactory.getDaoFactory().getFuncionarioDao();
        try {
            Funcionario[] funcionarios;
            
            if (text.trim().length() == 0) {
                funcionarios = dao.findAll();
            } else {
                Long idFuncionario = new FuncionarioValidacao().validaLong(text);
                funcionarios = dao.findWhereCodigoEquals(idFuncionario);
            }
            
            for (Funcionario funcionario : funcionarios) {
                System.out.println("- " + funcionario.toString());
            }
            listener.exibirBusca(converterEntidadesEmTO(funcionarios));
        } catch (DaoException ex) {
            throw new Exception(ex);
        }
    }

    public void buscarFuncionariosPorNome(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void buscarFuncionariosPorCPF(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public FuncionarioTO bucarFuncionarioPorChave(String pk) throws Exception {
        FuncionarioDao funcionarioDao = PostgresqlDaoFactory.getDaoFactory().getFuncionarioDao();

        Funcionario[] funcionarios = funcionarioDao.findWhereCodigoEquals(new FuncionarioValidacao().validaLong(pk));
        if (funcionarios.length > 0) {
            return funcionarios[0].toTO();
        } else {
            return new FuncionarioTO();
        }
    }
    // ------------------------------------------------------------------------
    // OUTROS MÉTODOS
    // ------------------------------------------------------------------------

    private FuncionarioTO[] converterEntidadesEmTO(Funcionario[] funcionarios) throws Exception {
        FuncionarioTO[] result = new FuncionarioTO[funcionarios.length];
        int i = 0;
        for (Funcionario f : funcionarios) {
            result[i++] = f.toTO();
        }

        return result;
    }

    public void excluirFuncionario(String pk) throws Exception {
        FuncionarioDao funcionarioDao = PostgresqlDaoFactory.getDaoFactory().getFuncionarioDao();
        funcionarioDao.delete(new FuncionarioValidacao().validaLong(pk));
        listener.funcionarioExcluidoSucesso(pk);
    }
}
