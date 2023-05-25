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
    
    // Construtor
    public Seguradora(String cnpj, String nome, String telefone, String email, String endereco) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.listaClientes = new ArrayList<Cliente>();
        this.listaSeguros = new ArrayList<Seguro>();
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
    public ArrayList<Seguro> getListaSeguross() {
        return this.listaSeguros;
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

    public Boolean gerarSeguro(ClientePF cliente, Veiculo veiculo) {
        SeguroPF novo = new SeguroPF(LocalDate.now(), LocalDate.now().plusYears(3), this, veiculo, cliente);
        novo.autorizarCondutor(new Condutor(cliente.getCpf(), cliente.getNome(), cliente.getTelefone(), 
        cliente.getEndereco(), cliente.getEmail(), cliente.getDataNascimento()));
        return true;
    }

    public Boolean cancelarSeguro(int id) {
        for (Seguro seg : listaSeguros) {
            if (seg.id == id) {
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
                if (((SeguroPF)seg).getCliente().equals(cliente)) {
                    lista.add(seg);
                }
            }
        } else if (cliente instanceof ClientePJ) {
            for (Seguro seg : listaSeguros) {
                if (((SeguroPJ)seg).getCliente().equals(cliente)) {
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
        for (int i = 0; i < this.listaClientes.size(); i++) {
            if (this.listaClientes.get(i).getCadastro().equals(cadastro)) {
                return this.listaClientes.get(i);
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
        for (Seguro seg : listaSeguros) {
            System.out.println(seg.toString());
        }
    }

    public void listClientes() {
        for (Cliente cli : listaClientes) {
            System.out.println(cli.toString());
        }
    }
}