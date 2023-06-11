package lab05;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class ClientePF extends Cliente {
    // Atributos
    private final String cpf;
    private String genero;
    private String educacao;
    private Date dataNasc;
    private List<Veiculo> listaVeiculos; // ponteiro p/ lista não se altera (final)

    // Construtor
    public ClientePF(String nome, String telefone, String endereco, String email,
            String cpf, String genero, String educacao, Date dataNasc) {
        super(nome, telefone, endereco, email);
        this.cpf = cpf;
        this.genero = genero;
        this.educacao = educacao;
        this.dataNasc = dataNasc;
        listaVeiculos = new ArrayList<Veiculo>();
    }

    // toString()
	/* ClientePF - <nome>:
	 * - Telefone: <telefone>
	 * - Endereco: <endereco>
	 * - E-mail: <email> 
     * - CPF: <cpf>
     * - Data de nascimento: <dataNasc [dd/MM/aaaa]>
     * - Genero: <genero>
     * - Educacao: <educacao>
     * - Veiculos: "Nenhum veiculo cadastrado" OU <veiculo1.placa>, <veiculo2.placa>, ... */
    public String toString() {
        String str = super.toString().replace("Cliente", "ClientePF");
        str += String.format("\n- CPF: %s\n- Data de nascimento: %s\n- Genero: %s\n" +
        "- Educacao: %s\n- Veiculos: ", cpf, Data.dateToString(dataNasc), genero, educacao);
        if (listaVeiculos.isEmpty()) {
            str += "Nenhum veiculo cadastrado";
        } else {
            for (int i = 0; i < listaVeiculos.size(); i++) {
                str += listaVeiculos.get(i).getPlaca();
                if (i != listaVeiculos.size() - 1)
                    str += ", ";
            }
        }
        return str;
    }

    /* Retorna boolean indicando se o objeto obj eh igual. Comparacão é feita por CPF. */
    @Override
    public boolean equals(Object obj) {
		if (this == obj)
			return true;
        if (obj == null || getClass() != obj.getClass())
			return false;
        return cpf.equals(((ClientePF) obj).getCpf());
    }
    /* Retorna uma string no formato "CPF <cpf>". */
    @Override
    public String strDocumento() {
        String str = "CPF " + cpf;
        return str;
    }
    /* Lista os veiculos cadastrados para o cliente no formato "i - <veiculo[i].placa>,"
     * onde i é o índice do veiculo na lista. Retorna boolean indicando se imprimiu. */
    public boolean listarVeiculos() {
        if (listaVeiculos.isEmpty()) // lista vazia
            return false;
        for (int i = 0; i < listaVeiculos.size(); i++)
            System.out.println(i + " - " + listaVeiculos.get(i).getPlaca());
        return true;
    }
	/* Adiciona o veiculo v a listaVeiculos, retorna boolean indicando se adicionou */
    public boolean cadastrarVeiculo(Veiculo v) {
        if (listaVeiculos.contains(v))
            return false;
        return listaVeiculos.add(v);
    }
	/* Remove o veiculo v de listaVeiculos, retorna boolean indicando se removeu */
	public boolean removerVeiculo(Veiculo v) {
		return listaVeiculos.remove(v);
	}

    //Getters e setters
    public String getCpf() {
        return cpf;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public String getEducacao() {
        return educacao;
    }
    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }
    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }
    public Date getDataNasc() {
        return dataNasc;
    }
    public List<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }
    public void setListaVeiculos(List<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

}