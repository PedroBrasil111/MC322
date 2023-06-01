package lab05;

/* Contém o valor base para o preco de um seguro e os fatores para cada faixa de idade */

public enum CalcSeguro {
    VALOR_BASE (10.), // valor base
    FATOR_0 (1.25), // entre 18 e 30 anos
    FATOR_30 (1.),  // entre 30 e 60 anos
    FATOR_60 (1.5); // entre 60 e 90 anos

    private final double valor;

    CalcSeguro (double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return this.valor;
    }
}
