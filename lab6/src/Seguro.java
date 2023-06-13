import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Seguro implements I_Arquivo {
    protected static int numSeguros = 0;
    protected final int id;
    protected LocalDate dataInicio;
    protected LocalDate dataFim;
    protected Seguradora seguradora;
    protected ArrayList<Sinistro> listaSinistros;
    protected ArrayList<Condutor> listaCondutores;
    protected double valorMensal;

    public Seguro(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora) {
        this.id = numSeguros;
        numSeguros++;
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

    public double getValorMensal() {
        return this.valorMensal;
    }

    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }

    public String toString() {
        return "{" +
            " Id: " + getId() +
            ", Data de inicio: " + getDataInicio() +
            ", Data de expiracao: " + getDataFim() +
            ", Valor mensal: " + getValorMensal() +
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

    public Boolean gravarArquivo(String caminho) {
        // Adiciona uma linha com as informacoes do objeto no arquivo em formato CSV especificado

        try {
            FileWriter w = new FileWriter(caminho);
            String linha = id + ", " + dataInicio.toString() + ", " + dataFim + ", "
            + seguradora.getNome() + ", \"";
            for (Sinistro s : listaSinistros) {
                linha += s.getId() + ","; 
            }
            linha += "\", ";
            for (Condutor c : listaCondutores) {
                linha += c.getCpf() + ","; 
            }
            linha += "\", ";
            linha += valorMensal;

            w.write(linha);
            w.close();
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo " + caminho);
            return false;
        }


        return true;
    }

    public String lerArquivo() {
        String arq = "placeholder";

        return arq;
    }

    public abstract void gerarSinistro(String endereco, String cpfCondutor);
    public abstract double calcularValor();
    public abstract Cliente getCliente();
}