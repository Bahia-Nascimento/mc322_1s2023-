public enum MenuListar {
    CLIENTE(1),
    SINISTROS_CLIENTE(2),
    VEICULO_CLIENTE(3),
    FROTA_CLIENTE(4),
    SEGURO(5),
    VOLTAR(6);

    public final int operacao;

    MenuListar(int operacao) {
        this.operacao = operacao;
    }

    public int getOperacao() {
        return this.operacao;
    }
}
