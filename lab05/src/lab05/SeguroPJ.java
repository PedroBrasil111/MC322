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

    public boolean autorizarCondutor() {
        return true;
    }
    public boolean desautorizarCondutor() {
        return true;
    }
    public boolean gerarSinistro() {
        return true;
    }
    public double calcularValor() {
        double valor = CalcSeguro.VALOR_BASE.getValor() *
            (10 + (0.));
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
