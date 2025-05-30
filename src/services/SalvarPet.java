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
        String fileName = (now.format(df) + "-" + nomePetUpper);

        File file = new File("src/petsCadastrados/" + fileName+ ".TXT");
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileWriter fw = new FileWriter(file);
             BufferedWriter bw = new BufferedWriter(fw)){

            bw.write("1 - " + pet.getNomeCompleto()); bw.newLine();
            bw.write("2 - " +pet.getTipo().getTIPO()); bw.newLine();
            bw.write("3 - " +pet.getSexo().getSEXO()); bw.newLine();
            bw.write("4 - " +pet.getEndereco()); bw.newLine();
            bw.write("5 - " +pet.getIdade() + " anos"); bw.newLine();
            bw.write("6 - " +pet.getPeso() + "kg"); bw.newLine();
            bw.write("7 - " +pet.getRaca()); bw.newLine();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
