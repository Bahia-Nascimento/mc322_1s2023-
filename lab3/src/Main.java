import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int a;
        boolean flag = true;
        ArrayList<Seguradora> listaSeguradoras = new ArrayList<Seguradora>();
        Scanner s = new Scanner(System.in);
        while (flag) {
            System.out.println("0. Sair\n1. Cadastrar seguradora\n2. Cadastrar cliente\n3. Casatrar cliente");
            a = Integer.parseInt(s.nextLine());
            int seg;
            switch (a) {
                case 0:
                flag = false;
                break;
                case 1:
                Seguradora seguradora = Main.cadastrarSeguradora(s);
                listaSeguradoras.add(seguradora);
                break;
                case 2:
                System.out.println("Indice da seguradora: ");
                seg = Integer.parseInt(s.nextLine());
                Main.cadastrarCliente(s, listaSeguradoras.get(seg));
                break;
                case 3:
                System.out.println("Indice da seguradora: ");
                seg = Integer.parseInt(s.nextLine());
                Main.cadastrarVeiculo(s, listaSeguradoras.get(seg));
                break;
            }
        }
        s.close();
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
            break;
            case 2:
            System.out.print("CNPJ: ");
            String cnpj = s.nextLine();
            System.out.print("Data da fundacao (YYYY-MM-DD): ");
            LocalDate dataFundacao = LocalDate.parse(s.nextLine());
            ClientePJ novoPj = new ClientePJ(nome, endereco, dataFundacao, cnpj);
            seguradora.cadastrarCliente(novoPj);
            break;
        }
    }
    public static void cadastrarVeiculo(Scanner s, Seguradora seguradora) {
        System.out.print("Cliente PF ou PJ? ");
        String tipo  = s.nextLine();
        ArrayList<Cliente> clientes = seguradora.listarClientes(tipo);
        if (tipo.equals("PF")) {
            System.out.print("Digite nome do cliente: ");
            String nome = s.nextLine();
            int i;
            for (i = 0; i < clientes.size(); i++) {
                if (clientes.get(i).getNome().equals(nome)) break;
                //TODO
            }
        }

    }
}