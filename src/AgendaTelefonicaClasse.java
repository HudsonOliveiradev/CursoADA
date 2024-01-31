import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class agendaTelefonica {
    private static final String Arquivo_Contato = "contatos.txt";
    private static Scanner scanner = new Scanner(System.in);

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
                 sair();
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
            Long numero = scanner.nextLong();
            List<Telefone>telefones = new ArrayList<>();
            Telefone contatoNum = new Telefone(ddd, numero);
            telefones.add(contatoNum);
            Contato nomeCadastrado = new Contato(nome, sobrenome, telefones);

            bWcompleto.write(CriarContato(nomeCadastrado));
            bWcompleto.newLine();

            System.out.println("Cadastro Finalizado!");
        } catch (IOException e) {
            System.err.println("Não foi possível criar contato " + e.getMessage());
        }
    }
    public static String CriarContato(Contato contato){
        String novoContato = "";
        Calendar calendario = Calendar.getInstance();
        Long id = calendario.getTimeInMillis()/1000;
        contato.setId(id);
        novoContato.concat(contato.getId().toString() + " | ");
        novoContato.concat(contato.getNome() + " " + contato.getSobreNome());
        novoContato.concat(" | " + contato.getTelefones());
        return novoContato;
    }
    public static void RemoverContato(Long id){
        File arquivo = new File(Arquivo_Contato);
        try {
            FileReader fileReader = new FileReader(arquivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linha;

            StringBuilder textoVazio = new StringBuilder();
            while ((linha = bufferedReader.readLine()) != null){
                if(!linha.contains(id.toString())){
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
}