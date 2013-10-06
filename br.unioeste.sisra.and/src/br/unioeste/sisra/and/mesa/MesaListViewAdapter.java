package br.unioeste.sisra.and.mesa;

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
import br.unioeste.sisra.modelo.to.MesaTO;

public class MesaListViewAdapter extends ArrayAdapter<MesaTO> {

	private LayoutInflater mInflater;
	ListView lista;
	MesaActivity activity;
	List<MesaTO> mesas;

	public MesaListViewAdapter(Context context, ListView lista,
			int textViewResourceId) {
		super(context, textViewResourceId);
		this.lista = lista;
		activity = (MesaActivity) context;

		// Objeto responsável por pegar o Layout do item.
		mInflater = LayoutInflater.from(activity);

		mesas = new ArrayList<MesaTO>();

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
		TextView descricao = new TextView(getContext());
		ImageView status = new ImageView(getContext());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Tag tag;
		if (convertView == null) {
			tag = new Tag();
			convertView = mInflater.inflate(R.layout.list_mesa_item, null);
			tag.descricao = (TextView) convertView
					.findViewById(R.id.id_list_mesa_listagem_descricao);
			tag.status = (ImageView) convertView
					.findViewById(R.id.id_list_mesa_listagem_status);
			convertView.setTag(tag);
		} else {
			tag = (Tag) convertView.getTag();
		}

		MesaTO mesa = mesas.get(position);
		tag.descricao.setText(mesa.getDescricao() == null? "": mesa.getDescricao());

		if (mesa.getStatus().equals(MesaTO.STATUS_LIVRE)) {
			tag.status.setImageResource(R.drawable.ic_list_available);
		} else if (mesa.getStatus().equals(MesaTO.STATUS_OCUPADO)) {
			tag.status.setImageResource(R.drawable.ic_list_not_available);
		}

		return convertView;
	}

	@Override
	public int getCount() {
		return mesas.size();
	}

	public void atualizarLista(List<MesaTO> mesas) {
		this.mesas = mesas;
		notifyDataSetChanged();
	}

}
