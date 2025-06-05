package src.services;

import src.enuns.BuscaPet;
import src.enuns.SexoPet;
import src.enuns.TipoPet;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeletarPet {
    public static void buscarPetMenu(){
        Scanner input = new Scanner(System.in);
        System.out.println("Selecione os critérios de busca:");
        System.out.println("1 - Nome e/ou sobrenome");
        System.out.println("2 - Sexo");
        System.out.println("3 - Idade");
        System.out.println("4 - Peso");
        System.out.println("5 - Raça");
        System.out.println("6 - Endereço");
        int opcao = input.nextInt();
        input.nextLine();

        switch (opcao) {
            case 1: buscarPetPart2(opcao);break;
            case 2: buscarPetPart2(opcao);break;
            case 3: buscarPetPart2(opcao);break;
            case 4: buscarPetPart2(opcao);break;
            case 5: buscarPetPart2(opcao);break;
            case 6: buscarPetPart2(opcao);break;
            default: System.out.println("Selecione uma opçao de 1 a 6!"); break;
        }
    }

    public static void buscarPetPart2(int opcao) {
        Scanner input = new Scanner(System.in);
        System.out.println("1- Selecionar outro critério de busca");
        System.out.println("2- Buscar");
        int opcao2 = input.nextInt();
        input.nextLine();
        switch (opcao2) {
            case 1:
                selecionarOutroCriterio(opcao); break;
            case 2:
                fazerBusca(opcao); break;
        }
    }

    public static void selecionarOutroCriterio(int opcao) {
        Scanner input = new Scanner(System.in);
        BuscaPet buscaPet = null;
        for (int i = 1; i < 7; i++) {
            if (i == opcao) {
                continue;
            } else if (i == 1) {
                buscaPet = BuscaPet.NOME_E_SOBRENOME;
                System.out.println(buscaPet.getVALOR() + " - " + buscaPet.getINFO());
            } else if (i == 2) {
                buscaPet = BuscaPet.SEXO;
                System.out.println(buscaPet.getVALOR() + " - " + buscaPet.getINFO());
            } else if (i == 3) {
                buscaPet = BuscaPet.IDADE;
                System.out.println(buscaPet.getVALOR() + " - " + buscaPet.getINFO());
            } else if (i == 4) {
                buscaPet = BuscaPet.PESO;
                System.out.println(buscaPet.getVALOR() + " - " + buscaPet.getINFO());
            } else if (i == 5) {
                buscaPet = BuscaPet.RACA;
                System.out.println(buscaPet.getVALOR() + " - " + buscaPet.getINFO());
            } else if (i == 6) {
                buscaPet = BuscaPet.ENDERECO;
                System.out.println(buscaPet.getVALOR() + " - " + buscaPet.getINFO());
            }
        }
        int opcao2 = input.nextInt();
        input.nextLine();
        switch (opcao2) {
            case 1: fazerBusca2(opcao, opcao2); break;
            case 2: fazerBusca2(opcao, opcao2); break;
            case 3: fazerBusca2(opcao, opcao2); break;
            case 4: fazerBusca2(opcao, opcao2); break;
            case 5: fazerBusca2(opcao, opcao2); break;
            case 6: fazerBusca2(opcao, opcao2); break;
            default: System.out.println("Selecione apenas as opções disponíveis!") ;break;
        }
    }

    public static void fazerBusca(int opcao) {
        Scanner input = new Scanner(System.in);
        String tipo;
        String atributo = "";

        switch (opcao) {
            case 1:
                atributo = "Nome";
                break;
            case 2:
                atributo = "Sexo";
                break;
            case 3:
                atributo = "Idade";
                break;
            case 4:
                atributo = "Peso";
                break;
            case 5:
                atributo = "Raça";
                break;
            case 6:
                atributo = "Endereço";
                break;
        }

        do {
            do {
                System.out.println("Qual o tipo do animal que deseja buscar? (Cachorro ou Gato)");
                tipo = input.nextLine();
                if (tipo == null || tipo.trim().isEmpty()) {
                    System.out.println("Informe o tipo!");
                }
            } while (tipo == null || tipo.trim().isEmpty());
            if (tipo.equalsIgnoreCase("Gato")) {
                tipo = TipoPet.GATO.getTIPO();
            } else if (tipo.equalsIgnoreCase("Cachorro")) {
                tipo = TipoPet.CACHORRO.getTIPO();
            } else {
                System.out.println("Informe ou Cachorro ou Gato!");
            }
        } while (!(tipo.equalsIgnoreCase("Cachorro") || tipo.equalsIgnoreCase("Gato")));

        System.out.println("Faça sua busca por " + atributo.toLowerCase());
        String buscaRegex = input.nextLine().trim();

        clearTempFile("src/petsCadastrados/PETSENCONTRADOS.txt");

        File file = new File("src/petsCadastrados/PETSGERAL.txt");
        boolean encontrou = false;

        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                Pattern pattern = Pattern.compile(atributo + ":\\s*(.*?)(?:\\s[A-Za-zÀ-ÿ]+:|\\s*\\/|$)", Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(line);

                if (line.contains("Tipo: " + tipo) && matcher.find()) {
                    String valorBusca = matcher.group(1).trim(); // Para especificar no regex o que queremos buscar. Neste caso, o que vier depois do atributo
                    if (valorBusca.toLowerCase().contains(buscaRegex.toLowerCase())) {
                        Pattern todosCampos = Pattern.compile("Nome: (.*?) Tipo: (.*?) Sexo: (.*?) Endereço: (.*?) Idade: (.*?) Peso: (.*?) Raça: (.*?)\\s+/\\s+(\\d{8}T\\d{4}-.*?\\.TXT)", Pattern.CASE_INSENSITIVE);
                        Matcher matcherTodosCampos = todosCampos.matcher(line);

                        if (matcherTodosCampos.find()){
                            salvarArquivoTemp(line);
                            encontrou = true;
                        }
                    }
                }
            }

            if (!encontrou){
                System.out.println("Nenhum resultado encontrado.");
                File fileTemp = new File("src/petsCadastrados/PETSENCONTRADOS.txt");
                fileTemp.delete();
            } else {
                deletarPetMenu();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void fazerBusca2(int opcao, int opcao2) {
        Scanner input = new Scanner(System.in);
        String tipo;
        String atributo1 = "";
        String atributo2 = "";

        switch (opcao) {
            case 1:
                atributo1 = "Nome";
                break;
            case 2:
                atributo1 = "Sexo";
                break;
            case 3:
                atributo1 = "Idade";
                break;
            case 4:
                atributo1 = "Peso";
                break;
            case 5:
                atributo1 = "Raça";
                break;
            case 6:
                atributo1 = "Endereço";
                break;
        }

        switch (opcao2) {
            case 1:
                atributo2 = "Nome";
                break;
            case 2:
                atributo2 = "Sexo";
                break;
            case 3:
                atributo2 = "Idade";
                break;
            case 4:
                atributo2 = "Peso";
                break;
            case 5:
                atributo2 = "Raça";
                break;
            case 6:
                atributo2 = "Endereço";
                break;
        }

        do {
            do {
                System.out.println("Qual o tipo do animal que deseja buscar? (Cachorro ou Gato)");
                tipo = input.nextLine();
                if (tipo == null || tipo.trim().isEmpty()) {
                    System.out.println("Informe o tipo!");
                }
            } while (tipo == null || tipo.trim().isEmpty());
            if (tipo.equalsIgnoreCase("Gato")) {
                tipo = TipoPet.GATO.getTIPO();
            } else if (tipo.equalsIgnoreCase("Cachorro")) {
                tipo = TipoPet.CACHORRO.getTIPO();
            } else {
                System.out.println("Informe ou Cachorro ou Gato!");
            }
        } while (!(tipo.equalsIgnoreCase("Cachorro") || tipo.equalsIgnoreCase("Gato")));

        System.out.println("Faça sua busca por " + atributo1.toLowerCase());
        String buscaRegex1 = input.nextLine();
        System.out.println("Agora por " + atributo2.toLowerCase());
        String buscaRegex2 = input.nextLine().trim();

        clearTempFile("src/petsCadastrados/PETSENCONTRADOS.txt");

        File file = new File("src/petsCadastrados/PETSGERAL.txt");
        boolean encontrou = false;

        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {

                Pattern pattern1 = Pattern.compile(atributo1 + ":\\s*(.*?)(?:\\s[A-Za-zÀ-ÿ]+:|\\s*\\/|$)", Pattern.CASE_INSENSITIVE);
                Pattern pattern2 = Pattern.compile(atributo2 + ":\\s*(.*?)(?:\\s[A-Za-zÀ-ÿ]+:|\\s*\\/|$)", Pattern.CASE_INSENSITIVE);
                Matcher matcher1 = pattern1.matcher(line);
                Matcher matcher2 = pattern2.matcher(line);

                if (line.contains("Tipo: " + tipo) && matcher1.find() && matcher2.find()) {
                    String valorCampo1 = matcher1.group(1);
                    String valorCampo2 = matcher2.group(1);

                    if (valorCampo1.toLowerCase().contains(buscaRegex1.toLowerCase()) && valorCampo2.toLowerCase().contains(buscaRegex2.toLowerCase())){
                        Pattern todosCampos = Pattern.compile("Nome: (.*?) Tipo: (.*?) Sexo: (.*?) Endereço: (.*?) Idade: (.*?) Peso: (.*?) Raça: (.*?)\\s+/\\s+(\\d{8}T\\d{4}-.*?\\.TXT)", Pattern.CASE_INSENSITIVE);
                        Matcher matcher = todosCampos.matcher(line);

                        if (matcher.find()){

                            salvarArquivoTemp(line);
                            encontrou = true;

                        }
                    }
                }
            }
            if (!encontrou){
                System.out.println("Nenhum resultado encontrado.");
                File fileTemp = new File("src/petsCadastrados/PETSENCONTRADOS.txt");
                fileTemp.delete();
            }else {
                deletarPetMenu();
            }

        } catch (IOException e) {
            System.out.println("Não foi possível ler o arquivo" + e.getMessage());
        }

    }

    public static void clearTempFile(String filePath) {
        File file = new File(filePath);
        try (FileWriter fw = new FileWriter(file, false);
             BufferedWriter bw = new BufferedWriter(fw)) {

        } catch (IOException e) {
            System.out.println("Falha ao limpar o arquivo temporário: " + e.getMessage());
        }
    }

    public static void salvarArquivoTemp(String line){
        File fileTemp = new File("src/petsCadastrados/PETSENCONTRADOS.txt");
        try (FileWriter fw = new FileWriter(fileTemp);
        BufferedWriter bw = new BufferedWriter(fw)){
            bw.write(line); bw.newLine(); bw.flush();
        }catch (IOException e){
            System.out.println("Falha ao escrever arquivo" + e.getMessage());
        }
    }

    public static void deletarPetMenu(){
        Scanner input = new Scanner(System.in);
        String fileName = "";
        Pattern patternAtributos = Pattern.compile("Nome: (.*?) Tipo: (.*?) Sexo: (.*?) Endereço: (.*?) Idade: (.*?) Peso: (.*?) Raça: (.*?)\\s+/\\s+(\\d{8}T\\d{4}-.*?\\.TXT)", Pattern.CASE_INSENSITIVE);
        boolean petEncontrado = false;
        int cont = 1;

        File fileTemp = new File("src/petsCadastrados/PETSENCONTRADOS.txt");

        try (FileReader fr = new FileReader(fileTemp);
        BufferedReader br = new BufferedReader(fr)) {

            String line;
            while ((line = br.readLine()) != null){
                Matcher matcher = patternAtributos.matcher(line);

                if (matcher.find()){
                    String nomeP = matcher.group(1);
                    String tipoP = matcher.group(2);
                    String sexoP = matcher.group(3);
                    String enderecoP = matcher.group(4);
                    String idadeP = matcher.group(5);
                    String pesoP = matcher.group(6);
                    String racaP = matcher.group(7);

                    System.out.println(cont + ". " + nomeP + " - " + tipoP + " - " + sexoP + " - " + enderecoP + " - " + idadeP + " - " + pesoP + " - " + racaP);
                    cont ++;
                }
            }

            System.out.println("Digite o numero do pet que deseja deletar:");
            String numeroPetEscolhido = input.nextLine();
            int numeroPetEscolhidoInt;
            try {
                numeroPetEscolhidoInt = Integer.parseInt(numeroPetEscolhido);
                if (numeroPetEscolhidoInt <=0 || numeroPetEscolhidoInt >= cont){
                    System.out.println("Número inválido. Selecione um número da lista.");
                    return;
                }
            } catch (RuntimeException e) {
                System.out.println("Entrada inválida. Digite um número válido.");
                return;
            }

            int cont2 = 0;
            try (FileReader fr2 = new FileReader(fileTemp);
            BufferedReader br2 = new BufferedReader(fr2)){

                while ((line = br2.readLine()) != null){
                    Matcher matcher = patternAtributos.matcher(line);
                    if (matcher.find()){
                        cont2++;
                        if (numeroPetEscolhidoInt == cont2){
                            fileName = matcher.group(8);

                            petEncontrado = true;
                        }
                    }
                }
            }
            if (petEncontrado){
                deletarPet(fileName);
            }else {
                System.out.println("Nenhum pet com esse número foi encontrado.");
            }
        } catch (IOException e) {
            System.out.println("Falha ao ler arquivo" + e.getMessage());
        }finally {
            fileTemp.delete();
        }
    }

    public static void deletarPet(String fileName){
        Scanner input = new Scanner(System.in);
        String confirmacao;
        do {
            System.out.println("Confirma a exclusão do pet? Digite sim ou não. (Essa ação não pode ser desfeita)");
            confirmacao = input.nextLine();
            if (confirmacao.equalsIgnoreCase("sim")){
                SalvarPet.deletarPet(fileName);
            } else if (confirmacao.equalsIgnoreCase("não") || confirmacao.equalsIgnoreCase("nao")) {
                System.out.println("Nenhuma exclusão feita.");
                Menu.exibirMenu();
            } else {
                System.out.println("Digite apenas \"sim\" ou \"Não\".");
            }
        }while (!confirmacao.equalsIgnoreCase("sim") && !confirmacao.equalsIgnoreCase("não") && !confirmacao.equalsIgnoreCase("nao"));
    }
}
