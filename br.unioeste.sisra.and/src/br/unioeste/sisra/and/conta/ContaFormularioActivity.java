package br.unioeste.sisra.and.conta;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import br.unioeste.sisra.and.R;
import br.unioeste.sisra.and.login.LoginActivity;
import br.unioeste.sisra.and.mesa.MesaActivity;
import br.unioeste.sisra.and.sync.Syncronizacao;
import br.unioeste.sisra.and.sync.SyncronizacaoFactory;
import br.unioeste.sisra.modelo.listener.IRetornoSyncQuery;
import br.unioeste.sisra.modelo.to.ContaTO;
import br.unioeste.sisra.modelo.to.MesaTO;
import br.unioeste.sisra.utils.Codigo;

public class ContaFormularioActivity extends Activity {
	public static final String PARAMETRO_MESA = "PARAMETRO_MESA";
	MesaTO mesa;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conta_formulario);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			this.mesa = (MesaTO) extras.get(PARAMETRO_MESA);
		}

		configurarAcoes();
	}

	private void configurarAcoes() {
		// Configurando botao cancelar
		Button btnCancelar = (Button) findViewById(R.id.btn_conta_cancelar);
		btnCancelar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		// Configurando botao salvar
		Button btnSalvar = (Button) findViewById(R.id.btn_conta_salvar);
		btnSalvar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ContaTO conta = getContaPreenchida();
				new SyncContaTask().execute(Codigo.Entidade.CONTA, Codigo.TipoAcesso.INSERT, "", conta);
			}
		});
	}

	public ContaTO getContaPreenchida() {
		ContaTO conta = new ContaTO();
		EditText edtDescricao = (EditText) findViewById(R.id.edt_conta_descricao);

		conta.setDescricao(edtDescricao.getText().toString());
		conta.setMesa(mesa);

		return conta;
	}

	@Override
	public void finish() {

		super.finish();
	}

	// -----------------------------------------------------
	// ACESSO AO BANCO
	// -----------------------------------------------------
	/**
	 * Os parametros devem ser na seguinte ordem:
	 * <p>
	 * [0] Codigo Entidade, [1] Codigo Tipo Acesso, [2] Query, [3] Objeto
	 * </p>
	 * */
	private class SyncContaTask extends AsyncTask<Object, Object, Object> {

		@Override
		protected Object doInBackground(Object... params) {
			try {

				int codigoEntidade = (Integer) params[0];
				int codigoTipoAcesso = (Integer) params[1];
				String query = (String) params[2];
				Serializable obj = (Serializable) params[3];
				Syncronizacao sync = SyncronizacaoFactory.create();
				sync.syncQuery(codigoEntidade, codigoTipoAcesso, query, obj,
						new IRetornoSyncQuery() {

							@Override
							public void onRetornoConsulta(final List result) {
								ContaFormularioActivity.this
										.runOnUiThread(new Runnable() {

											@Override
											public void run() {
												ContaFormularioActivity.this.finish();
											}
										});

							}

							@Override
							public void onRetornoInsersao() {
								
								ContaFormularioActivity.this
								.runOnUiThread(new Runnable() {

									@Override
									public void run() {
										Builder builder = new AlertDialog.Builder(
												ContaFormularioActivity.this);
										builder.setTitle("Sisra - Conta")
												.setIcon(android.R.drawable.ic_dialog_info)
												.setMessage("Conta inserida com sucesso.");
										builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
									           public void onClick(DialogInterface dialog, int id) {
									        	   ContaFormularioActivity.this.finish();
									           }
									       });
										AlertDialog dialog = builder.create();
										dialog.show();
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
}