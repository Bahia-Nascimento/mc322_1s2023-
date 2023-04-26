import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int entrada;
        boolean flag = true;
        ArrayList<Seguradora> listaSeguradoras = new ArrayList<Seguradora>();
        Scanner s = new Scanner(System.in);
        System.out.println("Bem vindo!");
        while (flag) {
            System.out.println("1. Cadastros\n2. Listar\n3. Excluir\n4. Gerar Sinistro\n"
            + "5. Transferir Seguro\n6. Calcular Receita Seguradora\n0. Sair");
            entrada = Integer.parseInt(s.nextLine());
            Main.clearScreen();
            int seg;
            switch (entrada) {
                case 1:
                    Main.cadastrar(s, listaSeguradoras);
                    break;
                case 2:
                    Main.listar(s, listaSeguradoras);
                    break;
                case 3:
                    Main.excluir(s, listaSeguradoras);
                    break;
                case 4:
                    System.out.println("Indice da seguradora: ");
                    seg = Integer.parseInt(s.nextLine());
                    Main.gerarSinistro(s, listaSeguradoras.get(seg));
                    break;
                case 5:

                    break;
                case 6:

                    break;

                case 0:
                    flag = false;
                    break;
            }
        }
        s.close();
    }
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }
    public static void cadastrar(Scanner s, ArrayList<Seguradora> listaSeguradoras) {
        System.out.println("1. Cadastar Cliente\n2. Cadastrar Veiculo\n3. Cadastrar Seguradora\n4. Voltar");
        int entrada = Integer.parseInt(s.nextLine());
        Main.clearScreen();
        int seg;
        switch (entrada) {
            case 1:
                System.out.println("Indice da seguradora: ");
                seg = Integer.parseInt(s.nextLine());
                Main.clearScreen();
                Main.cadastrarCliente(s, listaSeguradoras.get(seg));
                break;
            case 2:
                System.out.println("Indice da seguradora: ");
                seg = Integer.parseInt(s.nextLine());
                Main.clearScreen();
                Main.cadastrarVeiculo(s, listaSeguradoras.get(seg));
                break;
            case 3:
                Seguradora seguradora = Main.cadastrarSeguradora(s);
                listaSeguradoras.add(seguradora);
                Main.clearScreen();
                System.out.print("Seguradora " + seguradora.getNome() + " cadastrada no indice ");
                System.out.println(listaSeguradoras.size() - 1);
                break;
            case 4:
                return;
        }
    }
    public static void listar(Scanner s, ArrayList<Seguradora> listaSeguradoras) {
        System.out.println("1. Listar Clientes por Seg.\n2. Listar Sinistros por Seg.\n3. Listar Sinistros por Cliente\n"
        + "4. Listar Veiculos por Cliente\n5. Listar Veiculos por Seg.\n6. Voltar");
        int entrada = Integer.parseInt(s.nextLine());
        Main.clearScreen();
        int seg;
        switch(entrada) {
            case 1:
                System.out.println("Indice da seguradora: ");
                seg = Integer.parseInt(s.nextLine());
                System.out.print("PF ou PJ? ");
                String tipo = s.nextLine();
                Main.clearScreen();
                listaSeguradoras.get(seg).listarClientes(tipo);
                break;
            case 2:
                System.out.println("Indice da seguradora: ");
                seg = Integer.parseInt(s.nextLine());
                Main.clearScreen();
                listaSeguradoras.get(seg).listarSinistros();
                break;
            case 3:
                System.out.println("Indice da seguradora: ");
                seg = Integer.parseInt(s.nextLine());
                System.out.print("Nome: ");
                String nome = s.nextLine();
                Main.clearScreen();
                listaSeguradoras.get(seg).visualizarSinistro(nome);
                break;
            case 4:

                break;
            case 5:
            
                break;
            case 6:
                return;
        }
    }
    public static void excluir(Scanner s, ArrayList<Seguradora> listaSeguradoras) {
        System.out.println("1. Excluir Cliente\n2. Excluir Veiculo\n3. Excluir Sinistro\n4. Voltar");
        int entrada = Integer.parseInt(s.nextLine());
        Main.clearScreen();
        int seg;
        switch (entrada) {
            case 1:
                System.out.println("Indice da seguradora: ");
                seg = Integer.parseInt(s.nextLine());
                Main.removerCliente(s, listaSeguradoras.get(seg));
                break;
            case 2:
                
                break;
            case 3:
                
                break;
            case 4:
                return;
        }
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
        if (!Validacao.validaNome(nome)) {
            Main.clearScreen();
            System.out.println("Nome invalido");
            return;
        }
        System.out.print("Endereco: ");
        String endereco = s.nextLine();
        switch (a) {
            case 1:
            System.out.print("Idade: ");
            int idade = Integer.parseInt(s.nextLine());
            System.out.print("CPF: ");
            String cpf = s.nextLine();
            if (!Validacao.validaCPF(cpf)) {
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
            if (!Validacao.validaCNPJ(cnpj)) {
                Main.clearScreen();
                System.out.println("CNPJ invalido");
                return;
            }
            System.out.print("Data da fundacao (YYYY-MM-DD): ");
            LocalDate dataFundacao = LocalDate.parse(s.nextLine());
            System.out.print("Quantidade de funcionarios: ");
            int qtdeFuncionarios = Integer.parseInt(s.nextLine());
            ClientePJ novoPj = new ClientePJ(nome, endereco, dataFundacao, cnpj, qtdeFuncionarios);
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