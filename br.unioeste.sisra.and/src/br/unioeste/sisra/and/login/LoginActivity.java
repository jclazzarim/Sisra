package br.unioeste.sisra.and.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import br.unioeste.sisra.and.MainActivity;
import br.unioeste.sisra.and.R;
import br.unioeste.sisra.and.mesa.MesaActivity;
import br.unioeste.sisra.and.sync.SyncronizacaoFactory;
import br.unioeste.sisra.modelo.listener.IRetornoTestarConexao;
import br.unioeste.sisra.persistencia.dao.ConfiguracaoBanco;

public class LoginActivity extends Activity {
	
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.actity_login);

		initComponents();
		configureActions();
	}

	private void initComponents() {
		// Servidor
		EditText edtServidor = (EditText) findViewById(R.id.edt_Servidor);
		edtServidor.setText("192.168.25.6");
		// Porta
		EditText edtPorta = (EditText) findViewById(R.id.edt_Porta);
		edtPorta.setText("6666");

	}

	private void configureActions() {
		

		Button btnConectar = (Button) findViewById(R.id.btn_Entrar);
		btnConectar.setOnClickListener(new OnClickListener() {

			

			@Override
			public void onClick(View v) {
				// Servidor
				EditText edtServidor = (EditText) findViewById(R.id.edt_Servidor);
				String servidor = edtServidor.getText().toString();
				// Porta
				EditText edtPorta = (EditText) findViewById(R.id.edt_Porta);
				String porta= edtPorta.getText().toString();
				
				progressDialog = ProgressDialog.show(LoginActivity.this, "", "Conectando...");
				
				
				new SyncTask().execute(servidor, porta);
			}
		});

	}

	private class SyncTask extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			final String servidor = params[0];
			final int porta = Integer.parseInt(params[1]);
			SyncronizacaoFactory.conectar(servidor, porta, new IRetornoTestarConexao() {

				@Override
				public void onRetornoSucesso() {
					LoginActivity.this.runOnUiThread(new Runnable() {

						@Override
						public void run() {
							LoginActivity.this.progressDialog.dismiss();
							Builder builder = new AlertDialog.Builder(
									LoginActivity.this);
							
							builder.setTitle(R.string.dialog_conectar_titulo)
									.setIcon(android.R.drawable.ic_dialog_info)
									.setMessage(
											R.string.dialog_conectar_mensagem_sucesso);
							builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
						           public void onClick(DialogInterface dialog, int id) {
						               LoginActivity.this.abrirTelaMenuPrincipal();
						           }
						       });
							final AlertDialog dialog = builder.create();
							dialog.show();

						}
					});
				}

				@Override
				public void onRetornoFalha(final String msg) {
					
					LoginActivity.this.runOnUiThread(new Runnable() {

						@Override
						public void run() {
							LoginActivity.this.progressDialog.dismiss();
							Builder builder = new AlertDialog.Builder(
									LoginActivity.this);
							builder.setTitle(R.string.dialog_conectar_titulo)
									.setIcon(android.R.drawable.ic_dialog_alert)
									.setMessage(msg);
							builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
						           public void onClick(DialogInterface dialog, int id) {
						               //Não fazer nada
						           }
						       });
							AlertDialog dialog = builder.create();
							dialog.show();

						}
					});
				}
			});
			return null;
		}

	}

	protected void abrirTelaMenuPrincipal() {
		Intent i = new Intent(LoginActivity.this, MainActivity.class);
		startActivity(i);
	}
}
