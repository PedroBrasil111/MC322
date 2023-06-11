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
    /* Retorna o indice em listaFrota da frota com frota.code == code. Se nao estiver, retorna -1. */
    private int indiceFrota(String code) {
        for (int i = 0; i < listaFrota.size(); i++)
            if (listaFrota.get(i).getCode() == code)
                return i;
        return -1;
    }
    /* Adiciona o veiculo a frota se ela estiver em listaFrota,
     * retorna boolean indicando se adicionou. */
    public boolean atualizarFrota(Frota frota, Veiculo veiculo) {
        if (listaFrota.contains(frota))
            return frota.addVeiculo(veiculo);
        return false;
    }
    /* Remove o veiculo com veiculo.placa == placa da frota se ela estiver em listaFrota, 
     * retorna boolean indicando se removeu. */
    public boolean atualizarFrota(Frota frota, String placa) {
        if (listaFrota.contains(frota))
            return frota.removeVeiculo(placa);
        return false;
    }
    /* Remove a frota com frota.code = code de listasFrotas,
     * retorna boolean indicando se removeu. */
    public boolean atualizarFrota(String code) {
        int index = indiceFrota(code);
        if (index < 0)
            return false;
        listaFrota.remove(index);
        return true;
    }
    /* Retorna uma lista com os veiculos contidos na frota com frota.code == code em listaFrota,
     * retorna null se a frota nao estiver na lista. */
    public List<Veiculo> getVeiculosPorFrota(String code) {
        int index = indiceFrota(code);
        if (index < 0)
            return null;
        ArrayList<Veiculo> copia = new ArrayList<Veiculo>();
        copia.addAll(listaFrota.get(index).getListaVeiculos());
        return copia;
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