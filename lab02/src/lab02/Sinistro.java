package lab02;

public class Sinistro {
	// Atributos
	private int id;
	private String data;
	private String endereco;

	// Construtor
	public Sinistro(String data, String endereco) {
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
	}

	// toString
	@Override
	public String toString() {
		String str = String.format("Sinistro id %d:\n\tdata: %s,\n\tendereco: %s.",
				id, data, endereco);
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

}
