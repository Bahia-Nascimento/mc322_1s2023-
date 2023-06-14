import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ArquivoSeguro implements I_Arquivo<Seguro> {
    
    public Boolean gravarArquivo(String caminho, Seguro s) {
        // Adiciona uma linha com as informacoes do objeto no arquivo em formato CSV especificado

        try {
            FileWriter w = new FileWriter(caminho);
            String linha = s.getId() + ", " + s.getDataInicio().toString() + ", " + s.getDataFim().toString() + ", "
            + s.getSeguradora().getNome() + ", \"";
            for (Sinistro sin : s.getListaSinistros()) {
                linha += sin.getId() + ","; 
            }
            linha += "\", ";
            for (Condutor c : s.getListaCondutores()) {
                linha += c.getCpf() + ","; 
            }
            linha += "\", ";
            linha += s.valorMensal;

            w.write(linha);
            w.close();
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo " + caminho);
            return false;
        }


        return true;
    }

    public ArrayList<String> lerArquivo(String caminho) {
        ArrayList<String> lista = new ArrayList<String>();
        try {
            File f = new File(caminho);
            Scanner s = new Scanner(f);
            s.nextLine();
            while (s.hasNextLine()) {
                lista.add(s.nextLine()); 
            }
            s.close();

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo " + caminho + " nao encontrado");
        }


        return lista;
    }

}
