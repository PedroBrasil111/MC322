package lab03;

public class Veiculo {
	// Propriedades
	private String placa;
	private String marca;
	private String modelo;
	private int anoFabricacao;

	// Construtor
	public Veiculo(String placa, String marca, String modelo, int anoFabricacao) {
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.anoFabricacao = anoFabricacao;
	}

	// toString
	public String toString() {
		String str = String.format("Veiculo - placa %s:\n- Marca: %s\n- Modelo: %s" +
				"\n- Ano de fabricacao: %d", placa, marca, modelo, anoFabricacao);
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
	public void setAnoFabricacao(int anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}
	public int getAnoFabricacao() {
		return anoFabricacao;
	}

}