package lab05;

import java.util.Date;

public class SeguroPF extends Seguro {
    // Atributos
    private Veiculo veiculo;
    private ClientePF cliente;

    // Construtor
    public SeguroPF(Date dataInicio, Date dataFim, Seguradora seguradora,
            Veiculo veiculo, ClientePF cliente) {
        super(dataInicio, dataFim, seguradora);
        this.veiculo = veiculo;
        this.cliente = cliente;
    }

    // TODO - comentar
    @Override
    public boolean autorizarCondutor(Condutor c) {
        boolean retorno = super.autorizarCondutor(c);
        calcularValor();
        return retorno;
    }
    // TODO - comentar
    public boolean desautorizarCondutor(Condutor c) {
        boolean retorno = super.desautorizarCondutor(c);
        calcularValor();
        return retorno;
    }
    // TODO - comentar
    public boolean gerarSinistro(Date data, Condutor condutor, String endereco) {
        boolean retorno = super.gerarSinistro(data, condutor, endereco);
        calcularValor();
        return retorno;
    }
    // TODO - comentar
    public void calcularValor() {
        double valor = CalcSeguro.VALOR_BASE.getValor() *
            // FATOR_IDADE muda a cada 30 anos -- indice idade/30 + 1 em CalcSeguro.values()
            CalcSeguro.values()[Data.calcularIdade(cliente.getDataNasc())/30 + 1].getValor() *
            // (1 + 1 / (quantidadeVeiculos + 2))
            (1 + 1. / (getSeguradora().getVeiculosPorCliente(cliente).size() + 2)) *
            // (2 + quantidadeSinistrosCliente / 10)
            (2 + getSeguradora().getSinistrosPorCliente(cliente).size() / 10.) *
            // (5 + quantidadeSinistrosConsutor / 10)
            (5 + quantidadeSinistrosPorCondutor(cliente) / 10.);
        setValorMensal(valor);
    }

    // Getters e setters
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
