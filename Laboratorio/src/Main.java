public class Main {
    public static void main(String[] args) {
        Cliente c1 = new Cliente("Gabriel", "057.009.095-45", "21/10/2003", 19, "salvador");
        if (c1.verificarCpf(c1.getCpf())) System.out.println((c1.toString()));
        Veiculo v1 = new Veiculo("IZZ9A99", "Subaru", "Forester");
        System.out.println(v1.getMarca());
        Seguradora se1 = new Seguradora("seguranca seguros", "71 99588-9865", "seguros@seguranca.com", "logo ali, esquina, Campinas");
        System.out.println(se1.getNome());
        Sinistro si1 = new Sinistro("20/03/2023", "na outra rua");
        System.out.println(si1.getEndereco());
    }
}