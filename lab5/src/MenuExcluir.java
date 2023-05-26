public enum MenuExcluir {
    CLIENTE(1),
    VEICULO(2),
    SEGURO(3),
    CONDUTOR(4),
    FROTA(5),
    VOLTAR(6);

    public final int operacao;

    MenuExcluir(int operacao) {
        this.operacao = operacao;
    }

    public int getOperacao() {
        return this.operacao;
    }
}
