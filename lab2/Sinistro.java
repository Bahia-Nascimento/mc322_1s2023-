public class Sinistro {
    private int id;
    private String data;
    private String endereco;
    private static int numSinistros = 0;

    // Construtor
    public Sinistro(String data, String endereco) {
        this.id = numSinistros;
        this.data = data;
        this.endereco = endereco;
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
  
    // Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setData(String data) {
        this.data = data;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}