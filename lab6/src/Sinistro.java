import java.time.LocalDate;

public class Sinistro {
    private static int numSinistros = 0;
    private final int id;
    private LocalDate data;
    private String endereco;
    private Seguro seguro;
    private Condutor condutor;

    // Construtor
    public Sinistro(LocalDate data, String endereco, Seguro seguro, Condutor condutor) {
        this.id = numSinistros;
        this.data = data;
        this.endereco = endereco;
        this.seguro = seguro;
        this.condutor = condutor;
        Sinistro.numSinistros++;
    }

    // Getters
    public int getId() {
        return this.id;
    }
    public LocalDate getData() {
        return this.data;
    }
    public String getEndereco() {
        return this.endereco;
    }
    public Seguro getSeguro() {
        return this.seguro;
    }
    public Condutor getCondutor() {
        return this.condutor;
    }
  
    // Setters
    public void setData(LocalDate data) {
        this.data = data;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }   
    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }

    public String toString() {
        return "{" +
            " id: " + getId() +
            ", data: " + getData() +
            ", endereco: " + getEndereco() +
            ", id do seguro: " + seguro.getId() +
            ", condutor: " + getCondutor().getNome() +
            "}";
    }

}