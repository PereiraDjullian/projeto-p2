/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste.controller;

import com.mycompany.gestaoestoque.controller.ProdutoController;
import com.mycompany.gestaoestoque.dao.ProdutoDao;
import com.mycompany.gestaoestoque.model.Produto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author notedj
 */
public class TesteProdutoController {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/armazem_db2";
        String username = "root";
        String password = "root";

        try (Connection conexao = DriverManager.getConnection(jdbcUrl, username, password)) {
            ProdutoDao produtoDao = new ProdutoDao(conexao);
            ProdutoController produtoController = new ProdutoController(produtoDao);

            Scanner scanner = new Scanner(System.in);

            int opcao;
            do {
                System.out.println("\nMenu CRUD de Produtos");
                System.out.println("1. Criar Produto");
                System.out.println("2. Listar Produtos");
                System.out.println("3. Atualizar Produto por ID");
                System.out.println("4. Buscar Produto por ID");
                System.out.println("5. Excluir Produto por ID");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha após a leitura do número

                switch (opcao) {
                    case 1 -> criarProduto(scanner, produtoController);
                    case 2 -> listarProdutos(produtoController);
                    case 3 -> atualizarProdutoPorId(scanner, produtoController);
                    case 4 -> buscarProdutoPorId(scanner, produtoController);
                    case 5 -> excluirProdutoPorId(scanner, produtoController);
                    case 0 -> System.out.println("Encerrando o programa.");
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }

            } while (opcao != 0);

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }

    private static void criarProduto(Scanner scanner, ProdutoController produtoController) throws SQLException {
        System.out.println("Cadastro de Produto");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Preço: ");
        double preco = scanner.nextDouble();
        scanner.nextLine(); // Consumir a nova linha após a leitura do número

        produtoController.criarProduto(nome, preco);
        System.out.println("Produto criado com sucesso!");
    }

    private static void listarProdutos(ProdutoController produtoController) {
        List<Produto> produtosListados = produtoController.listarProdutos();
        System.out.println("Lista de Produtos:");
        for (Produto produto : produtosListados) {
            System.out.println(produto);
        }
    }

    private static void atualizarProdutoPorId(Scanner scanner, ProdutoController produtoController) {
        System.out.println("Atualização de Produto por ID");
        System.out.print("ID do produto a ser atualizado: ");
        int idParaAtualizar = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha após a leitura do número

        System.out.print("Novo Nome: ");
        String novoNome = scanner.nextLine();

        System.out.print("Novo Preço: ");
        double novoPreco = scanner.nextDouble();
        scanner.nextLine(); // Consumir a nova linha após a leitura do número

        produtoController.atualizarProdutoPorId(idParaAtualizar, novoNome, novoPreco);
        System.out.println("Produto atualizado com sucesso!");
    }

    private static void buscarProdutoPorId(Scanner scanner, ProdutoController produtoController) {
        System.out.println("Buscar Produto por ID");
        System.out.print("ID do produto a ser buscado: ");
        int idParaBuscar = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha após a leitura do número

        Produto produtoEncontrado = produtoController.buscarProdutoPorId(idParaBuscar);

        if (produtoEncontrado != null) {
            System.out.println("Produto encontrado:");
            System.out.println(produtoEncontrado);
        } else {
            System.out.println("Nenhum produto encontrado com o ID especificado.");
        }
    }

    private static void excluirProdutoPorId(Scanner scanner, ProdutoController produtoController) {
        System.out.println("Excluir Produto por ID");
        System.out.print("ID do produto a ser excluído: ");
        int idParaExcluir = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha após a leitura do número

        produtoController.excluirProdutoPorId(idParaExcluir);
        System.out.println("Produto excluído com sucesso!");

        // Listar Produtos
        listarProdutos(produtoController);
    }

}
