import java.util.ArrayList;

public class Cliente {
    private String nome;
    private String endereco;
    private ArrayList<Veiculo> listaVeiculos;

    // Construtor
    public Cliente(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.listaVeiculos = new ArrayList<Veiculo>();
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

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    // Métodos
    public void cadastrarVeiculo(Veiculo veiculo) {
        this.listaVeiculos.add(veiculo);
    }

    public int findVeiculo(String placa) {
        for (int i = 0; i < this.listaVeiculos.size(); i++) {
            if (this.listaVeiculos.get(i).getPlaca().equals(placa)) return i;
        }
        return -1;
    }

    public String toString() {
        return "{" +
            " nome='" + getNome() + "'" +
            ", endereco='" + getEndereco() + "'" +
            "}";
    }
    

}