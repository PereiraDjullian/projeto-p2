/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoestoque.dao;

import com.mycompany.gestaoestoque.model.Estoque;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;

/**
 *
 * @author notedj
 */
public class EstoqueDao {

    private final Connection conexao;

    public EstoqueDao(Connection conexao) {
        this.conexao = conexao;
    }

    public void criarEstoque(Estoque estoque) {
        try {
            String sql = "INSERT INTO estoque(produto_id, usuario_id, quantidade, tipo_operacao) VALUES(?, ?, ?, ?)";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, estoque.getProdutoId());
            preparedStatement.setInt(2, estoque.getUsuarioId());
            preparedStatement.setInt(3, estoque.getQuantidade());
            preparedStatement.setString(4, estoque.getTipoOperacao());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao criar estoque: " + e.getMessage());
        }
    }

    public List<Estoque> listarEstoques() {
        List<Estoque> estoques = new ArrayList<>();

        try {
            String sql = "SELECT * FROM estoque";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Estoque estoque = new Estoque(
                        resultSet.getInt("id"),
                        resultSet.getInt("produto_id"),
                        resultSet.getInt("usuario_id"),
                        resultSet.getInt("quantidade"),
                        resultSet.getString("tipo_operacao")
                );
                estoques.add(estoque);
            }

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao listar estoques: " + e.getMessage());
        }

        return estoques;
    }

//    public void atualizarEstoquePorId(int id, Estoque novoEstoque) {
//        try {
//            String sql = "UPDATE estoque SET produto_id = ?, usuario_id = ?, quantidade = ?, tipo_operacao = ? WHERE id = ?";
//            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
//            preparedStatement.setInt(1, novoEstoque.getProdutoId());
//            preparedStatement.setInt(2, novoEstoque.getUsuarioId());
//            preparedStatement.setInt(3, novoEstoque.getQuantidade());
//            preparedStatement.setString(4, novoEstoque.getTipoOperacao());
//            preparedStatement.setInt(5, id);
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            System.out.println("Ocorreu um erro ao atualizar estoque por ID: " + e.getMessage());
//        }
//    }
    public void adicionarAoEstoque(int id, int quantidade) {
        atualizarEstoquePorId(id, quantidade);
        System.out.println("Estoque atualizado com sucesso!");
    }

    public void subtrairDoEstoque(int id, int quantidade) {
        atualizarEstoquePorId(id, -quantidade);
        System.out.println("Estoque atualizado com sucesso!");
    }

    private void atualizarEstoquePorId(int id, int quantidade) {
        try {
            String sql = "UPDATE estoque SET quantidade = quantidade + ? WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, quantidade);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao atualizar estoque por ID: " + e.getMessage());
        }
    }

    // Exemplo de exclusão de estoque
    public void deletarEstoque(int id) {
        try {
            String sql = "DELETE FROM estoque WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao excluir estoque por ID: " + e.getMessage());
        }
    }

    public Estoque buscarEstoquePorId(int id) {
        try {
            String sql = "SELECT * FROM estoque WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Estoque estoque = new Estoque(
                        resultSet.getInt("id"),
                        resultSet.getInt("produto_id"),
                        resultSet.getInt("usuario_id"),
                        resultSet.getInt("quantidade"),
                        resultSet.getString("tipo_operacao")
                );
                return estoque;
            }
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao buscar estoque pelo id: " + e.getMessage());
        }

        return null; // Retorne null caso não encontre nenhum estoque com o ID especificado
    }
}
