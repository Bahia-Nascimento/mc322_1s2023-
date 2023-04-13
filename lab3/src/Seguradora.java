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
    
    public Boolean removerCliente(String nome) {
        for (int i  = 0; i < this.listaClientes.size(); i++) {
            if (this.listaClientes.get(i).getNome().equals(nome)) {
                this.listaClientes.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Cliente> listarClientes(String tipo) {
        ArrayList<Cliente> lista = new ArrayList<Cliente>();
        if (tipo.equals("PF")) {
            for (int i  = 0; i < this.listaClientes.size(); i++) {
                if (this.listaClientes.get(i) instanceof ClientePF) {
                    lista.add(this.listaClientes.get(i));
                }
            }
        }
        else if (tipo.equals("PJ")) {
            for (int i  = 0; i < this.listaClientes.size(); i++) {
                if (this.listaClientes.get(i) instanceof ClientePJ) {
                    lista.add(this.listaClientes.get(i));
                }
            }
        }

        return lista;
    }

    public Boolean gerarSinistro(Veiculo veiculo, Cliente cliente, String endereco) {
        if (cliente.getListaVeiculos().contains(veiculo)) {
            Sinistro novo = new Sinistro(java.time.LocalDate.now(), endereco, this, veiculo, cliente);
            this.listaSinistros.add(novo);
            return true;
        }
        return false;
    }

    public Boolean visualizarSinistro(String cliente) {
        for (int i = 0; i < this.listaSinistros.size(); i++) {
            if (this.listaSinistros.get(i).getCliente().getNome().equals(cliente)) {
                System.out.println(this.listaSinistros.get(i).toString());
            }
        }
        return true;
    }

    public ArrayList<Sinistro> listarSinistros() {
        return this.listaSinistros;
    }
}