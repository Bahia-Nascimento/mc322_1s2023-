public enum MenuOperacoes {
    SAIR(0),
    CADASTRAR(1),
    LISTAR(2),
    EXCLUIR(3),
    GERAR_SINISTRO(4),
    GERAR_SEGURO(5),
    CALCULAR_RECEITA_SEGURADORA(6),
    GRAVAR_ARQUIVOS(7);


    public final int operacao;

    MenuOperacoes(int operacao) {
        this.operacao = operacao;
    }

    public int getOperacao() {
        return this.operacao;
    }

    
}
