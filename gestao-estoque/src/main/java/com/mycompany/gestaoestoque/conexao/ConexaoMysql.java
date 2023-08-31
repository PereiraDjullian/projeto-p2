/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoestoque.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author notedj
 */
public class ConexaoMysql implements Conexao {
    
    
    private final String usuario = "root";
    private final String senha = "root";
    private final String url ="jdbc:mysql://localhost:3306/armazem_db2";
    private Connection conectar;

    
    @Override
    public Connection obterConexao() throws SQLException {
        if (conectar==null){
            conectar = DriverManager.getConnection(url, usuario, senha);
        }
        return conectar;
        
    }
    
}
