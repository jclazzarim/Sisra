/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.controle;

import br.unioeste.sisra.controle.validacao.PedidoItemValidacao;
import br.unioeste.sisra.modelo.entidade.PedidoItem;
import br.unioeste.sisra.modelo.execao.DaoException;
import br.unioeste.sisra.modelo.execao.ValidacaoException;
import br.unioeste.sisra.modelo.listener.IPedidoItemListener;
import br.unioeste.sisra.modelo.to.PedidoItemTO;
import br.unioeste.sisra.modelo.to.MesaTO;
import br.unioeste.sisra.persistencia.dao.PedidoItemDao;
import br.unioeste.sisra.persistencia.dao.MesaDao;
import br.unioeste.sisra.persistencia.factory.PostgresqlDaoFactory;
import br.unioeste.sisra.utils.DataUtils;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Charlinho
 */
public class PedidoItemControle {

    public class Query {

        public final static String POR_MESA_E_EM_ABERTO = "POR_MESA_E_EM_ABERTO";
    }
    private IPedidoItemListener listener;

    public PedidoItemControle(IPedidoItemListener listener) {
        this.listener = listener;

    }

    public static PedidoItem pedidoItemTOAdapter(PedidoItemTO to) throws Exception {
        PedidoItem pedidoItem = new PedidoItem();
        pedidoItem.setIdPedidoItem(to.getIdPedidoItem());
        pedidoItem.setQuantidade(to.getQuantidade());
        pedidoItem.setStatus(to.getStatus());
        pedidoItem.setItem(ItemControle.itemTOAdapter(to.getItem()));
        pedidoItem.setPedido(PedidoControle.pedidoTOAdapter(to.getPedido()));

        return pedidoItem;
    }

    public void gravar(Object obj, boolean novo) throws Exception {
        PedidoItemTO to = (PedidoItemTO) obj;
        //TODO precisamos criar uma validação de tadas dentro do validate
        //  new PedidoItemValidacao().validar(retorno);

        PedidoItem pedidoItem = pedidoItemTOAdapter(to);

        PedidoItemDao pedidoItemDao = PostgresqlDaoFactory.getDaoFactory().getPedidoItemDao();
        try {
            if (novo) {
                pedidoItemDao.insert(pedidoItem, pedidoItem.getIdPedidoItem());
            } else {
                pedidoItemDao.update(pedidoItem.getIdPedidoItem(), pedidoItem);
            }            
        } catch (DaoException ex) {
            Logger.getLogger(PedidoItemControle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    // ------------------------------------------------------------------------
    // BUSCAS
    // ------------------------------------------------------------------------
    public void buscarPedidoItemsPorId(String text) throws ValidacaoException, Exception {

        PedidoItemDao dao = PostgresqlDaoFactory.getDaoFactory().getPedidoItemDao();
        try {
            PedidoItem[] pedidoItems;

            if (text.trim().length() == 0) {
                pedidoItems = dao.findAll();
            } else {
                Long idPedidoItem = new PedidoItemValidacao().validaLong(text);
                pedidoItems = dao.findWhereCodigoEquals(idPedidoItem);
            }

            for (PedidoItem funcionario : pedidoItems) {
                System.out.println("- " + funcionario.toString());
            }
            listener.exibirBusca(converterEntidadesEmTO(pedidoItems));
        } catch (DaoException ex) {
            throw new Exception(ex);
        }
    }

    public void buscarPedidoItemsPorDescricao(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void buscarPedidoItemsPorMesa(MesaTO mesa, boolean abertas) throws ValidacaoException, Exception {
        Long idMesa = mesa.getId();

        PedidoItemDao dao = PostgresqlDaoFactory.getDaoFactory().getPedidoItemDao();

        PedidoItem[] pedidoItems = dao.findWhereIdMesa(idMesa, abertas);

        for (PedidoItem funcionario : pedidoItems) {
            System.out.println("- " + funcionario.toString());
        }
        listener.exibirBusca(converterEntidadesEmTO(pedidoItems));

    }

    public PedidoItemTO bucarPedidoItemPorChave(String pk) throws Exception {
        PedidoItemDao pedidoItemDao = PostgresqlDaoFactory.getDaoFactory().getPedidoItemDao();

        PedidoItem[] pedidoItems = pedidoItemDao.findWhereCodigoEquals(new PedidoItemValidacao().validaLong(pk));
        if (pedidoItems.length > 0) {
            return pedidoItems[0].toTO();
        } else {
            return new PedidoItemTO();
        }
    }
    // ------------------------------------------------------------------------
    // OUTROS MÉTODOS
    // ------------------------------------------------------------------------

    private PedidoItemTO[] converterEntidadesEmTO(PedidoItem[] pedidoItems) throws Exception {
        PedidoItemTO[] result = new PedidoItemTO[pedidoItems.length];
        int i = 0;
        for (PedidoItem c : pedidoItems) {
            result[i++] = c.toTO();
        }

        return result;
    }

    public void excluirPedidoItem(String pk) throws Exception {
        PedidoItemDao funcionarioDao = PostgresqlDaoFactory.getDaoFactory().getPedidoItemDao();
        funcionarioDao.delete(new PedidoItemValidacao().validaLong(pk));
        listener.excluidoSucesso(pk);
    }
}
