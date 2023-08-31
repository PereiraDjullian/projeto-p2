/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoestoque.controller;

import com.mycompany.gestaoestoque.dao.UsuarioDao;
import com.mycompany.gestaoestoque.model.Usuario;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author notedj
 */
public class UsuarioController {

private final UsuarioDao usuarioDao;

    public UsuarioController(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public void criarUsuario(int pessoaId, String senha, String tipoUsuario) throws SQLException {
        Usuario novoUsuario = new Usuario(senha, tipoUsuario, pessoaId, senha, senha, senha, LocalDateTime.now());
        usuarioDao.criarUsuarioPorId(pessoaId, novoUsuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDao.listarUsuarios();
    }

    public void atualizarUsuarioPorId(int id, String novaSenha, String novoTipoUsuario) {
        Usuario usuarioEncontrado = usuarioDao.buscarUsuarioPorId(id);
        if (usuarioEncontrado != null) {
            Usuario novoUsuario = new Usuario(novaSenha, novoTipoUsuario, id, novaSenha, novaSenha, novaSenha, LocalDateTime.now());
            usuarioDao.atualizarUsuarioPorId(id, novoUsuario);
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    public Usuario buscarUsuarioPorId(int id) {
        return usuarioDao.buscarUsuarioPorId(id);
    }

    public void excluirUsuarioPorId(int id) {
        usuarioDao.deletarUsuario(id);
    }

}
