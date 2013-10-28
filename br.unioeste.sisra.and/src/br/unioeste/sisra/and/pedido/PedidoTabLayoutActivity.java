package br.unioeste.sisra.and.pedido;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import br.unioeste.sisra.and.R;

public class PedidoTabLayoutActivity extends TabActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_layout);

		TabHost tabHost = getTabHost();

		// Tab for Photos
		TabSpec itensspec = tabHost.newTabSpec("Itens");
		// setting Title and Icon for the Tab
		itensspec.setIndicator("Itens", getResources().getDrawable(R.drawable.ic_itens));
		Intent itensIntent = new Intent(this, ItensActivity.class);
		itensspec.setContent(itensIntent);

		// Tab for Songs
		TabSpec pedidoItensspec = tabHost.newTabSpec("PedidoItens");
		pedidoItensspec.setIndicator("Carrinho", getResources().getDrawable(R.drawable.ic_pedido_itens));
		Intent pedidoItensIntent = new Intent(this, PedidoItensActivity.class);
		pedidoItensspec.setContent(pedidoItensIntent);

		// Adding all TabSpec to TabHost
		tabHost.addTab(itensspec); // Adding photos tab
		tabHost.addTab(pedidoItensspec); // Adding songs tab
	}
}
