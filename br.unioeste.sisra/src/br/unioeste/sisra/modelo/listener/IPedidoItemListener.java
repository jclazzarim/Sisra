/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.modelo.listener;

import br.unioeste.sisra.modelo.to.PedidoItemTO;

/**
 *
 * @author J.C
 */
public interface IPedidoItemListener {

    public void exibirBusca(PedidoItemTO[] pedidoItems);

    public void excluidoSucesso(String pk);

}
