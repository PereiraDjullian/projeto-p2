/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoestoque.dao;

import com.mycompany.gestaoestoque.model.Pessoa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author notedj
 */
public class PessoaDao {

    private final Connection conexao;

    public PessoaDao(Connection conexao) {
        this.conexao = conexao;
    }

    public void criarPessoa(Pessoa pessoa) throws SQLException {
        try {
            String sql = "INSERT INTO pessoa(nome,email,cpf,data_criacao) VALUES(?,?,?,?)";

            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
                preparedStatement.setString(1, pessoa.getNome());
                preparedStatement.setString(2, pessoa.getEmail());
                preparedStatement.setString(3, pessoa.getCpf());
                preparedStatement.setTimestamp(4, Timestamp.valueOf(pessoa.getDataCriacao()));
                preparedStatement.executeUpdate(); // Executa a inserção no banco de dados
                // Fecha o PreparedStatement
            }

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao criar pessoas: " + e.getMessage());
        }
    }

    public List<Pessoa> listarPessoas() {
        List<Pessoa> pessoas = new ArrayList<>();

        try {
            String sql = "SELECT * FROM pessoa ";
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Pessoa pessoa = new Pessoa(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("email"),
                        resultSet.getString("cpf"),
                        resultSet.getTimestamp("data_criacao").toLocalDateTime()
                );
                pessoas.add(pessoa);

            }
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao listar pessoas: " + e.getMessage());
        }

        return pessoas; // Retorne a lista de pessoas
    }

    public void atualizarPessoaPorNome(String nome, Pessoa novaPessoa) {
        try {
            String sql = "UPDATE pessoa SET nome = ?, email = ?, cpf = ? WHERE nome = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, novaPessoa.getNome());
            preparedStatement.setString(2, novaPessoa.getEmail());
            preparedStatement.setString(3, novaPessoa.getCpf());
            preparedStatement.setString(4, nome);

            preparedStatement.executeUpdate(); // Executa a atualização no banco de dados

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao atualizar pessoas por nome: " + e.getMessage());
        }
    }

    public Pessoa buscarPessoaPorId(int id) {
        try {
            String sql = "SELECT * FROM pessoa WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Pessoa pessoa = new Pessoa(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("email"),
                        resultSet.getString("cpf"),
                        resultSet.getTimestamp("data_criacao").toLocalDateTime()
                );
                return pessoa;
            }
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao buscar pessoas pelo id: " + e.getMessage());
        }

        return null; // Retorne null caso não encontre nenhuma pessoa com o ID especificado
    }

    public void deletarPessoa(int id) {
        try {
            String sql = "DELETE FROM pessoa WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao Excluir pessoas pelo id: " + e.getMessage());
        }
    }

