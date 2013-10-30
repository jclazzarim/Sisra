/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.modelo.entidade;

import br.unioeste.sisra.modelo.to.PedidoItemTO;
import br.unioeste.sisra.modelo.to.PedidoTO;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Charlinho
 */
public class Pedido {

    private Long idPedido;
    private Date horaPedido;
    private boolean atendido;
    private Conta conta;
    private ArrayList<PedidoItem> pedidoItens;

    public Pedido() {
        this.idPedido = 0l;
        this.horaPedido = new Date();
        this.atendido = false;
        this.conta = new Conta();
        this.pedidoItens = new ArrayList<>();
    }

    //private Funcionario funcionario;
    
    public Pedido(Long idPedido, Date horaPedido, boolean atendido, Conta conta) {
        this.idPedido = idPedido;
        this.horaPedido = horaPedido;
        this.atendido = atendido;
        this.conta = conta;
        this.pedidoItens = new ArrayList<>();
    }
    
    public Pedido(Long idPedido, Date horaPedido, boolean atendido, Conta conta, ArrayList<PedidoItem> pedidoItens) {
        this.idPedido = idPedido;
        this.horaPedido = horaPedido;
        this.atendido = atendido;
        this.conta = conta;
        this.pedidoItens = pedidoItens;
    }

    /**
     * @return the idPedido
     */
    public Long getIdPedido() {
        return idPedido;
    }

    /**
     * @param idPedido the idPedido to set
     */
    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    /**
     * @return the horaPedido
     */
    public Date getHoraPedido() {
        return horaPedido;
    }

    /**
     * @param horaPedido the horaPedido to set
     */
    public void setHoraPedido(Date horaPedido) {
        this.horaPedido = horaPedido;
    }

    /**
     * @return the atendido
     */
    public boolean isAtendido() {
        return atendido;
    }

    /**
     * @param atendido the atendido to set
     */
    public void setAtendido(boolean atendido) {
        this.atendido = atendido;
    }

    /**
     * @return the conta
     */
    public Conta getConta() {
        return conta;
    }

    /**
     * @param conta the conta to set
     */
    public void setConta(Conta conta) {
        this.conta = conta;
    }
    
    public PedidoTO toTo(){
        ArrayList<PedidoItemTO> pedidoItens = new ArrayList<>();
        for (PedidoItem pedidoItem : this.pedidoItens) {
            pedidoItens.add(pedidoItem.toTO());
        }
        
        return new PedidoTO(idPedido, horaPedido, atendido, conta.toTO(), pedidoItens);
    }

}
