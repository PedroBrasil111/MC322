package lab03;

public class Sinistro {

	// Propriedades
	private final int id;
	private String data;
	private String endereco;
	private Seguradora seguradora;
	private Veiculo veiculo;
	private Cliente cliente;

	// Construtor
	public Sinistro(String data, String endereco, Seguradora seguradora, Veiculo veiculo,
			Cliente cliente) {
		int id = 0;
		// cálculo do id como no hashing modular
		// utiliza endereço e data para diminuir chance de conflito
		for (int i = 0; i < endereco.length(); i++)
			id = (256 * id + (int) endereco.charAt(i)) % 1783;
		for (int i = 0; i < data.length(); i++)
			id = (256 * id + (int) endereco.charAt(i)) % 1783;
		this.id = id;
		this.data = data;
		this.endereco = endereco;
		this.seguradora = seguradora;
		this.veiculo = veiculo;
		this.cliente = cliente;
	}

	// toString
	@Override
	public String toString() {
		String str = String.format("Sinistro - id %d:\n- Data: %s,\n- Endereco: %s.\n" +
				"- Seguradora: %s\n- Veiculo: %s\n- Cliente: %s", id, data, endereco,
				seguradora.getNome(), veiculo.getPlaca(), cliente.getNome());
		return str;
	}

	// Getters e setters
	public int getId() {
		return id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
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
