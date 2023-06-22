import java.time.LocalDate;
import java.util.ArrayList;

public class Seguradora {
    private final String cnpj;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Seguro> listaSeguros;
    private ArrayList<Condutor> listaCondutores;
    private final ArquivoSeguro arqSeguro;
    private final ArquivoSinistro arqSinistro;
    private final ArquivoClientePF arqClientePF;
    private final ArquivoClientePJ arqClientePJ;
    private final ArquivoCondutor arqCondutor;
    private final ArquivoFrota arqFrota;
    private final ArquivoVeiculo arqVeiculo;

    // Construtor
    public Seguradora(String cnpj, String nome, String telefone, String email, String endereco, String caminho) {
        // O caminho passado deve ser o de uma pasta, terminando sempre em '/'
        // e sera tratado como o caminho de leitura e escrita de dados
        this.cnpj = cnpj;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.listaClientes = new ArrayList<Cliente>();
        this.listaSeguros = new ArrayList<Seguro>();
        this.listaCondutores = new ArrayList<Condutor>();
        this.arqSeguro = new ArquivoSeguro(caminho + "seguros.csv");
        this.arqSinistro = new ArquivoSinistro(caminho + "sinistros.csv");
        this.arqClientePF = new ArquivoClientePF(caminho + "clientesPF.csv");
        this.arqClientePJ = new ArquivoClientePJ(caminho + "clientesPJ.csv");
        this.arqCondutor = new ArquivoCondutor(caminho + "condutores.csv");
        this.arqFrota = new ArquivoFrota(caminho + "frotas.csv");
        this.arqVeiculo = new ArquivoVeiculo(caminho + "veiculos.csv");
    }

    // Getters
    public String getNome() {
        return nome;
    }
    public String getCnpj() {
        return cnpj;
    }
    public String getTelefone() {
        return telefone;
    }
    public String getEmail() {
        return email;
    }
    public String getEndereco() {
        return endereco;
    }
    public ArrayList<Cliente> getListaClientes() {
        return this.listaClientes;
    }
    public ArrayList<Seguro> getListaSeguros() {
        return this.listaSeguros;
    }
    public ArrayList<Condutor> getListaCondutores() {
        return this.listaCondutores;
    }
    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    // Metodos
    public Boolean cadastrarCliente(Cliente cliente) {
        return this.listaClientes.add(cliente);
    }
    
    public Boolean removerCliente(String cadastro) {
        /* Remove todos os seguros de um cliente, e depois o proprio cliente
            frotas e veiculos serao removidos pelo garbage collector, enquanto que sinistros seram mantidos no sistema*/
        for (Cliente cliente : listaClientes) {
            if (cliente.getCadastro().equals(cadastro)) {
                for (Seguro seg : listaSeguros) {
                    if (seg.getCliente().equals(cliente)) {
                        cancelarSeguro(seg.getId());
                    }
                }
                listaClientes.remove(cliente);
                return true;
            }
        }
        return false;
    }

    public void listarClientes(String tipo) {
        // Imprime na tela todos os clientes tipo PF ou PJ da seguradora
        if (tipo.equals("PF")) {
            for (int i  = 0; i < this.listaClientes.size(); i++) {
                if (this.listaClientes.get(i) instanceof ClientePF) {
                    System.out.println(this.listaClientes.get(i).toString());
                }
            }
        }
        else if (tipo.equals("PJ")) {
            for (int i  = 0; i < this.listaClientes.size(); i++) {
                if (this.listaClientes.get(i) instanceof ClientePJ) {
                    System.out.println(this.listaClientes.get(i).toString());
                }
            }
        }
    }

    public Boolean cadastrarCondutor(Condutor condutor) {
        return this.listaCondutores.add(condutor);
    }
    
    public Boolean removerCondutor(String cpf) {
        for (Condutor c : listaCondutores) {
            if (c.getCpf().equals(cpf)) {
                this.listaCondutores.remove(c);
                return true;
            }
        }
        return false;
    }

    public Boolean gerarSeguro(ClientePF cliente, Veiculo veiculo) {
        // Gera um novo seguro PF, seguros PF expiram em 2 anos
        SeguroPF novo = new SeguroPF(LocalDate.now(), LocalDate.now().plusYears(2), this, veiculo, cliente);
        listaSeguros.add(novo);
        System.out.println("Seguro cadastrado\n" + novo.toString());
        return true;
    }

    public Boolean gerarSeguro(ClientePJ cliente, Frota frota) {
        // Gera um novo seguro PJ, seguros PJ expiram em 1 ano
        SeguroPJ novo = new SeguroPJ(LocalDate.now(), LocalDate.now().plusYears(1), this, frota, cliente);
        listaSeguros.add(novo);
        System.out.println("Seguro cadastrado\n" + novo.toString());
        return true;
    }

