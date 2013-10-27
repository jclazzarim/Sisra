/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.modelo.to;

import java.io.Serializable;
import java.util.Date;

import br.unioeste.sisra.utils.DataUtils;
import java.sql.Timestamp;

/**
 *
 * @author Charlinho
 */
public class ContaTO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -160081288000099661L;
	//private List<PedidoTO> pedidos;

	private String descricao;
    private Long id;
    private Date horaAbertura, horaFechamento;
    private MesaTO mesa;
    private String total;

    public ContaTO() {

        this.id = 0l;
        this.descricao = "";
        this.horaAbertura = new Date();
        this.mesa = new MesaTO();
        this.total = "";
    }

    public ContaTO(Long id, String descricao, Date horaAbertura, Date horaFechamento, MesaTO mesa, String total) {
        this.id = id;
		this.descricao = descricao;
        this.horaAbertura = horaAbertura;
        this.horaFechamento = horaFechamento;
        this.mesa = mesa;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHoraAberturaString() throws Exception {
        return  DataUtils.converterDataParaString(horaAbertura);
    }

    public void setHoraAbertura(Date horaAbertura) {
        this.horaAbertura = horaAbertura;
    }

    public String getHoraFechamentoString() throws Exception {
    	return  DataUtils.converterDataParaString(horaFechamento);
    }

    public void setHoraFechamento(Date horaFechamento) {
        this.horaFechamento = horaFechamento;
    }

    public MesaTO getMesaTO() {
        return mesa;
    }

    public void setMesa(MesaTO mesa) {
        this.mesa = mesa;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

    public Date getHoraAbertura() {
        return this.horaAbertura;
    }

    public Date getHoraFechamento() {
        return this.horaFechamento;
    }
}
