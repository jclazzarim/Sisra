package br.unioeste.sisra.and.list;

import br.unioeste.sisra.and.R;
import android.R.anim;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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
			ImageView seta = new ImageView(getContext());

			public static final int ID_NOME = 0;
			public static final int ID_ICON = 1;
			public static final int ID_SETA = 2;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Tag tag;
			if (convertView == null) {
				tag = new Tag();
				convertView = layout();
				tag.nome = (TextView) convertView.findViewById(Tag.ID_NOME);
				 tag.icon = (ImageView) convertView.findViewById(Tag.ID_ICON);
				// tag.nome = (TextView) convertView.findViewById(Tag.ID_SETA);
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

		private LinearLayout layout() {
			LinearLayout layout = new LinearLayout(getContext());
			layout.setOrientation(LinearLayout.HORIZONTAL);
			{
				ImageView imgIcon = new ImageView(getContext());
				imgIcon.setId(Tag.ID_ICON);
				layout.addView(imgIcon);
			}
			// nome
			{
				TextView txNome = new TextView(getContext());
				txNome.setTextSize(30);
				txNome.setId(Tag.ID_NOME);
				layout.addView(txNome);
			}
			return layout;
		}

	}
}
