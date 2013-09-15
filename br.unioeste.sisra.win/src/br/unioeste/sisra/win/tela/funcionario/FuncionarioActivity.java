/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.win.tela.funcionario;

import br.sinforoso.modelo.listener.OnDialogResult;
import br.unioeste.sisra.controle.FuncionarioControle;
import br.unioeste.sisra.modelo.listener.IFuncionarioListener;
import br.unioeste.sisra.modelo.to.FuncionarioTO;
import br.unioeste.sisra.win.componente.Handler;
import br.unioeste.sisra.win.listener.OnFormularioResult;
import br.unioeste.sisra.win.tela.TelaPrincial;
import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author J.C
 */
public class FuncionarioActivity implements IFuncionarioListener {

    public static class Campo {

        public final static int ID = 0;
        public final static int NOME = 1;
        public final static int CPF = 2;
        public final static int RG = 3;
        
    }
    private final TelaPrincial pai;
    private FuncionarioListagem tela;
    Handler handler;
    FuncionarioControle controlador;

    public FuncionarioActivity(TelaPrincial aThis) {
        this.pai = aThis;
        handler = new Handler(aThis);
        tela = new FuncionarioListagem(this, handler);
        controlador = new FuncionarioControle(this);
    }

    public Component getTela() {
        return tela;
    }

    void abrirFormularioFuncionario(Object object) {
        final FuncionarioTO funcionario = (FuncionarioTO) object;
        final FuncionarioDialog fd = new FuncionarioDialog(pai, true, handler, funcionario, new OnFormularioResult() {
            @Override
            public boolean onCloseDialog(int button, Object retorno, boolean novo) {
                if (OnDialogResult.Botao.OK == button) {
                    try {
                        FuncionarioTO f = (FuncionarioTO) retorno;

                        //Gravar
                        controlador.gravar(retorno, novo);

                        tela.atualizaFuncionario(f);
                        return true;
                    } catch (Exception ex) {
                        handler.handle(ex);
                        tela.atualizaFuncionario(funcionario);
                        return false;
                    }
                } else {
                    return true;
                }
            }
        });

        fd.setVisible(true);
    }

    void buscarFuncionario(String text, int campo) {
        switch (campo) {
            case Campo.ID:
                try {
                    controlador.buscarFuncionariosPorId(text);
                } catch (Exception ex) {
                    handler.handle(ex);
                }
                break;
            case Campo.NOME:
                controlador.buscarFuncionariosPorNome(text);
                break;
            case Campo.CPF:
                controlador.buscarFuncionariosPorCPF(text);
                break;
        }
    }

    void editarFuncionario(String pk) throws Exception {
        FuncionarioTO f = controlador.bucarFuncionarioPorChave(pk);
        abrirFormularioFuncionario(f);
    }

    void excluirFuncionario(String pk, String nome) throws Exception {
        int showConfirmDialog = JOptionPane.showConfirmDialog(pai, "Tem certeza que deseja excluir \"" + nome + "\"?", "Excluir", JOptionPane.YES_NO_OPTION);
        if (showConfirmDialog == JOptionPane.YES_OPTION) {
            controlador.excluirFuncionario(pk);
        }
    }

    //--------------------------------------------------------------------------
    // MÉTODOS DO FUNCIONARIO LISTENER (RETORNO DO CONTROLADOR)
    //--------------------------------------------------------------------------
    @Override
    public void exibirBusca(FuncionarioTO[] funcionarios) {
        tela.exibeBusca(funcionarios);
    }

    @Override
    public void funcionarioExcluidoSucesso(String pk) {
        JOptionPane.showMessageDialog(pai, "Excluido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        tela.remover(pk);
    }
}
