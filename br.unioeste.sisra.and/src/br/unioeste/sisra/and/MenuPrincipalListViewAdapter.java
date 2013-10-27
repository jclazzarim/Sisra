package br.unioeste.sisra.and;

import sun.applet.Main;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MenuPrincipalListViewAdapter extends ArrayAdapter<String> {

	private LayoutInflater mInflater;
	ListView lista;
	MainActivity activity;

	public MenuPrincipalListViewAdapter(Context context, ListView lista, int textViewResourceId) {
		super(context, textViewResourceId);
		this.lista = lista;
		activity = (MainActivity) context;

		// Objeto responsável por pegar o Layout do item.
		mInflater = LayoutInflater.from(activity);

		add("Pedido");
		add("Conta");
		add("Mesa");
		
		configurarAcoes();
		
	}

	private void configurarAcoes() {
		lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				if(position == 0){//Conta
					
				}else if(position == 1){//Pedido
					
				}else if(position == 2){//Mesa
					activity.abrirMesaListagem();
				}
				
			}
		});
	}

	private class Tag {
		TextView nome = new TextView(getContext());
		ImageView icon = new ImageView(getContext());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Tag tag;
		if (convertView == null) {
			tag = new Tag();
			convertView = mInflater.inflate(R.layout.list_principal_item, null);
			tag.nome = (TextView) convertView
					.findViewById(R.id.id_list_principal_descricao);
			tag.icon = (ImageView) convertView
					.findViewById(R.id.id_list_principal_icon);
		} else {
			tag = (Tag) parent.getTag();
		}

		if (position == 0) {
			tag.icon.setImageResource(R.drawable.ic_list_pedido);
		} else if (position == 1) {
			tag.icon.setImageResource(R.drawable.ic_list_conta);
		} else {
			tag.icon.setImageResource(R.drawable.ic_list_mesa);
		}

		String str = getItem(position);
		tag.nome.setText(str);

		return convertView;
	}

}
