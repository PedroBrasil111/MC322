package lab04;

import java.util.Date;

public class Sinistro {
	// Propriedades
	private final int id;
	private Date data;
	private String endereco;
	private Seguradora seguradora;
	private Veiculo veiculo;
	private Cliente cliente;

	// Construtor
	public Sinistro(Date data, String endereco, Seguradora seguradora, Veiculo veiculo,
			Cliente cliente) {
		// Gera um id aleatório baseado no endereco de memória (único p/ cada objeto).
		// Porém, esse id varia a cada iteracão do programa.
		id = hashCode();
		this.data = data;
		this.endereco = endereco;
		this.seguradora = seguradora;
		this.veiculo = veiculo;
		this.cliente = cliente;
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
				"- Seguradora: %s\n- Veiculo: %s\n- Cliente: %s", id, Data.dateToString(data),
				endereco, seguradora.getNome(), veiculo.getPlaca(), cliente.getNome());
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
	public Seguradora getSeguradora() {
		return seguradora;
	}
	public void setSeguradora(Seguradora seguradora) {
		this.seguradora = seguradora;
	}
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}