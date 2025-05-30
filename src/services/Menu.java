package src.services;

import java.util.Scanner;

public class Menu {
    public static void exibirMenu(){
        Scanner input = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("### MENU ###");
            System.out.println("1 - Cadastrar um novo pet");
            System.out.println("2 - Alterar os dados do pet cadastrado");
            System.out.println("3 - Deletar um pet cadastrado");
            System.out.println("4 - Listar todos os pets cadastrados");
            System.out.println("5 - Listar pets por algum critério (idade, nome, raça)");
            System.out.println("6 - Sair");
            opcao = input.nextInt();

            switch (opcao){
                case 1: CadastrarPet.CadastrarPet(); break;
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                default: System.out.println("Escolha uma opção de 1 a 6!"); break;
            }
        } while (opcao <1 || opcao >6);

    }
}
