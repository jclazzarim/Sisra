/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.win.tela.fecharConta;

import br.sinforoso.modelo.listener.OnDialogResult;
import br.unioeste.sisra.controle.ContaControle;
import br.unioeste.sisra.modelo.listener.IContaListener;
import br.unioeste.sisra.modelo.to.ContaTO;
import br.unioeste.sisra.win.componente.Handler;
import br.unioeste.sisra.win.listener.OnFormularioResult;
import br.unioeste.sisra.win.tela.TelaPrincial;
import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author JCLazzarim
 */
public class FecharContaActivity implements IContaListener{

    
    public static class Campo {

        public final static int ID = 0;
        public final static int DESCRICAO = 1;
        public final static int HR_ABERTURA = 2;
        public final static int TOTAL = 3;
        public final static int MESA = 4;
        
    }
    private final TelaPrincial pai;
    private ContaListagem tela;
    Handler handler;
    ContaControle controlador;

    public FecharContaActivity(TelaPrincial aThis) {
        this.pai = aThis;
        handler = new Handler(aThis);
        tela = new ContaListagem(this, handler);
        controlador = new ContaControle(this);
    }

    public Component getTela() {
        return tela;
    }

    void abrirFormularioConta(Object object) {
        final ContaTO conta = (ContaTO) object;
        final FecharContaDialog fd = new FecharContaDialog(pai, true, handler, conta, new OnFormularioResult() {
            @Override
            public boolean onCloseDialog(int button, Object retorno, boolean novo) {
                if (OnDialogResult.Botao.OK == button) {
                    try {
                        ContaTO c = (ContaTO) retorno;

                        //Gravar
                        controlador.gravar(retorno, novo);

                        tela.atualizaConta(c);
                        return true;
                    } catch (Exception ex) {
                        handler.handle(ex);
                        tela.atualizaConta(conta);
                        return false;
                    }
                } else {
                    return true;
                }
            }
        });

        fd.setVisible(true);
    }

    void buscarConta(String text, int campo) {
        switch (campo) {
            case FecharContaActivity.Campo.ID:
                try {
                    controlador.bucarContaPorChave(text);
                } catch (Exception ex) {
                    handler.handle(ex);
                }
                break;
            case FecharContaActivity.Campo.DESCRICAO:
                controlador.buscarContasPorDescricao(text);
                break;
        }
    }

    void editarConta(String pk) throws Exception {
        ContaTO f = controlador.bucarContaPorChave(pk);
        abrirFormularioConta(f);
    }

    void excluirFuncionario(String pk, String nome) throws Exception {
        int showConfirmDialog = JOptionPane.showConfirmDialog(pai, "Tem certeza que deseja excluir \"" + nome + "\"?", "Excluir", JOptionPane.YES_NO_OPTION);
        if (showConfirmDialog == JOptionPane.YES_OPTION) {
            controlador.excluirConta(pk);
        }
    }

    //--------------------------------------------------------------------------
    // MÉTODOS DO FUNCIONARIO LISTENER (RETORNO DO CONTROLADOR)
    //--------------------------------------------------------------------------
   @Override
    public void exibirBusca(ContaTO[] conta) {
       tela.exibeBusca(conta);
   }

    @Override
    public void excluidoSucesso(String pk) {
        JOptionPane.showMessageDialog(pai, "Excluido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        tela.remover(pk);
    }
}
