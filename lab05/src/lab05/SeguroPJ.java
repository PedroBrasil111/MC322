package lab05;
import java.util.Date;

public class SeguroPJ extends Seguro {
    // Atributos
    Frota frota;
    ClientePJ cliente;

    // Construtor
    public SeguroPJ(Date dataInicio, Date dataFim, Seguradora seguradora, 
            Frota frota, ClientePJ cliente) {
        super(dataInicio, dataFim, seguradora);
        this.frota = frota;
        this.cliente = cliente;
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
    public void calcularValor() {
        double valor;
        if (frota == null || getSeguradora() == null || cliente == null)
            return;
        valor = CalcSeguro.VALOR_BASE.getValor() *
            // (10 + quantidadeFunc/10)
            (10 + getListaCondutores().size() / 10.) *
            // (1 + 1/(quantidadeVeiculos + 2))
            (1 + 1. / (frota.getListaVeiculos().size() + 2)) *
            // (1 + 1/(AnosPosFundacao + 2))
            (1 + 1. / (Data.calcularIdade(cliente.getDataFundacao()) + 2)) *
            // (2 + quantidadeSinistrosCliente/10)
            (2 + getSeguradora().getSinistrosPorCliente(cliente).size() / 10.) *
            // (5 + quantidadeSinistrosConsutor / 10)
            (5 + quantidadeSinistrosPorCondutor(cliente) / 10.);
            setValorMensal(valor);
    }

    // Getters e setters
    public void setFrota(Frota frota) {
        this.frota = frota;
    }
    public Frota getFrota() {
        return frota;
    }
    public void setCliente(ClientePJ cliente) {
        this.cliente = cliente;
    }
    public ClientePJ getCliente() {
        return cliente;
    }
}