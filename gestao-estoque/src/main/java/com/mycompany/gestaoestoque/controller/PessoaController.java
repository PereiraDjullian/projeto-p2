/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoestoque.controller;


import com.mycompany.gestaoestoque.dao.PessoaDao;
import com.mycompany.gestaoestoque.model.Pessoa;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author notedj
 */
public class PessoaController {

    private final PessoaDao pessoaDao;

    public PessoaController(PessoaDao pessoaDao) {
        this.pessoaDao = pessoaDao;
    }

    public void criarPessoa(String nome, String email, String cpf) throws SQLException {
        LocalDateTime dataCriacao = LocalDateTime.now();
        Pessoa pessoa = new Pessoa(0, nome, email, cpf, dataCriacao);
        pessoaDao.criarPessoa(pessoa);

    }

    public List<Pessoa> listarPessoas() {
        return pessoaDao.listarPessoas();
    }

    public void atualizarPessoaPorNome(String nomeParaAtualizar, String novoNome, String novoEmail, String novoCpf) {
        Pessoa pessoaEncontrada = pessoaDao.buscarPessoaPorNome(nomeParaAtualizar);
        if (pessoaEncontrada != null) {
            Pessoa novaPessoa = new Pessoa(pessoaEncontrada.getId(), novoNome, novoEmail, novoCpf, LocalDateTime.now());
            pessoaDao.atualizarPessoaPorNome(nomeParaAtualizar, novaPessoa);
        } else {
            System.out.println("Pessoa não encontrada.");
        }
    }

    public Pessoa buscarPessoaPorId(int id) {
        return pessoaDao.buscarPessoaPorId(id);
    }

    public void excluirPessoaPorId(int id) {
        pessoaDao.deletarPessoa(id);
    }

}
