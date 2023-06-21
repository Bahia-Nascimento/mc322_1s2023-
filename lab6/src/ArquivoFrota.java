import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ArquivoFrota implements I_Arquivo<Frota> {
    private String caminho;

    public ArquivoFrota(String caminho) {
        this.caminho = caminho;
    }
    
    public Boolean gravarArquivo(Frota c) {
        // Adiciona uma linha com as informacoes do objeto no arquivo em formato CSV especificado

        try {
            FileWriter w = new FileWriter(caminho, true);
            w.write(c.toString() + "\n");
            w.close();
        } catch (IOException e) {
            System.out.println("Erro ao criar o arquivo " + caminho);
            return false;
        }


        return true;
    }

    public ArrayList<String> lerArquivo() {
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
