public enum MenuListar {
    CLIENTE(1),
    SINISTROS_SEG(2),
    SINSITROS_CLIENTE(3),
    VEICULO_CLIENTE(4),
    VEICULO_SEGURADORA(5),
    VOLTAR(6);

    public final int operacao;

    MenuListar(int operacao) {
        this.operacao = operacao;
    }

    public int getOperacao() {
        return this.operacao;
    }
}
