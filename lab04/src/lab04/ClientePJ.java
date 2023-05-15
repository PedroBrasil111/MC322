package lab04;

import java.util.Date;

public class ClientePJ extends Cliente {
    // Propriedades
    private final String cnpj;
    private Date dataFundacao;
    private int qtdeFuncionarios;

    // Construtor
    public ClientePJ(String nome, String endereco, String cnpj, Date dataFundacao,
            int qtdeFuncionarios, Veiculo...veiculos) {
        super(nome, endereco, veiculos);
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
        this.qtdeFuncionarios = qtdeFuncionarios;
    }

    // toString()
	/* ClientePJ - <nome>:
	 * - Endereco: <endereco>
     * - Valor do seguro: <valorSeguro>
	 * - Veiculo(s): Nenhum veiculo cadastrado OU <veiculo1.placa>, <veiculo2.placa>, ...
     * - CNPJ: <cnpj>
     * - Data de fundacao: <dataFundacao> */
    public String toString() {
        String str = super.toString().replace("Cliente", "ClientePJ");
        str += String.format("\n- CNPJ: %s\n- Data de fundacao: %s",
            cnpj, Data.dateToString(dataFundacao));
		return str;
    }

    /* Retorna o valor do score para o cliente. Para pessoa jurídica, o score é dado por
     * (VALOR_BASE * (1 + (quantidadeFunc)/100) * quantidadeCarros). */
    public double calculaScore() {
        return CalcSeguro.VALOR_BASE.getValor() * (1 + (qtdeFuncionarios / 100)) *
                getListaVeiculos().size();
    }

    // Getters e setters
    public String getCnpj() {
        return cnpj;
    }
    public Date getDataFundacao() {
        return dataFundacao;
    }
    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

}