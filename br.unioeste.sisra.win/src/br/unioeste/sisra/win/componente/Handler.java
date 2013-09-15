/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.win.componente;


import br.unioeste.sisra.modelo.execao.ValidacaoException;
import br.unioeste.sisra.utils.CorUtils;
import java.awt.Color;
import java.awt.Frame;
import java.util.HashMap;
import javax.swing.JComponent;

/**
 *
 * @author Charlinho
 */
public class Handler {
    private HashMap<String, JComponent> componentes;
    private final Frame pai;

    public Handler(Frame pai) {
        this.pai = pai;
        componentes = new HashMap<>();
    }
    
        /**
     * Adiciona um componente no mapa de componentes do sistema.
     * @param chaveComponente chave correspondente ao componente
     * @param componente componente em questão, que vai ser adicionado no mapa
     */
    public void add(String chaveComponente, JComponent componente){
        componentes.put(chaveComponente, componente);
    }
    
    /**
     * Método criado para sinalizar um componente com erros, atráves da sua chave
     * @param chaveComponente chave que indexa o componente no mapa.
     */
    private void erro(String chaveComponente){
        JComponent componente = componentes.get(chaveComponente);
        if (componente != null) {
            componente.setBackground(CorUtils.ERRO);
        }
    }
    
    /**
     * Método que captura uma exceção, acha qual foi o componemte q deu erro
     * sinalizando ele com fundo contida em {@link CorUtils} e exibe a mensagem
     * enviada
     * @param e exeção tratada
     */
    public void handle(Exception e){
        String mensagem = e.getMessage();
        //aqui verifica se veiod e uma validacao
        if(e instanceof ValidacaoException){
            //pois, a validacao traz a chave do campo que foi validado
            ValidacaoException ve = (ValidacaoException) e;
            JComponent c = componentes.get(ve.getChaveCampo());
            //Se existir um componente registrado com essa chave, vai retornar
            //um valor diferente de null
            if(c!=null){
                c.setBackground(CorUtils.ERRO);
            }
            Dialogs.showErroDialog(pai, mensagem);
        }else{
            Dialogs.showAlertaDialog(pai, "Ocorreu um erro! Informe o Suporte técnico.");
        }
        e.printStackTrace();
    }
    
    /**
     * Método criado que limpa as notificações com erro dos componentes em tela.
     */
    public void limparNotificacoes(){
        for (JComponent comp : componentes.values()) {
            comp.setBackground(Color.white);
        }
    }
}
