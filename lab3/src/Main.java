import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int a;
        boolean flag = true;
        ArrayList<Seguradora> listaSeguradoras = new ArrayList<Seguradora>();
        Scanner s = new Scanner(System.in);
        System.out.println("Bem vindo!\nSe desejar, digite 0 no menu para obter uma rotina de testes");
        while (flag) {
            System.out.println("1. Cadastrar seguradora\n2. Cadastrar cliente\n3. Remover cliente\n4. Listar clientes\n" 
            + "5. Cadastrar veiculo\n6. Gerar sinistro\n7. Visualizar sinistros de um cliente\n8. Listar sinistros");
            a = Integer.parseInt(s.nextLine());
            Main.clearScreen();
            int seg;
            switch (a) {
                case 0:
                System.out.println("Cadastre uma seguradora");
                listaSeguradoras.add(Main.cadastrarSeguradora(s));
                Seguradora teste = listaSeguradoras.get(0);
                Main.clearScreen();
                System.out.print("Seguradora " + teste.getNome() + " cadastrada no indice ");
                System.out.println(listaSeguradoras.size() - 1);
                System.out.println("Cadastre o cliente PF");
                Main.cadastrarCliente(s, teste);
                System.out.println("Cadastre o cliente PJ");
                Main.cadastrarCliente(s, teste);
                System.out.println("Cadastre o veiculo do cliente PF");
                Main.cadastrarVeiculo(s, teste);
                System.out.println("Cadastre o veiculo do cliente PJ");
                Main.cadastrarVeiculo(s, teste);
                System.out.println("Remova algum cliente");
                Main.removerCliente(s, teste);
                System.out.println("Cadastre outro cliente qualquer");
                Main.cadastrarCliente(s, teste);
                System.out.println("Cadastre o veiculo do cliente novo");
                Main.cadastrarVeiculo(s, teste);
                System.out.println("Listando clientes PF:");
                teste.listarClientes("PF");
                System.out.println("Gere um sinistro em algum cliente");
                Main.gerarSinistro(s, teste);
                System.out.println("Gere outro sinistro em algum cliente");
                Main.gerarSinistro(s, teste);
                System.out.println("Listando os sinistros da seguradora:");
                teste.listarSinistros();
                System.out.println("Visualize os sinistros de algum cliente");
                System.out.print("Nome: ");
                String clienteTeste = s.nextLine();
                Main.clearScreen();
                teste.visualizarSinistro(clienteTeste);           
                flag = false;
                break;
                case 1:
                Seguradora seguradora = Main.cadastrarSeguradora(s);
                listaSeguradoras.add(seguradora);
                Main.clearScreen();
                System.out.print("Seguradora " + seguradora.getNome() + " cadastrada no indice ");
                System.out.println(listaSeguradoras.size() - 1);
                break;
                case 2:
                System.out.println("Indice da seguradora: ");
                seg = Integer.parseInt(s.nextLine());
                Main.cadastrarCliente(s, listaSeguradoras.get(seg));
                break;
                case 3:
                System.out.println("Indice da seguradora: ");
                seg = Integer.parseInt(s.nextLine());
                Main.removerCliente(s, listaSeguradoras.get(seg));
                break;
                case 4:
                System.out.println("Indice da seguradora: ");
                seg = Integer.parseInt(s.nextLine());
                System.out.print("PF ou PJ? ");
                String tipo = s.nextLine();
                Main.clearScreen();
                listaSeguradoras.get(seg).listarClientes(tipo);
                break;
                case 5:
                System.out.println("Indice da seguradora: ");
                seg = Integer.parseInt(s.nextLine());
                Main.cadastrarVeiculo(s, listaSeguradoras.get(seg));
                break;
                case 6:
                System.out.println("Indice da seguradora: ");
                seg = Integer.parseInt(s.nextLine());
                Main.gerarSinistro(s, listaSeguradoras.get(seg));
                break;
                case 7:
                System.out.println("Indice da seguradora: ");
                seg = Integer.parseInt(s.nextLine());
                System.out.print("Nome: ");
                String nome = s.nextLine();
                Main.clearScreen();
                listaSeguradoras.get(seg).visualizarSinistro(nome);
                break;
                case 8:
                System.out.println("Indice da seguradora: ");
                seg = Integer.parseInt(s.nextLine());
                Main.clearScreen();
                listaSeguradoras.get(seg).listarSinistros();
                break;
            }
        }
        s.close();
    }
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }
    public static Seguradora cadastrarSeguradora(Scanner s) {
        System.out.print("Nome: ");
        String nome = s.nextLine();
        System.out.print("Endereco: ");
        String endereco = s.nextLine();
        System.out.print("Telefone: ");
        String telefone = s.nextLine();
        System.out.print("Email: ");
        String email = s.nextLine();
        return new Seguradora(nome, telefone, email, endereco);
    }
    public static void cadastrarCliente(Scanner s, Seguradora seguradora) {
        System.out.print("1. Cliente PF\n2. Cliente PJ\n");
        int a = Integer.parseInt(s.nextLine());
        System.out.print("Nome: ");
        String nome = s.nextLine();
        System.out.print("Endereco: ");
        String endereco = s.nextLine();
        switch (a) {
            case 1:
            System.out.print("Idade: ");
            int idade = Integer.parseInt(s.nextLine());
            System.out.print("CPF: ");
            String cpf = s.nextLine();
            if (!ClientePF.verificarCPF(cpf)) {
                Main.clearScreen();
                System.out.println("CPF invalido");
                return;
            }
            System.out.print("Educacao: ");
            String educacao = s.nextLine();
            System.out.print("Genero: ");
            String genero = s.nextLine();
            System.out.print("Classe Economica: ");
            String classeEconomica = s.nextLine();
            System.out.print("Data de nascimento (YYYY-MM-DD): ");
            LocalDate dataNascimento = LocalDate.parse(s.nextLine());
            System.out.print("Data da licenca (YYYY-MM-DD): ");
            LocalDate dataLicenca = LocalDate.parse(s.nextLine());
            ClientePF novoPf = new ClientePF(nome, endereco, cpf, dataNascimento, idade, educacao, genero, classeEconomica, dataLicenca);
            seguradora.cadastrarCliente(novoPf);
            Main.clearScreen();
            System.out.println(novoPf.toString() + "\nCliente cadastrado");
            break;
            case 2:
            System.out.print("CNPJ: ");
            String cnpj = s.nextLine();
            if (!ClientePJ.verificarCNPJ(cnpj)) {
                Main.clearScreen();
                System.out.println("CNPJ invalido");
                return;
            }
            System.out.print("Data da fundacao (YYYY-MM-DD): ");
            LocalDate dataFundacao = LocalDate.parse(s.nextLine());
            ClientePJ novoPj = new ClientePJ(nome, endereco, dataFundacao, cnpj);
            seguradora.cadastrarCliente(novoPj);
            Main.clearScreen();
            System.out.println(novoPj.toString() + "\nCliente cadastrado");
            break;
        }
    }
    public static void cadastrarVeiculo(Scanner s, Seguradora seguradora) {
        System.out.print("Digite CPF ou CNPJ do cliente: ");
        String cadastro = s.nextLine();
        Cliente cliente = seguradora.findCliente(cadastro);
        if (cliente == null) {
            Main.clearScreen();
            System.out.println("Cliente nao encontrado");
            return;
        }
        System.out.print("Marca: ");
        String marca = s.nextLine();
        System.out.print("Modelo: ");
        String modelo = s.nextLine();
        System.out.print("Placa: ");
        String placa = s.nextLine();
        System.out.print("Ano de fabricacao: ");
        int anoFabricacao = Integer.parseInt(s.nextLine());
        Veiculo novo = new Veiculo(placa, marca, modelo, anoFabricacao);
        cliente.cadastrarVeiculo(novo);
        Main.clearScreen();
        System.out.println(novo.toString() + "\nCadastrado para " + cliente.getNome());
    }
    public static void removerCliente(Scanner s, Seguradora seguradora) {
        System.out.print("Insira CPF ou CNPJ do cliente: ");
        String cadastro = s.nextLine();
        if (!seguradora.removerCliente(cadastro)) {
            Main.clearScreen();
            System.out.println(cadastro + " nao encontrado");
            return;
        }
        Main.clearScreen();
        System.out.println(cadastro + " removido");
    }
    public static void gerarSinistro(Scanner s, Seguradora seguradora) {
        System.out.print("CPF ou CNPJ do cliente: ");
        String cadastro = s.nextLine();
        System.out.print("Placa do veiculo: ");
        String placa = s.nextLine();
        System.out.print("Endereco do evento: ");
        String endereco = s.nextLine();
        Cliente cliente = seguradora.findCliente(cadastro);
        if (cliente == null) {
            Main.clearScreen();
            System.out.println("Cliente nao encontrado");
            return;
        }
        Veiculo veiculo = cliente.findVeiculo(placa);
        if (veiculo == null) {
            Main.clearScreen();
            System.out.println("Veiculo nao encontrado");
            return;
        }
        Main.clearScreen();
        seguradora.gerarSinistro(veiculo, cliente, endereco);
    }
}