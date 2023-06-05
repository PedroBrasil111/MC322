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
        if (cliente != null && getSeguradora() != null)
            calcularValor();
    }

    // TODO - comentar
    @Override
    public boolean autorizarCondutor(Condutor c) {
        boolean autorizou = super.autorizarCondutor(c);
        if (autorizou)
            calcularValor();
        return autorizou;
    }
    // TODO - comentar
    public boolean desautorizarCondutor(Condutor c) {
        boolean desautorizou = super.desautorizarCondutor(c);
        if (desautorizou)
            calcularValor();
        return desautorizou;
    }
    // TODO - comentar
    public boolean gerarSinistro(Date data, Condutor condutor, String endereco) {
        boolean gerou = super.gerarSinistro(data, condutor, endereco);
            if (gerou)
        calcularValor();
        return gerou;
    }
    public boolean removerSinistro(Sinistro s) {
        boolean removeu = super.removerSinistro(s);
        if (removeu)
            calcularValor();
        return removeu;
    }
    // TODO - comentar
    public void calcularValor() {
        double valor;
        if (getSeguradora() == null || cliente == null)
            return;
        valor = CalcSeguro.VALOR_BASE.getValor() *
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