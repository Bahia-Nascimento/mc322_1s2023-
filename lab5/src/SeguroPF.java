import java.time.LocalDate;
import java.time.Period;

public class SeguroPF extends Seguro {
    private Veiculo veiculo;
    private ClientePF cliente;


    public Veiculo getVeiculo() {
        return this.veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public ClientePF getCliente() {
        return this.cliente;
    }

    public void setCliente(ClientePF cliente) {
        this.cliente = cliente;
    }

    public SeguroPF(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora, Veiculo veiculo, ClientePF cliente) {
        super(dataInicio, dataFim, seguradora);
        this.veiculo = veiculo;
        this.cliente = cliente;
    }

    public double calcularValor() {
        int idade = (Period.between(this.cliente.getDataNascimento(), LocalDate.now())).getYears();
        double valor = CalcSeguro.VALOR_BASE.getFator();
        if (idade < 30) {
            valor *= CalcSeguro.FATOR_MENOR_30.getFator();
        } else if (idade <= 60 && idade >= 30) {
            valor *= CalcSeguro.FATOR_30_60.getFator();
        } else if (idade > 60) {
            valor *= CalcSeguro.FATOR_MAIOR_60.getFator();
        }
        return valor * (1 + (1 / (cliente.getListaVeiculos().size() + 2))) * (2 + (cliente.getQtdeSinistros() / 10)) * (5 + con)
    }
}
