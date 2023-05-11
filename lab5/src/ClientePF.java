import java.time.LocalDate;
import java.time.Period;

public class ClientePF extends Cliente {
    private String cpf;
    private LocalDate dataNascimento;
    private String educacao;
    private String genero;
    private String classeEconomica;
    private LocalDate dataLicenca;


    public ClientePF(String nome, String endereco, String cpf, LocalDate dataNascimento, String educacao, String genero, String classeEconomica, LocalDate dataLicenca) {
        super(nome, endereco);
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.educacao = educacao;
        this.genero = genero;
        this.classeEconomica = classeEconomica;
        this.dataLicenca = dataLicenca;
        this.setValorSeguro(Seguradora.calculaPrecoSeguroCliente(this));
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

    @Override
    public String getCadastro() {
        return this.cpf;
    }

    @Override
    public void cadastrarVeiculo(Veiculo veiculo) {
        // Adiciona veiculo e atualiza o valor do seguro conforme convencao PF
        this.getListaVeiculos().add(veiculo);
        this.setValorSeguro(Seguradora.calculaPrecoSeguroCliente(this));
    }

    @Override
    public Boolean removerVeiculo(String placa) {
        // Remove veiculo e atualiza o valor do seguro conforme convencao PF, retorna se foi encontrado ou nao tal veiculo
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
            ", cpf: " + getCpf() +
            "}";
    }

    @Override
    public double calculaScore() {
        int idade = (Period.between(this.dataNascimento, LocalDate.now())).getYears();
        if (idade < 30 && idade >= 18) {
            return CalcSeguro.VALOR_BASE.getFator() * CalcSeguro.FATOR_18_30.getFator() * this.getListaVeiculos().size();
        } else if (idade < 60 && idade >= 30) {
            return CalcSeguro.VALOR_BASE.getFator() * CalcSeguro.FATOR_30_60.getFator() * this.getListaVeiculos().size();
        } else if (idade < 90 && idade >= 60) {
            return CalcSeguro.VALOR_BASE.getFator() * CalcSeguro.FATOR_60_90.getFator() * this.getListaVeiculos().size();
        }
        else return 0;
    }

}