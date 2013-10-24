package br.unioeste.sisra.and.sync;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class Syncronizacao {

	private int porta;
	private String endereco;
	private Socket syncSocket;
	ObjectOutputStream saidaParaServidor;
	ObjectInputStream entradaDoServidor;

	public Syncronizacao(String endereco, int porta) throws IOException {
		this.porta = porta;
		this.endereco = endereco;

		// Cria um Socket cliente passando como parâmetro o ip e a porta do
		// servidor
		syncSocket = new Socket(endereco, porta);

	}

	public void syncQuery(int entidade, int tipoAcessCodigo, String query, IRetornoSyncQuery listener)
			throws IOException, ClassNotFoundException {
		// Cria um stream de saída
		saidaParaServidor = new ObjectOutputStream(syncSocket.getOutputStream());
		
		// Cria um buffer que armazenará as informações retornadas pelo servidor
		entradaDoServidor = new ObjectInputStream(syncSocket.getInputStream());
		
		// Consulta
		// codigo da entidade
		saidaParaServidor.write(entidade);

		// codigo do tipo de acesso
		saidaParaServidor.write(tipoAcessCodigo);
		saidaParaServidor.writeObject(query);
		
		//Retornando a consulta
		List retorno = (List) entradaDoServidor.readObject();

		listener.onRetornoConsulta(retorno);
		saidaParaServidor.close();
		entradaDoServidor.close();
	}

	public boolean isClosed() {

		return false;
	}

}
