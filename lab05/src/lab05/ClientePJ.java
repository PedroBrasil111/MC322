package lab05;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientePJ extends Cliente {
    // Propriedades
    private final String cnpj;
    private Date dataFundacao;
    private final List<Frota> listaFrota;

    // Construtor
    public ClientePJ(String nome, String telefone, String endereco, String email, String cnpj,
            Date dataFundacao) {
        super(nome, telefone, endereco, email);
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
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
        str += String.format("\n- CNPJ: %s\n- Data de fundacao: %s",
            cnpj, Data.dateToString(dataFundacao));
		return str;
    }

    public String documento() {
        String str = "CNPJ: " + cnpj;
        return str;
    }
    public boolean cadastrarFrota(Frota f) {
        return listaFrota.add(f);
    }
    public boolean atualizarFrota() {
        return true;
    }
    public boolean getVeiculosPorFrota() {
        return true;
    }


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

}