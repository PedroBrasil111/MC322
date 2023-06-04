package lab05;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public abstract class Seguro {
    private final int id;
    private Date dataInicio;
    private Date dataFim;
    private Seguradora seguradora;
    private final List<Sinistro> listaSinistros;
    private final List<Condutor> listaCondutores;
    private int valorMensal;

    private int gerarId() {
        return hashCode();
    }

    public Seguro(Date dataInicio, Date dataFim, Seguradora seguradora) {
        id = gerarId();
        this.seguradora = seguradora;
        listaSinistros = new ArrayList<Sinistro>();
        listaCondutores = new ArrayList<Condutor>();
    }

    public abstract boolean autorizarCondutor();
    public abstract boolean desautorizarCondutor();
    public abstract double calcularValor();
    public abstract boolean gerarSinistro();

    public int getId() {
        return id;
    }
    public Date getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }
    public Date getDataFim() {
        return dataFim;
    }
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
    public Seguradora getSeguradora() {
        return seguradora;
    }
    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }
    public List<Condutor> getListaCondutores() {
        return listaCondutores;
    }
    public List<Sinistro> getListaSinistros() {
        return listaSinistros;
    }
    public int getValorMensal() {
        return valorMensal;
    }
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public abstract class Seguro {
    private final int id;
    private Date dataInicio;
    private Date dataFim;
    private Seguradora seguradora;
    private final List<Sinistro> listaSinistros;
    private final List<Condutor> listaCondutores;
    private int valorMensal;

    private int gerarId() {
        return hashCode();
    }

    public Seguro(Date dataInicio, Date dataFim, Seguradora seguradora) {
        id = gerarId();
        this.seguradora = seguradora;
        listaSinistros = new ArrayList<Sinistro>();
        listaCondutores = new ArrayList<Condutor>();
    }

    public abstract boolean autorizarCondutor();
    public abstract boolean desautorizarCondutor();
    public abstract double calcularValor();
    public abstract boolean gerarSinistro();

    public int getId() {
        return id;
    }
    public Date getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }
    public Date getDataFim() {
        return dataFim;
    }
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
    public Seguradora getSeguradora() {
        return seguradora;
    }
    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }
    public List<Condutor> getListaCondutores() {
        return listaCondutores;
    }
    public List<Sinistro> getListaSinistros() {
        return listaSinistros;
    }
    public int getValorMensal() {
        return valorMensal;
    }

}
