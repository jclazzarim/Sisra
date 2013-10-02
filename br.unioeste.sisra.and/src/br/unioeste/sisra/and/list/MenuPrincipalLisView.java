package br.unioeste.sisra.and.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import br.unioeste.sisra.and.R;

public class MenuPrincipalLisView extends ListView {

	private MenuPrincipalLisViewAdapter adapter;

	public MenuPrincipalLisView(Context context) {
		super(context);
		adapter = new MenuPrincipalLisViewAdapter(context,
				android.R.layout.simple_list_item_1);
		this.setAdapter(adapter);
	}

	private class MenuPrincipalLisViewAdapter extends ArrayAdapter<String> {

		public MenuPrincipalLisViewAdapter(Context context,
				int textViewResourceId) {
			super(context, textViewResourceId);
			add("Conta");
			add("Pedido");
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
				LinearLayout root = new LinearLayout(getContext());
				convertView = inflate(getContext(), R.layout.lay_list_principal, root);
				//convertView = layout();
				tag.nome = (TextView) convertView.findViewById(R.id.lay_list_principal_descricao);
				 tag.icon = (ImageView) convertView.findViewById(R.id.lay_list_principal_icon);
			} else {
				tag = (Tag) getTag();
			}
			
			if(position == 0){
				tag.icon.setImageResource(R.drawable.ic_pedido_list);
			}else{
				tag.icon.setImageResource(R.drawable.ic_conta_list);
			}

			String str = getItem(position);
			tag.nome.setText(str);

			return convertView;
		}

	}
}
