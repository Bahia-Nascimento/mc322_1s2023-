import java.time.LocalDate;
import java.time.Period;

public class SeguroPJ extends Seguro {
    private Frota frota;
    private ClientePJ cliente;
    
    public SeguroPJ(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora, Frota frota, ClientePJ cliente) {
        super(dataInicio, dataFim, seguradora);
        this.frota = frota;
        this.cliente = cliente;
        this.valorMensal = calcularValor();
    }

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

    public double calcularValor() {
        double valor = CalcSeguro.VALOR_BASE.getFator();
        int sinistrosCondutores = 0;
        for (int i = 0; i < listaCondutores.size(); i++) {
            sinistrosCondutores += listaCondutores.get(i).getListaSinistros().size();
        }
        int idade = (Period.between(this.cliente.getDataFundacao(), LocalDate.now())).getYears();
        valor = valor * (10 + cliente.getQtdeFuncionarios()/10) * (1+ (1/(frota.getListaVeiculos().size() + 2))) * (1 + (1/(idade + 2)))
        * (2 + cliente.getQtdeSinistros()/10) * (5 + sinistrosCondutores/10);
        valorMensal = valor;
        return valorMensal;
    }

    public void gerarSinistro(String endereco, String cpfCondutor) {
        // Se o condutor passado for valido, gera um novo sinistro e cadastra em ambos seguro e condutor
        Condutor condutor = findCondutor(cpfCondutor);
        if (condutor == null) {
            System.out.println("Por favor, cadastre o condutor antes no seguro antes de gerar o sinistro");
            return;
        }
        Sinistro novo = new Sinistro(LocalDate.now(), endereco, this, condutor);
        listaSinistros.add(novo);
        condutor.adicionarSinistro(novo);
        cliente.setQtdeSinistros(cliente.getQtdeSinistros() + 1);
        return;
    }

    @Override
    public String toString() {
        return "{" +
            " Id: " + getId() +
            ", Data de inicio: " + getDataInicio() +
            ", Data de expiracao: " + getDataFim() +
            ", Frota: " + frota.getCode() +
            ", Valor mensal: " + getValorMensal() +
            "}";
    }
}
