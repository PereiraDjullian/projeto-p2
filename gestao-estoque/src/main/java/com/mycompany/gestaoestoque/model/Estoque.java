/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoestoque.model;

/**
 *
 * @author notedj
 */
public class Estoque {
    private int id;
    private int produtoId;
    private int usuarioId;
    private int quantidade;
    private String tipoOperacao;

    public Estoque(int id, int produtoId, int usuarioId, int quantidade, String tipoOperacao) {
        this.id = id;
        this.produtoId = produtoId;
        this.usuarioId = usuarioId;
        this.quantidade = quantidade;
        this.tipoOperacao = tipoOperacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(String tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    @Override
    public String toString() {
        return "Estoque{" + "id=" + id + ", produtoId=" + produtoId + ", usuarioId=" + usuarioId + ", quantidade=" + quantidade + ", tipoOperacao=" + tipoOperacao + '}';
    }
    
    


    
    
}
