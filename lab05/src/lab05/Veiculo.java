package lab05;

public class Veiculo {
	// Atributos
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

	// toString()
	/* Veiculo - Placa <placa>:
	 * - Marca: <marca>
	 * - Modelo: <modelo>
	 * - Ano de fabricacao: <anoFabricacao> */
	public String toString() {
		String str = String.format("Veiculo - Placa %s:\n- Marca: %s\n- Modelo: %s" +
				"\n- Ano de fabricacao: %d", placa, marca, modelo, anoFabricacao);
		return str;
	}

    /* Retorna boolean indicando se o objeto obj eh igual. Comparacão é feita por placa. */
	@Override
    public boolean equals(Object obj) {
		if (this == obj)
			return true;
        if (obj == null || getClass() != obj.getClass())
			return false;
        return placa.equals(((Veiculo) obj).getPlaca());
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