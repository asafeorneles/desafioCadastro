package src.services;

import src.enuns.SexoPet;
import src.enuns.TipoPet;
import src.model.Pet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class CadastrarPet {
    public static void cadastrarPet() {
        Scanner input = new Scanner(System.in);
        Pet pet = null;
        File file = new File("src/data/formulario.txt");

        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {

            //NOME
            String line = br.readLine();
            System.out.println(line);
            String nomeCompleto = input.nextLine();
            if (nomeCompleto == null || nomeCompleto.trim().isEmpty()) {
                nomeCompleto = Pet.NULL;
            } else if (!nomeCompleto.matches("^[a-zA-ZÀ-ÿ]+(?:\\s[a-zA-ZÀ-ÿ]+)*$")){
                throw new IllegalArgumentException("O nome pode conter apenas Letras.");
            } else if (nomeCompleto.trim().split("\\s+").length < 2) {
                throw new IllegalArgumentException("Informe o nome e o sobrenome.");
            }

            //TIPO
            line = br.readLine();
            String tipo;
            TipoPet tipoPet = null;
            do {
                System.out.println(line);
                tipo = input.nextLine();
                if (tipo == null || tipo.trim().isEmpty()){
                    System.out.println("O tipo do animal precisa ser inserido!");
                }
            }while (tipo == null || tipo.trim().isEmpty());

            if (tipo.equalsIgnoreCase("Cachorro")){
                tipoPet = TipoPet.CACHORRO;
            } else if (tipo.equalsIgnoreCase("Gato")){
                tipoPet = TipoPet.GATO;
            } else {
                throw new IllegalArgumentException("Cadastre Cachorro ou Gato!");
            }

            //SEXO
            line = br.readLine();
            String sexo;
            SexoPet sexoPet = null;
            do {
                System.out.println(line);
                System.out.println("Digite 'F' para Fêmea e 'M' para Macho.");
                sexo = input.nextLine();
                if (sexo == null || sexo.trim().isEmpty()){
                    System.out.println("Informe o sexo do animal!");
                }
            }while (sexo == null || sexo.trim().isEmpty());

            if (sexo.equalsIgnoreCase("F")) {
                sexoPet = SexoPet.FEMININO;
            } else if (sexo.equalsIgnoreCase("M")) {
                sexoPet = SexoPet.MASCULINO;
            } else {
                throw new IllegalArgumentException("Sexo inválido! Insira 'F' ou 'M'.");
            }

            //ENDEREÇO
            line = br.readLine();
            System.out.println(line);
            String rua;
            String numero;
            String bairro;
            String cidade;
            do {
                System.out.println("Informe a rua:");
                rua = input.nextLine();
                if ((rua == null || rua.trim().isEmpty())){
                    System.out.println("A rua não pode ficar vazia!");
                }
            }while ((rua == null || rua.trim().isEmpty()));

            System.out.println("Número:");
            numero = input.nextLine();
            if (numero == null || numero.trim().isEmpty()){
                numero = Pet.NULL;
            }
            else if (!numero.matches("\\d+")) {
                throw new IllegalArgumentException("Insira apenas números!");
            }
            do {
                System.out.println("Bairro:");
                bairro = input.nextLine();
                if ((bairro == null || bairro.trim().isEmpty())){
                    System.out.println("O bairro não pode ficar vazio!");
                }
            }while ((bairro == null || bairro.trim().isEmpty()));
            do {
                System.out.println("Cidade");
                cidade = input.nextLine();
                if ((cidade == null || cidade.trim().isEmpty())){
                    System.out.println("A cidade não pode ficar vazia!");
                }
            } while ((cidade == null || cidade.trim().isEmpty()));

            String endereco = rua.concat(", " + numero + ", " + bairro + " - " + cidade);

            //IDADE
            line = br.readLine();
            System.out.println(line);
            String idade = input.nextLine();
            if (idade == null || idade.trim().isEmpty()) {
                idade = Pet.NULL;
            } else if (idade.matches("0")) {
                throw new IllegalArgumentException("A idade não pode ser 0!");
            } else if (idade.matches("^0+([,.]\\d+)?$")) {
                idade = idade.replace(',', '.');
                idade = idade + " anos";
            } else if (idade.matches("\\d+")) {
                int idadeInt = Integer.parseInt(idade);
                if (idadeInt < 0 || idadeInt > 20) {
                    throw new RuntimeException("Insira uma idade entre 1 a 20 anos.");
                } else if (idadeInt == 1) {
                    idade = idade + " ano";
                } else {
                    idade = idade + " anos";
                }
            } else {
                throw new RuntimeException("Insira apenas números inteiros.");
            }

            // IDADE (Permite idades quebradas. A de cima, só idade quebrada até 1 ano)
//            line = br.readLine();
//            System.out.println(line);
//            String idade = input.nextLine();
//            if (idade == null || idade.trim().isEmpty()){
//                idade = Pet.NULL;
//            }
//            else if (idade.matches("^\\d+([,.]\\d)?$")){
//                idade = idade.replace(',', '.');
//
//                double idadeDouble = Double.parseDouble(idade);
//                if (idadeDouble < 0 || idadeDouble > 20){
//                    throw new RuntimeException("Insira uma idade entre 1 a 20 anos.");
//                }
//
//            } else {
//                throw new RuntimeException("Insira apenas números.");
//            }

            // PESO
            line = br.readLine();
            System.out.println(line);
            String peso = input.nextLine();
            if (peso == null || peso.trim().isEmpty()) {
                peso = Pet.NULL;
            } else if (peso.matches("^\\d+([,.]\\d)?$")) {
                peso = peso.replace(',', '.');

                double pesoDouble = Double.parseDouble(peso);
                if (pesoDouble < 0.5 || pesoDouble > 60) {
                    throw new RuntimeException("Peso inválido! Insira um peso entre 0.5kg e 60kg.");
                }
                else {
                    peso = peso + "kg";
                }

            } else {
                throw new RuntimeException("Insira apenas números.");
            }

            //RAÇA
            line = br.readLine();
            System.out.println(line);
            String raca = input.nextLine();
            if (raca == null || raca.trim().isEmpty()) {
                raca = Pet.NULL;
            } else if (!raca.matches("^[a-zA-ZÀ-ÿ]+(?:[\\s-][a-zA-ZÀ-ÿ]+)*$")) {
                throw new IllegalArgumentException("Utilize apenas letras e espaços em branco entre as palavras");
            }

            pet = new Pet(nomeCompleto, tipoPet, sexoPet, endereco, idade, peso, raca);
            SalvarPet.writerPet(pet);

            Menu.exibirMenu();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
