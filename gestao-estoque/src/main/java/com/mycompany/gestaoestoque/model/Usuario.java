/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoestoque.model;

import java.time.LocalDateTime;

/**
 *
 * @author notedj
 */
public class Usuario extends Pessoa {
    private String senha;
    private String tipoUsuario;

    public Usuario(String senha, String tipoUsuario, int id, String nome, String email, String cpf, LocalDateTime dataCriacao) {
        super(id, nome, email, cpf, dataCriacao);
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String toString() {
        return "Usuario{" + "senha=" + senha + ", tipoUsuario=" + tipoUsuario + '}';
    }
    
    
    
}
