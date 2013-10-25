package br.unioeste.sisra.and.sync;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import br.unioeste.sisra.modelo.listener.IRetornoSyncQuery;

public class Syncronizacao {

	private int porta;
	private String endereco;
	private Socket syncSocket;

	public Syncronizacao(String endereco, int porta) {
		this.porta = porta;
		this.endereco = endereco;
	}

	public void syncQuery(int entidade, int tipoAcessCodigo, String query, IRetornoSyncQuery listener)
			throws IOException, ClassNotFoundException {
		// Cria um stream de saída
		ObjectOutputStream saidaParaServidor = new ObjectOutputStream(syncSocket.getOutputStream());
		
		// Cria um buffer que armazenará as informações retornadas pelo servidor
		ObjectInputStream entradaDoServidor = new ObjectInputStream(syncSocket.getInputStream());
		
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
	
	public void desconectar() throws Exception{
		syncSocket.close();
	}
	
	// Cria um Socket cliente passando como parâmetro o ip e a porta do
	// servidor
	public void conectar() throws Exception{
		syncSocket = new Socket(endereco, porta);
	}

	public boolean isClosed() {

		return syncSocket.isClosed();
	}

}
