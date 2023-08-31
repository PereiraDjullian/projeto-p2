/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoestoque.controller;

import com.mycompany.gestaoestoque.dao.ProdutoDao;
import com.mycompany.gestaoestoque.model.Produto;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author notedj
 */
public class ProdutoController {
    
    private final ProdutoDao produtoDao;

    public ProdutoController(ProdutoDao produtoDao) {
        this.produtoDao = produtoDao;
    }

    public void criarProduto(String nome, double preco) throws SQLException {
        Produto novoProduto = new Produto(0, nome, nome, preco, 0, LocalDateTime.now(), 0);
        produtoDao.criarProduto(novoProduto);
    }

    public List<Produto> listarProdutos() {
        return produtoDao.listarProdutos();
    }

    public void atualizarProdutoPorId(int id, String novoNome, double novoPreco) {
        Produto produtoEncontrado = produtoDao.buscarProdutoPorId(id);
        if (produtoEncontrado != null) {
            Produto novoProduto = new Produto(id, novoNome, novoNome, novoPreco, id, LocalDateTime.now(), id);
            produtoDao.atualizarProdutoPorId(id, novoProduto);
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public Produto buscarProdutoPorId(int id) {
        return produtoDao.buscarProdutoPorId(id);
    }

    public void excluirProdutoPorId(int id) {
        produtoDao.deletarProduto(id);
    }
    
}
