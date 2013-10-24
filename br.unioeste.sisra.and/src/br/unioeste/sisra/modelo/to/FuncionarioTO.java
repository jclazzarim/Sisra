/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.modelo.to;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author J.C
 */
public class FuncionarioTO implements Serializable{
    
    private static final long serialVersionUID = -815590442199148424L;
    
    private Long id;
    private String nome;
    private boolean sexo;
    private Date dtNascimento;
    private Date dtContratacao;
    private Date dtDemissao;
    private String rg;
    private String rua;
    private String bairro;
    private String cidade;
    private String numero;
    private String estado;
    private String pais;
    private String cpf;

    public FuncionarioTO() {
        this.id = (long) 1;
        this.nome = "";
        this.sexo = false;
        this.dtNascimento = null;
        this.dtContratacao = null;
        this.dtDemissao = null;
        this.rg = "";
        this.rua = "";
        this.bairro = "";
        this.cidade = "";
        this.numero = "";
        this.estado = "";
        this.pais = "";
        this.cpf = "";
    }
    
    public FuncionarioTO(Long id, String nome, boolean sexo, Date  dt_nascimento, Date dt_contratacao, Date dt_demissao, String rg, String rua, String bairro, String cidade, String numero, String estado, String pais, String cpf) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.dtNascimento = dt_nascimento;
        this.dtContratacao = dt_contratacao;
        this.dtDemissao = dt_demissao;
        this.rg = rg;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.numero = numero;
        this.estado = estado;
        this.pais = pais;
        this.cpf = cpf;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean getSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public Date getDtContratacao() {
        return dtContratacao;
    }

    public void setDtContratacao(Date dtContratacao) {
        this.dtContratacao = dtContratacao;
    }

    public Date getDtDemissao() {
        return dtDemissao;
    }

    public void setDtDemissao(Date dtDemissao) {
        this.dtDemissao = dtDemissao;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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
    
    
    
}
