package lab05;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientePJ extends Cliente {
    // Atributos
    private final String cnpj;
    private Date dataFundacao;
    private List<Frota> listaFrota;

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
	 * - Telefone: <telefone>
	 * - Endereco: <endereco>
	 * - E-mail: <email>
     * - CNPJ: <cnpj>
     * - Data de fundacao: <dataFundacao [dd/MM/aaaa]>
     * - Quantidade de funcionarios: <qtdeFuncionarios>
     * - Frotas: "Nenhuma frota cadastrada" OU <frota1.code>, <frota2.code>, ... */
    public String toString() {
        String str = super.toString().replace("Cliente", "ClientePJ");
        str += String.format("\n- CNPJ: %s\n- Data de fundacao: %s\n" +
            "- Frotas: ", cnpj, Data.dateToString(dataFundacao));
        if (listaFrota.isEmpty()) {
            str += "Nenhuma frota cadastrada";
        } else {
            for (int i = 0; i < listaFrota.size(); i++) {
                str += listaFrota.get(i).getCode();
                if (i != listaFrota.size() - 1)
                    str += ", ";
            }
        }
		return str;
    }
    /* Retorna boolean indicando se o cliente c é igual. Comparacão é feita por CNPJ. */
    public boolean equals(ClientePJ c) {
        if (c == null || ! cnpj.equals(c.getCnpj()))
            return false;
        return true;
    }
    /* Retorna uma string no formato "CNPJ <cnpj>". */
    @Override
    public String strDocumento() {
        String str = "CNPJ " + cnpj;
        return str;
    }
    /* Insere a frota f na lista de frotas (se for única). Retorna boolean indicando se inseriu. */
    public boolean cadastrarFrota(Frota f) {
        if (listaFrota.contains(f))
            return false;
        return listaFrota.add(f);
    }
    /* Adiciona o veiculo v à frota f. Retorna boolean indicando se adicionou. */
    private boolean addVeiculoFrota(Frota f, Veiculo v) { 
        return f.addVeiculo(v);
    }
    /* Remove o veiculo v da frota f. Retorna boolean indicando se removeu. */
    private boolean removerVeiculoFrota(Frota f, Veiculo v) {
        return f.addVeiculo(v);
    }
    /* Remove a frota f da lista de frotas. Retorna boolean indicando se removeu. */
    private boolean removerFrota(Frota f) {
        return listaFrota.remove(f);
    }
    /* Dependendo do valor de "atualizacao", realiza uma operacão diferente:
     * "addVeic" para adicionar veiculo à frota,
     * "rmVeic" para remover veiculo da frota,
     * "rmFrota" para remover a frota do cliente.
     * Retorna boolean indicando se a operacão ocorreu. */
    public boolean atualizarFrota(String atualizacao, Frota frota, Veiculo veiculo) {
        if (! listaFrota.contains(frota))
            return false;
        switch (atualizacao) {
            case "addVeic":
                return addVeiculoFrota(frota, veiculo);
            case "rmVeic":
                return removerVeiculoFrota(frota, veiculo);
            case "rmFrota":
                return removerFrota(frota);
            default:
                return false;
        }
    }
    /* Se atualizacao for "rmFrota", remove a frota do cliente.
     * Retorna boolean indicando se removeu. */
    public boolean atualizarFrota(String atualizacao, Frota frota) {
        return atualizarFrota(atualizacao, frota, null);
    }
    // TODO - n faco ideia de pra q serve esse metodo
    public boolean getVeiculosPorFrota() {
        return true;
    }
    /* Lista as frotas cadastradas para o cliente no formato "i - <frota[i].code>,"
     * onde i é o índice da frota na lista. Retorna boolean indicando se imprimiu. */
	public boolean listarFrotas() {
		if (listaFrota.isEmpty()) // lista vazia
			return false;
		for (int i = 0; i < listaFrota.size(); i++)
			System.out.println(i + " - " + listaFrota.get(i).getCode());
		return true;
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
    public List<Frota> getListaFrota() {
        return listaFrota;
    }
    public void setListaFrota(List<Frota> listaFrota) {
        this.listaFrota = listaFrota;
    }

}