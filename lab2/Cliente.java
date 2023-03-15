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
        int digitos = Integer.parseInt(cpf.substring(9));

        // primeiro digito
        int primeiro = 0;
        for (int i = 0; i < 9; i++) {
            primeiro += (10 - i)*(cpf.charAt(i) - 48);
        }
        primeiro = 11 - (primeiro%11);
        if (primeiro > 9) primeiro = 0;

        // segundo
        int segundo = 0;
        for (int i = 0; i < 9; i++) {
            segundo += (11 - i)*(cpf.charAt(i) - 48);
        }
        segundo += 2*primeiro;
        segundo = 11 - (segundo%11);
        if (segundo > 9) segundo = 0;

        if (digitos == 10*primeiro + segundo) return true;
        return false;
    }

    public String toString() {
        return "{" +
            " nome='" + getNome() + "'" +
            ", cpf='" + getCpf() + "'" +
            ", dataNascimento='" + getDataNascimento() + "'" +
            ", idade='" + getIdade() + "'" +
            ", endereco='" + getEndereco() + "'" +
            "}";
    }

}