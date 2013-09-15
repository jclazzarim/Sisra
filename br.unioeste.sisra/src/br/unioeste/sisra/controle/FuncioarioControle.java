package br.unioeste.sisra.controle;

import br.unioeste.sisra.modelo.listener.IFuncionarioListener;

public class FuncioarioControle {
	private IFuncionarioListener listener;

	public FuncioarioControle(IFuncionarioListener listener) {
		this.listener = listener;
		
	}
}
