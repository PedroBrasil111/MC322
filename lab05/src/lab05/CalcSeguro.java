package lab05;

/* Contém o valor base para o preco de um seguro e os fatores para cada faixa de idade */

public enum CalcSeguro {
    VALOR_BASE (10.), // valor base
    FATOR_30 (1.25), // idade < 30
    FATOR_30_60 (1.),  // 30 <= idade <= 60
    FATOR_60 (1.5); // idade > 60

    private final double valor;

    CalcSeguro (double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return this.valor;
    }
}