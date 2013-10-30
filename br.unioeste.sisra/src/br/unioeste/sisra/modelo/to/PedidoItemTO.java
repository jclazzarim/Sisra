package br.unioeste.sisra.modelo.to;

import br.unioeste.sisra.modelo.entidade.PedidoItem;
import java.io.Serializable;

public class PedidoItemTO implements Serializable {

    private static final long serialVersionUID = -4295223262221459778L;
    private Long idPedidoItem;
    private int status;
    private int quantidade;
    private String valorTotal;
    private ItemTO item;
    private PedidoTO pedido;

    public PedidoItemTO(Long idPedidoItem, int status, int quantidade, String valorTotal, ItemTO item, PedidoTO pedido) {
        this.idPedidoItem = idPedidoItem;
        this.status = status;
        this.valorTotal = valorTotal;
        this.quantidade = quantidade;
        this.item = item;
        this.pedido = pedido;
    }

    public PedidoItemTO() {
        this.idPedidoItem = 0l;
        this.status = PedidoItem.Status.NOVO;
        this.valorTotal = "0";
        this.quantidade = 0;
        this.item = new ItemTO();
        this.pedido = new PedidoTO();
    }

    public Long getIdPedidoItem() {
        return idPedidoItem;
    }

    public void setIdPedidoItem(Long idPedidoItem) {
        this.idPedidoItem = idPedidoItem;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getTotal() {
        return valorTotal;
    }

    public void setTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }

    /**
     * @return the pedido
     */
    public PedidoTO getPedido() {
        return pedido;
    }

    /**
     * @param pedido the pedido to set
     */
    public void setPedido(PedidoTO pedido) {
        this.pedido = pedido;
    }
    
    
}
