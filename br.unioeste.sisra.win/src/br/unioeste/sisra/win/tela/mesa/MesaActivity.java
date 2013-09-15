/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.win.tela.mesa;

import br.sinforoso.modelo.listener.OnDialogResult;
import br.unioeste.sisra.controle.MesaControle;
import br.unioeste.sisra.modelo.listener.IMesaListener;
import br.unioeste.sisra.modelo.to.MesaTO;
import br.unioeste.sisra.win.componente.Handler;
import br.unioeste.sisra.win.listener.OnFormularioResult;
import br.unioeste.sisra.win.tela.TelaPrincial;
import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author JCLazzarim
 */
public class MesaActivity implements IMesaListener {

    public static class Campo {

        public final static int ID = 0;
        public final static int DESCRICAO = 1;
        public final static int OBS = 2;
        public final static int STATUS = 3;
       
    }
    private final TelaPrincial pai;
    private MesaListagem tela;
    Handler handler;
    MesaControle controlador;

    public MesaActivity(TelaPrincial aThis) {
        this.pai = aThis;
        handler = new Handler(aThis);
        tela = new MesaListagem(this, handler);
        controlador = new MesaControle(this);
    }

    public Component getTela() {
        return tela;
    }

    void abrirFormularioMesa(Object object) {
        final MesaTO mesa = (MesaTO) object;
        final MesaDialog fd = new MesaDialog(pai, true, handler, mesa, new OnFormularioResult() {
            @Override
            public boolean onCloseDialog(int button, Object retorno, boolean novo) {
                if (OnDialogResult.Botao.OK == button) {
                    try {
                        MesaTO f = (MesaTO) retorno;

                        //Gravar
                        controlador.gravar(retorno, novo);

                        tela.atualizaMesa(f);
                        return true;
                    } catch (Exception ex) {
                        handler.handle(ex);
                        tela.atualizaMesa(mesa);
                        return false;
                    }
                } else {
                    return true;
                }
            }
        });

        fd.setVisible(true);
    }

    void buscarMesa(String text, int campo) {
        switch (campo) {
            case Campo.ID:
                try {
                    controlador.buscarMesasPorId(text);
                } catch (Exception ex) {
                    handler.handle(ex);
                }
                break;
            case Campo.OBS:
                controlador.buscarMesasPorNome(text);
                break;
        }
    }

    void editarMesa(String pk) throws Exception {
        MesaTO f = controlador.bucarMesaPorChave(pk);
        abrirFormularioMesa(f);
    }

    void excluirMesa(String pk, String nome) throws Exception {
        int showConfirmDialog = JOptionPane.showConfirmDialog(pai, "Tem certeza que deseja excluir \"" + nome + "\"?", "Excluir", JOptionPane.YES_NO_OPTION);
        if (showConfirmDialog == JOptionPane.YES_OPTION) {
            controlador.excluirMesa(pk);
        }
    }

    //--------------------------------------------------------------------------
    // MÉTODOS DO FUNCIONARIO LISTENER (RETORNO DO CONTROLADOR)
    //--------------------------------------------------------------------------
    @Override
    public void exibirBusca(MesaTO[] mesa) {
        tela.exibeBusca(mesa);
    }

    @Override
    public void mesaExcluidaSucesso(String pk) {
        JOptionPane.showMessageDialog(pai, "Excluido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        tela.remover(pk);
    }
    
    
}
