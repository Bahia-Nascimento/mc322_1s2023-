import java.util.ArrayList;

public class Seguradora {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Sinistro> listaSinistros;
    
    // Construtor
    public Seguradora(String nome, String telefone, String email, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.listaClientes = new ArrayList<Cliente>();
        this.listaSinistros = new ArrayList<Sinistro>();
    }

    // Getters
    public String getNome() {
        return nome;
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
    public ArrayList<Sinistro> getListaSinistros() {
        return this.listaSinistros;
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
        for (int i  = 0; i < this.listaClientes.size(); i++) {
            if (this.listaClientes.get(i).getCadastro().equals(cadastro)) {
                this.listaClientes.remove(i);
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

    public Boolean gerarSinistro(Veiculo veiculo, Cliente cliente, String endereco) {
        if (cliente.getListaVeiculos().contains(veiculo)) {
            Sinistro novo = new Sinistro(java.time.LocalDate.now(), endereco, this, veiculo, cliente);
            this.listaSinistros.add(novo);
            cliente.setQtdeSinistros(cliente.getQtdeSinistros() + 1);
            System.out.println(novo.toString());
            return true;
        }
        return false;
    }

    public Boolean visualizarSinistros(Cliente cliente) {
        // Imprime na tela todos os sinistros de um cliente
        for (int i = 0; i < this.listaSinistros.size(); i++) {
            if (this.listaSinistros.get(i).getCliente().equals(cliente)) {
                System.out.println(this.listaSinistros.get(i).toString());
            }
        }
        return true;
    }

    public void listarSinistros() {
        // IMprime na tela todos os sinistros cadastrados na seguradora
        int len = this.listaSinistros.size();
        if (len == 0){
            System.out.println("Nao ha sinistros cadastrados na seguradora");
        }
        for (int i = 0; i < len; i++) {
            System.out.println(listaSinistros.get(i).toString());
        }
    }

    public Boolean removerSinistro(int id) {
        // REmove sinistro e atualiza o valor do seguro do cliente
        for (int i = 0; i < listaSinistros.size(); i++) {
            if (listaSinistros.get(i).getId() == id) {
                Cliente cliente = listaSinistros.get(i).getCliente();
                cliente.setQtdeSinistros(cliente.getQtdeSinistros() - 1);
                if (cliente instanceof ClientePF) {
                    cliente.setValorSeguro(Seguradora.calculaPrecoSeguroCliente((ClientePF)cliente));
                    listaSinistros.remove(i);
                    return true;
                }
                if (cliente instanceof ClientePJ) {
                    cliente.setValorSeguro(Seguradora.calculaPrecoSeguroCliente((ClientePJ)cliente));
                    listaSinistros.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    public Cliente findCliente(String cadastro) {
        for (int i = 0; i < this.listaClientes.size(); i++) {
            if (this.listaClientes.get(i).getCadastro().equals(cadastro)) {
                return this.listaClientes.get(i);
            }
        }
        return null;
    }

    public void transferir(Cliente c1, Cliente c2) {
        // Transfere todos os veiculos e sinistros associados de c1 para c2
        ArrayList<Veiculo> veiculos = c1.getListaVeiculos();
        for (int i = 0; i < veiculos.size(); i++) {
            c2.cadastrarVeiculo(veiculos.get(i));
        }
        c1.limparVeiculos();
        ArrayList<Sinistro> sinistros = this.getListaSinistros();
        for (int i = 0; i < sinistros.size(); i++) {
            if (sinistros.get(i).getCliente().equals(c1)) {
                sinistros.get(i).setCliente(c2);
            }
        }
        c2.setQtdeSinistros(c2.getQtdeSinistros() + c1.getQtdeSinistros());
        c1.setQtdeSinistros(0);
        if (c1 instanceof ClientePF) {
            c1.setValorSeguro(Seguradora.calculaPrecoSeguroCliente((ClientePF)c1));
        }
        if (c1 instanceof ClientePJ) {
            c1.setValorSeguro(Seguradora.calculaPrecoSeguroCliente((ClientePJ)c1));
        }

    }

    public static double calculaPrecoSeguroCliente(ClientePF cliente) {
        return cliente.calculaScore() * (1 + cliente.getQtdeSinistros());
    }
    public static double calculaPrecoSeguroCliente(ClientePJ cliente) {
        return cliente.calculaScore() * (1 + cliente.getQtdeSinistros());
    }

    public double calcularReceita() {
        // Retorna a somatoria do valor do seguro de todos os clientes cadastrados na seguradora
        double receita = 0;
        for (int i = 0; i < listaClientes.size(); i++) {
            receita += listaClientes.get(i).getValorSeguro();
        }
        return receita;
    }
}