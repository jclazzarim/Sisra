/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.controle;

import br.unioeste.sisra.controle.validacao.PedidoValidacao;
import br.unioeste.sisra.modelo.entidade.Pedido;
import br.unioeste.sisra.modelo.execao.DaoException;
import br.unioeste.sisra.modelo.execao.ValidacaoException;
import br.unioeste.sisra.modelo.listener.IPedidoListener;
import br.unioeste.sisra.modelo.to.ContaTO;
import br.unioeste.sisra.modelo.to.PedidoTO;
import br.unioeste.sisra.persistencia.dao.PedidoDao;
import br.unioeste.sisra.persistencia.factory.PostgresqlDaoFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Charlinho
 */
public class PedidoControle {

    public class Query {

     //   public final static String POR_MESA_E_EM_ABERTO = "POR_MESA_E_EM_ABERTO";
    }
    private IPedidoListener listener;

    public PedidoControle(IPedidoListener listener) {
        this.listener = listener;

    }

    public static Pedido pedidoTOAdapter(PedidoTO to) throws Exception {
        
        Pedido pedido = new Pedido();
        pedido.setIdPedido(to.getIdPedido());
        pedido.setAtendido(to.isAtendido());
        pedido.setHoraPedido(to.getHoraPedido());
        pedido.setConta(ContaControle.contaTOAdapter(to.getConta()));

        return pedido;
    }

    public void gravar(Object obj, boolean novo) throws Exception {
        PedidoTO to = (PedidoTO) obj;
        //TODO precisamos criar uma validação de tadas dentro do validate
        //  new PedidoValidacao().validar(retorno);

        Pedido pedido = pedidoTOAdapter(to);

        PedidoDao pedidoDao = PostgresqlDaoFactory.getDaoFactory().getPedidoDao();
        try {
            if (novo) {
                pedidoDao.insert(pedido, pedido.getIdPedido());
            } else {
                pedidoDao.update(pedido.getIdPedido(), pedido);
            }            
        } catch (DaoException ex) {
            Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Atualiza se a mesa está ou não ocupada
//        MesaControle mesaControle = new MesaControle(null);
//        mesaControle.atualizarMesaOcupada(to.getMesaTO());
    }

    // ------------------------------------------------------------------------
    // BUSCAS
    // ------------------------------------------------------------------------
    public void buscarPedidosPorId(String text) throws ValidacaoException, Exception {

        PedidoDao dao = PostgresqlDaoFactory.getDaoFactory().getPedidoDao();
        try {
            Pedido[] pedidos;

            if (text.trim().length() == 0) {
                pedidos = dao.findAll();
            } else {
                Long idPedido = new PedidoValidacao().validaLong(text);
                pedidos = dao.findWhereCodigoEquals(idPedido);
            }

            listener.exibirBusca(converterEntidadesEmTO(pedidos));
        } catch (DaoException ex) {
            throw new Exception(ex);
        }
    }

    public void buscarPedidosPorDescricao(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void buscarPedidosPorConta(ContaTO conta) throws ValidacaoException, Exception {
        Long idConta = conta.getId();

        PedidoDao dao = PostgresqlDaoFactory.getDaoFactory().getPedidoDao();

        Pedido[] pedidos = dao.findWhereIdConta(idConta);

        listener.exibirBusca(converterEntidadesEmTO(pedidos));

    }

    public PedidoTO bucarPedidoPorChave(String pk) throws Exception {
        PedidoDao pedidoDao = PostgresqlDaoFactory.getDaoFactory().getPedidoDao();

        Pedido[] pedidos = pedidoDao.findWhereCodigoEquals(new PedidoValidacao().validaLong(pk));
        if (pedidos.length > 0) {
            return pedidos[0].toTo();
        } else {
            return new PedidoTO();
        }
    }
    // ------------------------------------------------------------------------
    // OUTROS MÉTODOS
    // ------------------------------------------------------------------------

    private PedidoTO[] converterEntidadesEmTO(Pedido[] pedidos) throws Exception {
        PedidoTO[] result = new PedidoTO[pedidos.length];
        int i = 0;
        for (Pedido c : pedidos) {
            result[i++] = c.toTo();
        }

        return result;
    }

    public void excluirPedido(String pk) throws Exception {
        PedidoDao funcionarioDao = PostgresqlDaoFactory.getDaoFactory().getPedidoDao();
        funcionarioDao.delete(new PedidoValidacao().validaLong(pk));
        listener.excluidoSucesso(pk);
    }
}
