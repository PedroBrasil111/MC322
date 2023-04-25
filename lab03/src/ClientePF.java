package lab03;

import java.text.ParseException;
import java.util.Date;

public class ClientePF extends Cliente {

    // Propriedades
    private final String cpf;
    private String genero;
    private Date dataLicenca;
    private String educacao;
    private Date dataNascimento;
    private String classeEconomica;

    // Construtor
    public ClientePF(String nome, String endereco, String cpf, String genero, String dataLicenca,
            String educacao, String dataNascimento, String classeEconomica, Veiculo...veiculos) {
        super(nome, endereco, veiculos);
        Date data;
        this.cpf = cpf;
        this.genero = genero;
        this.educacao = educacao;
        this.classeEconomica = classeEconomica;
        try {
            data = parseDate(dataLicenca);
            this.dataLicenca = data;
            data = parseDate(dataNascimento);
            this.dataNascimento = data;
        } catch (ParseException e) { //erro com SimpleDateFormat.parse()
            e.printStackTrace();
        }
    }
    
    // toString
    @Override
    public String toString() {
        String str = String.format("Cliente PF - %s:\n- CPF: %s;\n- Endereco: %s;\n- Genero: %s;\n" +
                "- Data da licenca: %s;\n- Educacao: %s;\n- Data de nascimento: %s;\n" + 
                "- Classe economica: %s;\n- Veiculos: ", getNome(), cpf, getEndereco(), genero,
                parseString(dataLicenca), educacao, parseString(dataNascimento), classeEconomica);
        if (getListaVeiculos().isEmpty())
			str += "\n- Nenhum veiculo cadastrado";
        for (int i = 0; i < getListaVeiculos().size(); i++)
            str += "\n- " + (i + 1) + ". " + getListaVeiculos().get(i).getPlaca();
        return str;
    }

    // Métodos privados
	/* Calcula um digito verificador de String cpf. A conta para ambos é a mesma, alterando
	 * apenas a posição dos outros dígitos usados na conta. Para calcular o primeiro,
	 * ini = 0 e fim = 9. Ja para calcular o segundo, ini = 1 e fim = 10. */
	private static int digitoVerificadorCPF(String cpf, int ini, int fim) {
		int aux = 0; // guarda os resultados das operacões com os dígitos
		for (int i = ini; i < fim; i++)
			aux += charAtToInt(cpf, i) * (10 - (i - ini)); // multiplica digitos por 10, 9, ..., 2
		aux %= 11;
        return aux < 2 ? 0 : 11 - aux; // se aux == (0 ou 1): dígito = 0, senão: dígito = 11 - aux
	}
	/* Retorna true se os digitos verificadores do CPF passado como argumento são validos,
     * false caso contrario */
	private static boolean digitosValidosCPF(String cpf) {
		if (digitoVerificadorCPF(cpf, 0, 9) == charAtToInt(cpf, 9) &&
				digitoVerificadorCPF(cpf, 1, 10) == charAtToInt(cpf, 10))
			return true;
		return false;
	}

	// Métodos públicos
	/* Retorna true se o CPF for valido, false caso contrario. Para que um CPF seja valido,
	 * é necessario que ele tenha 11 caracteres numéricos, sendo ao menos um número diferente
	 * dos demais e que os digitos verificadores sejam validos. */
	public static boolean validarCPF(String cpf) {
		cpf = cpf.replaceAll("[^0-9]", ""); // Remove caracteres não numéricos
		if (cpf.length() != 11 || todosCharIguais(cpf) || ! digitosValidosCPF(cpf))
			return false;
		return true;
	}

    //Getters e setters
    public String getCpf() {
        return cpf;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public Date getDataLicenca() {
        return dataLicenca;
    }
    public void setDataLicenca(Date dataLicenca) {
        this.dataLicenca = dataLicenca;
    }
    public String getEducacao() {
        return educacao;
    }
    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }
    public Date getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public String getClasseEconomica() {
        return classeEconomica;
    }
    public void setClasseEconomica(String classeEconomica) {
        this.classeEconomica = classeEconomica;
    }

}
