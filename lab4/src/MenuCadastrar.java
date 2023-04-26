public enum MenuCadastrar {
    CLIENTE(1),
    VEICULO(2),
    SEGURADORA(3),
    VOLTAR(4);

    public final int operacao;

    MenuCadastrar(int operacao) {
        this.operacao = operacao;
    }

    public int getOperacao() {
        return this.operacao;
    }
}
