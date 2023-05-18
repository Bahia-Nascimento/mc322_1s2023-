import java.time.LocalDate;

public class ClientePJ extends Cliente {
    private LocalDate dataFundacao;
    private String cnpj;
    private int qtdeFuncionarios;

    public ClientePJ(String nome, String endereco, String telefone, String email, LocalDate dataFundacao, String cnpj, int qtdeFuncionarios) {
        super(nome, endereco, telefone, email);
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
    public void cadastrarVeiculo(Veiculo veiculo) {
        // Adiciona veiculo e atualiza o valor do seguro conforme convencao PJ
        this.getListaVeiculos().add(veiculo);
        this.setValorSeguro(Seguradora.calculaPrecoSeguroCliente(this));
    }

    @Override
    public Boolean removerVeiculo(String placa) {
            // Remove veiculo e atualiza o valor do seguro conforme convencao PJ, retorna se foi encontrado ou nao tal veiculo
        Veiculo remover = findVeiculo(placa);
        if (remover == null) {
            return false;
        }
        this.getListaVeiculos().remove(remover);
        this.setValorSeguro(Seguradora.calculaPrecoSeguroCliente(this));
        return true;
    }

    @Override
    public String toString() {
        return "{" +
            " nome: " + getNome() +
            ", cnpj: " + getCnpj() +
            "}";
    }

    @Override
    public double calculaScore() {
        return CalcSeguro.VALOR_BASE.getFator() * (1 + qtdeFuncionarios/100) * getListaVeiculos().size();
    }
    
}