package lab02;

public class Seguradora {
	// Atributos
	private String nome;
	private String telefone;
	private String email;
	private String endereco;

	// Construtor
	public Seguradora(String nome, String telefone, String email, String endereco) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
	}
	
	// toString
	@Override
	public String toString() {
		String str = String.format("Seguradora %s:\n\tTelefone: %s,\n\t" +
				"E-mail: %s,\n\tEndereco: %s.", nome, telefone, email, endereco);
		return str;
	}

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

}
