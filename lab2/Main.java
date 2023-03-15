public class Main {
    public static void main(String[] args) {
        Cliente c1 = new Cliente("Gabriel", "057.009.095-45", "21/10/2003", 19, "salvador");
        if (c1.verificarCpf(c1.getCpf())) System.out.println((c1.toString()));
    }
}