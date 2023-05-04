import java.util.ArrayList;

public abstract class Cliente {
    private String nome;
    private String endereco;
    private ArrayList<Veiculo> listaVeiculos;
    private int qtdeSinistros;
    private double valorSeguro;

    // Construtor
    public Cliente(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.listaVeiculos = new ArrayList<Veiculo>();
        this.qtdeSinistros = 0;
    }

    // Getters
    public String getNome() {
        return nome;
    }
    public String getEndereco() {
        return endereco;
    }
    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }
    public String getCadastro() {
        return null;
    }
    public int getQtdeSinistros() {
        return qtdeSinistros;
    }
    public double getValorSeguro() {
        return valorSeguro;
    }
    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public void setQtdeSinistros(int qtdeSinistros) {
        this.qtdeSinistros = qtdeSinistros;
    }
    public void setValorSeguro(double valor) {
        this.valorSeguro = valor;
    }

    // Métodos
    public void cadastrarVeiculo(Veiculo veiculo) {
        this.listaVeiculos.add(veiculo);
    }

    public Boolean removerVeiculo(String placa) {
        Veiculo remover = findVeiculo(placa);
        if (remover == null) {
            return false;
        }
        this.listaVeiculos.remove(remover);
        return true;
    }

    public void limparVeiculos() {
        this.listaVeiculos = new ArrayList<Veiculo>();
    }

    public Veiculo findVeiculo(String placa) {
        for (int i = 0; i < this.listaVeiculos.size(); i++) {
            if (this.listaVeiculos.get(i).getPlaca().equals(placa)) {
                return this.listaVeiculos.get(i);
            }
        }
        return null;
    }

    public String toString() {
        return "{" +
            " nome='" + getNome() + "'" +
            ", endereco='" + getEndereco() + "'" +
            "}";
    }
    
    public abstract double calculaScore();

    public void listarVeiculos() {
        for (int i = 0; i < listaVeiculos.size(); i++) {
            System.out.println(listaVeiculos.get(i).toString());
        }
    }

}