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

    // Construtor
    public Seguro(Date dataInicio, Date dataFim, Seguradora seguradora) {
        id = gerarId();
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.seguradora = seguradora;
        listaSinistros = new ArrayList<Sinistro>();
        listaCondutores = new ArrayList<Condutor>();
    }

    // toString()
    /* Seguro - id <id>:
     * - Data de inicio: <Data.dateToString(dataInicio)>
     * - Data de fim: <Data.dateToString(dataFim)>
     * - Seguradora: <seguradora.nome>
     * - Valor mensal: <valorMensal>
     * - Sinistros: Nenhum sinistro cadastrado OU <sinistro1.id>, <sinistro2.id>, ...
     * - Condutores: Nenhum condutor cadastrado OU <condutor1.cpf>, <condutor2.cpf>, ... */
    public String toString() {
        int i;
        String str = String.format("Seguro - id %d:\n- Data de inicio: %s\n- Data de fim: %s\n" +
                "- Seguradora: %s\n- Valor mensal: %.2f\n- Sinistros: ", id, Data.dateToString(dataInicio),
                Data.dateToString(dataFim), seguradora.getNome(), valorMensal);
        if (! listaSinistros.isEmpty()) {
            for (i = 0; i < listaSinistros.size() - 1; i++)
                str += listaSinistros.get(i).getId() + ", ";
            str += listaSinistros.get(listaSinistros.size() - 1).getId();
        } else
            str += "Nenhum sinistro cadastrado";
        str += "\n- Condutores: ";
        if (! listaCondutores.isEmpty()) {
            for (i = 0; i < listaCondutores.size() - 1; i++)
                str += listaCondutores.get(i).getCpf() + ", ";
            str += listaCondutores.get(listaCondutores.size() - 1).getCpf();
        } else
            str += "Nenhum condutor cadastrado";
        return str;
    }


    /* Retorna um id único para o objeto */
    private int gerarId() {
		// Gera um id aleatório baseado no endereco de memória (único p/ cada objeto).
		// Porém, esse id varia a cada iteracão do programa.
        return hashCode();
    }
    /* Lista os sinistros cadastrados no seguro no formato "i - <sinistro[i].id>,"
     * onde i é o índice do sinistro na lista. Retorna boolean indicando se imprimiu. */
    public boolean listarSinistros() {
        if (listaSinistros.isEmpty()) // lista vazia
            return false;
        for (int i = 0; i < listaSinistros.size(); i++)
            System.out.println(i + " - " +  listaSinistros.get(i).getId());
        return true;
    }
    /* Lista os condutores cadastrados no seguro no formato "i - <condutor[i].cpf>,"
     * onde i é o índice do condutor na lista. Retorna boolean indicando se imprimiu. */
    public boolean listarCondutores() {
        if (listaCondutores.isEmpty()) // lista vazia
            return false;
        for (int i = 0; i < listaCondutores.size(); i++)
            System.out.println(i + " - " + listaCondutores.get(i).getNome() +
                    " - CPF " + listaCondutores.get(i).getCpf());
        return true;
    }
	/* Adiciona o condutor c a listaCondutores, retorna boolean indicando se adicionou */
    public boolean autorizarCondutor(Condutor c) {
        if (listaCondutores.contains(c))
            return false;
        return listaCondutores.add(c);
    }
	/* Remove o condutor c de listaCondutores, retorna boolean indicando se removeu */
    public boolean desautorizarCondutor(Condutor c) {
        return listaCondutores.remove(c);
    }
    /* Gera um sinistro a partir dos parâmetros e o adiciona a listaSinistros.
     * Retorna boolean indicando se gerou. O condutor deve estar incluso no seguro. */
    public boolean gerarSinistro(Date data, Condutor condutor, String endereco) {
        Sinistro sinistro;
        boolean gerou;
        // se o condutor não está incluso no seguro, não cria o sinistro e retorna false
        if (! listaCondutores.contains((condutor)))
            return false;
        sinistro = new Sinistro(data, endereco, condutor, this);
        // adiciona o sinistro à lista do condutor
        condutor.adicionarSinistro(sinistro);
        gerou = listaSinistros.add(sinistro);
        calcularValor();
        return gerou;
    }
	/* Remove o sinistro s de listaSinistros, retorna boolean indicando se removeu */
    public boolean removerSinistro(Sinistro s) {
        boolean removeu = listaSinistros.remove(s);
        for (Condutor condutor: listaCondutores)
            condutor.getListaSinistros().remove(s);
        calcularValor();
        return removeu;
    }
	/* Retorna a quantidade de sinistros que os condutores possuem na seguradora */
    protected int quantidadeSinistrosPorCondutor() {
        int qtdeSinistrosCondutor = 0;
        for (Condutor condutor: getListaCondutores())
            // tamanho da lista é o número de sinistros
            qtdeSinistrosCondutor += condutor.getSinistrosPorSeguradora(getSeguradora()).size();
        return qtdeSinistrosCondutor;
    }
    /* Atribui o valor do seguro */
    public abstract void calcularValor();
    /* Retorna o cliente */
    public abstract Cliente getCliente();

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