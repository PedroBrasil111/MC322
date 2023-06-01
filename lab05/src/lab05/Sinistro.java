package lab05;

import java.util.Date;

public class Sinistro {
	// Propriedades
	private final int id;
	private Date data;
	private String endereco;
	private Condutor condutor;
	private Seguro seguro;

	private int gerarId() {
		return hashCode();
	}

	// Construtor
	public Sinistro(Date data, String endereco, Condutor condutor, Seguro seguro) {
		// Gera um id aleatório baseado no endereco de memória (único p/ cada objeto).
		// Porém, esse id varia a cada iteracão do programa.
		id = gerarId();
		this.data = data;
		this.endereco = endereco;
		this.condutor = condutor;
		this.seguro = seguro;
	}

	// toString()
	/* Sinistro - id <id>:
	 * - Data: <data>
	 * - Endereco: <endereco>
	 * - Seguradora: <seguradora.nome>
	 * - Veiculo: <veiculo.placa>
	 * - Cliente: <cliente.nome> */
	public String toString() {
		String str = String.format("Sinistro - id %d:\n- Data: %s\n- Endereco: %s\n" +
				"- Condutor: %s\n- Seguro: %s", id, Data.dateToString(data), endereco,
				condutor.getNome(), seguro.getId());
		return str;
	}

	// Getters e setters
	public int getId() {
		return id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Condutor getCondutor() {
		return condutor;
	}
	public void setCondutor(Condutor condutor) {
		this.condutor = condutor;
	}
	public Seguro getSeguro() {
		return seguro;
	}
	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

}