public class Cliente {
    private String nome;
    private String cpf;
    private String dataNascimento;
    private int idade;
    private String endereco;

    // Construtor
    public Cliente(String nome, String cpf, String dataNascimento, int idade, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.idade = idade;
        this.endereco = endereco;
    }

    // Getters
    public String getNome() {
        return nome;
    }
    public String getCpf() {
        return cpf;
    }
    public String getDataNascimento() {
        return dataNascimento;
    }
    public int getIdade() {
        return idade;
    }
    public String getEndereco() {
        return endereco;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    // Métodos
    public Boolean verificarCpf(String cpf) {
        cpf = cpf.replaceAll("[^\\d]", "");
        String digitos = cpf.substring(9);

        // provisório
        return true;
    }
}