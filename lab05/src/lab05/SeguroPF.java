package lab05;

import java.util.Date;

public class SeguroPF extends Seguro {
    private Veiculo veiculo;
    private ClientePF cliente;

    public SeguroPF(Date dataInicio, Date dataFim, Seguradora seguradora,
            Veiculo veiculo, ClientePF cliente) {
        super(dataInicio, dataFim, seguradora);
        this.veiculo = veiculo;
        this.cliente = cliente;
    }

    // TODO
    public boolean autorizarCondutor() {
        return true;
    }
    // TODO
    public boolean desautorizarCondutor() {
        return true;
    }
    // TODO
    public boolean gerarSinistro() {
        return true;
    }
    public double calcularValor() {
        double qtdeSinistrosPorCondutor = 0.;
        double valor = CalcSeguro.VALOR_BASE.getValor() *
            // FATOR_IDADE muda a cada 30 anos -- indice idade/30 + 1 em CalcSeguro.values()
            CalcSeguro.values()[Data.calcularIdade(cliente.getDataNasc())/30 + 1].getValor() *
            (1 + 1. / (getSeguradora().getVeiculosPorCliente(cliente).size() + 2)) *
            (2 + getSeguradora().getSinistrosPorCliente(cliente).size() / 10.);
        // cálculo do num. de sinistros que os condutores possuem na seguradora
        for (Condutor condutor: getListaCondutores())
            qtdeSinistrosPorCondutor += condutor.getSinistrosPorSeguradora(getSeguradora()).size();
        valor *= 5 + qtdeSinistrosPorCondutor / 10.;
        return valor;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    public ClientePF getCliente() {
        return cliente;
    }
    public void setCliente(ClientePF cliente) {
        this.cliente = cliente;
    }

}
