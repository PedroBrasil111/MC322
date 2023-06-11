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
        calcularValor(); // inicializa o valor do seguro
    }

    // toString()
    /* SeguroPF - id <id>:
     * - Data de inicio: <Data.dateToString(dataInicio)>
     * - Data de fim: <Data.dateToString(dataFim)>
     * - Seguradora: <seguradora.nome>
     * - Valor mensal: <valorMensal>
     * - Sinistros: Nenhum sinistro cadastrado OU <sinistro1.id>, <sinistro2.id>, ...
     * - Condutores: Nenhum condutor cadastrado OU <condutor1.cpf>, <condutor2.cpf>, ... 
     * - Veiculo: <veiculo.placa>
     * - Cliente: <cliente.strDocumento()> */
    public String toString() {
        String str = super.toString().replaceFirst("Seguro", "SeguroPF");
        str += "\n- Veiculo: " + veiculo.getPlaca() + "\n- Cliente: " + cliente.strDocumento();
        return str;
    }

    /* Atribui o valor do seguro */
    @Override
    public void calcularValor() {
        int idade;
        double valor, fatorIdade;
        if (getSeguradora() == null || cliente == null)
            return;
        idade = Data.calcularIdade(cliente.getDataNasc());
        if (idade < 30)
            fatorIdade = CalcSeguro.FATOR_30.getValor();
        else if (idade <= 60)
            fatorIdade = CalcSeguro.FATOR_30_60.getValor();
        else
            fatorIdade = CalcSeguro.FATOR_60.getValor();
        // VALOR_BASE * FATOR_IDADE
        valor = CalcSeguro.VALOR_BASE.getValor() * fatorIdade *
            // (1 + 1 / (quantidadeVeiculos + 2))
            (1 + 1. / (getSeguradora().getVeiculosPorCliente(cliente).size() + 2)) *
            // (2 + quantidadeSinistrosCliente / 10)
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
    public Veiculo getVeiculo() {
        return veiculo;
    }
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    @Override
    public ClientePF getCliente() {
        return cliente;
    }
    public void setCliente(ClientePF cliente) {
        this.cliente = cliente;
    }

}