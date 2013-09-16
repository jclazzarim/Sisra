package br.unioeste.sisra.android.sync;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Syncronizacao {
	
	
	private int porta;
	private String endereco;
	private Socket syncSocket;
	DataOutputStream saidaParaServidor;
	BufferedReader entradaParaServidor;

	public Syncronizacao(String endereco, int porta) throws IOException {
		this.porta = porta;
		this.endereco = endereco;

		// Cria um Socket cliente passando como par�metro o ip e a porta do
		// servidor
		syncSocket = new Socket(endereco, porta);

		// Cria um stream de sa�da
		saidaParaServidor = new DataOutputStream(syncSocket.getOutputStream());

		// Cria um buffer que armazenar� as informa��es retornadas pelo servidor
		entradaParaServidor = new BufferedReader(new InputStreamReader(
				syncSocket.getInputStream()));
	}

	public void syncFuncionario(int tipoAcessCodigo, String query) throws IOException {
		// Consulta
		// codigo da entidade funcionario
		saidaParaServidor.write(0);

		// codigo do tipo de acesso
		saidaParaServidor.write(tipoAcessCodigo);
		saidaParaServidor.writeChars(query);
		//entradaParaServidor.read();
	}

}
