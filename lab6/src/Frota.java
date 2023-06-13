import java.util.ArrayList;

public class Frota {
    // Frotas vazias sao permitidas, eh obrigacao do usuario remover frotas vazias caso queira reduzir seus custos
    private String code;
    private ArrayList<Veiculo> listaVeiculos;

    public Frota(String code) {
        this.code = code;
        this.listaVeiculos = new ArrayList<Veiculo>();
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return this.listaVeiculos;
    }

    public String toString() {
        return "{" +
            " code: " + getCode() +
            ", Numero de veiculos: " + getListaVeiculos().size() +
            "}";
    }
       
    // Métodos
    public Boolean addVeiculo(Veiculo veiculo) {
        this.listaVeiculos.add(veiculo);
        return true;
    }
    public Boolean removeVeiculo(String placa) {
        Veiculo remover = findVeiculo(placa);
        if (remover == null) {
            return false;
        }
        this.listaVeiculos.remove(remover);
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

}
