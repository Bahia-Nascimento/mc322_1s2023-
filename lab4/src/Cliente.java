import java.util.ArrayList;

public abstract class Cliente {
    private String nome;
    private String endereco;
    private ArrayList<Veiculo> listaVeiculos;
    private int qtdeSinistros;

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

    // Métodos
    public void cadastrarVeiculo(Veiculo veiculo) {
        this.listaVeiculos.add(veiculo);
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
    

}