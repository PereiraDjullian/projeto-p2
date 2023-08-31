/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste.controller;

import com.mycompany.gestaoestoque.controller.UsuarioController;
import com.mycompany.gestaoestoque.dao.UsuarioDao;
import com.mycompany.gestaoestoque.model.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author notedj
 */
public class TesteUsuarioController {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/armazem_db2";
        String username = "root";
        String password = "root";

        try (Connection conexao = DriverManager.getConnection(jdbcUrl, username, password)) {
            UsuarioDao usuarioDao = new UsuarioDao(conexao);
            UsuarioController usuarioController = new UsuarioController(usuarioDao);

            Scanner scanner = new Scanner(System.in);

            int opcao;
            do {
                System.out.println("\nMenu CRUD de Usuários");
                System.out.println("1. Criar Usuário");
                System.out.println("2. Listar Usuários");
                System.out.println("3. Atualizar Usuário por ID");
                System.out.println("4. Buscar Usuário por ID");
                System.out.println("5. Excluir Usuário por ID");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha após a leitura do número

                switch (opcao) {
                    case 1 -> criarUsuario(scanner, usuarioController);
                    case 2 -> listarUsuarios(usuarioController);
                    case 3 -> atualizarUsuarioPorId(scanner, usuarioController);
                    case 4 -> buscarUsuarioPorId(scanner, usuarioController);
                    case 5 -> excluirUsuarioPorId(scanner, usuarioController);
                    case 0 -> System.out.println("Encerrando o programa.");
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }

            } while (opcao != 0);

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }

    private static void criarUsuario(Scanner scanner, UsuarioController usuarioController) throws SQLException {
        System.out.println("Cadastro de Usuário");
        System.out.print("ID da Pessoa: ");
        int pessoaId = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha após a leitura do número

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        System.out.print("Tipo de Usuário: ");
        String tipoUsuario = scanner.nextLine();

        usuarioController.criarUsuario(pessoaId, senha, tipoUsuario);
        System.out.println("Usuário criado com sucesso!");
    }

    private static void listarUsuarios(UsuarioController usuarioController) {
        List<Usuario> usuariosListados = usuarioController.listarUsuarios();
        System.out.println("Lista de Usuários:");
        for (Usuario usuario : usuariosListados) {
            System.out.println(usuario);
        }
    }

    private static void atualizarUsuarioPorId(Scanner scanner, UsuarioController usuarioController) {
        System.out.println("Atualização de Usuário por ID");
        System.out.print("ID do usuário a ser atualizado: ");
        int idParaAtualizar = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha após a leitura do número

        System.out.print("Nova Senha: ");
        String novaSenha = scanner.nextLine();

        System.out.print("Novo Tipo de Usuário: ");
        String novoTipoUsuario = scanner.nextLine();

        usuarioController.atualizarUsuarioPorId(idParaAtualizar, novaSenha, novoTipoUsuario);
        System.out.println("Usuário atualizado com sucesso!");
    }

    private static void buscarUsuarioPorId(Scanner scanner, UsuarioController usuarioController) {
        System.out.println("Buscar Usuário por ID");
        System.out.print("ID do usuário a ser buscado: ");
        int idParaBuscar = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha após a leitura do número

        Usuario usuarioEncontrado = usuarioController.buscarUsuarioPorId(idParaBuscar);

        if (usuarioEncontrado != null) {
            System.out.println("Usuário encontrado:");
            System.out.println(usuarioEncontrado);
        } else {
            System.out.println("Nenhum usuário encontrado com o ID especificado.");
        }
    }

    private static void excluirUsuarioPorId(Scanner scanner, UsuarioController usuarioController) {
        System.out.println("Excluir Usuário por ID");
        System.out.print("ID do usuário a ser excluído: ");
        int idParaExcluir = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha após a leitura do número

        usuarioController.excluirUsuarioPorId(idParaExcluir);
        System.out.println("Usuário excluído com sucesso!");

        // Listar Usuários
        listarUsuarios(usuarioController);
    }
}
