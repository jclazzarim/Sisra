/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.modelo.listener;

import br.unioeste.sisra.modelo.to.ContaTO;

/**
 *
 * @author J.C
 */
public interface IContaListener {

    public void exibirBusca(ContaTO[] funcionarios);

    public void funcionarioExcluidoSucesso(String pk);
    
}
