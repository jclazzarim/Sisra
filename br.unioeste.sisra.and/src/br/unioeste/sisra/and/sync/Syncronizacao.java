package br.unioeste.sisra.and.sync;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import android.util.Log;
import br.unioeste.sisra.modelo.to.FuncionarioTO;

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

		// Cria um stream de saída
		saidaParaServidor = new ObjectOutputStream(syncSocket.getOutputStream());

		// Cria um buffer que armazenará as informações retornadas pelo servidor
		entradaDoServidor = new ObjectInputStream(syncSocket.getInputStream());
	}

	public void syncFuncionario(int tipoAcessCodigo, String query) throws IOException, ClassNotFoundException {
		// Consulta
		// codigo da entidade funcionario
		saidaParaServidor.write(0);

		// codigo do tipo de acesso
		saidaParaServidor.write(tipoAcessCodigo);
		saidaParaServidor.writeObject(query);
		List<FuncionarioTO> retorno =  (List<FuncionarioTO>) entradaDoServidor.readObject();
		for (FuncionarioTO funcionarioTO : retorno) {
			Log.i("FUNCIONARIO", funcionarioTO.getNome());
		}
	}

}
