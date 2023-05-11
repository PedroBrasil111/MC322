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

	public double calculaScore() {
		return 0.;
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