/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoestoque.conexao;


import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author notedj
 */
public class TesteBancoConexao {
    
        public static void main(String[] args) {
        try {
            Connection connection = BancoConexao.getConnection();
            System.out.println("Conexão com o banco de dados estabelecida.");
            connection.close();
            System.out.println("Conexão fechada.");
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
    
}
