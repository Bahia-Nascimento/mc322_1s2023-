import java.time.LocalDate;
import java.util.ArrayList;

public class ClientePJ extends Cliente {
    private LocalDate dataFundacao;
    private String cnpj;
    private ArrayList<Frota> listaFrota;
    private int qtdeFuncionarios;

    public ClientePJ(String nome, String endereco, String telefone, String email, LocalDate dataFundacao, String cnpj, int qtdeFuncionarios) {
        super(nome, endereco, telefone, email);
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
        this.qtdeFuncionarios = qtdeFuncionarios;
        this.listaFrota = new ArrayList<Frota>();
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

    public ArrayList<Frota> getListaFrota() {
        return this.listaFrota;
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
    
    public void cadastrarFrota(String code) {
    Frota nova = new Frota(code);
    this.listaFrota.add(nova);
   }

    public Frota findFrota(String code) {
    for (Frota frota : listaFrota) {
        if (frota.getCode().equals(code)) {
            return frota;
        }
    }
    return null;
   }

    public Boolean atualizarFrota(String code) {
        Frota frota = findFrota(code);
        if (frota == null) {
        return false;
        }
        listaFrota.remove(frota);
        return true;
    }
    public Boolean atualizarFrota(String code, Veiculo v) {
        Frota frota = findFrota(code);
        if (frota == null) {
        return false;
        }
        frota.addVeiculo(v);
        return true;
    }
    public Boolean atualizarFrota(String code, String placa) {
        Frota frota = findFrota(code);
        if (frota == null) {
        return false;
        }
        frota.removeVeiculo(placa);
        return true;
    }

    public Boolean getVeiculosPorFrota(String code) {
        Frota frota = findFrota(code);
        if (frota == null) {
            return false;
        }
        for (Veiculo v : frota.getListaVeiculos()) {
            System.out.println(v.toString());
        }
        return true;
    }

    public void listFrotas() {
        for (Frota frota : listaFrota) {
            System.out.println(frota.toString());
        }
    }

    public void listVeiculos() {
        for (Frota f : listaFrota) {
            System.out.println("Frota " + f.getCode());
            for (Veiculo v : f.getListaVeiculos()) {
                System.out.println(v.toString());
            }
        }
    }
}