package src.services;

import src.model.Pet;

import java.io.*;
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
            bw.write("Raça: " +pet.getRaca());
            bw.write(" / " + fileName);
            bw.newLine(); bw.flush();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static void rewriterPet(Pet pet, String fileName){
        File file = new File("src/petsCadastrados/" + fileName);
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

        File filePets = new File("src/petsCadastrados/PETSGERAL.txt");
        String petsAnteriores = "";
        try (BufferedReader br = new BufferedReader(new FileReader(filePets))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (!line.contains(fileName)) {
                    petsAnteriores += line + "\n";
                }
            }

        } catch (IOException e) {
            System.err.println("Erro ao armazenar os pets anteriores " + e.getMessage());
            throw new RuntimeException(e);
        }

        try (FileWriter fw = new FileWriter(filePets);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(petsAnteriores);
            bw.write("Nome: " + pet.getNomeCompleto() + " ");
            bw.write("Tipo: " + pet.getTipo().getTIPO() + " ");
            bw.write("Sexo: " + pet.getSexo().getSEXO() + " ");
            bw.write("Endereço: " + pet.getEndereco() + " ");
            bw.write("Idade: " + pet.getIdade() + " ");
            bw.write("Peso: " + pet.getPeso() + " ");
            bw.write("Raça: " + pet.getRaca());
            bw.write(" / " + fileName);
            bw.newLine();
        }catch (IOException e){
            System.out.println("Erro ao atualizar pet" + e.getMessage());
        }
        renomearArquivoPet(pet, file);
    }

    public static void renomearArquivoPet(Pet pet, File fileAntigo){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        LocalDateTime now = LocalDateTime.now();

        String [] nomes = pet.getNomeCompleto().split("\\s");
        String nomePetUpper = "";
        for (String nome : nomes) {
            nomePetUpper += nome.toUpperCase();
        }
        String fileName = (now.format(df) + "-" + nomePetUpper + ".TXT");
        String filePathName = ("src/petsCadastrados/" + fileName);

        File fileRenomed = new File(filePathName);
        boolean isFileRenomed = fileAntigo.renameTo(fileRenomed);
        if (isFileRenomed){
            System.out.println("Pet atualizado com sucesso!!");
        }else {
            System.out.println("Erro ao atualizar arquivo do pet");
        }

        Menu.exibirMenu();
    }

}
