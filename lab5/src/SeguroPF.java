import java.time.LocalDate;
import java.time.Period;

public class SeguroPF extends Seguro {
    private Veiculo veiculo;
    private ClientePF cliente;

    public SeguroPF(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora, Veiculo veiculo, ClientePF cliente) {
        super(dataInicio, dataFim, seguradora);
        this.veiculo = veiculo;
        this.cliente = cliente;
    }

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
        int sinistrosCondutores = 0;
        for (int i = 0; i < listaCondutores.size(); i++) {
            sinistrosCondutores += listaCondutores.get(i).getListaSinistros().size();
        }
        return valor * (1 + (1 / (cliente.getListaVeiculos().size() + 2))) 
        * (2 + (cliente.getQtdeSinistros() / 10)) * (5 + (sinistrosCondutores/10));
    }

    public void gerarSinistro(String endereco, String cpfCondutor, String placa) {
        Condutor condutor = findCondutor(cpfCondutor);
        if (condutor == null) {
            System.out.println("Por favor, cadastre o condutor antes no seguro antes de gerar o sinistro");
            return;
        }
        if (!veiculo.getPlaca().equals(placa)) {
            System.out.println("Placa inserida nao corresponde com o veiculo segurado");
            return;
        }
        Sinistro novo = new Sinistro(LocalDate.now(), endereco, this, veiculo, condutor);
        listaSinistros.add(novo);
        return;
    }
}
