/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.modelo.listener;

import br.unioeste.sisra.modelo.to.PedidoTO;

/**
 *
 * @author J.C
 */
public interface IPedidoListener {

    public void exibirBusca(PedidoTO[] pedidos);

    public void excluidoSucesso(String pk);

}
