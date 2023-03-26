package lab02;

public class Cliente {
	// Atributos
	private String nome;
	private String cpf;
	private String dataNascimento;
	private int idade;
	private String endereco;

	// Construtor
	public Cliente(String nome, String cpf, String dataNascimento, int idade, String endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.idade = idade;
		this.endereco = endereco;
	}

	// toString
	@Override
	public String toString() {
		String str = String.format("Cliente %s:\n\tCPF: %s,\n\tData de nascimento: %s,\n\t" +
				"Idade: %d,\n\tEndereco: %s.", nome, cpf, dataNascimento, idade, endereco);
		return str;
	}

	// Getters e setters
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}

	// Funções privadas
	/* Retorna o valor inteiro do char na posição pos de str */
	private int charAtToInt(String str, int pos) {
		return Character.getNumericValue(str.charAt(pos));
	}
	/* Se todos os caracteres de str forem iguais, retorna true; caso contrário, retorna false */
	private boolean todosCharIguais(String str) {
		char c = str.charAt(0); // para comparar
		for (int i = 1; i < str.length(); i++)
			if (str.charAt(i) != c)
				return false;
		return true;
	}
	/* Calcula um digito verificador. A conta para ambos é a mesma, alterando apenas a posição
	 * dos outros dígitos usados na conta. Para calcular o primeiro, ini = 0 e fim = 9.
	 * Já para calcular o segundo, ini = 1 e fim = 10. */
	private int digitoVerificador(String cpf, int ini, int fim) {
		int fator = 10, digito = 0; // fator é usado p/ multiplicar os dígitos na conta
		for (int i = ini; i < fim; i++) {
			digito += charAtToInt(cpf, i) * fator;
			fator -= 1;
		}
		digito %= 11;
		if (digito == 0 || digito == 1)
			return 0;
		return 11 - digito;
	}
	/* Retorna true se os digitos verificadores do cpf passado como argumento são
	 * válidos, false caso contrário */
	private boolean digitosValidos(String cpf) {
		if (digitoVerificador(cpf, 0, 9) == charAtToInt(cpf, 9) &&
				digitoVerificador(cpf, 1, 10) == charAtToInt(cpf, 10))
			return true;
		return false;
	}

	// Métodos públicos
	/* Retorna true se o CPF for válido, false caso contrário. Para que um CPF seja válido,
	 * é necessário que ele tenha 11 caracteres numéricos, sendo ao menos um número diferente
	 * dos demais e que os digitos verificadores sejam válidos. */
	public boolean validarCPF(String cpf) {
		cpf = cpf.replaceAll("[^0-9]", ""); // Remove caracteres não numéricos
		if (cpf.length() != 11 || todosCharIguais(cpf) || ! digitosValidos(cpf))
			return false;
		return true;
	}

}