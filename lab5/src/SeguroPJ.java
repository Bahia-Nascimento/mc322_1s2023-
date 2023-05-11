import java.time.LocalDate;

public class SeguroPJ extends Seguro {
    private Frota frota;
    private ClientePF cliente;


    public Frota getFrota() {
        return this.frota;
    }

    public void setVeiculo(Frota frota) {
        this.frota = frota;
    }

    public ClientePF getCliente() {
        return this.cliente;
    }

    public void setCliente(ClientePF cliente) {
        this.cliente = cliente;
    }


    public SeguroPJ(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora, Frota frota, ClientePF cliente) {
        super(dataInicio, dataFim, seguradora);
        this.frota = frota;
        this.cliente = cliente;
    }

}
