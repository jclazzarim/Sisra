/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.unioeste.sisra.modelo.to;

import br.unioeste.sisra.modelo.entidade.Conta;
import br.unioeste.sisra.modelo.entidade.PedidoItem;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Charlinho
 */
public class PedidoTO {
     private Long idPedido;
    private Date horaPedido;
    private boolean atendido;
    private ContaTO conta;
    private ArrayList<PedidoItemTO> pedidoItens;

    public PedidoTO() {
        this.idPedido = 0l;
        this.horaPedido = new Date();
        this.atendido = false;
        this.conta = new ContaTO();
        this.pedidoItens = new ArrayList<>();
    }

    public PedidoTO(Long idPedido, Date horaPedido, boolean atendido, ContaTO conta, ArrayList<PedidoItemTO> pedidoItens) {
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
    public ContaTO getConta() {
        return conta;
    }

    /**
     * @param conta the conta to set
     */
    public void setConta(ContaTO conta) {
        this.conta = conta;
    }

    /**
     * @return the pedidoItens
     */
    public ArrayList<PedidoItemTO> getPedidoItens() {
        return pedidoItens;
    }

    /**
     * @param pedidoItens the pedidoItens to set
     */
    public void setPedidoItens(ArrayList<PedidoItemTO> pedidoItens) {
        this.pedidoItens = pedidoItens;
    }
    
    
    
    
}
