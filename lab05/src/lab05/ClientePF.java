package lab05;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class ClientePF extends Cliente {
    // Propriedades
    private final String cpf;
    private String genero;
    private String educacao;
    private Date dataNasc;
    private final List<Veiculo> listaVeiculos; // ponteiro p/ lista não se altera (final)

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
     * - Endereco: <endereco>
     * - Valor do seguro: <valorSeguro>
	 * - Veiculo(s): Nenhum veiculo cadastrado OU <veiculo1.placa>, <veiculo2.placa>, ...
     * - CPF: <cpf>
     * - Data de nascimento: <dataNascimento>
     * - Data da licenca: <dataLicenca>
     * - Genero: <genero>
     * - Educacao: <educacao>
     * - Classe economica: <classeEconomica> */
    public String toString() {
        String str = super.toString().replace("Cliente", "ClientePF");
        str += String.format("\n- CPF: %s\n- Data de nascimento: %s\n- Genero: %s\n" +
        "- Educacao: %s\n- Veiculos: ", cpf, Data.dateToString(dataNasc), genero, educacao);
        if (listaVeiculos.isEmpty())
            str += "Nenhum veiculo cadastrado";
        else {
            for (int i = 0; i < listaVeiculos.size(); i++) {
                str += listaVeiculos.get(i).getPlaca();
                if (i != listaVeiculos.size() - 1)
                    str += ", ";
            }
        }
        return str;
    }

    public String documento() {
        String str = "CPF: " + cpf;
        return str;
    }
	// Adiciona o veiculo v a listaVeiculos, retorna boolean indicando se adicionou
    public boolean cadastrarVeiculo(Veiculo v) {
        return listaVeiculos.add(v);
    }
	public boolean cadastrarVeiculo(String placa, String marca, String modelo, int anoFabricacao) {
		return cadastrarVeiculo(new Veiculo(placa, marca, modelo, anoFabricacao));
	}
	// Remove o veiculo v de listaVeiculos, retorna boolean indicando se removeu 
	public boolean removerVeiculo(Veiculo v) {
		return listaVeiculos.remove(v);
	}
    public boolean removerVeiculo(String placa) {
        for (Veiculo v: listaVeiculos)
            if (v.getPlaca().equals(placa))
                return removerVeiculo(v);
        return false;
    }

/*
    @Override
    /* Retorna valor do score para o cliente. Para pessoa jurídica, o score é dado por
     * (VALOR_BASE * FATOR_IDADE * quantidadeCarros). FATOR_BASE varia com a idade do cliente. 
    public double calculaScore() {
        CalcSeguro fatorIdade;
        int idade = Data.calcularIdade(dataNascimento);
        if (idade >= 18 && idade <= 90) {
            fatorIdade = CalcSeguro.values()[(idade / 30) + 1]; // fator se altera a cada 30 anos
            return CalcSeguro.VALOR_BASE.getValor() * fatorIdade.getValor() *
                    getListaVeiculos().size();
        } else // idades inválidas / cálculo não especificado -- retorna valor base
            return CalcSeguro.VALOR_BASE.getValor();
    }
*/

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

}