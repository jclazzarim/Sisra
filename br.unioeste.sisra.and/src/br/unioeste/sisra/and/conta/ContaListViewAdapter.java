package br.unioeste.sisra.and.conta;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import br.unioeste.sisra.and.R;
import br.unioeste.sisra.modelo.to.ContaTO;
import br.unioeste.sisra.modelo.to.MesaTO;

public class ContaListViewAdapter extends ArrayAdapter<ContaTO> {

	private LayoutInflater mInflater;
	ListView lista;
	ContaListagemActivity activity;
	List<ContaTO> contas;

	public ContaListViewAdapter(Context context, ListView lista,
			int textViewResourceId) {
		super(context, textViewResourceId);
		this.lista = lista;
		activity = (ContaListagemActivity) context;

		// Objeto responsável por pegar o Layout do item.
		mInflater = LayoutInflater.from(activity);

		contas = new ArrayList<ContaTO>();

		configurarAcoes();

	}

	private void configurarAcoes() {
		lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO fazer isso

			}
		});
	}

	private class Tag {
		TextView txtDescricao = new TextView(getContext());
		TextView txtTotal = new TextView(getContext());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Tag tag;
		if (convertView == null) {
			tag = new Tag();
			convertView = mInflater.inflate(R.layout.list_conta_listagem_item, null);
			tag.txtDescricao = (TextView) convertView
					.findViewById(R.id.id_list_conta_listagem_descricao);
			tag.txtTotal = (TextView) convertView
					.findViewById(R.id.id_list_conta_listagem_total_valor);
			convertView.setTag(tag);
		} else {
			tag = (Tag) convertView.getTag();
		}

		ContaTO conta = contas.get(position);
		tag.txtDescricao.setText(conta.getDescricao());
		tag.txtTotal.setText(conta.getTotal());

		return convertView;
	}

	@Override
	public int getCount() {
		return contas.size();
	}

	public void atualizarLista(List<ContaTO> contas) {
		this.contas = contas;
		notifyDataSetChanged();
	}

}