    public Boolean cancelarSeguro(int id) {
        // Atualiza a data de fim e cancela o seguro, sinistros nao sao apagados
        for (Seguro seg : listaSeguros) {
            if (seg.id == id) {
                seg.setDataFim(LocalDate.now());
                listaSeguros.remove(seg);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Seguro> getSegurosPorCliente(Cliente cliente) {
        // Retorna todos os seguros que o cliente tem cadastrados na seguradora
        ArrayList<Seguro> lista = new ArrayList<Seguro>();
        if (cliente instanceof ClientePF) {
            for (Seguro seg : listaSeguros) {
                if (seg.getCliente().equals(cliente)) {
                    lista.add(seg);
                }
            }
        } else if (cliente instanceof ClientePJ) {
            for (Seguro seg : listaSeguros) {
                if (seg.getCliente().equals(cliente)) {
                    lista.add(seg);
                }
            }
        }
        return lista;
    }

    public ArrayList<Sinistro> getSinistrosPorCliente(Cliente cliente) {
        // Retorna todos os sinistros que o cliente em questão tem cadastrados em algum seguro da seguradora
        ArrayList<Sinistro> lista = new ArrayList<Sinistro>();
        ArrayList<Seguro> seguros = getSegurosPorCliente(cliente);
        for (Seguro seg : seguros) {
            lista.addAll(seg.getListaSinistros());
        }
        return lista;
}

    public Cliente findCliente(String cadastro) {
        // Retorna o cliente associado ao cadastro (cpf ou cnpj) passado
        for (int i = 0; i < this.listaClientes.size(); i++) {
            if (this.listaClientes.get(i).getCadastro().equals(cadastro)) {
                return this.listaClientes.get(i);
            }
        }
        return null;
    }

    public Seguro findSeguro(int id) {
        // Retorna o seguro associado ao id passado
        for (Seguro se : listaSeguros) {
            if (se.getId() == id) {
                return se;
            }
        }
        return null;
    }

    public Condutor findCondutor(String cpf) {
        for (Condutor c : listaCondutores) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        return null;
    }

    public double calcularReceita() {
        // Retorna a somatoria do valor de todos os seguros cadastrados na seguradora
        double receita = 0;
        for (Seguro seg : listaSeguros) {
            receita += seg.calcularValor();
        }
        return receita;
    }

    public void listSeguros() {
        // Imprime todos os seguros cadastrados na seguradora
        for (Seguro seg : listaSeguros) {
            System.out.println(seg.toString());
        }
    }

    public void listClientes() {
        // Imprime todos os clientes cadastrados na seguradora
        for (Cliente cli : listaClientes) {
            System.out.println(cli.toString());
        }
    }

    public String toString() {
        return "{" +
            " cnpj: " + getCnpj() +
            ", nome: " + getNome() +
            ", telefone: " + getTelefone() +
            ", email: " + getEmail() +
            ", endereco: " + getEndereco() +
            "}";
    }

    public void gravar() {
        if (arqSeguro.initArquivo()) {
            for (Seguro s : listaSeguros) {
                arqSeguro.gravarArquivo(s);
            }
        }
        if (arqSinistro.initArquivo()) {
            for (Cliente cliente : listaClientes) {
                for (Sinistro s : getSinistrosPorCliente(cliente)) {
                    arqSinistro.gravarArquivo(s);
                }
    
            }
        }
    }

    public void lerArquivos() {
        // Lendo veiculos
        System.out.println("Instanciando veiculos:");
        ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();
        for (String linha : arqVeiculo.lerArquivo()) {
            String[] array = linha.split(",");
            veiculos.add(new Veiculo(array[0], array[1], array[2], Integer.parseInt(array[3])));
            System.out.println(veiculos.get(veiculos.size() - 1).toString());
        }
        // Lendo clientes PF
        System.out.println("\nInstanciando clientes PF:");
        for (String linha : arqClientePF.lerArquivo()) {
            String[] array = linha.split(",");
            ClientePF cli = new ClientePF(array[1], array[3], array[2], array[4],
             array[0], LocalDate.parse(array[7]), array[6], array[5], null);
            System.out.println(cli.toString());
            cadastrarCliente(cli);
            for (Veiculo v : veiculos) {
                if (v.getPlaca().equals(array[8])) {
                    cli.cadastrarVeiculo(v);
                    gerarSeguro(cli, v);
                    break;
                }
            }
        }
        // Lendo frotas
        System.out.println("\nInstanciando frotas:");
        ArrayList<Frota> frotas = new ArrayList<Frota>();
        for (String linha : arqFrota.lerArquivo()) {
            String[] array = linha.split(",");
            Frota f = new Frota(array[0]);
            System.out.println(f.toString());
            int found = 0;
            for (Veiculo v : veiculos) {
                for (String s : array) {
                    if (s.equals(v.getPlaca())) {
                        f.addVeiculo(v);
                        found++;
                        break;
                    }
                }
                if (found == 3) break;
            }
            frotas.add(f);
        }
        // Lendo Clientes PJ
        System.out.println("\nInstanciando clientes PJ:");
        for (String linha : arqClientePJ.lerArquivo()) {
            String[] array  = linha.split(",");
            ClientePJ cli = new ClientePJ(array[1], array[3], array[2], array[4], LocalDate.parse(array[5]),
             array[0], 5);
            System.out.println(cli.toString());
            for (Frota f : frotas) {
                if (f.getCode().equals(array[6])) {
                    cli.addFrota(f);
                    gerarSeguro(cli, f);
                    break;
                }
            }
        }
        // Lendo condutores
        System.out.println("\nInstanciando condutores:");
        for (String linha : arqCondutor.lerArquivo()) {
            String[] array = linha.split(",");
            Condutor cond = new Condutor(array[0], array[1], array[2], array[3], array[4], LocalDate.parse(array[5]));
            System.out.println(cond.toString());
            cadastrarCondutor(cond);
        }
    }
}