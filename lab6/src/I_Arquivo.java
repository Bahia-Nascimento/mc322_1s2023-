import java.util.ArrayList;

interface I_Arquivo<T> {

    Boolean gravarArquivo(T o);
    ArrayList<String> lerArquivo(String caminho);
}