package br.unioeste.sisra.and;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import br.unioeste.sisra.and.list.MenuPrincipalLisView;
import br.unioeste.sisra.and.sync.Syncronizacao;
import br.unioeste.sisra.utils.Codigo;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MenuPrincipalLisView menuLst = new MenuPrincipalLisView(this);
		setContentView(menuLst);

		configurarAcoes();
	}

	private void configurarAcoes() {
//		// Teste de syncronização
//		Button btConta = (Button) findViewById(R.id.btConta);
//		btConta.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				new SyncFuncionarioTask().execute("");
//			}
//		});
//		
//		Button btPedido = (Button) findViewById(R.id.btPedido);
//		btPedido.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
////				FuncionarioTO to = new FuncionarioTO();]
//				FuncionarioControle controle = new FuncionarioControle(new IFuncionarioListener() {
//					@Override
//					public void funcionarioExcluidoSucesso(String pk) {
//					}
//					@Override
//					public void exibirBusca(FuncionarioTO[] funcionarios) {
//						for (FuncionarioTO to : funcionarios) {
//							Log.i("Funcioanrio", to.toString());
//						}
//					
//					}
//				});
//				try {
//					controle.buscarFuncionariosPorId("");
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}

	private class SyncFuncionarioTask extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			try {
				Syncronizacao sync = new Syncronizacao("192.168.25.6", 6666);
				sync.syncFuncionario(Codigo.TipoAcesso.SEACH, "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
