/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.win.tela.item;

import br.sinforoso.modelo.listener.OnDialogResult;
import br.unioeste.sisra.controle.ItemControle;
import br.unioeste.sisra.modelo.listener.IItemListener;
import br.unioeste.sisra.modelo.to.ItemTO;
import br.unioeste.sisra.win.componente.Handler;
import br.unioeste.sisra.win.listener.OnFormularioResult;
import br.unioeste.sisra.win.tela.TelaPrincial;
import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Mauriverti
 */
public class ItemActivity implements IItemListener {

    public static class Campo {

        public final static int ID = 0;
        public final static int NOME = 1;
        public final static int CODIGO = 2;
        public final static int PRECO = 3;
        public final static int DESCRICAO = 4;
        
    }
    private final TelaPrincial pai;
    private ItemListagem tela;
    Handler handler;
    ItemControle controlador;

    public ItemActivity(TelaPrincial aThis) {
        this.pai = aThis;
        handler = new Handler(aThis);
        tela = new ItemListagem(this, handler);
        controlador = new ItemControle(this);
    }

    public Component getTela() {
        return tela;
    }

    void abrirFormularioItem(Object object) {
        final ItemTO item = (ItemTO) object;
        final ItemDialog fd = new ItemDialog(pai, true, handler, item, new OnFormularioResult() {
            @Override
            public boolean onCloseDialog(int button, Object retorno, boolean novo) {
                if (OnDialogResult.Botao.OK == button) {
                    try {
                        ItemTO f = (ItemTO) retorno;

                        //Gravar
                        controlador.gravar(retorno, novo);

                        tela.atualizaItem(f);
                        return true;
                    } catch (Exception ex) {
                        handler.handle(ex);
                        tela.atualizaItem(item);
                        return false;
                    }
                } else {
                    return true;
                }
            }
        });

        fd.setVisible(true);
    }

    void buscarItem(String text, int campo) {
        switch (campo) {
            case ItemActivity.Campo.ID:
                try {
                    controlador.buscarItemsPorId(text);
                } catch (Exception ex) {
                    handler.handle(ex);
                }
                break;
            case ItemActivity.Campo.NOME:
                controlador.buscarItemsPorNome(text);
                break;
            case ItemActivity.Campo.PRECO:
                controlador.buscarItemsPorPRECO(text);
                break;
        }
    }

    void editarItem(String pk) throws Exception {
        ItemTO f = controlador.bucarItemPorChave(pk);
        abrirFormularioItem(f);
    }

    void excluirItem(String pk, String nome) throws Exception {
        int showConfirmDialog = JOptionPane.showConfirmDialog(pai, "Tem certeza que deseja excluir \"" + nome + "\"?", "Excluir", JOptionPane.YES_NO_OPTION);
        if (showConfirmDialog == JOptionPane.YES_OPTION) {
            controlador.excluirItem(pk);
        }
    }

    //--------------------------------------------------------------------------
    // MÉTODOS DO ITEM LISTENER (RETORNO DO CONTROLADOR)
    //--------------------------------------------------------------------------
    @Override
    public void exibirBusca(ItemTO[] itens) {
        tela.exibeBusca(itens);
    }

    @Override
    public void itemExcluidoSucesso(String pk) {
        JOptionPane.showMessageDialog(pai, "Excluido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        tela.remover(pk);
    }
}
