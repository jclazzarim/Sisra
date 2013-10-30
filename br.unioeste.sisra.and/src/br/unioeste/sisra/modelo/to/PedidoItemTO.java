package br.unioeste.sisra.modelo.to;

import java.io.Serializable;

public class PedidoItemTO implements Serializable {
	private static final long serialVersionUID = -4295223262221459778L;
	private Long idPedidoItem;
	private boolean pronto;
	private int quantidade;
	private ItemTO item;
	// private PedidoTO pedido;

	public PedidoItemTO(Long idPedidoItem, boolean pronto, int quantidade, ItemTO item) {
		this.idPedidoItem = idPedidoItem;
		this.pronto = pronto;
		this.setQuantidade(quantidade);
		this.item = item;
	}

	public Long getIdPedidoItem() {
		return idPedidoItem;
	}

	public void setIdPedidoItem(Long idPedidoItem) {
		this.idPedidoItem = idPedidoItem;
	}

	public boolean isPronto() {
		return pronto;
	}

	public void setPronto(boolean pronto) {
		this.pronto = pronto;
	}

	public ItemTO getItem() {
		return item;
	}

	public void setItem(ItemTO item) {
		this.item = item;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}
