import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ArquivoSinistro implements I_Arquivo<Sinistro> {
    private String caminho;

    public ArquivoSinistro(String caminho) {
        this.caminho = caminho;
    }

    public Boolean initArquivo() {
        // Gera o arquivo e escreve o cabecalho
        try {
            FileWriter w = new FileWriter(caminho);
            w.write("ID, DATA, ENDERECO, CONDUTOR, SEGURO\n");
            w.close();
            System.out.println("Arquivo de sinistros criado com sucesso");
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao criar o arquivo " + caminho);
            return false;
        }
    }
    
    public Boolean gravarArquivo(Sinistro s) {
        // Adiciona uma linha com as informacoes do objeto no arquivo em formato CSV especificado

        try {
            FileWriter w = new FileWriter(caminho, true);
            String linha = s.getId() + ", " + s.getData().toString() + ", " + s.getEndereco() + ", "
            + s.getCondutor().getCpf() + ", " + s.getSeguro().getId();
            w.write(linha + "\n");
            w.close();
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo " + caminho);
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