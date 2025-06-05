package src.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListarPets {
    public static void listarTodosPets() {
        File file = new File("src/petsCadastrados/PETSGERAL.txt");
        Pattern pattern = Pattern.compile("Nome: (.*?) Tipo: (.*?) Sexo: (.*?) Endereço: (.*?) Idade: (.*?) Peso: (.*?) Raça: (.*?)\\s+/\\s+(\\d{8}T\\d{4}-.*?\\.TXT)", Pattern.CASE_INSENSITIVE);
        boolean petEncontrado = false;
        int cont = 1;

        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {
            String line;

            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String nomeP = matcher.group(1);
                    String tipoP = matcher.group(2);
                    String sexoP = matcher.group(3);
                    String enderecoP = matcher.group(4);
                    String idadeP = matcher.group(5);
                    String pesoP = matcher.group(6);
                    String racaP = matcher.group(7);

                    System.out.println(cont + ". " + nomeP + " - " + tipoP + " - " + sexoP + " - " + enderecoP + " - " + idadeP + " - " + pesoP + " - " + racaP);
                    cont++;
                    petEncontrado = true;
                }
            }
            if (!petEncontrado) {
                System.out.println("Nenhum pet foi encontrado");
            } else {
                Menu.exibirMenu();
            }
        } catch (IOException e) {
            System.out.println("Falha ao listar os pets cadastrados.");
        }
    }
}
