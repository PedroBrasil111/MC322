package lab05;

public abstract class Cliente {
	// Propriedades
	private String nome;
	private String telefone;
	private String endereco;
	private String email;

	// Construtor
	public Cliente(String nome, String telefone, String endereco, String email) {
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
	}

	// toString()
	/* Cliente - <nome>:
	 * - Telefone: <telefone>
	 * - Endereco: <endereco>
	 * - Valor do seguro: R$<valorSeguro>
	 * - Veiculo(s): Nenhum veiculo cadastrado OU <veiculo1.placa>, <veiculo2.placa>, ... */
	public String toString() {
		String str = String.format("Cliente - %s:\n- Telefone: %s\n- Endereco: %s\n" + 
				"- E-mail: %s", nome, telefone, endereco, email);
		return str;
	}

	public abstract String documento();

/*
	// Retorna o valor do score do cliente.
	public abstract double calculaScore();
	// Adiciona o veiculo v a listaVeiculos, retorna boolean indicando se adicionou
	public boolean adicionarVeiculo(Veiculo v) {
		return listaVeiculos.add(v);
	}
	// Remove o veiculo v de listaVeiculos, retorna boolean indicando se removeu 
	public boolean removerVeiculo(Veiculo v) {
		return listaVeiculos.remove(v);
	}
	// Lista os veiculos em listaVeiculos no formato "<i> - <veiculo.placa>", onde i é o
	// índice do veículo em listaVeiculos. Retorna boolean indicando se listou algum veiculo 
	public boolean listarVeiculos() {
		if (! listaVeiculos.isEmpty()) {
			for (int i = 0; i < listaVeiculos.size(); i++)
				System.out.println(String.valueOf(i + 1) + " - " + listaVeiculos.get(i).getPlaca());
			return true;
		}
		return false;
	}
*/

	// Getters e setters
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}