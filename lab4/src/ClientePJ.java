import java.time.LocalDate;

public class ClientePJ extends Cliente {
    private LocalDate dataFundacao;
    private String cnpj;
    private int qtdeFuncionarios;

    public ClientePJ(String nome, String endereco, LocalDate dataFundacao, String cnpj, int qtdeFuncionarios) {
        super(nome, endereco);
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
        this.qtdeFuncionarios = qtdeFuncionarios;
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


    public int getQtdeFuncionarios() {
        return this.qtdeFuncionarios;
    }

    public void setQtdeFuncionarios(int qtdeFuncionarios) {
        this.qtdeFuncionarios = qtdeFuncionarios;
    }


    @Override
    public String getCadastro() {
        return this.cnpj;
    }


    @Override
    public String toString() {
        return "{" +
            " nome: " + getNome() +
            ", cnpj: " + getCnpj() +
            "}";
    }
    
}