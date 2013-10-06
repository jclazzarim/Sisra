/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.sync;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Charlinho
 */
public class SyncMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Servidor serv = null;
        try {
            serv = new Servidor(6666);
            serv.iniciarServidor();
        } catch (Exception ex) {
            Logger.getLogger(SyncMain.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (serv != null) {
                try {
                    serv.fecharConexao();
                } catch (IOException ex) {
                    Logger.getLogger(SyncMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
