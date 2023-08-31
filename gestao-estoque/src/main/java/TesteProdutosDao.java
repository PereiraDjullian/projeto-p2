
import com.mycompany.gestaoestoque.dao.ProdutoDao;
import com.mycompany.gestaoestoque.model.Produto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author notedj
 */
public class TesteProdutosDao {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/armazem_db2";
        String username = "root";
        String password = "root";

        try (Connection conexao = DriverManager.getConnection(jdbcUrl, username, password)) {
            Scanner scanner = new Scanner(System.in);
            ProdutoDao produtoDao = new ProdutoDao(conexao);

            while (true) {
                System.out.println("\nMenu de CRUD de Produtos");
                System.out.println("1. Criar Produto");
                System.out.println("2. Listar Produtos");
                System.out.println("3. Atualizar Produto por ID");
                System.out.println("4. Buscar Produto por ID");
                System.out.println("5. Deletar Produto por ID");
                System.out.println("6. Sair");
                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer

                switch (opcao) {
                    case 1 -> {
                        // Criação de Produto associado a um usuário existente
                        System.out.print("ID do Usuário Existente: ");
                        int usuarioIdExistente = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer
                        System.out.print("Nome do Produto: ");
                        String nomeProduto = scanner.nextLine();
                        System.out.print("Descrição do Produto: ");
                        String descricaoProduto = scanner.nextLine();
                        System.out.print("Preço do Produto: ");
                        double precoProduto = scanner.nextDouble();
                        System.out.print("Quantidade em Estoque: ");
                        int quantidadeEstoque = scanner.nextInt();
                        LocalDateTime dataCriacaoProduto = LocalDateTime.now();

                        Produto novoProduto = new Produto(0, nomeProduto, descricaoProduto, precoProduto, quantidadeEstoque, dataCriacaoProduto, usuarioIdExistente);
                        produtoDao.criarProduto(novoProduto);
                        System.out.println("Produto criado com sucesso!");
                        break;
                    }

                    case 2 -> {
                        // Listagem de Produtos
                        System.out.println("Listagem de Produtos:");
                        for (Produto produto : produtoDao.listarProdutos()) {
                            System.out.println(produto);
                        }
                    }

                    case 3 -> {
                        // Atualização de Produto por ID
                        System.out.print("Digite o ID do produto que deseja atualizar: ");
                        int idAtualizar = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer

                        System.out.print("Novo nome do produto: ");
                        String novoNome = scanner.nextLine();

                        System.out.print("Nova descrição do produto: ");
                        String novaDescricao = scanner.nextLine();

                        System.out.print("Novo preço do produto: ");
                        double novoPreco = scanner.nextDouble();

                        System.out.print("Nova quantidade em estoque: ");
                        int novaQuantidadeEstoque = scanner.nextInt();

                        LocalDateTime novaDataCriacao = LocalDateTime.now();

                        Produto produtoAtualizado = new Produto(idAtualizar, novoNome, novaDescricao, novoPreco, novaQuantidadeEstoque, novaDataCriacao, 0);
                        produtoDao.atualizarProdutoPorId(idAtualizar, produtoAtualizado);
                        System.out.println("Produto atualizado com sucesso!");
                        break;
                    }

                    case 4 -> {
                        // Busca de Produto por ID
                        System.out.print("Digite o ID do produto que deseja buscar: ");
                        int idBuscar = scanner.nextInt();
                        Produto produtoEncontrado = produtoDao.buscarProdutoPorId(idBuscar);
                        if (produtoEncontrado != null) {
                            System.out.println("Produto encontrado: " + produtoEncontrado);
                        } else {
                            System.out.println("Nenhum produto encontrado com o ID especificado.");
                        }
                    }

                    case 5 -> {
                        // Exclusão de Produto por ID
                        System.out.print("Digite o ID do produto que deseja excluir: ");
                        int idExcluir = scanner.nextInt();
                        produtoDao.deletarProduto(idExcluir);
                        System.out.println("Produto excluído com sucesso!");
                    }

                    case 6 -> {
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
