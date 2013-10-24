package br.unioeste.sisra.and.sync;

import java.io.IOException;

import br.unioeste.sisra.modelo.listener.IRetornoTestarConexao;
import br.unioeste.sisra.persistencia.dao.ConfiguracaoBanco;

public class SyncronizacaoFactory {
	private static Syncronizacao sync;
	private static String servidor;
	private static int porta;
	public static Syncronizacao create() throws Exception{
		if(sync == null || sync.isClosed())
			sync  = new Syncronizacao(servidor, porta);
		return  sync;
	}
	public static void conectar(String servidor, int porta, IRetornoTestarConexao listener) {
		SyncronizacaoFactory.servidor = servidor;
		SyncronizacaoFactory.porta = porta;
		try {
			new Syncronizacao(servidor, porta);
			listener.onRetornoSucesso();
		} catch (Exception e) {
			listener.onRetornoFalha(e.getMessage());
		}
	}
}

