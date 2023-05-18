import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Seguro {
    protected static int numSeguros = 0;
    protected final int id;
    protected LocalDate dataInicio;
    protected LocalDate dataFim;
    protected Seguradora seguradora;
    protected ArrayList<Sinistro> listaSinistros;
    protected ArrayList<Condutor> listaCondutores;
    protected int valorMensal;

    public Seguro(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora) {
        this.id = numSeguros;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.seguradora = seguradora;
        this.listaSinistros = new ArrayList<Sinistro>();
        this.listaCondutores = new ArrayList<Condutor>();
    }

    public int getId() {
        return this.id;
    }

    public LocalDate getDataInicio() {
        return this.dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return this.dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Seguradora getSeguradora() {
        return this.seguradora;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public ArrayList<Sinistro> getListaSinistros() {
        return this.listaSinistros;
    }

    public ArrayList<Condutor> getListaCondutores() {
        return this.listaCondutores;
    }

    public int getValorMensal() {
        return this.valorMensal;
    }

    public void setValorMensal(int valorMensal) {
        this.valorMensal = valorMensal;
    }

    public String toString() {
        return "{" +
            " id: " + getId() +
            ", dataInicio: " + getDataInicio() +
            ", dataFim: " + getDataFim() +
            ", seguradora: " + getSeguradora() +
            ", valorMensal: " + getValorMensal() +
            "}";
    }
    
    // Metodos
    public Boolean autorizarCondutor(Condutor c) {
        this.listaCondutores.add(c);
        return true;
    }

    public Boolean desautorizarCondutor(String cpf) {
        Condutor c = findCondutor(cpf);
        if (c != null) {
            this.listaCondutores.remove(c);
            return true;
        }
        return false;
    }

    public Condutor findCondutor(String cpf) {
        for (int i = 0; i < this.listaCondutores.size(); i++) {
            if (this.listaCondutores.get(i).getCpf().equals(cpf)) {
                return this.listaCondutores.get(i);
            }
        }
        return null;
    }

    public abstract double calcularValor();

    public abstract Cliente getCliente();
}