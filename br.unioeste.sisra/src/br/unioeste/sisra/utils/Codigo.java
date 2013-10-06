/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.utils;

/**
 *
 * @author Charlinho
 */
public class Codigo {

    public class Entidade {

        public final static int FUNCIONARIO = 0;
        public final static int ITEM = 1;
        public final static int MESA = 2;
        public final static int PEDIDO = 3;
        public final static int PEDIDO_ITEM = 4;
        public final static int CONTA = 5;
        public final static int GERENCIA = 6;
    }
    
    public class TipoAcesso{
        public final static int INSERT = 0;
        public final static int UPDATE = 1;
        public final static int SEACH = 2;
        public final static int DELETE = 3;
    }
}
