package br.unioeste.sisra.and.sync;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import br.unioeste.sisra.modelo.listener.IRetornoSyncQuery;
import br.unioeste.sisra.modelo.to.FuncionarioTO;
import br.unioeste.sisra.utils.Codigo;

public class Syncronizacao {

	private int porta;
	private String endereco;
	private Socket syncSocket;
	ObjectOutputStream saidaParaServidor;
	ObjectInputStream entradaDoServidor;

	public Syncronizacao(String endereco, int porta) throws IOException {
		this.porta = porta;
		this.endereco = endereco;

		// Cria um Socket cliente passando como par�metro o ip e a porta do
		// servidor
		syncSocket = new Socket(endereco, porta);

		// Cria um stream de sa�da
		saidaParaServidor = new ObjectOutputStream(syncSocket.getOutputStream());

		// Cria um buffer que armazenar� as informa��es retornadas pelo servidor
		entradaDoServidor = new ObjectInputStream(syncSocket.getInputStream());
	}

	public void syncQuery(int entidade, int tipoAcessCodigo, String query, IRetornoSyncQuery listener)
			throws IOException, ClassNotFoundException {
		// Consulta
		// codigo da entidade
		saidaParaServidor.write(entidade);

		// codigo do tipo de acesso
		saidaParaServidor.write(tipoAcessCodigo);
		saidaParaServidor.writeObject(query);
		
		//Retornando a consulta
		List retorno = (List) entradaDoServidor.readObject();

		listener.onRetornoConsulta(retorno);
	}

	public boolean isClosed() {

		return false;
	}

}
