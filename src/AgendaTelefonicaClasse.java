import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}