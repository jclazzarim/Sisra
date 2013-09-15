/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.modelo.listener;

/**
 *
 * @author Charlinho
 */
    public interface OnDialogResult {
        public interface Botao{
        public static final int NENHUM = 0;
        public static final int OK = 1;
        public static final int CANCELAR = 2;
    }
    
    
    public void onCloseDialog(int button, Object retorno);
}
