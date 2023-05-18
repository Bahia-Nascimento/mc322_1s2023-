import java.time.LocalDate;
import java.time.Period;

public class SeguroPJ extends Seguro {
    private Frota frota;
    private ClientePJ cliente;


    public Frota getFrota() {
        return this.frota;
    }

    public void setVeiculo(Frota frota) {
        this.frota = frota;
    }

    public ClientePJ getCliente() {
        return this.cliente;
    }

    public void setCliente(ClientePJ cliente) {
        this.cliente = cliente;
    }


    public SeguroPJ(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora, Frota frota, ClientePJ cliente) {
        super(dataInicio, dataFim, seguradora);
        this.frota = frota;
        this.cliente = cliente;
    }

    public double calcularValor() {
        double valor = CalcSeguro.VALOR_BASE.getFator();
        int sinistrosCondutores = 0;
        for (int i = 0; i < listaCondutores.size(); i++) {
            sinistrosCondutores += listaCondutores.get(i).getListaSinistros().size();
        }
        int idade = (Period.between(this.cliente.getDataFundacao(), LocalDate.now())).getYears();

        return valor * (10 + cliente.getQtdeFuncionarios()/10) * (1+ (1/(frota.getListaVeiculos().size() + 2))) * (1 + (1/(idade + 2)))
        * (2 + cliente.getQtdeSinistros()/10) * (5 + sinistrosCondutores/10);
    }

}
