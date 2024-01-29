import java.util.List;

public class Contatos {
    private Long id;
    private String nome;
    private String sobreNome;
    private List<Telefone> telefones;

    public Contatos() {
    }

    public Contatos(Long id, String nome, String sobreNome, List<Telefone> telefones) {
        this.id = id;
        this.nome = nome;

        this.sobreNome = sobreNome;
        this.telefones = telefones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }
}
