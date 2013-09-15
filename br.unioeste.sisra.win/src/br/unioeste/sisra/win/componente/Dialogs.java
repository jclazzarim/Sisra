/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.win.componente;


import br.unioeste.sisra.modelo.listener.OnDialogResult;
import java.awt.Frame;

/**
 *
 * @author Charlinho
 */
public class Dialogs {

    public static void showErroDialog(Frame pai, String mensagem){
        showErroDialog(pai, mensagem, null);
    }
    
    public static void showErroDialog(Frame pai, String mensagem, OnDialogResult onDialogResult) {

        ErroDialog dialog = new ErroDialog(new javax.swing.JFrame(), true, mensagem, onDialogResult);
        dialog.setVisible(true);
    }
    
    
    public static void showAlertaDialog(Frame pai, String mensagem){
        showAlertaDialog(pai, mensagem, null);
    }
    
    public static void showAlertaDialog(Frame pai, String mensagem, OnDialogResult onDialogResult) {

        AlertaDialog dialog = new AlertaDialog(new javax.swing.JFrame(), true, mensagem, onDialogResult);
        dialog.setVisible(true);
    }
}
