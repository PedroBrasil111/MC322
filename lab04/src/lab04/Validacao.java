package lab04;

import java.util.Date;
import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Validacao {
    /************GERAL**********/
	// Métodos protegidos (utilizados tanto para ClientePF quanto ClientePJ)
	/* Retorna o valor inteiro do char na posicão pos de str */
	private static int charAtToInt(String str, int pos) {
        return str.charAt(pos) - '0';
		//return Character.getNumericValue(str.charAt(pos));
	}
	/* Se todos os caracteres de str forem iguais, retorna true; caso contrário, retorna false */
	private static boolean todosCharIguais(String str) {
		char c = str.charAt(0); // para comparar
		for (int i = 1; i < str.length(); i++)
			if (str.charAt(i) != c)
				return false;
		return true;
	}

    /***********CNPJ**************/
    /* Calcula  e retorna o primeiro dígito verificador de String cnpj se numDigito == 0,
     * e o segundo dígito se numDigito == 1. */
    private static int digitoVerificadorCNPJ(String cnpj, int numDigito) {
        int i, aux = 0; // aux guarda os resultados das operacões com os dígitos
        // Multiplica primeiros dígitos sequencialmente por 5, 4, 3, ..., 1 se numDigito == 0,
        // ou por 6, 4, 3, ..., 1, se numDigito == 1, e soma em aux:
        for (i = 0; i < (4 + numDigito); i++)
            aux += charAtToInt(cnpj, i) * (5 + numDigito - i);
        // Multiplica 5°/6° dígito em diante por 9, 8, 7, ..., 2 e soma em aux:
        for (i = 4 + numDigito; i < (12 + numDigito); i++)
            aux += charAtToInt(cnpj, i) * (13 + numDigito - i);
        aux %= 11;
        return aux < 2 ? 0 : 11 - aux; // se aux == (0 ou 1): dígito = 0, senão: dígito = 11 - aux
    }
    /* Retorna true se os dígitos verificadores do CNPJ passado como argumento são válidos,
     * false caso contrário. */
    private static boolean digitosValidosCNPJ(String cnpj) {
        if (digitoVerificadorCNPJ(cnpj, 0) == charAtToInt(cnpj, 12) &&
                digitoVerificadorCNPJ(cnpj, 1) == charAtToInt(cnpj, 13))
            return true;
        return false;
    }
    /* Retorna true se o CNPJ for válido, false caso contrário. Para que um CNPJ seja válido,
	 * é necessário que ele tenha 14 caracteres numéricos, sendo ao menos um número diferente
	 * dos demais e que os digitos verificadores sejam válidos. */
    public static boolean validarCNPJ(String cnpj) {
        cnpj = cnpj.replaceAll("[^0-9]", ""); // Remove caracteres não numéricos
        if (cnpj.length() != 14 || todosCharIguais(cnpj) || ! digitosValidosCNPJ(cnpj))
            return false;
        return true;
    }

    /***********CPF**************/
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

	/* Retorna true se o CPF for valido, false caso contrario. Para que um CPF seja valido,
	 * é necessario que ele tenha 11 caracteres numéricos, sendo ao menos um número diferente
	 * dos demais e que os digitos verificadores sejam validos. */
	public static boolean validarCPF(String cpf) {
		cpf = cpf.replaceAll("[^0-9]", ""); // Remove caracteres não numéricos
		if (cpf.length() != 11 || todosCharIguais(cpf) || ! digitosValidosCPF(cpf))
			return false;
		return true;
	}

    /***********NOME*************/
    /* Retorna true se o nome é válido, false caso contrário.
     * Para um nome ser válido, ele deve conter apenas letras e espacos. */
    public static boolean validaNome(String nome) {
        if (nome.length() <= 2) // nome muito curto
            return false;
        return nome.matches("[\\p{L} ]+"); // true se nome só contêm letras e espacos
    }

    /***********DATA**********/
    /* Dada uma string no formato "dd/MM/yyy", retorna o objeto Date equivalente.
	 * Levanta uma excecão parseException se a string não estiver no formato especificado. */
	public static Date parseDate(String data) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.parse(data);
	}
	/* Dado um objeto Date, retorna a String equivalente no formato dd/MM/yyyy */
	public static String parseString(Date data) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String str = dateFormat.format(data);
		return str;
	}
    public static int calcularIdade(Date dataNascimento) {
        int idade;
        Calendar calendarNascimento = Calendar.getInstance();
        Calendar calendarAtual = Calendar.getInstance();
        calendarNascimento.setTime(dataNascimento);
        calendarAtual.setTime(new Date());
        // calcula idade que completa esse ano
        idade = calendarAtual.get(Calendar.YEAR) - calendarNascimento.get(Calendar.YEAR);
        // se ainda nao fez aniversario no ano atual, deve subtrair 1 da idade
        if (calendarAtual.get(Calendar.DAY_OF_YEAR) < calendarNascimento.get(Calendar.DAY_OF_YEAR))
            idade--;
        return idade;
    }

}
