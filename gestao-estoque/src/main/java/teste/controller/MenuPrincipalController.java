/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste.controller;

import java.util.Scanner;

/**
 *
 * @author notedj
 */
public class MenuPrincipalController {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu Principal");
            System.out.println("1. CRUD de Pessoas");
            System.out.println("2. CRUD de Usuários");
            System.out.println("3. CRUD de Produtos");
            System.out.println("4. CRUD de Estoques");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1 -> TestePessoaController.main(null);
                case 2 -> TesteUsuarioController.main(null);
                case 3 -> TesteProdutoController.main(null);
                case 4 -> TesteEstoqueController.main(null);
                case 5 -> {
                    System.out.println("Saindo do programa.");
                    return;
                }
                default -> System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        }
    }

}
