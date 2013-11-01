/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.win.tela.pedidos;

import br.unioeste.sisra.controle.PedidoControle;
import br.unioeste.sisra.modelo.execao.DaoException;
import br.unioeste.sisra.modelo.execao.ValidacaoException;
import br.unioeste.sisra.modelo.listener.IPedidoListener;
import br.unioeste.sisra.modelo.to.PedidoTO;
import br.unioeste.sisra.win.componente.Handler;
import br.unioeste.sisra.win.tela.TelaPrincial;
import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author JCLazzarim
 */
public class PedidosActivity implements IPedidoListener{

   
    public static class Campo {

        public final static int ID = 0;
        public final static int ATENDIDO = 1;
        public final static int HR_PEDIDO = 2;
        public final static int CONTA = 3;
        public final static int FUNCIONARIO = 4;
        
    }
    private final TelaPrincial pai;
    private PedidosListagem tela;
    Handler handler;
    PedidoControle controlador;

    public PedidosActivity(TelaPrincial aThis) {
        this.pai = aThis;
        handler = new Handler(aThis);
        tela = new PedidosListagem(this, handler);
        controlador = new PedidoControle(this);
    }

    public Component getTela() {
        return tela;
    }

   
    void finalizarPedido(String pk){
        try {
            controlador.finalizar(pk);
        } catch (ValidacaoException ex) {
            Logger.getLogger(PedidosActivity.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DaoException ex) {
            Logger.getLogger(PedidosActivity.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PedidosActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
   public  void buscarPedido() {
        try {
            controlador.buscarPedidosPorAberto();
        } catch (DaoException ex) {
            Logger.getLogger(PedidosActivity.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PedidosActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void excluirPedido(String pk) throws Exception {
        int showConfirmDialog = JOptionPane.showConfirmDialog(pai, "Tem certeza que deseja excluir ?", "Excluir", JOptionPane.YES_NO_OPTION);
        if (showConfirmDialog == JOptionPane.YES_OPTION) {
            controlador.excluirPedido(pk);
        }
    }

    //--------------------------------------------------------------------------
    // MÉTODOS DO FUNCIONARIO LISTENER (RETORNO DO CONTROLADOR)
    //--------------------------------------------------------------------------
   @Override
    public void exibirBusca(PedidoTO[] pedidos) {
        tela.exibeBusca(pedidos);
    }

    @Override
    public void excluidoSucesso(String pk) {
//        JOptionPane.showMessageDialog(pai, "Excluido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
//        tela.remover(pk);
    }
}
