package lab05;

public abstract class Cliente {
	// Atributos
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
	 * - E-mail: <email> */
	public String toString() {
		String str = String.format("Cliente - %s:\n- Telefone: %s\n- Endereco: %s\n" + 
				"- E-mail: %s", nome, telefone, endereco, email);
		return str;
	}

	/* Retorna uma string no formato "CPF/CNPJ - <documento>", dependendo da subclasse de Cliente. */
	public abstract String strDocumento();

	// Getters e setters
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}