package lab02;

public class Veiculo {
	// Atributos
	private String placa;
	private String marca;
	private String modelo;

	// Construtor
	public Veiculo(String placa, String marca, String modelo) {
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
	}

	// toString
	@Override
	public String toString() {
		String str = String.format("Veiculo %s:\n\tMarca: %s,\n\tModelo: %s.",
				placa, marca, modelo);
		return str;
	}

	// Getters e setters
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

}
