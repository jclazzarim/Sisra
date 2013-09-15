/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.modelo.listener;

import br.unioeste.sisra.modelo.to.ItemTO;

/**
 *
 * @author Mauriverti
 */
public interface IItemListener {

    public void exibirBusca(ItemTO[] itens);

    public void itemExcluidoSucesso(String pk);
}
