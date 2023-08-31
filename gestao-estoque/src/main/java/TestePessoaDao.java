import com.mycompany.gestaoestoque.dao.PessoaDao;
import com.mycompany.gestaoestoque.model.Pessoa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class TestePessoaDao {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/armazem_db2";
        String username = "root";
        String password = "root";

        try (Connection conexao = DriverManager.getConnection(jdbcUrl, username, password)) {
            PessoaDao pessoaDao = new PessoaDao(conexao);
            Scanner scanner = new Scanner(System.in);

            int opcao;
            do {
                System.out.println("\nMenu CRUD de Pessoas");
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
                    case 1 -> criarPessoa(scanner, pessoaDao);
                    case 2 -> listarPessoas(pessoaDao);
                    case 3 -> atualizarPessoaPorNome(scanner, pessoaDao);
                    case 4 -> buscarPessoaPorId(scanner, pessoaDao);
                    case 5 -> excluirPessoaPorId(scanner, pessoaDao);
                    case 0 -> System.out.println("Encerrando o programa.");
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }

            } while (opcao != 0);

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }

    private static void criarPessoa(Scanner scanner, PessoaDao pessoaDao) throws SQLException {
        System.out.println("Cadastro de Pessoa");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        LocalDateTime dataCriacao = LocalDateTime.now();
        Pessoa pessoa = new Pessoa(0, nome, email, cpf, dataCriacao);

        pessoaDao.criarPessoa(pessoa);
        System.out.println("Pessoa criada com sucesso!");
    }

    private static void listarPessoas(PessoaDao pessoaDao) {
        List<Pessoa> pessoasListadas = pessoaDao.listarPessoas();
        System.out.println("Lista de Pessoas:");
        for (Pessoa p : pessoasListadas) {
            System.out.println(p);
        }
    }

    private static void atualizarPessoaPorNome(Scanner scanner, PessoaDao pessoaDao) {
        System.out.println("Atualização de Pessoa por Nome");
        System.out.print("Nome da pessoa a ser atualizada: ");
        String nomeParaAtualizar = scanner.nextLine();

        System.out.print("Novo nome: ");
        String novoNome = scanner.nextLine();

        System.out.print("Novo email: ");
        String novoEmail = scanner.nextLine();

        System.out.print("Novo CPF: ");
        String novoCpf = scanner.nextLine();

        Pessoa novaPessoa = new Pessoa(0, novoNome, novoEmail, novoCpf, LocalDateTime.now());

        pessoaDao.atualizarPessoaPorNome(nomeParaAtualizar, novaPessoa);
        System.out.println("Pessoa atualizada com sucesso!");
    }

    private static void buscarPessoaPorId(Scanner scanner, PessoaDao pessoaDao) {
        System.out.println("Buscar Pessoa por ID");
        System.out.print("ID da pessoa a ser buscada: ");
        int idParaBuscar = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha após a leitura do número

        Pessoa pessoaEncontrada = pessoaDao.buscarPessoaPorId(idParaBuscar);

        if (pessoaEncontrada != null) {
            System.out.println("Pessoa encontrada:");
            System.out.println(pessoaEncontrada);
        } else {
            System.out.println("Nenhuma pessoa encontrada com o ID especificado.");
        }
    }

    private static void excluirPessoaPorId(Scanner scanner, PessoaDao pessoaDao) {
        System.out.println("Excluir Pessoa por ID");
        System.out.print("ID da pessoa a ser excluída: ");
        int idParaExcluir = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha após a leitura do número

        pessoaDao.deletarPessoa(idParaExcluir);
        System.out.println("Pessoa excluída com sucesso!");

        // Listar Pessoas
        listarPessoas(pessoaDao);
    }
}