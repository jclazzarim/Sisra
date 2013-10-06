package br.unioeste.sisra.and;

import br.unioeste.sisra.and.mesa.MesaActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

public class MainActivity extends Activity {

	private MenuPrincipalListViewAdapter menuPrincipalListViewAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		configurarAcoes();
	}

	private void configurarAcoes() {
		//Configundo a lista de menu principal
		ListView lstMenu = (ListView) findViewById(R.id.id_list_menu_principal);
		menuPrincipalListViewAdapter = new MenuPrincipalListViewAdapter(this, lstMenu, android.R.layout.simple_list_item_1);
		lstMenu.setAdapter(menuPrincipalListViewAdapter);
		
		
		
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	//-----------------------------------------------------------
	// METODOS DE ABRIR AS TELAS
	//-----------------------------------------------------------
	public void abrirMesaListagem() {
		Intent i = new Intent(MainActivity.this, MesaActivity.class);
		startActivity(i);
	}

}
