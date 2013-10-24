/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.controle;

import br.unioeste.sisra.controle.validacao.FuncionarioValidacao;
import br.unioeste.sisra.controle.validacao.MesaValidacao;
import br.unioeste.sisra.modelo.entidade.Mesa;
import br.unioeste.sisra.modelo.execao.DaoException;
import br.unioeste.sisra.modelo.listener.IMesaListener;
import br.unioeste.sisra.modelo.to.MesaTO;
import br.unioeste.sisra.persistencia.dao.MesaDao;
import br.unioeste.sisra.persistencia.factory.PostgresqlDaoFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Charlinho
 */
public class MesaControle {

    private IMesaListener listener;

    public MesaControle(IMesaListener listener) {
        this.listener = listener;
    }

    public void gravar(Object retorno, boolean novo) throws Exception {
        MesaTO to = (MesaTO) retorno;
        //TODO precisamos criar uma validação de tadas dentro do validate
        new MesaValidacao().validar(retorno);

        Mesa mesa = new Mesa();
        mesa.setId(to.getId());
        //mesa.setStatus(to.Status());
        mesa.setDescricao(to.getDescricao());
        mesa.setObs(to.getObs());



        MesaDao mesaDao = PostgresqlDaoFactory.getDaoFactory().getMesaDao();
        try {
            if (novo) {
                mesaDao.insert(mesa, mesa.getId());
            } else {
                mesaDao.update(mesa.getId(), mesa);

            }
        } catch (DaoException ex) {
            Logger.getLogger(MesaControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void buscarMesasPorId(String text) throws Exception {
        MesaDao dao = PostgresqlDaoFactory.getDaoFactory().getMesaDao();
        try {
            Mesa[] mesas;

            if (text.trim().length() == 0) {
                mesas = dao.findAll();
            } else {
                Long idMesa = new MesaValidacao().validaLong(text);
                mesas = dao.findWhereCodigoEquals(idMesa);
            }

            for (Mesa mesa : mesas) {
                System.out.println("- " + mesa.toString());
            }
            listener.exibirBusca(converterEntidadesEmTO(mesas));
        } catch (DaoException ex) {
            throw new Exception(ex);
        }
    }

    public void buscarMesasPorNome(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void buscarMesasPorPRECO(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public MesaTO bucarMesaPorChave(String pk) throws Exception{
        MesaDao dao = PostgresqlDaoFactory.getDaoFactory().getMesaDao();

        Mesa[] itens = dao.findWhereCodigoEquals(new FuncionarioValidacao().validaLong(pk));
        if (itens.length > 0) {
            return itens[0].toTO();
        } else {
            return new MesaTO();
        }
    }

    public void excluirMesa(String pk) throws Exception{
        MesaDao dao = PostgresqlDaoFactory.getDaoFactory().getMesaDao();
        dao.delete(new MesaValidacao().validaLong(pk));
        listener.mesaExcluidaSucesso(pk);
    }

    private MesaTO[] converterEntidadesEmTO(Mesa[] mesas) {
        MesaTO[] result = new MesaTO[mesas.length];
        int i = 0;
        for (Mesa mesa : mesas) {
            result[i++] = mesa.toTO();
        }

        return result;
    }
}
