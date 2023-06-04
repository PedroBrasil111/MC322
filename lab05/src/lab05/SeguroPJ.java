package lab05;

import java.util.Date;

public class SeguroPJ extends Seguro {
    Frota frota;
    ClientePJ cliente;

    public SeguroPJ(Date dataInicio, Date dataFim, Seguradora seguradora, 
            Frota frota, ClientePJ cliente) {
        super(dataInicio, dataFim, seguradora);
        this.frota = frota;
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
        int qtdeSinistrosPorCondutor = 0;
        double valor = CalcSeguro.VALOR_BASE.getValor() *
            (10 + cliente.getQtdeFuncionarios() / 10.) *
            // quantidadeVeiculos
            (1 + 1. / (frota.getListaVeiculos().size() + 2)) *
            // anosPosFundacao
            (1 + 1. / (Data.calcularIdade(cliente.getDataFundacao()) + 2)) *
            // quantidadeSinistrosCliente
            (2 + getSeguradora().getSinistrosPorCliente(cliente).size() / 10.);
        for (Condutor condutor: getListaCondutores())
            qtdeSinistrosPorCondutor += condutor.getSinistrosPorSeguradora(getSeguradora()).size();
        valor *= 5 + qtdeSinistrosPorCondutor / 10.;
        return valor;
    }

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
