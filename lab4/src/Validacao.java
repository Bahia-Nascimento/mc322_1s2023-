public class Validacao {
    public static Boolean validaCPF(String cpf) {
        cpf = cpf.replaceAll("[^\\d]", "");

        // Primeira analise
        if (cpf.length() != 11) return false;
        boolean iguais = true;
        for (int i = 1; i < cpf.length(); i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) iguais = false;
        }
        if (iguais) return false;

        // Digitos verificadores
        int digitos = Integer.parseInt(cpf.substring(9));
        int primeiro = 0;
        for (int i = 0; i < 9; i++) {
            primeiro += (10 - i)*(cpf.charAt(i) - 48);
        }
        primeiro = 11 - (primeiro%11);
        if (primeiro > 9) primeiro = 0;

        int segundo = 0;
        for (int i = 0; i < 9; i++) {
            segundo += (11 - i)*(cpf.charAt(i) - 48);
        }
        segundo += 2*primeiro;
        segundo = 11 - (segundo%11);
        if (segundo > 9) segundo = 0;

        if (digitos != 10*primeiro + segundo) return false;
        return true;
    }

    public static Boolean validaCNPJ(String cnpj) {
        cnpj = cnpj.replaceAll("[^\\d]", "");

        // Primeira analise
        if (cnpj.length() != 14) return false;
        boolean iguais = true;
        for (int i = 1; i < cnpj.length(); i++) {
            if (cnpj.charAt(i) != cnpj.charAt(0)) iguais = false;
        }
        if (iguais) return false;

        // Digitos verificadores
        int digitos = Integer.parseInt(cnpj.substring(12));
        int primeiro = 0;
        int k = 5;
        for (int i = 0; i < 12; i++) {
            primeiro += (k - i)*(cnpj.charAt(i) - 48);
            if (i == 3) k = 13;
        }
        primeiro = 11 - (primeiro%11);
        if (primeiro >= 10) primeiro = 0;

        int segundo = 0;
        k = 6;
        for (int i = 0; i < 12; i++) {
            segundo += (k - i)*(cnpj.charAt(i) - 48);
            if (i == 4) k = 14;
        }
        segundo += 2*primeiro;
        segundo = 11 - (segundo%11);
        if (segundo >= 10) segundo = 0;

        if (digitos != 10*primeiro + segundo) return false;
        return true;
    }

    public static Boolean validaNome(String nome) {
        if (!nome.equals("")) {
            return false;
        }
        String numeros = nome.replaceAll("[^\\d]", "");
        if (!numeros.equals("")) {
            return false;
        }
        return true;
    }
}