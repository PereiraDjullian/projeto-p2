/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste.controller;


import com.mycompany.gestaoestoque.controller.PessoaController;
import com.mycompany.gestaoestoque.dao.PessoaDao;
import com.mycompany.gestaoestoque.model.Pessoa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author notedj
 */
public class TestePessoaController {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/armazem_db2";
        String username = "root";
        String password = "root";

        try (Connection conexao = DriverManager.getConnection(jdbcUrl, username, password)) {
            PessoaDao pessoaDao = new PessoaDao(conexao);
            PessoaController pessoaController = new PessoaController(pessoaDao);
            Scanner scanner = new Scanner(System.in);

            int opcao;
            do {
                System.out.println("\nMenu de Teste do Pessoa Controller");
                System.out.println("1. Criar Pessoa");
                System.out.println("2. Listar Pessoas");
                System.out.println("3. Atualizar Pessoa por Nome");
                System.out.println("4. Buscar Pessoa por ID");
                System.out.println("5. Excluir Pessoa por ID");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha após a leitura do número

                switch (opcao) {
                    case 1 -> {
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();
                        pessoaController.criarPessoa(nome, email, cpf);
                    }
                    case 2 -> {
                        List<Pessoa> pessoasListadas = pessoaController.listarPessoas();
                        System.out.println("Lista de Pessoas:");
                        for (Pessoa pessoa : pessoasListadas) {
                            System.out.println(pessoa);
                        }
                    }
                    case 3 -> {
                        System.out.print("Nome da pessoa a ser atualizada: ");
                        String nomeParaAtualizar = scanner.nextLine();
                        System.out.print("Novo nome: ");
                        String novoNome = scanner.nextLine();
                        System.out.print("Novo email: ");
                        String novoEmail = scanner.nextLine();
                        System.out.print("Novo CPF: ");
                        String novoCpf = scanner.nextLine();
                        pessoaController.atualizarPessoaPorNome(nomeParaAtualizar, novoNome, novoEmail, novoCpf);
                    }
                    case 4 -> {
                        System.out.print("ID da pessoa a ser buscada: ");
                        int idParaBuscar = scanner.nextInt();
                        scanner.nextLine(); // Consumir a nova linha após a leitura do número
                        Pessoa pessoaEncontrada = pessoaController.buscarPessoaPorId(idParaBuscar);
                        if (pessoaEncontrada != null) {
                            System.out.println("Pessoa encontrada: " + pessoaEncontrada);
                        } else {
                            System.out.println("Nenhuma pessoa encontrada com o ID especificado.");
                        }
                    }
                    case 5 -> {
                        System.out.print("ID da pessoa a ser excluída: ");
                        int idParaExcluir = scanner.nextInt();
                        scanner.nextLine(); // Consumir a nova linha após a leitura do número
                        pessoaController.excluirPessoaPorId(idParaExcluir);
                        System.out.println("Pessoa excluída com sucesso!");
                    }
                    case 0 -> System.out.println("Encerrando o programa.");
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }

            } while (opcao != 0);

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }

}
