package br.unioeste.sisra.and.conta;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import br.unioeste.sisra.and.R;
import br.unioeste.sisra.and.mesa.MesaActivity;
import br.unioeste.sisra.modelo.to.ContaTO;
import br.unioeste.sisra.modelo.to.MesaTO;

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

		// configurando botao de consulta
		Button btConsulta = (Button) findViewById(R.id.btn_conta_listagem_pesquisar);
		btConsulta.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ArrayList<ContaTO> contas = new ArrayList<ContaTO>();
				contas.add(new ContaTO(1l, "Maria Joaquina", new Date(),
						new Date(), new MesaTO(), "10,00"));
				contas.add(new ContaTO(2l, "Jão", new Date(), new Date(),
						new MesaTO(), "12,00"));
				contas.add(new ContaTO(3l, "Mioslk", new Date(), new Date(),
						new MesaTO(), "120,00"));
				contas.add(new ContaTO(4l, "Tharle", new Date(), new Date(),
						new MesaTO(), "90,30"));
				contaListagemListViewAdapter.atualizarLista(contas);
				// // new SyncContaTask().execute("");
			}
		});
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
}
