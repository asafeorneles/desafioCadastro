package src.services;

import src.enuns.SexoPet;
import src.enuns.TipoPet;
import src.model.Pet;

import java.io.*;
import java.util.Scanner;

public class CadastrarPet {
    public static void CadastrarPet() {
        Scanner input = new Scanner(System.in);
        Pet pet = null;
        File file = new File("src/data/formulario.txt");

        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {

            String line = br.readLine(); //Nome
            System.out.println(line);
            String nomeCompleto = input.nextLine();
            if (nomeCompleto == null || nomeCompleto.trim().isEmpty()) {
                nomeCompleto = Pet.NULL;
            } else if (!nomeCompleto.matches("^[a-zA-ZÀ-ÿ]+(?:\\s[a-zA-ZÀ-ÿ]+)*$")){
                throw new IllegalArgumentException("O nome pode conter apenas Letras.");
            } else if (nomeCompleto.trim().split("\\s+").length < 2) {
                throw new IllegalArgumentException("Informe o nome e o sobrenome.");
            }

            line = br.readLine(); //Tipo
            System.out.println(line);
            String tipo = input.nextLine();
            TipoPet tipoPet = null;
            if (tipo == null || tipo.trim().isEmpty()){
                tipo = Pet.NULL;
            }
            else if (tipo.equalsIgnoreCase("Cachorro")){
                tipoPet = TipoPet.CACHORRO;

            } else if (tipo.equalsIgnoreCase("Gato")){
                tipoPet = TipoPet.GATO;
            } else {
                throw new IllegalArgumentException("Cadastre Cachorro ou Gato!");
            }

            line = br.readLine(); //Sexo
            System.out.println(line);
            System.out.println("Digite 'F' para Fêmea e 'M' para Macho.");
            String sexo = input.nextLine();
            SexoPet sexoPet = null;
            if (sexo == null || sexo.trim().isEmpty()){
                tipo = Pet.NULL;
            } else if (sexo.equalsIgnoreCase("F")) {
                sexo = SexoPet.FEMININO.getSEXO();
            } else if (sexo.equalsIgnoreCase("M")) {
                sexo = SexoPet.MASCULINO.getSEXO();
            } else {
                throw new IllegalArgumentException("Sexo inválido! Insira 'F' ou 'M'.");
            }

            line = br.readLine(); // Endereço (Tratar caso rua, bairro e cidade seja vazio ou numeros)
            System.out.println(line);
            System.out.println("Informe a rua:");
            String rua = input.nextLine();
            System.out.println("Número:");
            String numero = input.nextLine();
            if (numero == null || numero.trim().isEmpty()){
                numero = Pet.NULL;
            }
            else if (!numero.matches("\\d+")) {
                throw new IllegalArgumentException("Insira apenas números!");
            }

            System.out.println("Bairro:");
            String bairro = input.nextLine();
            System.out.println("Cidade");
            String cidade = input.nextLine();

            String endereco = rua.concat(" " + numero + " " + bairro + " " + cidade);
            if (endereco == null || endereco.trim().isEmpty()) {
                endereco = Pet.NULL;
            }
            System.out.println(endereco);

            line = br.readLine(); //Idade
            System.out.println(line);
            String idade = input.nextLine();
            if (idade == null || idade.trim().isEmpty()) {
                idade = Pet.NULL;
            } else if (idade.matches("^0+([,.]\\d)?$")) {
                idade = idade.replace(',', '.');
            } else if (idade.matches("^\\d+([,.]\\d)+$")) {
                throw new RuntimeException("Insira numeros inteiros");
            } else if (idade.matches("\\d+")) {
                int idadeInt = Integer.parseInt(idade);
                if (idadeInt < 0 || idadeInt > 20) {
                    throw new RuntimeException("Insira uma idade entre 1 a 20 anos.");
                }

            } else {
                throw new RuntimeException("Insira apenas números inteiros.");
            }

//            line = br.readLine(); //Idade
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

            line = br.readLine(); //Peso
            System.out.println(line);
            String peso = input.nextLine();
            if (peso == null || peso.trim().isEmpty()) {
                peso = Pet.NULL;
                System.out.println(peso);
            } else if (peso.matches("^\\d+([,.]\\d)?$")) {
                peso = peso.replace(',', '.');

                double pesoDouble = Double.parseDouble(peso);
                if (pesoDouble < 0.5 || pesoDouble > 60) {
                    throw new RuntimeException("Peso inválido! Insira um peso entre 0.5kg e 60kg.");
                }

            } else {
                throw new RuntimeException("Insira apenas números.");
            }

            line = br.readLine(); // Raça
            System.out.println(line);
            String raca = input.nextLine();
            if (raca == null || raca.trim().isEmpty()) {
                raca = Pet.NULL;
            } else if (!raca.matches("^[a-zA-ZÀ-ÿ]+(?:\\s[a-zA-ZÀ-ÿ]+)*$")) {
                throw new IllegalArgumentException("Utilize apenas letras e espaços em branco entre as palavras");
            }

            pet = new Pet(nomeCompleto, tipoPet, sexoPet, endereco, idade, peso, raca);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
