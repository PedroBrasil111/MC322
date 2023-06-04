package lab05;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientePJ extends Cliente {
    // Propriedades
    private final String cnpj;
    private Date dataFundacao;
    private int qtdeFuncionarios;
    // TODO -- remover final
    private final List<Frota> listaFrota;

    // Construtor
    public ClientePJ(String nome, String telefone, String endereco, String email, String cnpj,
            Date dataFundacao, int qtdeFuncionarios) {
        super(nome, telefone, endereco, email);
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
        this.qtdeFuncionarios = qtdeFuncionarios;
        listaFrota = new ArrayList<Frota>();
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
        str += String.format("\n- CNPJ: %s\n- Data de fundacao: %s\n- Quantidade de funcionarios: %d",
            cnpj, Data.dateToString(dataFundacao), qtdeFuncionarios);
		return str;
    }

    public String documento() {
        String str = "CNPJ: " + cnpj;
        return str;
    }
    public boolean cadastrarFrota(Frota f) {
        return listaFrota.add(f);
    }
    // TODO -- atualizar ponteiro
    public boolean atualizarFrota() {
        return true;
    }
    // TODO -- n faco ideia de pra q serve esse metodo
    public boolean getVeiculosPorFrota() {
        return true;
    }
    /*
    public List<Veiculo> getVeiculosPorFrota(Frota frota) {
        ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
        return 
    }
    */


/*
    @Override
    /* Retorna valor do score para o cliente. Para pessoa jurídica, o score é dado por
     * (VALOR_BASE * (1 + (quantidadeFunc)/100) * quantidadeCarros). 
    public double calculaScore() {
        return CalcSeguro.VALOR_BASE.getValor() * (1 + (qtdeFuncionarios / 100)) *
                getListaVeiculos().size();
    }
*/

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
    public int getQtdeFuncionarios() {
        return qtdeFuncionarios;
    }
    public void setQtdeFuncionarios(int qtdeFuncionarios) {
        this.qtdeFuncionarios = qtdeFuncionarios;
    }

}