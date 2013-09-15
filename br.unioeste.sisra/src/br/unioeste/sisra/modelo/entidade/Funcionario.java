/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.modelo.entidade;

import br.unioeste.sisra.modelo.to.FuncionarioTO;
import br.unioeste.sisra.utils.DataUtils;
import java.util.Date;

/**
 *
 * @author J.C
 */
public class Funcionario {

    public static boolean SEXO_MASCULINO = true;
    public static boolean SEXO_FEMININO = false;
    private Long id;
    private int numResidencia;
    private String nome, rg, rua, bairro, cidade, estado, pais, cpf;
    private boolean sexo;
    private Date dtNasc, dtContrata, dtDemite;

    
    public Funcionario (){
        this.id = 0l;
        this.numResidencia = 0;
        this.nome = "";
        this.rg = "";
        this.rua = "";
        this.bairro = "";
        this.cidade = "";
        this.estado = "";
        this.pais = "";
        this.cpf = "";
        this.sexo = false;
        this.dtNasc = new Date();
        this.dtContrata = new Date();
    }
    
    public Funcionario(Long id, int numResidencia, String nome, String rg,
            String rua, String bairro, String cidade, String estado,
            String pais, String cpf, boolean sexo, Date dtNasc,
            Date dtContrata, Date dtDemite) {
        this.id = id;
        this.numResidencia = numResidencia;
        this.nome = nome;
        this.rg = rg;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.cpf = cpf;
        this.sexo = sexo;
        this.dtNasc = dtNasc;
        this.dtContrata = dtContrata;
        this.dtDemite = dtDemite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumResidencia() {
        return numResidencia;
    }

    public void setNumResidencia(int numResidencia) {
        this.numResidencia = numResidencia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean getSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    public Date getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(Date dtNasc) {
        this.dtNasc = dtNasc;
    }

    public Date getDtContrata() {
        return dtContrata;
    }

    public void setDtContrata(Date dtContrata) {
        this.dtContrata = dtContrata;
    }

    public Date getDtDemite() {
        return dtDemite;
    }

    public void setDtDemite(Date dtDemite) {
        this.dtDemite = dtDemite;
    }

    public FuncionarioTO toTO() throws Exception {
        String strNumero = String.valueOf(numResidencia);
        
        return new FuncionarioTO(id, nome, sexo, dtNasc, dtContrata, dtDemite, rg, rua, bairro, cidade, strNumero, estado, pais, cpf);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id);
        sb.append("\n");
        sb.append(nome);
        sb.append("\n");
        sb.append(bairro);
        sb.append("\n");
        sb.append(cidade);
        sb.append("\n");
        sb.append(cpf);
        sb.append("\n");
        sb.append(dtContrata);
        sb.append("\n");
        sb.append(dtDemite);
        sb.append("\n");
        sb.append(dtNasc);
        sb.append("\n");
        sb.append(estado);
        sb.append("\n");
        sb.append(numResidencia);
        sb.append("\n");
        sb.append(pais);
        sb.append("\n");
        sb.append(rg);
        sb.append("\n");
        sb.append(rua);
        sb.append("\n");
        sb.append(sexo? "Masculino" : "Feminino");
        sb.append("\n");
        
        return sb.toString();
    }
}
