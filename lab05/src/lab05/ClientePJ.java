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
        if (! listaFrota.isEmpty()) {
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

    /* Retorna uma string no formato "CNPJ - <cnpj>". */
    @Override
    public String strDocumento() {
        String str = "CNPJ: " + cnpj;
        return str;
    }
    // TODO - comentar
    public boolean cadastrarFrota(Frota f) {
        if (listaFrota.contains(f))
            return false;
        return listaFrota.add(f);
    }
    // TODO - comentar
    private boolean addVeiculoFrota(Frota f, Veiculo v) {
        if (f.getListaVeiculos().contains(v))
            return false;
        return f.addVeiculo(v);
    }
    // TODO - comentar
    private boolean removerVeiculoFrota(Frota f, Veiculo v) {
        return f.addVeiculo(v);
    }
    // TODO - comentar
    private boolean removerFrota(Frota f) {
        return listaFrota.remove(f);
    }
    // TODO - comentar
    // Alem de adicionar e remover veıculos em uma frota, este metodo tambem deve 
    // ser capaz de remover a frota inteira.
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
    // TODO - comentar
    public boolean atualizarFrota(String atualizacao, Frota frota) {
        return atualizarFrota(atualizacao, frota, null);
    }
    // TODO - n faco ideia de pra q serve esse metodo
    public boolean getVeiculosPorFrota() {
        return true;
    }
    // TODO - comentar
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