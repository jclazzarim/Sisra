/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.modelo.entidade;

import br.unioeste.sisra.modelo.to.ItemTO;

/**
 *
 * @author Charlinho
 */
public class Item {
    private long idItem;
    private String nome;
    private String descricao;
    private String codigo;
    private double preco;

    public Item() {
        this.idItem = 0l;
        this.codigo = "";
        this.nome = "";
        this.descricao = "";
        this.preco = 0d;
    }

    public Item(long idItem, String codigo, String nome, String descricao, double preco) {
        this.idItem = idItem;
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }
    
    
    

    /**
     * @return the idItem
     */
    public long getId() {
        return idItem;
    }

    /**
     * @param idItem the idItem to set
     */
    public void setId(long idItem) {
        this.idItem = idItem;
    }
    
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
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
     * @return the preco
     */
    public double getPreco() {
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    public ItemTO toTO(){
        String strPreco = String.valueOf(preco);
        return new ItemTO(idItem, nome, descricao, strPreco, codigo);
    }
}
