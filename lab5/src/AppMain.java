import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

public class AppMain {
    public static void main(String[] args) {
        boolean flag = true;
        MenuOperacoes[] op = MenuOperacoes.values();
        int e;
        ArrayList<Seguradora> listaSeguradoras = new ArrayList<Seguradora>();
        Scanner s = new Scanner(System.in);
        System.out.println("Bem vindo!");
        testeLab();
        while (flag) {
            System.out.println("1. Cadastros\n2. Listar\n3. Excluir\n4. Gerar Sinistro\n"
            + "5. Transferir Seguro\n6. Calcular Receita Seguradora\n0. Sair");
            e = Integer.parseInt(s.nextLine());
            clearScreen();
            int seg;
            switch (op[e]) {
                case SAIR:
                    flag = false;
                    break;
                case CADASTRAR:
                    cadastrar(s, listaSeguradoras);
                    break;
                case LISTAR:
                    listar(s, listaSeguradoras);
                    break;
                case EXCLUIR:
                    excluir(s, listaSeguradoras);
                    break;
                case GERAR_SINISTRO:
                    System.out.println("Indice da seguradora: ");
                    seg = Integer.parseInt(s.nextLine());
                    gerarSinistro(s, listaSeguradoras.get(seg));
                    break;
                case GERAR_SEGURO:
                    System.out.println("Indice da seguradora: ");
                    seg = Integer.parseInt(s.nextLine());
                    gerarSeguro(s, listaSeguradoras.get(seg));
                    break;
                case CALCULAR_RECEITA_SEGURADORA:
                    System.out.print("Indice da seguradora: ");
                    seg = Integer.parseInt(s.nextLine());
                    System.out.println(listaSeguradoras.get(seg).calcularReceita());
                    break;
                default:
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
        System.out.println("1. Cadastar Cliente\n2. Cadastrar Veiculo\n3. Cadastrar Seguradora\n" + 
        "4. Cadastrar Condutor\n5. Cadastrar Frota\n6. Voltar");
        MenuCadastrar op = MenuCadastrar.values()[Integer.parseInt(s.nextLine()) - 1];
        clearScreen();
        int seg;
        switch (op) {
            case CLIENTE:
                System.out.print("Indice da seguradora: ");
                seg = Integer.parseInt(s.nextLine());
                clearScreen();
                cadastrarCliente(s, listaSeguradoras.get(seg));
                break;
            case VEICULO:
                System.out.print("Indice da seguradora: ");
                seg = Integer.parseInt(s.nextLine());
                clearScreen();
                cadastrarVeiculo(s, listaSeguradoras.get(seg));
                break;
            case SEGURADORA:
                Seguradora seguradora = cadastrarSeguradora(s);
                if (seguradora ==  null) {
                    return;
                }
                listaSeguradoras.add(seguradora);
                clearScreen();
                System.out.print("Seguradora " + seguradora.getNome() + " cadastrada no indice ");
                System.out.println(listaSeguradoras.size() - 1);
                break;
            case CONDUTOR:
                System.out.print("Indice da seguradora: ");
                seg = Integer.parseInt(s.nextLine());
                clearScreen();
                cadastrarCondutor(s, listaSeguradoras.get(seg));
                break;
            case FROTA:
                System.out.print("Indice da seguradora: ");
                seg = Integer.parseInt(s.nextLine());
                clearScreen();
                cadastrarFrota(s, listaSeguradoras.get(seg));
                break;
            default:
                return;
        }
    }
    public static void listar(Scanner s, ArrayList<Seguradora> listaSeguradoras) {
        System.out.println("1. Listar Clientes por Seguradora\n2. Listar Sinistros por Cliente\n3. Listar Veiculos por Cliente\n"
        + "4. Listar Frota por Cliente\n5. Listar Seguros por Seguradora\n6. Voltar");
        MenuListar op = MenuListar.values()[Integer.parseInt(s.nextLine()) - 1];
        clearScreen();
        Cliente cliente;
        int seg;
        switch(op) {
            case CLIENTE:
                System.out.print("Indice da seguradora: ");
                seg = Integer.parseInt(s.nextLine());
                clearScreen();
                System.out.print("PF ou PJ? ");
                String tipo = s.nextLine();
                clearScreen();
                listaSeguradoras.get(seg).listarClientes(tipo);
                break;
            case SINISTROS_CLIENTE:
                System.out.print("Indice da seguradora: ");
                seg = Integer.parseInt(s.nextLine());
                clearScreen();
                System.out.print("Insira CPF ou CNPJ do cliente: ");
                cliente = listaSeguradoras.get(seg).findCliente(s.nextLine());
                clearScreen();
                if (cliente == null) {
                    System.out.println("Cliente nao encontrado");
                    return;
                }
                ArrayList<Sinistro> lista = listaSeguradoras.get(seg).getSinistrosPorCliente(cliente);
                for (Sinistro sin : lista) {
                    System.out.println(sin.toString());
                }
                break;
            case VEICULO_CLIENTE:
                System.out.print("Indice da seguradora: ");
                seg = Integer.parseInt(s.nextLine());
                clearScreen();
                System.out.print("Insira CPF ou CNPJ do cliente: ");
                cliente = listaSeguradoras.get(seg).findCliente(s.nextLine());
                clearScreen();
                if (cliente == null) {
                    System.out.println("Cliente nao encontrado");
                    return;
                }
                cliente.listVeiculos();
                break;
            case FROTA_CLIENTE:
                System.out.print("Indice da seguradora: ");
                seg = Integer.parseInt(s.nextLine());
                clearScreen();
                System.out.print("CNPJ do cliente: ");
                cliente = listaSeguradoras.get(seg).findCliente(s.nextLine());
                clearScreen();
                if (cliente == null) {
                    System.out.println("Cliente nao encontrado");
                    return;
                }
                ((ClientePJ)cliente).listFrotas();
                break;
            case SEGURO:
                System.out.print("Indice da seguradora: ");
                seg = Integer.parseInt(s.nextLine());
                clearScreen();
                listaSeguradoras.get(seg).listSeguros();;
                break;
            default:
                return;
        }
    }
    public static void excluir(Scanner s, ArrayList<Seguradora> listaSeguradoras) {
        System.out.println("1. Excluir Cliente\n2. Excluir Veiculo\n3. Excluir Sinistro\n4. Voltar");
        MenuExcluir op = MenuExcluir.values()[Integer.parseInt(s.nextLine()) - 1];
        clearScreen();
        int seg;
        switch (op) {
            case CLIENTE:
                System.out.print("Indice da seguradora: ");
                seg = Integer.parseInt(s.nextLine());
                clearScreen();
                removerCliente(s, listaSeguradoras.get(seg));
                break;
            case VEICULO:
                System.out.print("Indice da seguradora: ");
                seg = Integer.parseInt(s.nextLine());
                clearScreen();
                removerVeiculo(s, listaSeguradoras.get(seg));
                break;
            case SEGURO:
                System.out.print("Indice da seguradora: ");
                seg = Integer.parseInt(s.nextLine());
                clearScreen();
                removerSinistro(s, listaSeguradoras.get(seg));
                break;
            case CONDUTOR:
                break;
            case FROTA:
                break;
            default:
                return;
        }
    }
    public static Seguradora cadastrarSeguradora(Scanner s) {
        System.out.print("CNPJ: ");
        String cnpj = s.nextLine();
        if (!Validacao.validaCNPJ(cnpj)) {
            System.out.println("CNPJ invalido");
            return null;
        }
        System.out.print("Nome: ");
        String nome = s.nextLine();
        System.out.print("Endereco: ");
        String endereco = s.nextLine();
        System.out.print("Telefone: ");
        String telefone = s.nextLine();
        System.out.print("Email: ");
        String email = s.nextLine();
        return new Seguradora(cnpj, nome, telefone, email, endereco);
    }
    public static void cadastrarCliente(Scanner s, Seguradora seguradora) {
        System.out.print("1. Cliente PF\n2. Cliente PJ\n");
        int a = Integer.parseInt(s.nextLine());
        System.out.print("Nome: ");
        String nome = s.nextLine();
        if (!Validacao.validaNome(nome)) {
            clearScreen();
            System.out.println("Nome invalido");
            return;
        }
        System.out.print("Endereco: ");
        String endereco = s.nextLine();
        System.out.print("Telefone: ");
        String telefone = s.nextLine();
        System.out.print("Email: ");
        String email = s.nextLine();
        switch (a) {
            case 1:
                System.out.print("CPF: ");
                String cpf = s.nextLine();
                if (!Validacao.validaCPF(cpf)) {
                    clearScreen();
                    System.out.println("CPF invalido");
                    return;
                }
                System.out.print("Educacao: ");
                String educacao = s.nextLine();
                System.out.print("Genero: ");
                String genero = s.nextLine();
                System.out.print("Data de nascimento (YYYY-MM-DD): ");
                LocalDate dataNascimento = LocalDate.parse(s.nextLine());
                System.out.print("Data da licenca (YYYY-MM-DD): ");
                LocalDate dataLicenca = LocalDate.parse(s.nextLine());
                ClientePF novoPf = new ClientePF(nome, endereco, telefone, email, cpf, dataNascimento, educacao, genero, dataLicenca);
                seguradora.cadastrarCliente(novoPf);
                clearScreen();
                System.out.println(novoPf.toString() + "\nCliente cadastrado");
            break;
            case 2:
                System.out.print("CNPJ: ");
                String cnpj = s.nextLine();
                if (!Validacao.validaCNPJ(cnpj)) {
                    clearScreen();
                    System.out.println("CNPJ invalido");
                    return;
                }
                System.out.print("Data da fundacao (YYYY-MM-DD): ");
                LocalDate dataFundacao = LocalDate.parse(s.nextLine());
                System.out.print("Quantidade de funcionarios: ");
                int qtdeFuncionarios = Integer.parseInt(s.nextLine());
                ClientePJ novoPj = new ClientePJ(nome, endereco, telefone, email, dataFundacao, cnpj, qtdeFuncionarios);
                seguradora.cadastrarCliente(novoPj);
                clearScreen();
                System.out.println(novoPj.toString() + "\nCliente cadastrado");
            break;
        }
    }
    public static void cadastrarVeiculo(Scanner s, Seguradora seguradora) {
        System.out.print("Digite CPF ou CNPJ do cliente: ");
        String cadastro = s.nextLine();
        Cliente cliente = seguradora.findCliente(cadastro);
        if (cliente == null) {
            clearScreen();
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
        if (cliente instanceof ClientePF) {
            ((ClientePF)cliente).cadastrarVeiculo(novo);
        } else if (cliente instanceof ClientePJ) {
            System.out.print("Code da frota: ");
            String code = s.nextLine();
            if (!((ClientePJ)cliente).atualizarFrota(code, novo)) {
                System.out.println("Frota nao encontrada");
                return;
            }
        }
        clearScreen();
        System.out.println(novo.toString() + "\nCadastrado para " + cliente.getNome());
    }
    public static void removerCliente(Scanner s, Seguradora seguradora) {
        System.out.print("Insira CPF ou CNPJ do cliente: ");
        String cadastro = s.nextLine();
        if (!seguradora.removerCliente(cadastro)) {
            clearScreen();
            System.out.println(cadastro + " nao encontrado");
            return;
        }
        clearScreen();
        System.out.println(cadastro + " removido");
    }
    public static void removerVeiculo(Scanner s, Seguradora seguradora) {
        System.out.print("Insira CPF ou CNPJ do cliente: ");
        String cadastro = s.nextLine();
        Cliente cliente = seguradora.findCliente(cadastro);
        if (cliente == null) {
            clearScreen();
            System.out.println(cadastro + " nao encontrado");
            return;
        }
        clearScreen();
        System.out.print("Insira placa do veiculo: ");
        String placa = s.nextLine();
        if(!cliente.removerVeiculo(placa)) {
            clearScreen();
            System.out.println(placa + " nao encontrada");
        }
        System.out.println(placa + " removido");
    }
    public static void cadastrarCondutor(Scanner s, Seguradora seguradora) {
        // Autoriza um condutor no seguro alvo, caso o condutor ja exista previamente na seguradora, autoriza o objeto ja existente
        System.out.print("Digite Id do seguro: ");
        int id = Integer.parseInt(s.nextLine());
        Seguro seguro = seguradora.findSeguro(id);
        if (seguro == null) {
            System.out.println("Seguro nao encontrado");
            return;
        }
        System.out.print("CPF: ");
        String cpf = s.nextLine();
        if (!Validacao.validaCPF(cpf)) {
            clearScreen();
            System.out.println("CPF invalido");
            return;
        }
        Condutor cond = seguradora.findCondutor(cpf);
        if (cond != null) {
            seguro.autorizarCondutor(cond);
            clearScreen();
            System.out.println("Condutor " + cond.getNome() + " cadastrado no seguro " + id);
            return;
        }

        System.out.print("Nome: ");
        String nome = s.nextLine();
        if (!Validacao.validaNome(nome)) {
            clearScreen();
            System.out.println("Nome invalido");
            return;
        }
        System.out.print("Endereco: ");
        String endereco = s.nextLine();
        System.out.print("Telefone: ");
        String telefone = s.nextLine();
        System.out.print("Email: ");
        String email = s.nextLine();
        System.out.print("Data de nascimento (YYYY-MM-DD): ");
        LocalDate dataNascimento = LocalDate.parse(s.nextLine());
        cond = new Condutor(cpf, nome, telefone, endereco, email, dataNascimento);
        seguro.autorizarCondutor(cond);
        seguradora.cadastrarCondutor(cond);
        clearScreen();
        System.out.println("Condutor " + cond.getNome() + " cadastrado no seguro " + id);
    }
    public static void cadastrarFrota(Scanner s, Seguradora seguradora) {
        // Frota sempre eh cadastrada vazia, responsabilidade do usuario gerencia-la
        System.out.print("Digite CNPJ do cliente: ");
        String cadastro = s.nextLine();
        ClientePJ cliente = (ClientePJ)seguradora.findCliente(cadastro);
        if (cliente == null) {
            clearScreen();
            System.out.println("Cliente nao encontrado");
            return;
        }
        System.out.print("Insira um codigo identificador para a frota: ");
        String code = s.nextLine();
        cliente.cadastrarFrota(code);
        clearScreen();
        System.out.println("Frota " + code + " cadastrada para " + cliente.getNome());
    }
    public static void gerarSinistro(Scanner s, Seguradora seguradora) {
        System.out.print("Id do seguro: ");
        int id = Integer.parseInt(s.nextLine());
        Seguro seguro = seguradora.findSeguro(id);
        if (seguro == null) {
            clearScreen();
            System.out.println("Seguro nao encontrado");
            return;
        }
        System.out.print("CPF do condutor: ");
        String cpf = s.nextLine();
        Condutor condutor = seguro.findCondutor(cpf);
        if (condutor == null) {
            clearScreen();
            System.out.println("Condutor nao cadastrado neste seguro");
            return;
        }
        System.out.print("Endereco do evento: ");
        String endereco = s.nextLine();
        clearScreen();
        seguro.gerarSinistro(endereco, cpf);
    }
    public static void gerarSeguro(Scanner s, Seguradora seguradora) {
        System.out.print("Insira CPF ou CNPJ do cliente: ");
        String cadastro = s.nextLine();
        if (Validacao.validaCPF(cadastro)) {
            ClientePF cliente = (ClientePF)seguradora.findCliente(cadastro);
            System.out.print("Insira placa do veiculo: ");
            String placa = s.nextLine();
            Veiculo v = cliente.findVeiculo(placa);
            if (v == null) {
                System.out.println("Veiculo nao encontrado");
                return;
            }
            seguradora.gerarSeguro(cliente, v);
        } else if (Validacao.validaCNPJ(cadastro)) {
            ClientePJ cliente = (ClientePJ)seguradora.findCliente(cadastro);
            System.out.print("Insira code da frota: ");
            String code = s.nextLine();
            Frota f = cliente.findFrota(code);
            if (f == null) {
                System.out.println("Frota nao encontrada");
                return;
            }
            seguradora.gerarSeguro(cliente, f);
        } else {
            System.out.println("CPF ou CNPJ invalido");
        }
    }
    // public static void testeLab() {
    //     // Executa os teste pedidos no lab atual (lab 5)

    //     // Instanciando cliente PF:
    //     System.out.println("Cadastrando um cliente PF");
    //     ClientePF testePf = new ClientePF("Jorge", "Rua x", "731.275.720-00", LocalDate.of(2002, 12, 20), 
    //     "Ensino superior", "Masculino", LocalDate.of(2020, 10, 21));
    //     seg.cadastrarCliente(testePf);
    //     clearScreen();
    //     System.out.println(testePf.toString() + "\nCliente cadastrado\n");
        
    //     // Instanciando cliente PJ:
    //     System.out.println("Cadastrando um cliente PJ");
    //     ClientePJ testePj = new ClientePJ("Pastelaria", "Rua y", LocalDate.of(1999, 5, 10),
    //     "46.068.425/0001-33", 6);
    //     seg.cadastrarCliente(testePj);
    //     clearScreen();
    //     System.out.println(testePj.toString() + "\nCliente cadastrado\n");

    //     // Um veiculo para cada:
    //     System.out.println("Cadastrando um veiculo para cada cliente");
    //     Veiculo carroPf = new Veiculo("JVZ-9341", "Ford", "Fusion", 2022);
    //     testePf.cadastrarVeiculo(carroPf);
    //     System.out.println(carroPf.toString() + "\nCadastrado para " + testePf.getNome());
    //     Veiculo carroPj = new Veiculo("NEP-0842", "Fiat", "Uno", 2000);
    //     testePj.cadastrarVeiculo(carroPj);
    //     System.out.println(carroPj.toString() + "\nCadastrado para " + testePj.getNome() + "\n");

    //     // Um sinistro para cada cliente:
    //     seg.gerarSinistro(carroPf, testePf, "Esquina");
    //     seg.gerarSinistro(carroPj, testePj, "Rotatoria");
    //     System.out.println("");

    //     // Chamando alguns metodos:
    //     System.out.println("Listando os clientes tipo PF:");
    //     seg.listarClientes("PF");
    //     System.out.println("\nListando os sinistros da pastelaria");
    //     seg.visualizarSinistros(testePj);
    //     System.out.println("\nListando todos os sinistros da seguradora " + seg.getNome());
    //     seg.listarSinistros();
    //     System.out.println("\nValor da receita da seguradora: " + seg.calcularReceita());
    // }
}
