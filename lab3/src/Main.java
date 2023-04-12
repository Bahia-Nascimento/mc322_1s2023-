import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        java.time.LocalDate date = LocalDate.of(12, 10, 2003);
        ClientePJ alo = new ClientePJ("carlos coxinhas", "esquina", date, "12.345.678/0001-95");
        System.out.println(alo.verificarCNPJ(alo.getCnpj()));
    }
}