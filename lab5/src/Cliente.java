public abstract class Cliente {
    protected String nome;
    protected String endereco;
    protected String telefone;
    protected String email;
    protected int qtdeSinistros;

    // Construtor
    public Cliente(String nome, String endereco, String telefone, String email) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.qtdeSinistros = 0;
    }

    // Getters
    public String getNome() {
        return nome;
    }
    public String getEndereco() {
        return endereco;
    }
    public String getTelefone() {
        return telefone;
    }
    public String getEmail() {
        return email;
    }
    public String getCadastro() {
        return null;
    }
    public int getQtdeSinistros() {
        return qtdeSinistros;
    }
    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setQtdeSinistros(int qtdeSinistros) {
        this.qtdeSinistros = qtdeSinistros;
    }

    // Métodos

    public String toString() {
        return "{" +
            " nome: " + getNome() +
            ", endereco: " + getEndereco() +
            "}";
    }

    public abstract void listVeiculos();

}