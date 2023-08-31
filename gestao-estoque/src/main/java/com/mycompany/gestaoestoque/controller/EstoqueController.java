/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoestoque.controller;

import com.mycompany.gestaoestoque.dao.EstoqueDao;
import com.mycompany.gestaoestoque.model.Estoque;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author notedj
 */
public class EstoqueController {

    private final EstoqueDao estoqueDao;

    public EstoqueController(EstoqueDao estoqueDao) {
        this.estoqueDao = estoqueDao;
    }

    public void criarEstoque(int produtoId, int usuarioId, int quantidade, String tipoOperacao) throws SQLException {
        Estoque novoEstoque = new Estoque(0, produtoId, usuarioId, quantidade, tipoOperacao);
        estoqueDao.criarEstoque(novoEstoque);
    }

    public List<Estoque> listarEstoques() {
        return estoqueDao.listarEstoques();
    }

    public void adicionarAoEstoque(int id, int quantidade) {
        estoqueDao.adicionarAoEstoque(id, quantidade);
    }

    public void subtrairDoEstoque(int id, int quantidade) {
        estoqueDao.subtrairDoEstoque(id, quantidade);
    }

    public Estoque buscarEstoquePorId(int id) {
        return estoqueDao.buscarEstoquePorId(id);
    }

    public void excluirEstoquePorId(int id) {
        estoqueDao.deletarEstoque(id);
    }
}
