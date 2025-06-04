package src.services;

import src.model.Pet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SalvarPet {
    public static void writerPet(Pet pet){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        LocalDateTime now = LocalDateTime.now();

        String [] nomes = pet.getNomeCompleto().split("\\s");
        String nomePetUpper = "";
        for (String nome : nomes) {
            nomePetUpper += nome.toUpperCase();
        }
        String fileName = (now.format(df) + "-" + nomePetUpper + ".TXT");
        String filePathName = ("src/petsCadastrados/" + fileName);
        File file = new File(filePathName);
        try (FileWriter fw = new FileWriter(file);
             BufferedWriter bw = new BufferedWriter(fw)){

            bw.write("1 - " + pet.getNomeCompleto()); bw.newLine();
            bw.write("2 - " +pet.getTipo().getTIPO()); bw.newLine();
            bw.write("3 - " +pet.getSexo().getSEXO()); bw.newLine();
            bw.write("4 - " +pet.getEndereco()); bw.newLine();
            bw.write("5 - " +pet.getIdade()); bw.newLine();
            bw.write("6 - " +pet.getPeso()); bw.newLine();
            bw.write("7 - " +pet.getRaca()); bw.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File filePets = new File( "src/petsCadastrados/PETSGERAL.txt");
        try (FileWriter fw = new FileWriter(filePets, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write("Nome: " + pet.getNomeCompleto() + " ");
            bw.write("Tipo: " +pet.getTipo().getTIPO() + " ");
            bw.write("Sexo: " +pet.getSexo().getSEXO() + " ");
            bw.write("Endereço: " +pet.getEndereco() + " ");
            bw.write("Idade: " +pet.getIdade() + " ");
            bw.write("Peso: " +pet.getPeso() + " ");
            bw.write("Raça: " +pet.getRaca()); bw.newLine(); bw.flush();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

}
