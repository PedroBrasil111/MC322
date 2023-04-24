package lab03;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Cliente {
	// Propriedades
	private String nome;
	private String endereco;
	private List<Veiculo> listaVeiculos = new ArrayList<Veiculo>();

	// Construtor
	public Cliente(String nome, String endereco, Veiculo...veiculos) {
		this.nome = nome;
		this.endereco = endereco;
		for (Veiculo v:veiculos)
			listaVeiculos.add(v);
	}

	// toString
	@Override
	public String toString() {
		String str = String.format("Cliente - %s:\n- Endereco: %s;\n- Veiculos:", nome, endereco);
		if (listaVeiculos.isEmpty())
			str += "\n- Nenhum veiculo cadastrado.";
		for (int i = 0; i < listaVeiculos.size(); i++) {
			str += "\n- " + (i + 1) + ". " + listaVeiculos.get(i).getPlaca();
		}
		return str;
	}
	
	// Métodos protegidos (utilizados tanto para ClientePF quanto ClientePJ)
	/* Retorna o valor inteiro do char na posicão pos de str */
	protected static int charAtToInt(String str, int pos) {
		return Character.getNumericValue(str.charAt(pos));
	}
	/* Se todos os caracteres de str forem iguais, retorna true; caso contrário, retorna false */
	protected static boolean todosCharIguais(String str) {
		char c = str.charAt(0); // para comparar
		for (int i = 1; i < str.length(); i++)
			if (str.charAt(i) != c)
				return false;
		return true;
	}
	/* Dada uma string no formato "dd/MM/yyy", retorna o objeto Date equivalente.
	 * Levanta uma excecão parseException se a string não estiver no formato especificado. */
	protected Date parseDate(String data) throws ParseException {
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		return formatoData.parse(data);
	}
	/* Dado um objeto Date, retorna a String equivalente no formato dd/MM/yyyy */
	protected String parseString(Date data) {
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		String str = formatoData.format(data);
		return str;
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

}