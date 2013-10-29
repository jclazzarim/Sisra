package br.unioeste.sisra.and.pedido.item;

import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import br.unioeste.sisra.and.R;
import br.unioeste.sisra.and.sync.Syncronizacao;
import br.unioeste.sisra.and.sync.SyncronizacaoFactory;
import br.unioeste.sisra.modelo.listener.IRetornoSyncQuery;
import br.unioeste.sisra.modelo.to.ItemTO;
import br.unioeste.sisra.utils.Codigo;

public class ItensActivity extends Activity {
	private ItensListViewAdapter itensAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_itens);

		configurarAcoes();
	}

	private void configurarAcoes() {
		// Configundo a lista de Mesas
		ListView lista = (ListView) findViewById(R.id.list_tab_itens);
		itensAdapter = new ItensListViewAdapter(this, lista,
				android.R.layout.simple_list_item_1);
		lista.setAdapter(itensAdapter);

		lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View list,
					int position, long id) {
				ItemTO item = itensAdapter.getItem(position);
				abrirPedidoItemFormulario(item);
			}
		});

		// configurando botao de consulta
		Button btConsulta = (Button) findViewById(R.id.bt_tab_itens_pesquisar);
		btConsulta.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new SyncItemTask().execute("");
			}
		});

	}

	private class SyncItemTask extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			try {
				Syncronizacao sync = SyncronizacaoFactory.create();
				sync.syncQuery(Codigo.Entidade.ITEM, Codigo.TipoAcesso.SEACH,
						"", new IRetornoSyncQuery() {

							@Override
							public void onRetornoConsulta(final List result) {
								ItensActivity.this.runOnUiThread(new Runnable() {

									@Override
									public void run() {
										itensAdapter
												.atualizarLista(result);
									}
								});

							}

							@Override
							public void onRetornoInsersao() {
								// TODO Auto-generated method stub

							}
						});
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

	}

	// -----------------------------------------------------------
	// METODOS DE ABRIR AS TELAS
	// -----------------------------------------------------------
	public void abrirPedidoItemFormulario(ItemTO item) {
		// Intent i = new Intent(ItensActivity.this,
		// ContaListagemActivity.class);
		// i.putExtra(ContaListagemActivity.PARAMETRO_MESA, mesa);
		// startActivity(i);
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	//
	// return true;
	// }
}
