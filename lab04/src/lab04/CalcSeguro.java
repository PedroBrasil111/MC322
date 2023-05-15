package lab04;

/* Contém o valor base para o preco de um seguro e os fatores para cada faixa de idade */

public enum CalcSeguro {
    VALOR_BASE (100.),
    FATOR_18_30 (1.2),
    FATOR_30_60 (1.),
    FATOR_60_90 (1.5);

    private final double valor;

    CalcSeguro (double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return this.valor;
    }
}
