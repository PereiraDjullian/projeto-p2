/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoestoque.dao;

import com.mycompany.gestaoestoque.model.Pessoa;
import com.mycompany.gestaoestoque.model.Usuario;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author notedj
 */
public class UsuarioDao {

    private final Connection conexao;

    public UsuarioDao(Connection conexao) {
        this.conexao = conexao;
    }

    public void criarUsuarioPorId(int pessoaId, Usuario usuario) {
        try {
            String sql = "INSERT INTO usuario(id, senha, tipo_usuario) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, pessoaId); // Usar o id da pessoa existente
            preparedStatement.setString(2, usuario.getSenha());
            preparedStatement.setString(3, usuario.getTipoUsuario());

            preparedStatement.executeUpdate(); // Executar a inserção na tabela usuario

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao criar usuário por id: " + e.getMessage());
        }
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();

        try {
            String sql = "SELECT id, senha, tipo_usuario FROM usuario";
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Usuario usuario = new Usuario(
                        resultSet.getString("senha"),
                        resultSet.getString("tipo_usuario"),
                        resultSet.getInt("id"),
                        null, // Nome não está presente na tabela usuario
                        null, // Email não está presente na tabela usuario
                        null, // CPF não está presente na tabela usuario
                        null // Data de criação não está presente na tabela usuario
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao listar usuários: " + e.getMessage());
        }

        return usuarios; // Retorne a lista de usuários
    }

//    public void atualizarUsuarioPorNome(String nome, Usuario novoUsuario) {
//        try {
//            String sql = "UPDATE usuarios SET senha = ?, tipo_usuario = ?, id = ?, nome = ?, email = ?, cpf = ?, data_criacao = ? WHERE nome = ?";
//            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
//            preparedStatement.setString(1, novoUsuario.getSenha());
//            preparedStatement.setString(2, novoUsuario.getTipoUsuario());
//            preparedStatement.setInt(3, novoUsuario.getId());
//            preparedStatement.setString(4, novoUsuario.getNome());
//            preparedStatement.setString(5, novoUsuario.getEmail());
//            preparedStatement.setString(6, novoUsuario.getCpf());
//            preparedStatement.setTimestamp(7, Timestamp.valueOf(novoUsuario.getDataCriacao()));
//            preparedStatement.setString(8, nome);
//
//            preparedStatement.executeUpdate(); // Executa a atualização no banco de dados
//
//        } catch (SQLException e) {
//            System.out.println("Ocorreu um erro ao atualizar usuário por nome: " + e.getMessage());
//        }
//    }
    public void atualizarUsuarioPorId(int id, Usuario novoUsuario) {
        try {
            String sql = "UPDATE usuario SET senha = ?, tipo_usuario = ? WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, novoUsuario.getSenha());
            preparedStatement.setString(2, novoUsuario.getTipoUsuario());
            preparedStatement.setInt(3, id);

            preparedStatement.executeUpdate(); // Executa a atualização no banco de dados

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao atualizar usuário por ID: " + e.getMessage());
        }
    }

    public Usuario buscarUsuarioPorId(int id) {
        try {
            String sql = "SELECT * FROM usuario WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Usuario usuario = new Usuario(
                        resultSet.getString("senha"),
                        resultSet.getString("tipo_usuario"),
                        resultSet.getInt("id"),
                        null,
                        null,
                        null,
                        null
                );
                return usuario;
            }
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao buscar usuário pelo id: " + e.getMessage());
        }

        return null; // Retorne null caso não encontre nenhum usuário com o ID especificado
    }

    public void deletarUsuario(int id) {
        try {
            String sql = "DELETE FROM usuario WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao Excluir Usuario pelo id: " + e.getMessage());

        }
    }
}
