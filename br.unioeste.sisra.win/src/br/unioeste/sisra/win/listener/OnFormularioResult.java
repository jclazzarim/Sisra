/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.win.listener;

/**
 *
 * @author Charlinho
 */
public interface OnFormularioResult {
    public interface Botao{
        public static final int NENHUM = 0;
        public static final int OK = 1;
        public static final int CANCELAR = 2;
    }
    
    
    public boolean onCloseDialog(int button, Object retorno, boolean novo);
}
