/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.modelo.to;

/**
 *
 * @author Charlinho
 */
public class ContaTO {
    //private List<PedidoTO> pedidos;

    private Long id;
    private String horaAbertura, horaFechamento;
    private MesaTO mesa;
    private String total;

    public ContaTO() {

        this.id = 0l;
        this.horaAbertura = "";
        this.horaFechamento = "";
        this.mesa = new MesaTO();
        this.total = "";
    }

    public ContaTO(Long id, String horaAbertura, String horaFechamento, MesaTO mesa, String total) {
        this.id = id;
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

    public String getHoraAbertura() {
        return horaAbertura;
    }

    public void setHoraAbertura(String horaAbertura) {
        this.horaAbertura = horaAbertura;
    }

    public String getHoraFechamento() {
        return horaFechamento;
    }

    public void setHoraFechamento(String horaFechamento) {
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
}
