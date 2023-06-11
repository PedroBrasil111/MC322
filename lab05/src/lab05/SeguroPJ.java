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

    // toString()
    /* SeguroPJ - id <id>:
     * - Data de inicio: <Data.dateToString(dataInicio)>
     * - Data de fim: <Data.dateToString(dataFim)>
     * - Seguradora: <seguradora.nome>
     * - Valor mensal: <valorMensal>
     * - Sinistros: Nenhum sinistro cadastrado OU <sinistro1.id>, <sinistro2.id>, ...
     * - Condutores: Nenhum condutor cadastrado OU <condutor1.cpf>, <condutor2.cpf>, ... 
     * - Frota: <frota.code>
     * - Cliente: <cliente.strDocumento()> */
    public String toString() {
        String str = super.toString().replaceFirst("Seguro", "SeguroPJ");
        str += "\n- Frota: " + frota.getCode() + "\n- Cliente: " + cliente.strDocumento();
        return str;
    }

    /* Atribui o valor do seguro */
    @Override
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
            // (5 + quantidadeSinistrosCondutor / 10)
            (5 + quantidadeSinistrosPorCondutor() / 10.);
            setValorMensal(valor);
    }
	/* Adiciona o condutor c a listaCondutores, retorna boolean indicando se adicionou.
     * Atualiza o valor do seguro. */
    @Override
    public boolean autorizarCondutor(Condutor c) {
        boolean autorizou = super.autorizarCondutor(c);
        if (autorizou)
            calcularValor();
        return autorizou;
    }
	/* Remove o condutor c de listaCondutores, retorna boolean indicando se removeu.
     * Atualiza o valor do seguro. */
    @Override
    public boolean desautorizarCondutor(Condutor c) {
        boolean desautorizou = super.desautorizarCondutor(c);
        if (desautorizou)
            calcularValor();
        return desautorizou;
    }
    /* Gera um sinistro a partir dos parâmetros e o adiciona a listaSinistros.
     * Retorna boolean indicando se gerou. O condutor deve estar incluso no seguro.
     * Atualiza o valor do seguro. */
    @Override
    public boolean gerarSinistro(Date data, Condutor condutor, String endereco) {
        boolean gerou = super.gerarSinistro(data, condutor, endereco);
            if (gerou)
        calcularValor();
        return gerou;
    }
	/* Remove o sinistro s de listaSinistros, retorna boolean indicando se removeu.
     * Atualiza o valor do seguro. */
    @Override
    public boolean removerSinistro(Sinistro s) {
        boolean removeu = super.removerSinistro(s);
        if (removeu)
            calcularValor();
        return removeu;
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