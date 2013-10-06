/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.modelo.entidade;

import br.unioeste.sisra.modelo.to.MesaTO;

/**
 *
 * @author Charlinho
 */
public class Mesa {
    public static final boolean STATUS_OCUPADO = true;
    public static final boolean STATUS_LIVRE = false;
    
    private boolean status;
    private String descricao;
    private String obs;
    private Long id;

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
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
     * @param obs the obs to set
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
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    public MesaTO toTO() {
        String strStatus = status == STATUS_LIVRE? MesaTO.STATUS_LIVRE: MesaTO.STATUS_OCUPADO;
        return new MesaTO(strStatus, descricao, obs, id);
    }
}