//    public Pessoa buscarPessoaPorId(int id) {
//        try {
//            String sql = "SELECT * FROM pessoa WHERE id = ?";
//            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                Pessoa pessoa = new Pessoa(
//                        resultSet.getInt("id"),
//                        resultSet.getString("nome"),
//                        resultSet.getString("email"),
//                        resultSet.getString("cpf"),
//                        resultSet.getTimestamp("data_criacao").toLocalDateTime()
//                );
//                return pessoa;
//            }
//        } catch (SQLException e) {
//            System.out.println("Ocorreu um erro ao buscar pessoas pelo id: " + e.getMessage());
//        }
//
//        return null; // Retorne null caso não encontre nenhuma pessoa
//    }
//    public void atualizarPessoas(Pessoa pessoa) {
//        try {
//            String sql = "UPDATE pessoa SET nome = ?, email = ?, cpf = ? WHERE id = ?";
//            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
//
//            // Define os valores dos parâmetros na instrução SQL
//            preparedStatement.setString(1, pessoa.getNome());
//            preparedStatement.setString(2, pessoa.getEmail());
//            preparedStatement.setString(3, pessoa.getCpf());
//            preparedStatement.setInt(4, pessoa.getId());
//
//            preparedStatement.executeUpdate(); // Executa a atualização no banco de dados
//
//        } catch (SQLException e) {
//            System.out.println("Ocorreu um erro ao atualizar pessoas: " + e.getMessage());
//        }  testado pegando
//    }
//    public void criarPessoa(Pessoa pessoa) throws SQLException {
//
//        try {
//            String sql = "INSERT INTO pessoa(nome,email,cpf,data_criacao)VALUES(?,?,?,?)";
//
//            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
//
//            
//            preparedStatement.setString(1, pessoa.getNome());
//            preparedStatement.setString(2, pessoa.getEmail());
//            preparedStatement.setString(3, pessoa.getCpf());
//            preparedStatement.setTimestamp(4, Timestamp.valueOf(pessoa.getDataCriacao()));
//            
//        } catch (SQLException e) {
//            System.out.println("Ocorreu um erro ao criar pessoas: " + e.getMessage());
//
//        }
//
//    }
//    public List<Pessoa> listarPessoas() {
//        List<Pessoa> pessoas = new ArrayList<>();
//
//        try {
//            String sql = "SELECT * FROM pessoa ";
//            Statement statement = conexao.createStatement();
//            ResultSet resultSet = statement.executeQuery(sql);
//
//            while (resultSet.next()) {
//                Pessoa pessoa = new Pessoa(0, sql, sql, sql, LocalDateTime.now());
//                pessoa.setId(resultSet.getInt("id"));
//                pessoa.setNome(resultSet.getString("nome"));
//                pessoa.setEmail(resultSet.getString("email"));
//                pessoa.setCpf(resultSet.getString("cpf"));
//                pessoa.setDataCriacao(resultSet.getTimestamp("data_criacao").toLocalDateTime());
//                pessoas.add(pessoa);
//            }
//        } catch (SQLException e) {
//            System.out.println("Ocorreu um erro ao listar pessoas: " + e.getMessage());
//        }
//
//        return pessoas; // Retorne a lista de pessoas
//    }
//    public List<Pessoa> listarPessoas() {
//        List<Pessoa> pessoas = new ArrayList<>();
//
//        try {
//
//            String sql = "SELECT * FROM pessoa ";
//            Statement statement = conexao.createStatement();
//            ResultSet resultSet = statement.executeQuery(sql);
//
//            while (ResultSet.next()) {
//                Pessoa pessoa = new Pessoa();
//                
//                    
//                pessoa.setId(resultSet.getInt("id"));
//                pessoa.setNome(resultSet.getString("nome"));
//                pessoa.setEmail(resultSet.getString("email"));
//                pessoa.setCpf(resultSet.getString("cpf"));
//                pessoa.setDataCriacao(resultSet.getTimestamp("data_criacao"));
//                pessoas.add(pessoa);
//
//            }
//
//        } catch (SQLException e) {
//
//            System.out.println("Ocorreu um erro ao listar pessoas: " + e.getMessage());
//
//        }
//    }
//    public void atualizarPessoas(Pessoa pessoa) {
//        try {
//            String sql = "UPDATE pessoa SET nome = ?,email = ? ,cpf = ?,WHERE id = ?";
//            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
//            preparedStatement.setString(2, pessoa.getNome());
//            preparedStatement.setString(3, pessoa.getEmail());
//            preparedStatement.setString(4, pessoa.getCpf());
//            preparedStatement.setInt(5, pessoa.getId)
//            );
//               
//               preparedStatement.executeQuery(sql);
//
//        } catch (SQLException e) {
//
//            System.out.println("Ocorreu um erro ao atualizar pessoas: " + e.getMessage());
//
//        }
//    }
//    public void atualizarPessoas(Pessoa pessoa) {
//        try {
//            String sql = "UPDATE pessoa SET nome = ?, email = ?, cpf = ? WHERE id = ?";
//            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
//            preparedStatement.setString(1, pessoa.getNome());
//            preparedStatement.setString(2, pessoa.getEmail());
//            preparedStatement.setString(3, pessoa.getCpf());
//            preparedStatement.setInt(4, pessoa.getId());
//
//            preparedStatement.executeUpdate();  // Use executeUpdate() para instruções de atualização
//
//        } catch (SQLException e) {
//            System.out.println("Ocorreu um erro ao atualizar pessoas: " + e.getMessage());
//        }
//    }
//    public Pessoa buscarPessoaPorId(int id) {
//        try {
//            String sql = "SELECT * FROM pessoa WHERE id = ?";
//            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                Pessoa pessoa = new Pessoa();
//                pessoa.setId(resultSet.getInt("id"));
//                pessoa.setNome(resultSet.getString("nome"));
//                pessoa.setEmail(resultSet.getString("email"));
//                pessoa.setCpf(resultSet.getString("cpf"));
//                pessoa.setDataCriacao(resultSet.getTimestamp("data_criacao"));
//                return pessoa;
//
//            }
//        } catch (SQLException e) {
//            System.out.println("Ocorreu um erro ao buscar pessoas pelo id: " + e.getMessage());
//
//        }
//
//    }
//    public Pessoa buscarPessoaPorId(int id) {
//        try {
//            String sql = "SELECT * FROM pessoa WHERE id = ?";
//            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                Pessoa pessoa = new Pessoa(id, sql, sql, sql, LocalDateTime.now());
//                pessoa.setId(resultSet.getInt("id"));
//                pessoa.setNome(resultSet.getString("nome"));
//                pessoa.setEmail(resultSet.getString("email"));
//                pessoa.setCpf(resultSet.getString("cpf"));
//                //pessoa.setDataCriacao(resultSet.getTimestamp("data_criacao"));
//                pessoa.setDataCriacao(resultSet.getTimestamp("data_criacao").toLocalDateTime());
//                return pessoa;
//            }
//        } catch (SQLException e) {
//            System.out.println("Ocorreu um erro ao buscar pessoas pelo id: " + e.getMessage());
//        }
//
//        return null;  // Retorne null em caso de exceção
//    }

    public Pessoa buscarPessoaPorNome(String nomeParaAtualizar) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
