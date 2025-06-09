package src.services;

import src.enuns.BuscaPet;
import src.enuns.TipoPet;
import java.io.*;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeletarPet {
    public static void buscarPetMenu() {
        int opcao;
        do {
            Scanner input = new Scanner(System.in);
            System.out.println("Selecione os critérios de busca:");
            System.out.println("1. Nome e/ou sobrenome");
            System.out.println("2. Sexo");
            System.out.println("3. Idade");
            System.out.println("4. Peso");
            System.out.println("5. Raça");
            System.out.println("6. Endereço");
            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1: buscarPetPart2("Nome");break;
                case 2: buscarPetPart2("Sexo");break;
                case 3: buscarPetPart2("Idade");break;
                case 4: buscarPetPart2("Peso");break;
                case 5: buscarPetPart2("Raça");break;
                case 6: buscarPetPart2("Endereço");break;
                default: System.out.println("Selecione apenas as opções disponíveis!"); break;
            }
        } while (opcao <1 || opcao >6);
    }

    public static void buscarPetPart2(String atributoEscolhido) {
        Scanner input = new Scanner(System.in);
        int opcaoBusca2;
        do {
            System.out.println("1- Selecionar outro critério de busca");
            System.out.println("2- Buscar");
            opcaoBusca2 = input.nextInt();
            switch (opcaoBusca2) {
                case 1: selecionarOutroCriterio(atributoEscolhido); break;
                case 2: fazerBusca(atributoEscolhido); break;
                default: System.out.println("Selecione apenas as opções disponíveis!"); break;
            }
        }while (opcaoBusca2 <1 || opcaoBusca2 > 2);
    }

    public static void selecionarOutroCriterio(String atributoEscolhido) {
        Scanner input = new Scanner(System.in);
        String [] atributosPets = new String[] {BuscaPet.NOME_E_SOBRENOME.getINFO(), BuscaPet.SEXO.getINFO(), BuscaPet.IDADE.getINFO(), BuscaPet.PESO.getINFO(), BuscaPet.RACA.getINFO(), BuscaPet.ENDERECO.getINFO()};
        int opcao;
        String atributoEscolhido2;

        do {
            System.out.println("Selecione outro criterio: ");
            int cont = 1;
            StringBuilder listPetsSb = new StringBuilder();
            for (String atributosPet : atributosPets){
                if (atributosPet.equalsIgnoreCase(atributoEscolhido) || atributosPet.contains(atributoEscolhido)){
                    continue;
                }
                String line = cont++ + ". " + atributosPet;
                System.out.println(line);
                listPetsSb.append(line).append("\n");
            }
            String listPets = listPetsSb.toString();
            opcao = input.nextInt(); input.nextLine();

            Pattern pattern = Pattern.compile("^" + opcao + "\\.\\s*(.+)$", Pattern.MULTILINE);

            Matcher matcher = pattern.matcher(listPets);
            if (matcher.find()){
                atributoEscolhido2 = matcher.group(1);
                fazerBusca2(atributoEscolhido, atributoEscolhido2);
            } else {
                System.out.println("Selecione apenas as opções disponíveis!");
                atributoEscolhido2 = "";
            }

        }while (opcao < 1 || opcao > 5 );

    }

    public static void fazerBusca(String atributoEscolhido) {
        Scanner input = new Scanner(System.in);
        String tipo;

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

        System.out.println("Faça sua busca por " + atributoEscolhido.toLowerCase());
        String buscaRegex = input.nextLine().trim();

        clearTempFile("src/petsCadastrados/PETSENCONTRADOS.txt");

        File file = new File("src/petsCadastrados/PETSGERAL.txt");
        boolean encontrou = false;

        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                Pattern pattern = Pattern.compile(atributoEscolhido + ":\\s*(.*?)(?:\\s[A-Za-zÀ-ÿ]+:|\\s*\\/|$)", Pattern.CASE_INSENSITIVE);
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

    public static void fazerBusca2(String atributoEscolhido, String atributoEscolhido2) {
        Scanner input = new Scanner(System.in);
        String tipo;

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

        System.out.println("Faça sua busca por " + atributoEscolhido.toLowerCase());
        String buscaRegex1 = input.nextLine();
        System.out.println("Agora por " + atributoEscolhido2.toLowerCase());
        String buscaRegex2 = input.nextLine().trim();

        clearTempFile("src/petsCadastrados/PETSENCONTRADOS.txt");

        File file = new File("src/petsCadastrados/PETSGERAL.txt");
        boolean encontrou = false;

        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {

                Pattern pattern1 = Pattern.compile(atributoEscolhido + ":\\s*(.*?)(?:\\s[A-Za-zÀ-ÿ]+:|\\s*\\/|$)", Pattern.CASE_INSENSITIVE);
                Pattern pattern2 = Pattern.compile(atributoEscolhido2 + ":\\s*(.*?)(?:\\s[A-Za-zÀ-ÿ]+:|\\s*\\/|$)", Pattern.CASE_INSENSITIVE);
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
