package br.unioeste.sisra.and.sync;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;

import br.unioeste.sisra.modelo.listener.IRetornoSyncQuery;
import br.unioeste.sisra.utils.Codigo;

public class Syncronizacao {

	private int porta;
	private String endereco;
	private Socket syncSocket;

	public Syncronizacao(String endereco, int porta) {
		this.porta = porta;
		this.endereco = endereco;
	}

	public void syncQuery(int entidade, int tipoAcessCodigo,
			IRetornoSyncQuery listener) throws IOException,
			ClassNotFoundException {
		syncQuery(entidade, tipoAcessCodigo, "", listener);
	}

	public void syncQuery(int entidade, int tipoAcessCodigo, String query,
			IRetornoSyncQuery listener) throws IOException,
			ClassNotFoundException {
		syncQuery(entidade, tipoAcessCodigo, query, null, listener);
	}

	public void syncQuery(int entidade, int tipoAcessCodigo, String query, Serializable obj,
			IRetornoSyncQuery listener) throws IOException,
			ClassNotFoundException {
		// Cria um stream de saída
		ObjectOutputStream saidaParaServidor = new ObjectOutputStream(
				syncSocket.getOutputStream());

		// Cria um buffer que armazenará as informações retornadas pelo servidor
		ObjectInputStream entradaDoServidor = new ObjectInputStream(
				syncSocket.getInputStream());

		//Envia uma Hash com os dados
		
		HashMap<String, Serializable> params = new HashMap<String, Serializable>();
		
		// Consulta
		// codigo da entidade
		params.put(Codigo.Parametros.ENTIDADE, entidade);
		
		//saidaParaServidor.write(entidade);

		// codigo do tipo de acesso
		params.put(Codigo.Parametros.TIPO_ACESSO, tipoAcessCodigo);
		params.put(Codigo.Parametros.QUERY, query);
		//saidaParaServidor.write(tipoAcessCodigo);
		//saidaParaServidor.writeObject(query);
		
		//E objeto
		params.put(Codigo.Parametros.OBJETO, obj);
		
		saidaParaServidor.writeObject(params);
		
		// Retornando a consulta
		switch (tipoAcessCodigo) {
		case Codigo.TipoAcesso.SEACH:
			List retorno = (List) entradaDoServidor.readObject();

			listener.onRetornoConsulta(retorno);
			break;
			
		case Codigo.TipoAcesso.INSERT:
			listener.onRetornoInsersao();
			break;

		default:
			break;
		}
		
		saidaParaServidor.close();
		entradaDoServidor.close();
	}

	public void desconectar() throws Exception {
		syncSocket.close();
	}

	// Cria um Socket cliente passando como parâmetro o ip e a porta do
	// servidor
	public void conectar() throws Exception {
		syncSocket = new Socket(endereco, porta);
	}

	public boolean isClosed() {

		return syncSocket.isClosed();
	}

}
