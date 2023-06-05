package lab05;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public abstract class Seguro {
    // Atributos
    private final int id;
    private Date dataInicio;
    private Date dataFim;
    private Seguradora seguradora;
    private List<Sinistro> listaSinistros;
    private List<Condutor> listaCondutores;
    private double valorMensal;

    /* Gera o id baseado na posição de memória (único porém diferente a cada iteração). */
    private int gerarId() {
        return hashCode();
    }

    // Construtor
    public Seguro(Date dataInicio, Date dataFim, Seguradora seguradora) {
        id = gerarId();
        this.seguradora = seguradora;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        listaSinistros = new ArrayList<Sinistro>();
        listaCondutores = new ArrayList<Condutor>();
    }

    // TODO - comentar
    public boolean listarSinistros() {
        if (listaSinistros.isEmpty()) // lista vazia
            return false;
        for (int i = 0; i < listaSinistros.size(); i++)
            System.out.println(i + " - " +  listaSinistros.get(i).getId());
        return true;
    }
    // TODO - comentar
    public boolean listarCondutores() {
        if (listaCondutores.isEmpty()) // lista vazia
            return false;
        for (int i = 0; i < listaCondutores.size(); i++)
            System.out.println(i + " - " + listaCondutores.get(i).getNome() +
                    " CPF - " + listaCondutores.get(i).getCpf());
        return true;
    }
    // TODO - comentar
    public boolean autorizarCondutor(Condutor c) {
        if (listaCondutores.contains(c))
            return false;
        return listaCondutores.add(c);
    }
    // TODO - comentar
    public boolean desautorizarCondutor(Condutor c) {
        return listaCondutores.remove(c);
    }
    // TODO - comentar
    public boolean gerarSinistro(Date data, Condutor condutor, String endereco) {
        // se o condutor não está incluso no seguro, não cria o sinistro e retorna false
        if (! listaCondutores.contains((condutor)))
            return false;
        return listaSinistros.add(new Sinistro(data, endereco, condutor, this));
    }
    // TODO - comentar
    protected int quantidadeSinistrosPorCondutor(Cliente c) {
        int qtdeSinistrosCondutor = 0;
        for (Condutor condutor: getListaCondutores())
            qtdeSinistrosCondutor += condutor.getSinistrosPorSeguradora(getSeguradora()).size();
        return qtdeSinistrosCondutor;
    }
    public abstract void calcularValor();

    // Getters e setters
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
    public void setListaCondutores(List<Condutor> listaCondutores) {
        this.listaCondutores = listaCondutores;
    }
    public List<Sinistro> getListaSinistros() {
        return listaSinistros;
    }
    public void setListaSinistros(List<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }
    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }
    public double getValorMensal() {
        return valorMensal;
    }

}