package br.unioeste.sisra.and.mesa;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import br.unioeste.sisra.and.MainActivity;
import br.unioeste.sisra.and.R;
import br.unioeste.sisra.and.conta.ContaListagemActivity;
import br.unioeste.sisra.and.sync.Syncronizacao;
import br.unioeste.sisra.and.sync.SyncronizacaoFactory;
import br.unioeste.sisra.controle.MesaControle;
import br.unioeste.sisra.modelo.listener.IMesaListener;
import br.unioeste.sisra.modelo.listener.IRetornoSyncQuery;
import br.unioeste.sisra.modelo.to.ContaTO;
import br.unioeste.sisra.modelo.to.MesaTO;
import br.unioeste.sisra.utils.Codigo;

public class MesaActivity extends Activity {
	
	
	
	private MesaListViewAdapter mesaListagemListViewAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mesa);

		configurarAcoes();
	}

	private void configurarAcoes() {
		// Configundo a lista de Mesas
		ListView lista = (ListView) findViewById(R.id.id_list_mesa_listagem);
		mesaListagemListViewAdapter = new MesaListViewAdapter(this, lista,
				android.R.layout.simple_list_item_1);
		lista.setAdapter(mesaListagemListViewAdapter);
		
		lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View list, int position,
					long id) {
				MesaTO mesa = mesaListagemListViewAdapter.getItem(position);
				abrirContaListagem(mesa);
			}
		});

		// configurando botao de consulta
		Button btConsulta = (Button) findViewById(R.id.bt_mesa_listagem_pesquisar);
		btConsulta.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new SyncMesaTask().execute("");
			}
		});
	}

	private class SyncMesaTask extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			try {
				Syncronizacao sync = SyncronizacaoFactory.create();
				sync.syncQuery(Codigo.Entidade.MESA, Codigo.TipoAcesso.SEACH,
						"", new IRetornoSyncQuery() {

							@Override
							public void onRetornoConsulta(final List result) {
								MesaActivity.this.runOnUiThread(new Runnable() {

									@Override
									public void run() {
										mesaListagemListViewAdapter
												.atualizarLista(result);
									}
								});

							}
						});
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

	}
	

	//-----------------------------------------------------------
	// METODOS DE ABRIR AS TELAS
	//-----------------------------------------------------------
	public void abrirContaListagem(MesaTO mesa) {
		Intent i = new Intent(MesaActivity.this, ContaListagemActivity.class);
		i.putExtra(ContaListagemActivity.PARAMETRO_MESA, mesa);
		startActivity(i);
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	//
	// return true;
	// }
}
