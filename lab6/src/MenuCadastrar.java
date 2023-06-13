public enum MenuCadastrar {
    CLIENTE(1),
    VEICULO(2),
    SEGURADORA(3),
    CONDUTOR(4),
    FROTA(5),
    VOLTAR(6);

    public final int operacao;

    MenuCadastrar(int operacao) {
        this.operacao = operacao;
    }

    public int getOperacao() {
        return this.operacao;
    }
}
