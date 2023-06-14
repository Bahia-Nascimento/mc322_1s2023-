import java.util.ArrayList;

interface I_Arquivo<T> {


    Boolean gravarArquivo(String caminho, T o);
    ArrayList<String> lerArquivo(String caminho);
}