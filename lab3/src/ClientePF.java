import java.time.LocalDate;

public class ClientePF extends Cliente {
    private String cpf;
    private LocalDate dataNascimento;
    private int idade;
    private String educacao;
    private String genero;
    private String classeEconomica;
    private LocalDate dataLicenca;


    public ClientePF(String nome, String endereco, String cpf, LocalDate dataNascimento, int idade, String educacao, String genero, String classeEconomica, LocalDate dataLicenca) {
        super(nome, endereco);
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.idade = idade;
        this.educacao = educacao;
        this.genero = genero;
        this.classeEconomica = classeEconomica;
        this.dataLicenca = dataLicenca;
    }
        

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getIdade() {
        return this.idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEducacao() {
        return this.educacao;
    }

    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getClasseEconomica() {
        return this.classeEconomica;
    }

    public void setClasseEconomica(String classeEconomica) {
        this.classeEconomica = classeEconomica;
    }

    public LocalDate getDataLicenca() {
        return this.dataLicenca;
    }

    public void setDataLicenca(LocalDate dataLicenca) {
        this.dataLicenca = dataLicenca;
    }

    public static Boolean verificarCPF(String cpf) {
        cpf = cpf.replaceAll("[^\\d]", "");

        // Primeira analise
        if (cpf.length() != 11) return false;
        boolean iguais = true;
        for (int i = 1; i < cpf.length(); i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) iguais = false;
        }
        if (iguais) return false;

        // Digitos verificadores
        int digitos = Integer.parseInt(cpf.substring(9));
        int primeiro = 0;
        for (int i = 0; i < 9; i++) {
            primeiro += (10 - i)*(cpf.charAt(i) - 48);
        }
        primeiro = 11 - (primeiro%11);
        if (primeiro > 9) primeiro = 0;

        int segundo = 0;
        for (int i = 0; i < 9; i++) {
            segundo += (11 - i)*(cpf.charAt(i) - 48);
        }
        segundo += 2*primeiro;
        segundo = 11 - (segundo%11);
        if (segundo > 9) segundo = 0;

        if (digitos != 10*primeiro + segundo) return false;
        return true;
    }


    @Override
    public String toString() {
        return "{" +
            " nome: " + getNome() +
            ", cpf: " + getCpf() +
            "}";
    }

}