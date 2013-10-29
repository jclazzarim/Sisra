/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.modelo.to;

import java.io.Serializable;

/**
 *
 * @author JCLazzarim
 */
public class ItemTO implements Serializable{
	private static final long serialVersionUID = 2044508644846461059L;
	private String nome;
    private String descricao;
    private String preco;
    private Long id;
    private String codigo;

    public ItemTO(Long id, String nome, String descricao, String preco, String codigo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.codigo = codigo;
    }

    public ItemTO() {
        this.id = 0l;
        this.nome = "";
        this.descricao = "";
        this.preco = "";
        this.codigo = "";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
}
