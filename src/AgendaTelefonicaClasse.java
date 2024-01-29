import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AgendaTelefonicaClasse {
    public static List<Contatos> contatos = new ArrayList<>();

    public static void carregarContatosDados(String nomeDoArquivo) {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(nomeDoArquivo))) {


            String linha;
            while ((linha = bufferedReader.readLine()) != null){
                boolean leituraFeita = false;
                if (linha.equals(">>>> Contatos <<<<")){
                    leituraFeita = true;
                    continue;
                } else {
                    break;
                }

                if (leituraFeita){
                    List<String> texto = new ArrayList<>();
                    texto.add(Arrays.toString(linha.split("\\|")));
                    Long id = Long.parseLong(texto[0].trim());
                    String nome = texto[1].trim();
                    String[] nomeTexto = nome.split("\\|");
                    Contatos contato = new Contatos(id, nomeTexto[0], nomeTexto[1]);
                    contatos.add(contato);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void adicionandoContato(){

    }
}
