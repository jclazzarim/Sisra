package br.unioeste.sisra.and.conta;

import java.util.ArrayList;

import br.unioeste.sisra.and.R;
import br.unioeste.sisra.and.mesa.MesaListViewAdapter;
import br.unioeste.sisra.controle.MesaControle;
import br.unioeste.sisra.modelo.listener.IMesaListener;
import br.unioeste.sisra.modelo.to.ContaTO;
import br.unioeste.sisra.modelo.to.MesaTO;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class ContaListagemActivity extends Activity {
	
	private ContaListViewAdapter contaListagemListViewAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
				super.onCreate(savedInstanceState);
				setContentView(R.layout.activity_conta_listagem);

				configurarAcoes();
	}

	private void configurarAcoes() {
		// Configundo a lista de Mesas
				ListView lista = (ListView) findViewById(R.id.id_list_conta_listagem);
				contaListagemListViewAdapter = new ContaListViewAdapter(this, lista,
						android.R.layout.simple_list_item_1);
				lista.setAdapter(contaListagemListViewAdapter);
				

				// configurando botao de consulta
				Button btConsulta = (Button) findViewById(R.id.btn_conta_listagem_pesquisar);
				btConsulta.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						ArrayList<ContaTO> contas = new ArrayList<ContaTO>();
						contas.add(new ContaTO(1l,"Maria Joaquina", "", "", new MesaTO(), "10,00"));
						contas.add(new ContaTO(2l,"Jão", "", "", new MesaTO(), "12,00"));
						contas.add(new ContaTO(3l,"Mioslk", "", "", new MesaTO(), "120,00"));
						contas.add(new ContaTO(4l,"Tharle", "", "", new MesaTO(), "90,30"));
						contaListagemListViewAdapter.atualizarLista(contas);
//						// new SyncMesaTask().execute("");
//						MesaControle mc = new MesaControle(new IMesaListener() {
//
//							@Override
//							public void mesaExcluidaSucesso(String pk) {
//								// TODO Auto-generated method stub
//
//							}
//
//							@Override
//							public void exibirBusca(MesaTO[] itens) {
//								ArrayList<MesaTO> result = new ArrayList<MesaTO>();
//								for (MesaTO to : itens) {
//									result.add(to);
//									System.out.println(to.toString());
//								}
//							//	mesaListagemListViewAdapter.atualizarLista(result);
//
//							}
//						});
//						try {
//							mc.buscarMesasPorId("");
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
					}
				});
	}
	
	// @Override
		// public boolean onCreateOptionsMenu(Menu menu) {
		//
		// return true;
		// }
}
