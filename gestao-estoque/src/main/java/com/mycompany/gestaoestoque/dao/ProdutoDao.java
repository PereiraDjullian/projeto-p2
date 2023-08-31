/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoestoque.dao;

import com.mycompany.gestaoestoque.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author notedj
 */
public class ProdutoDao {

    private final Connection conexao;

    public ProdutoDao(Connection conexao) {
        this.conexao = conexao;
    }

    public void criarProduto(Produto produto) {
        try {
            String sql = "INSERT INTO produto(nome,descricao,preco,quantidade_estoque,data_criacao,usuario_id)VALUES(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, produto.getNome());
            preparedStatement.setString(2, produto.getDescricao());
            preparedStatement.setDouble(3, produto.getPreco());
            preparedStatement.setInt(4, produto.getQuantidadeEstoque());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(produto.getDataCriacao()));
            preparedStatement.setInt(6, produto.getUsuarioId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao criar produto: " + e.getMessage());
        }

    }

    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();

        try {
            String sql = "SELECT * FROM produto";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Produto produto = new Produto(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("descricao"),
                        resultSet.getDouble("preco"),
                        resultSet.getInt("quantidade_estoque"),
                        resultSet.getTimestamp("data_criacao").toLocalDateTime(),
                        resultSet.getInt("usuario_id")
                );
                produtos.add(produto);
            }
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao listar produtos: " + e.getMessage());
        }

        return produtos;
    }

    public void atualizarProduto(Produto produto) {
        try {
            String sql = "UPDATE produto SET nome = ?, descricao = ?, preco = ?, quantidade_estoque = ?, data_criacao = ?, usuario_id = ? WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, produto.getNome());
            preparedStatement.setString(2, produto.getDescricao());
            preparedStatement.setDouble(3, produto.getPreco());
            preparedStatement.setInt(4, produto.getQuantidadeEstoque());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(produto.getDataCriacao()));
            preparedStatement.setInt(6, produto.getUsuarioId());
            preparedStatement.setInt(7, produto.getId());

            preparedStatement.executeUpdate(); // Executa a atualização no banco de dados

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao atualizar produto: " + e.getMessage());
        }
    }

    /**
     *
     * @param id
     * @param novoProduto
     */
    public void atualizarProdutoPorId(int id, Produto novoProduto) {
        try {
            String sql = "UPDATE produto SET nome = ?, descricao = ?, preco = ?, quantidade_estoque = ?, data_criacao = ?, usuario_id = ? WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, novoProduto.getNome());
            preparedStatement.setString(2, novoProduto.getDescricao());
            preparedStatement.setDouble(3, novoProduto.getPreco());
            preparedStatement.setInt(4, novoProduto.getQuantidadeEstoque());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(novoProduto.getDataCriacao()));
            preparedStatement.setInt(6, novoProduto.getUsuarioId());
            preparedStatement.setInt(7, id);

            preparedStatement.executeUpdate(); // Executa a atualização no banco de dados

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao atualizar produto por id: " + e.getMessage());
        }
    }

    public Produto buscarProdutoPorId(int id) {
        try {
            String sql = "SELECT * FROM produto WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Produto produto = new Produto(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("descricao"),
                        resultSet.getDouble("preco"),
                        resultSet.getInt("quantidade_estoque"),
                        resultSet.getTimestamp("data_criacao").toLocalDateTime(),
                        resultSet.getInt("usuario_id")
                );
                return produto;
            }
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao buscar produto pelo id: " + e.getMessage());
        }

        return null; // Retorne null caso não encontre nenhum produto com o ID especificado
    }

    public void deletarProduto(int id) {
        try {
            String sql = "DELETE FROM produto WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao Excluir produto pelo id: " + e.getMessage());
        }
    }



}
