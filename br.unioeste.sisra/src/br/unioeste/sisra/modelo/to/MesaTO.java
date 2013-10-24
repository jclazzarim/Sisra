/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.modelo.to;

import java.io.Serializable;

/**
 * 
 * @author Charlinho
 */
public class MesaTO implements Serializable {
	private static final long serialVersionUID = -4129299364268095676L;
	public static final String STATUS_OCUPADO = "Ocupado";
	public static final String STATUS_LIVRE = "Livre";

	private String status;
	private String descricao;
	private String obs;
	private Long id;

	public MesaTO() {
		this.status = "Livre";
		this.descricao = "";
		this.obs = "";
		this.id = 0l;
	}

	public MesaTO(String status, String descricao, String obs, Long id) {
		this.status = status;
		this.descricao = descricao;
		this.obs = obs;
		this.id = id;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the obs
	 */
	public String getObs() {
		return obs;
	}

	/**
	 * @param obs
	 *            the obs to set
	 */
	public void setObs(String obs) {
		this.obs = obs;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
}
