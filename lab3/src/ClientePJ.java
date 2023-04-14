import java.time.LocalDate;

public class ClientePJ extends Cliente {
    private LocalDate dataFundacao;
    private String cnpj;

    public ClientePJ(String nome, String endereco, LocalDate dataFundacao, String cnpj) {
        super(nome, endereco);
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
    }


    public LocalDate getDataFundacao() {
        return this.dataFundacao;
    }

    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }


    @Override
    public String toString() {
        return "{" +
            " nome: " + getNome() +
            ", cnpj: " + getCnpj() +
            "}";
    }

    public static Boolean verificarCNPJ(String cnpj) {
        cnpj = cnpj.replaceAll("[^\\d]", "");

        // Primeira analise
        if (cnpj.length() != 14) return false;
        boolean iguais = true;
        for (int i = 1; i < cnpj.length(); i++) {
            if (cnpj.charAt(i) != cnpj.charAt(0)) iguais = false;
        }
        if (iguais) return false;

        // Digitos verificadores
        int digitos = Integer.parseInt(cnpj.substring(12));
        int primeiro = 0;
        int k = 5;
        for (int i = 0; i < 12; i++) {
            primeiro += (k - i)*(cnpj.charAt(i) - 48);
            if (i == 3) k = 13;
        }
        primeiro = 11 - (primeiro%11);
        if (primeiro < 2) primeiro = 0;

        int segundo = 0;
        k = 6;
        for (int i = 0; i < 12; i++) {
            segundo += (k - i)*(cnpj.charAt(i) - 48);
            if (i == 4) k = 14;
        }
        segundo += 2*primeiro;
        segundo = 11 - (segundo%11);
        if (segundo < 2) segundo = 0;

        if (digitos != 10*primeiro + segundo) return false;
        return true;
    }
    
}