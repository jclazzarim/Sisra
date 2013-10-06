package br.unioeste.sisra.and.sync;

import java.io.IOException;

public class SyncronizacaoFactory {
	private static Syncronizacao sync;
	public static Syncronizacao create() throws Exception{
		if(sync == null || sync.isClosed())
			sync  = new Syncronizacao("192.168.25.6", 6666);
		return  sync;
	}
}
