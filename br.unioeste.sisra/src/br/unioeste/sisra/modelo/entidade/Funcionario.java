package br.unioeste.sisra.modelo.entidade;

import java.util.Date;

public class Funcionario {
	private int id, numResidencia;
	private String nome, rg, rua, bairro, cidade, estado, pais, cpf;
	private boolean sexo;
	private Date dtNasc, dtContrata, dtDemite;
	
	
	public Funcionario(int id, int numResidencia, String nome, String rg,
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
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public boolean isSexo() {
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
	
	
}
