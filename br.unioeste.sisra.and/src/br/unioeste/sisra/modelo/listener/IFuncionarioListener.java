/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.modelo.listener;

import br.unioeste.sisra.modelo.to.FuncionarioTO;

/**
 *
 * @author J.C
 */
public interface IFuncionarioListener {

    public void exibirBusca(FuncionarioTO[] funcionarios);

    public void funcionarioExcluidoSucesso(String pk);
    
}
