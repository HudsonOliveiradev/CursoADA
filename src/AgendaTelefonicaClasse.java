import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class AgendaTelefonicaClasse {
    private static final String Arquivo_Contato = "contatos.txt";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean emExecucao = true;
        while (emExecucao) {
            exibirMenu();
         int opcaoMenu = Integer.parseInt(scanner.nextLine());
         switch (opcaoMenu){
             case 1:
                 adcionarContato();
                 break;
             case 2:
                 removerContato();
                 break;
             case 3:
                 editarContato();
                 break;
             case 4:
                 System.out.println("O sistema será encerrado!");
                 emExecucao = sair();
                 break;
             default:
                 System.out.println("Opção Inválida");
         }

        }
    }
    public static void exibirMenu(){
        System.out.println("\n > Menu < ");
        System.out.println("\n 1 - Adicionar Contato ");
        System.out.println("\n 2 - Remover Contato ");
        System.out.println("\n 3 - Editar Contato ");
        System.out.println("\n 4 - Sair ");
        System.out.println(" Digite a opção desejada: ");
    }
    public static void adcionarContato(){
        try (FileWriter escrever = new FileWriter(Arquivo_Contato, true);
             BufferedWriter bWcompleto = new BufferedWriter(escrever)){
            System.out.println("Digite o Nome");
            String nome = scanner.nextLine();
            System.out.println("Digite o Sobrenome");
            String sobrenome = scanner.nextLine();
            System.out.println("Digite DDD");
            String ddd = scanner.nextLine();
            System.out.println("Digite o número de telefone");
            String numero = scanner.nextLine();
            List<Telefone>telefones = new ArrayList<>();
            Telefone contatoNum = new Telefone(ddd, Long.parseLong(String.valueOf(numero)));
            telefones.add(contatoNum);
            Contato nomeCadastrado = new Contato(nome, sobrenome, telefones);

            bWcompleto.write(CriarContato(nomeCadastrado));
            bWcompleto.newLine();

            System.out.println("Cadastro Finalizado!");
        } catch (IOException e) {
            System.err.println("Não foi possível criar contato!" + e.getMessage());
        }
    }
    public static String CriarContato(Contato contato) {
        StringBuilder novoContato = new StringBuilder();
        Calendar calendario = Calendar.getInstance();
        Long id = calendario.getTimeInMillis() / 1000;
        contato.setId(id);
        novoContato.append(contato.getId()).append(" | ");
        novoContato.append(contato.getNome()).append(" ").append(contato.getSobreNome());
        novoContato.append(" | ").append(contato.getTelefones().get(0).getDdd());
        novoContato.append(" | ").append(contato.getTelefones().get(0).getNumero());
        return novoContato.toString();
    }

    public static void removerContato(){
        System.out.println("Qual Id que você deseja remover?");
        long id = scanner.nextLong();
        File arquivo = new File(Arquivo_Contato);
        try {
            FileReader fileReader = new FileReader(arquivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linha;

            StringBuilder textoVazio = new StringBuilder();
            while ((linha = bufferedReader.readLine()) != null){
                if(!linha.contains(Long.toString(id))){
                    textoVazio.append(linha).append(System.lineSeparator());
                }
            }
            bufferedReader.close();
            fileReader.close();

            FileWriter fileWriter = new FileWriter(arquivo);
            BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);

            bufferedWriter.write(textoVazio.toString());
            bufferedWriter.close();
            fileWriter.close();

            System.out.println("Contato removido com sucesso!");

        } catch (IOException e) {
            System.err.println("Erro ao remover contato!" + e.getMessage());
        }
    }

    public static void editarContato() {
        System.out.println("Qual Id que você deseja editar?");
        long idEditado = scanner.nextLong();

        scanner.nextLine();

        File arquivo = new File(Arquivo_Contato);
        System.out.println("Você deseja editar: nome, sobrenome, ddd ou telefone?");
        String valorEditado = scanner.nextLine().toLowerCase();
        int posicaoEditado = 0;
        switch (valorEditado) {
            case "nome":
                posicaoEditado = 2;
                break;
            case "sobrenome":
                posicaoEditado = 3;
                break;
            case "ddd":
                posicaoEditado = 5;
                break;
            case "telefone":
                posicaoEditado = 7;
                break;
            default:
                System.out.println("O valor informado deve estar igual ao impresso!");
                System.out.println("Você deseja editar: nome, sobrenome, ddd ou telefone?");
        }

        try {
            FileReader fileReader = new FileReader(arquivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linha;

            StringBuilder textoVazio = new StringBuilder();
            while ((linha = bufferedReader.readLine()) != null) {
                if (linha.contains(Long.toString(idEditado))) {
                    String[] split = linha.split(" ");

                    StringBuilder linhaEditada = new StringBuilder();

                    for (int i = 0; i < split.length; i++) {
                        if (i == posicaoEditado) {
                            System.out.println("Digite o novo valor:");
                            String novoValor = scanner.nextLine();
                            linhaEditada.append(novoValor).append(" ");
                        } else {
                            linhaEditada.append(split[i]).append(" ");
                        }
                    }
                    textoVazio.append(linhaEditada.toString()
                            .trim()).append(System.lineSeparator());
                } else {
                    textoVazio.append(linha).append(System.lineSeparator());
                }
            }
            bufferedReader.close();
            fileReader.close();

            FileWriter fileWriter = new FileWriter(arquivo);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(textoVazio.toString());
            bufferedWriter.close();
            fileWriter.close();

            System.out.println("Contato editado com sucesso!");
            return;

        } catch (IOException e) {
            System.err.println("Erro ao editar contato!" + e.getMessage());
        }
    }
    public static boolean sair(){
        return false;
    }
}