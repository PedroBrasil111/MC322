package lab04;

/* Contém o valor base para o preco de um seguro e os fatores para cada faixa de idade */

public enum CalcSeguro {
    VALOR_BASE (100.), // valor base
    FATOR_18_30 (1.2), // entre 18 e 30 anos
    FATOR_30_60 (1.),  // entre 30 e 60 anos
    FATOR_60_90 (1.5); // entre 60 e 90 anos

    private final double valor;

    CalcSeguro (double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return this.valor;
    }
}
