public class Sinistro {
    private static int numSinistros = 0;
    private final int id;
    private String data;
    private String endereco;
    private Seguradora seguradora;
    private Veiculo veiculo;
    private Cliente cliente;

    // Construtor
    public Sinistro(String data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente) {
        this.id = numSinistros;
        this.data = data;
        this.endereco = endereco;
        this.veiculo = veiculo;
        this.seguradora = seguradora;
        this.cliente = cliente;
        Sinistro.numSinistros++;
    }

    // Getters
    public int getId() {
        return this.id;
    }
    public String getData() {
        return this.data;
    }
    public String getEndereco() {
        return this.endereco;
    }
    public Seguradora getSeguradora() {
        return this.seguradora;
    }
    public Veiculo getVeiculo() {
        return this.veiculo;
    }
    public Cliente getCliente() {
        return this.cliente;
    }
  
    // Setters
    public void setData(String data) {
        this.data = data;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }   
    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", data='" + getData() + "'" +
            ", endereco='" + getEndereco() + "'" +
            ", seguradora='" + getSeguradora() + "'" +
            ", veiculo='" + getVeiculo() + "'" +
            ", cliente='" + getCliente() + "'" +
            "}";
    }

}