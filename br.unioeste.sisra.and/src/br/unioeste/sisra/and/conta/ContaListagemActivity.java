package br.unioeste.sisra.and.conta;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import br.unioeste.sisra.and.R;
import br.unioeste.sisra.and.pedido.item.PedidoTabLayoutActivity;
import br.unioeste.sisra.and.sync.Syncronizacao;
import br.unioeste.sisra.and.sync.SyncronizacaoFactory;
import br.unioeste.sisra.controle.ContaControle;
import br.unioeste.sisra.modelo.listener.IRetornoSyncQuery;
import br.unioeste.sisra.modelo.to.MesaTO;
import br.unioeste.sisra.utils.Codigo;

public class ContaListagemActivity extends Activity {

	public final static String PARAMETRO_MESA = "PARAMETRO_MESA"; 
	
	private ContaListViewAdapter contaListagemListViewAdapter;
	private MesaTO mesa;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conta_listagem);
		
		Bundle extras = getIntent().getExtras();
		if(extras != null){
			this.mesa = (MesaTO) extras.get(PARAMETRO_MESA);
		}

		configurarAcoes();
	}

	private void configurarAcoes() {
		// Configundo a lista de Mesas
		ListView lista = (ListView) findViewById(R.id.id_list_conta_listagem);
		contaListagemListViewAdapter = new ContaListViewAdapter(this, lista,
				android.R.layout.simple_list_item_1);
		lista.setAdapter(contaListagemListViewAdapter);
		
		lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View list, int position,
					long id) {
				//MesaTO mesa = mesaListagemListViewAdapter.getItem(position);
				abrirPedidoItensListagem();
			}

			
		});

		// configurando botao de consulta
		Button btConsulta = (Button) findViewById(R.id.btn_conta_listagem_pesquisar);
		btConsulta.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				 new SyncContaTask().execute("");
			}
		});
	}
	
	private class SyncContaTask extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			try {
				Syncronizacao sync = SyncronizacaoFactory.create();
				sync.syncQuery(Codigo.Entidade.CONTA, Codigo.TipoAcesso.SEACH,
						ContaControle.Query.POR_MESA_E_EM_ABERTO, mesa, new IRetornoSyncQuery() {

							@Override
							public void onRetornoConsulta(final List result) {
								ContaListagemActivity.this.runOnUiThread(new Runnable() {

									@Override
									public void run() {
										contaListagemListViewAdapter
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, 1, 1, R.string.txt_adicionar_conta).setIcon(
				R.drawable.ic_btn_add);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int opcao = item.getItemId();
		switch (opcao) {
		case 1:
			abrirTelaNovaConta();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void abrirTelaNovaConta() {
		Intent i = new Intent(ContaListagemActivity.this, ContaFormularioActivity.class);
		i.putExtra(ContaFormularioActivity.PARAMETRO_MESA, mesa);
		startActivity(i);
	}
	
	private void abrirPedidoItensListagem() {
		Intent i = new Intent(ContaListagemActivity.this, PedidoTabLayoutActivity.class);
		startActivity(i);
	}
}
