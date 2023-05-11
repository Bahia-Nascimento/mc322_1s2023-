public enum MenuExcluir {
    CLIENTE(1),
    VEICULO(2),
    SINISTRO(3),
    VOLTAR(4);

    public final int operacao;

    MenuExcluir(int operacao) {
        this.operacao = operacao;
    }

    public int getOperacao() {
        return this.operacao;
    }
}
