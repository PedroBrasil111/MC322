package lab04;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
	// Propriedades
	private String nome;
	private String endereco;
	private List<Veiculo> listaVeiculos;
	private double valorSeguro;

	// Construtor
	public Cliente(String nome, String endereco, Veiculo...veiculos) {
		this.nome = nome;
		this.endereco = endereco;
		this.valorSeguro = 0;
		listaVeiculos = new ArrayList<Veiculo>();
		for (Veiculo v:veiculos)
			listaVeiculos.add(v);
	}

	// toString()
	/* Cliente - <nome>:
	 * - Endereco: <endereco>
	 * - Valor do seguro: <valorSeguro>
	 * - Veiculo(s): Nenhum veiculo cadastrado OU <veiculo1.placa>, <veiculo2.placa>, ... */
	public String toString() {
		String str = String.format("Cliente - %s:\n- Endereco: %s\n- Valor do seguro: %.2f\n" + 
		"- Veiculo(s): ", nome, endereco, valorSeguro);
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

	/* Retorna o valor do score para o cliente. Para clientes do tipo Cliente,
	 * não é especificada forma de calcular. Portanto, retorna o valor base para essa classe. */
	public double calculaScore() {
		return CalcSeguro.VALOR_BASE.getValor();
	}
	/* Adiciona o veiculo v a listaVeiculos, retorna boolean indicando se adicionou */
	public boolean adicionarVeiculo(Veiculo v) {
		return listaVeiculos.add(v);
	}
	/* Remove o veiculo v de listaVeiculos, retorna boolean indicando se removeu */
	public boolean removerVeiculo(Veiculo v) {
		return listaVeiculos.remove(v);
	}
	/* Lista os veiculos em listaVeiculos no formato "<i>. <veiculo.placa>", onde i é o
	 * índice do veículo em listaVeiculos. Retorna boolean indicando se listou algum veiculo */
	public boolean listarVeiculos() {
		if (! listaVeiculos.isEmpty()) {
			for (int i = 0; i < listaVeiculos.size(); i++)
				System.out.println(String.valueOf(i) + ". " + listaVeiculos.get(i).getPlaca());
			return true;
		}
		return false;
	}

	// Getters e setters
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public void setListaVeiculos(List<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}
	public List<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}
	public void setValorSeguro(double valorSeguro) {
		this.valorSeguro = valorSeguro;
	}
	public double getValorSeguro() {
		return valorSeguro;
	}

}