import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ArquivoSeguro implements I_Arquivo<Seguro> {
    private String caminho;

    public ArquivoSeguro(String caminho) {
        try {
            FileWriter w = new FileWriter(caminho);
            this.caminho = caminho;
            w.write("ID, DATA_INICIO, DATA_FIM, SEGURADORA, LISTA_SINISTROS, LISTA_CONDUTORES, VALOR_MENSAL\n");
            w.close();
            System.out.println("Arquivo de seguros criado com sucesso");
        } catch (IOException e) {
            System.out.println("Erro ao criar o arquivo " + caminho);
        }
    }
    
    public Boolean gravarArquivo(Seguro s) {
        // Adiciona uma linha com as informacoes do objeto no arquivo em formato CSV especificado

        try {
            FileWriter w = new FileWriter(caminho, true);
            String linha = s.getId() + ", " + s.getDataInicio().toString() + ", " + s.getDataFim().toString() + ", "
            + s.getSeguradora().getNome() + ", \"";

            // Adicionando a lista de sinistros entre aspas
            for (int i = 0; i < s.getListaSinistros().size(); i++) {
                linha += s.getListaSinistros().get(i).getId();
                if (i != s.getListaSinistros().size() - 1) {
                    linha += ",";
                } 
            }
            linha += "\", \"";

            // Adicionando a lista de condutores entre aspas
            for (int i = 0; i < s.getListaCondutores().size(); i++) {
                linha += s.getListaCondutores().get(i).getCpf();
                if (i != s.getListaCondutores().size() - 1) {
                    linha += ",";
                } 
            }
            linha += "\", ";

            linha += s.valorMensal;

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
