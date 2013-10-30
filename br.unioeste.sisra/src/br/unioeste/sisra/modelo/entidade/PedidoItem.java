/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unioeste.sisra.modelo.entidade;

import br.unioeste.sisra.modelo.to.PedidoItemTO;

/**
 *
 * @author Charlinho
 */
public class PedidoItem {
    public class Status{
        public final static int NOVO = 0;
        public final static int EM_ANDAMENTO = 1;
        public final static int PRONTO = 2;
        public final static int CANCELADO = 3;
    }
    
    private Long idPedidoItem;
    private int status;
    private Pedido pedido;
    private Item item;
    private int quantidade;

    public PedidoItem() {
        this.idPedidoItem = 0l;
        this.status = Status.NOVO;
        this.pedido = new Pedido();
        this.item = new Item();
        this.quantidade = 0;
    }
    
    

    public PedidoItem(Long idPedido, int status, Pedido pedido, Item item, int quantidade) {
        this.idPedidoItem = idPedido;
        this.status = status;
        this.pedido = pedido;
        this.item = item;
        this.quantidade = quantidade;
    }
    
    

    /**
     * @return the idPedidoItem
     */
    public Long getIdPedidoItem() {
        return idPedidoItem;
    }

    /**
     * @param idPedidoItem the idPedidoItem to set
     */
    public void setIdPedidoItem(Long idPedidoItem) {
        this.idPedidoItem = idPedidoItem;
    }

    /**
     * @return the pronto
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param pronto the pronto to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the pedido
     */
    public Pedido getPedido() {
        return pedido;
    }

    /**
     * @param pedido the pedido to set
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    /**
     * @return the item
     */
    public Item getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(Item item) {
        this.item = item;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public PedidoItemTO toTO() {
        Double valorTotal = quantidade * item.getPreco();
        
       return new PedidoItemTO(idPedidoItem, status, quantidade, String.valueOf(valorTotal), item.toTO(), pedido.toTo());
    }
    
    
    
    
    
    
}
