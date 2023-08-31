
import com.mycompany.gestaoestoque.dao.EstoqueDao;
import com.mycompany.gestaoestoque.model.Estoque;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author notedj
 */
public class TesteEstoqueDao {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/armazem_db2";
        String username = "root";
        String password = "root";

        try (Connection conexao = DriverManager.getConnection(jdbcUrl, username, password)) {
            Scanner scanner = new Scanner(System.in);
            EstoqueDao estoqueDao = new EstoqueDao(conexao);

            while (true) {
                System.out.println("\nMenu de CRUD de Estoque");
                System.out.println("1. Criar Estoque");
                System.out.println("2. Listar Estoques");
                System.out.println("3. Adicionar ao Estoque ");
                System.out.println("4. Subtrair do Estoque");
                System.out.println("5. Sair");
                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer

                switch (opcao) {
                    case 1 -> {
                        // Criação de Estoque
                        System.out.print("ID do Produto: ");
                        int produtoId = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer
                        System.out.print("ID do Usuário: ");
                        int usuarioId = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer
                        System.out.print("Quantidade: ");
                        int quantidade = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer
                        System.out.print("Tipo de Operação: ");
                        String tipoOperacao = scanner.nextLine();

                        Estoque novoEstoque = new Estoque(0, produtoId, usuarioId, quantidade, tipoOperacao);
                        estoqueDao.criarEstoque(novoEstoque);
                        System.out.println("Estoque criado com sucesso!");
                        break;
                    }

                    case 2 -> {
                        // Listagem de Estoques
                        System.out.println("Listagem de Estoques:");
                        for (Estoque estoque : estoqueDao.listarEstoques()) {
                            System.out.println(estoque);
                        }
                    }

                    case 3 -> {
                        // Adicionar ao Estoque
                        System.out.print("ID do Estoque: ");
                        int idAdicionar = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer
                        System.out.print("Quantidade a adicionar: ");
                        int quantidadeAdicionar = scanner.nextInt();
                        estoqueDao.adicionarAoEstoque(idAdicionar, quantidadeAdicionar);
                        break;
                    }

                    case 4 -> {
                        // Subtrair do Estoque
                        System.out.print("ID do Estoque: ");
                        int idSubtrair = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer
                        System.out.print("Quantidade a subtrair: ");
                        int quantidadeSubtrair = scanner.nextInt();
                        estoqueDao.subtrairDoEstoque(idSubtrair, quantidadeSubtrair);
                        break;
                    }

                    case 5 -> {
                        // Sair
                        System.out.println("Saindo do programa.");
                        return;
                    }

                    default ->
                        System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }
}
