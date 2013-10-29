package br.unioeste.sisra.and.pedido.item;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import br.unioeste.sisra.and.R;
import br.unioeste.sisra.modelo.to.ItemTO;

public class ItensListViewAdapter extends ArrayAdapter<ItemTO> {

	private LayoutInflater mInflater;
	ListView lista;
	ItensActivity activity;
	List<ItemTO> itens;

	public ItensListViewAdapter(Context context, ListView lista,
			int textViewResourceId) {
		super(context, textViewResourceId);
		this.lista = lista;
		activity = (ItensActivity) context;

		// Objeto responsável por pegar o Layout do item.
		mInflater = LayoutInflater.from(activity);

		itens = new ArrayList<ItemTO>();

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
		TextView txtNome;
		TextView txtPreco;
		TextView txtCodigo;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Tag tag;
		if (convertView == null) {
			tag = new Tag();
			convertView = mInflater.inflate(R.layout.list_item_listagem_item, null);
			tag.txtNome = (TextView) convertView
					.findViewById(R.id.id_list_itens_listagem_nome);
			tag.txtCodigo = (TextView) convertView
					.findViewById(R.id.id_list_itens_listagem_codigo_valor);
			tag.txtPreco = (TextView) convertView
					.findViewById(R.id.id_list_itens_listagem_preco_valor);
			convertView.setTag(tag);
		} else {
			tag = (Tag) convertView.getTag();
		}

		ItemTO item = itens.get(position);
		tag.txtNome.setText(item.getNome());
		tag.txtCodigo.setText(item.getCodigo());
		tag.txtPreco.setText(item.getPreco());

		return convertView;
	}

	@Override
	public int getCount() {
		return itens.size();
	}

	public void atualizarLista(List<ItemTO> itens) {
		this.itens = itens;
		notifyDataSetChanged();
	}

}
