/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.modelo.listener;

import br.unioeste.sisra.modelo.to.MesaTO;

/**
 *
 * @author Charlinho
 */
public interface IMesaListener {
    public void exibirBusca(MesaTO[] itens);

    public void mesaExcluidaSucesso(String pk);

}
