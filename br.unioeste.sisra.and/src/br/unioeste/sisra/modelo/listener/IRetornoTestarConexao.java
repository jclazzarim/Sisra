package br.unioeste.sisra.modelo.listener;


public interface IRetornoTestarConexao {
	public void onRetornoSucesso();
	public void onRetornoFalha(String msg);
}
