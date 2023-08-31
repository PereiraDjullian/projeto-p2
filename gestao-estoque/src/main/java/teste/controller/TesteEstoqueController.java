/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste.controller;

import com.mycompany.gestaoestoque.controller.EstoqueController;
import com.mycompany.gestaoestoque.dao.EstoqueDao;
import com.mycompany.gestaoestoque.model.Estoque;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author notedj
 */
public class TesteEstoqueController {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/armazem_db2";
        String username = "root";
        String password = "root";

        try (Connection conexao = DriverManager.getConnection(jdbcUrl, username, password)) {
            EstoqueDao estoqueDao = new EstoqueDao(conexao);
            EstoqueController estoqueController = new EstoqueController(estoqueDao);

            Scanner scanner = new Scanner(System.in);

            int opcao;
            do {
                System.out.println("\nMenu CRUD de Estoque");
                System.out.println("1. Criar Estoque");
                System.out.println("2. Listar Estoques");
                System.out.println("3. Adicionar ao Estoque");
                System.out.println("4. Subtrair do Estoque");
                System.out.println("5. Buscar Estoque por ID");
                System.out.println("6. Excluir Estoque por ID");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha após a leitura do número

                switch (opcao) {
                    case 1:
                        criarEstoque(scanner, estoqueController);
                        break;
                    case 2:
                        listarEstoques(estoqueController);
                        break;
                    case 3:
                        adicionarAoEstoque(scanner, estoqueController);
                        break;
                    case 4:
                        subtrairDoEstoque(scanner, estoqueController);
                        break;
                    case 5:
                        buscarEstoquePorId(scanner, estoqueController);
                        break;
                    case 6:
                        excluirEstoquePorId(scanner, estoqueController);
                        break;
                    case 0:
                        System.out.println("Encerrando o programa.");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }

            } while (opcao != 0);

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }

    private static void criarEstoque(Scanner scanner, EstoqueController estoqueController) throws SQLException {
        System.out.println("Cadastro de Estoque");
        System.out.print("ID do Produto: ");
        int produtoId = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha após a leitura do número

        System.out.print("ID do Usuário: ");
        int usuarioId = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha após a leitura do número

        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha após a leitura do número

        System.out.print("Tipo de Operação: ");
        String tipoOperacao = scanner.nextLine();

        estoqueController.criarEstoque(produtoId, usuarioId, quantidade, tipoOperacao);
        System.out.println("Estoque criado com sucesso!");
    }

    private static void listarEstoques(EstoqueController estoqueController) {
        List<Estoque> estoquesListados = estoqueController.listarEstoques();
        System.out.println("Lista de Estoques:");
        for (Estoque estoque : estoquesListados) {
            System.out.println(estoque);
        }
    }

    private static void adicionarAoEstoque(Scanner scanner, EstoqueController estoqueController) {
        System.out.println("Adicionar ao Estoque");
        System.out.print("ID do Estoque: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha após a leitura do número

        System.out.print("Quantidade a ser adicionada: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha após a leitura do número

        estoqueController.adicionarAoEstoque(id, quantidade);
        System.out.println("Estoque atualizado com sucesso!");
    }

    private static void subtrairDoEstoque(Scanner scanner, EstoqueController estoqueController) {
        System.out.println("Subtrair do Estoque");
        System.out.print("ID do Estoque: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha após a leitura do número

        System.out.print("Quantidade a ser subtraída: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha após a leitura do número

        estoqueController.subtrairDoEstoque(id, quantidade);
        System.out.println("Estoque atualizado com sucesso!");
    }

    private static void buscarEstoquePorId(Scanner scanner, EstoqueController estoqueController) {
        System.out.println("Buscar Estoque por ID");
        System.out.print("ID do estoque a ser buscado: ");
        int idParaBuscar = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha após a leitura do número

        Estoque estoqueEncontrado = estoqueController.buscarEstoquePorId(idParaBuscar);

        if (estoqueEncontrado != null) {
            System.out.println("Estoque encontrado:");
            System.out.println(estoqueEncontrado);
        } else {
            System.out.println("Nenhum estoque encontrado com o ID especificado.");
        }
    }

    private static void excluirEstoquePorId(Scanner scanner, EstoqueController estoqueController) {
        System.out.println("Excluir Estoque por ID");
        System.out.print("ID do estoque a ser excluído: ");
        int idParaExcluir = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha após a leitura do número

        estoqueController.excluirEstoquePorId(idParaExcluir);
        System.out.println("Estoque excluído com sucesso!");

        // Listar Estoques
        listarEstoques(estoqueController);
    }

}
