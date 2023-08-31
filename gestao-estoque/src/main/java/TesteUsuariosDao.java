/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author notedj
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.time.LocalDateTime;
import com.mycompany.gestaoestoque.dao.UsuarioDao;
import com.mycompany.gestaoestoque.model.Usuario;

public class TesteUsuariosDao {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/armazem_db2";
        String username = "root";
        String password = "root";

        try (Connection conexao = DriverManager.getConnection(jdbcUrl, username, password)) {
            Scanner scanner = new Scanner(System.in);
            UsuarioDao usuarioDao = new UsuarioDao(conexao);

            while (true) {
                System.out.println("\nMenu de CRUD de Usuários");
                System.out.println("1. Criar Usuário");
                System.out.println("2. Listar Usuários");
                System.out.println("3. Atualizar Usuário por ID");
                System.out.println("4. Buscar Usuário por ID");
                System.out.println("5. Deletar Usuário por ID");
                System.out.println("6. Sair");
                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer

                switch (opcao) {
                    case 1 -> {
                        // Criação de Usuário associado a uma pessoa existente
                        System.out.print("ID da Pessoa Existente: ");
                        int pessoaIdExistente = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer
                        System.out.print("Senha: ");
                        String senha = scanner.nextLine();
                        System.out.print("Tipo de Usuário: ");
                        String tipoUsuario = scanner.nextLine();
                        LocalDateTime dataCriacao = LocalDateTime.now();
                        Usuario novoUsuario = new Usuario(senha, tipoUsuario, pessoaIdExistente, "Nome do Usuário", "usuario@example.com", "123456789", dataCriacao);
                        usuarioDao.criarUsuarioPorId(pessoaIdExistente, novoUsuario);
                        System.out.println("Usuário criado com sucesso!");
                        break;
                    }

                    case 2 -> {
                        // Listagem de Usuários
                        System.out.println("Listagem de Usuários:");
                        for (Usuario usuario : usuarioDao.listarUsuarios()) {
                            System.out.println(usuario);
                        }
                    }

                    case 3 -> {
                        // Atualização de Usuário por ID
                        System.out.print("Digite o ID do usuário que deseja atualizar: ");
                        int idAtualizar = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer

                        System.out.print("Nova senha: ");
                        String novaSenha = scanner.nextLine();

                        System.out.print("Novo tipo de usuário: ");
                        String novoTipoUsuario = scanner.nextLine();

                        LocalDateTime novaDataCriacao = LocalDateTime.now();

                        Usuario usuarioAtualizado = new Usuario(novaSenha, novoTipoUsuario, 0, "", "", "", novaDataCriacao);
                        usuarioDao.atualizarUsuarioPorId(idAtualizar, usuarioAtualizado);
                        System.out.println("Usuário atualizado com sucesso!");
                        break;
                    }

                    case 4 -> {
                        // Busca de Usuário por ID
                        System.out.print("Digite o ID do usuário que deseja buscar: ");
                        int idBuscar = scanner.nextInt();
                        Usuario usuarioEncontrado = usuarioDao.buscarUsuarioPorId(idBuscar);
                        if (usuarioEncontrado != null) {
                            System.out.println("Usuário encontrado: " + usuarioEncontrado);
                        } else {
                            System.out.println("Nenhum usuário encontrado com o ID especificado.");
                        }
                    }

                    case 5 -> {
                        // Exclusão de Usuário por ID
                        System.out.print("Digite o ID do usuário que deseja excluir: ");
                        int idExcluir = scanner.nextInt();
                        usuarioDao.deletarUsuario(idExcluir);
                        System.out.println("Usuário excluído com sucesso!");
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
