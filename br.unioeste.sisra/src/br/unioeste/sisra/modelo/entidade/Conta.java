/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.modelo.entidade;

import br.unioeste.sisra.modelo.to.ContaTO;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Charlinho
 */
public class Conta {
    //private List<Pedido> pedidos;

    private Long id;
    private Date horaAbertura, horaFechamento;
    private Mesa mesa;
    private Double total;
    private String descricao;

    public Conta() {
        this.id = 0l;
        this.horaAbertura = new Timestamp(new Date().getTime());
        this.horaFechamento = new Timestamp(new Date().getTime());;
        this.mesa = new Mesa();
        this.total = 0d;
        this.descricao = "";
    }

    public Conta(Long id, Timestamp horaAbertura, Timestamp horaFechamento, Mesa idMesa, Double total) {
        this.id = id;
        this.horaAbertura = horaAbertura;
        this.horaFechamento = horaFechamento;
        this.mesa = idMesa;
        this.total = total;
        this.descricao = "";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getHoraAbertura() {
        return horaAbertura;
    }

    public void setHoraAbertura(Date horaAbertura) {
        this.horaAbertura = horaAbertura;
    }

    public Date getHoraFechamento() {
        return horaFechamento;
    }

    public void setHoraFechamento(Date horaFechamento) {
        this.horaFechamento = horaFechamento;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public ContaTO toTO() {
        return new ContaTO(id, descricao,horaAbertura, horaFechamento, mesa.toTO(), String.valueOf(total));
    }

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
