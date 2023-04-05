public class Main {
    public static void main(String[] args) {
        // Teste classe Cliente
        Cliente c1 = new Cliente("Gabriel", "057.009.095-48", "21/10/2003", 19, "salvador");
        if (c1.verificarCpf(c1.getCpf())) {
            System.out.println((c1.toString()));
        }
        System.out.println("Oops... CPF errado");
        c1.setCpf("057.009.095-45");
        if (c1.verificarCpf(c1.getCpf())) {
            System.out.println((c1.toString()));
        } 
        // Teste classe Veiculo
        Veiculo v1 = new Veiculo("IZZ9A99", "Subaru", "Forester");
        System.out.println(v1.getMarca() + " - " + v1.getModelo());
        v1.setMarca("Fiat");
        v1.setModelo("Uno");
        System.out.println(v1.getMarca() + " - " + v1.getModelo());
        // Teste classe Seguradora
        Seguradora se1 = new Seguradora("Seguranca Seguros", "71 99588-9865", "seguros@seguranca.com", "logo ali, esquina, Campinas");
        System.out.println(se1.getNome() + " - Contato: " + se1.getEmail() + "\nEndereco: " + se1.getEndereco());
        se1.setEndereco("na esquina da outra rua");
        System.out.println("Nos mudamos: " + se1.getEndereco());
        // Teste classe Sinistro
        Sinistro si1 = new Sinistro("20/03/2023", "naquela rua la");
        Sinistro si2 = new Sinistro("null", "naquela outra rua la");
        System.out.println("Id: " + si1.getId() + "\nOcorrencia em: " + si1.getEndereco());
        System.out.println("Id: " + si2.getId() + "\nOcorrencia em: " + si2.getEndereco());
    }
}