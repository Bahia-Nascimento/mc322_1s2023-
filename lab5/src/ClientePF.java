import java.time.LocalDate;
import java.util.ArrayList;

public class ClientePF extends Cliente {
    private String cpf;
    private LocalDate dataNascimento;
    private String educacao;
    private String genero;
    private LocalDate dataLicenca;
    private ArrayList<Veiculo> listaVeiculos;


    public ClientePF(String nome, String endereco, String telefone, String email, String cpf, LocalDate dataNascimento,
     String educacao, String genero, LocalDate dataLicenca) {
        super(nome, endereco, telefone, email);
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.educacao = educacao;
        this.genero = genero;
        this.dataLicenca = dataLicenca;
        this.listaVeiculos = new ArrayList<Veiculo>();
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

    public LocalDate getDataLicenca() {
        return this.dataLicenca;
    }

    public void setDataLicenca(LocalDate dataLicenca) {
        this.dataLicenca = dataLicenca;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    @Override
    public String getCadastro() {
        return this.cpf;
    }

    public void cadastrarVeiculo(Veiculo veiculo) {
        // Adiciona veiculo e atualiza o valor do seguro conforme convencao PF
        this.listaVeiculos.add(veiculo);
    }

    public Boolean removerVeiculo(String placa) {
        // Remove veiculo e atualiza o valor do seguro conforme convencao PF, retorna se foi encontrado ou nao tal veiculo
        Veiculo remover = findVeiculo(placa);
        if (remover == null) {
            return false;
        }
        listaVeiculos.remove(remover);
        return true;
    }

    public Veiculo findVeiculo(String placa) {
        for (int i = 0; i < this.listaVeiculos.size(); i++) {
            if (this.listaVeiculos.get(i).getPlaca().equals(placa)) {
                return this.listaVeiculos.get(i);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "{" +
            " nome: " + getNome() +
            ", cpf: " + getCpf() +
            "}";
    }

    public void listVeiculos() {
        for (Veiculo ve : listaVeiculos) {
            System.out.println(ve.toString());
        }
    }    
}