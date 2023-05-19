package lab04;

/* Classe com métodos estáticos para validacão de CNPJ, CPF, nome e data. */

public class Validacao {
    // Métodos privados
	/* Retorna o valor inteiro do char na posicão pos de str */
	private static int charAtToInt(String str, int pos) {
        return str.charAt(pos) - '0';
	}
	/* Se todos os caracteres de str forem iguais, retorna true; caso contrário, retorna false */
	private static boolean todosCharIguais(String str) {
		char c = str.charAt(0); // para comparar
		for (int i = 1; i < str.length(); i++)
			if (str.charAt(i) != c)
				return false;
		return true;
	}

    // Validacão de CNPJ
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

    // Validacão de CPF
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

    // Validacão de nome
    /* Retorna true se o nome é válido, false caso contrário.
     * Para que um nome seja válido, ele deve conter apenas letras e espacos. */
    public static boolean validaNome(String nome) {
        return nome.matches("[\\p{L} ]+"); // boolean indicando se nome só contêm letras e espacos
    }

    // Validacão de data
    /* Converte cada item de arrayString para inteiro e atribui a arrayInt. Retorna boolean
     * indicando se a conversão pode ser feita (arrayString só tem números inteiros). */
    private static boolean ArrayStringToInt(String[] arrayString, int[] arrayInt) {
        for (int i = 0; i < arrayString.length; i++) {
            try {
                arrayInt[i] = Integer.parseInt(arrayString[i]);
            } catch (NumberFormatException e) { // algum item não é um inteiro
                return false;
            }
        }
        return true;
    }
    /* Retorna boolean indicando se dia em arrayIntData[0] é válido, levando em conta
     * o mes em arrayIntData[1] e o ano em arrayIntData[2] */
    private static boolean diaValido(int[] arrayIntData) {
        switch (arrayIntData[1]) {
            case 1: // janeiro
            case 3: // marco
            case 5: // maio
            case 7: // julho
            case 8: // agosto
            case 10: // outubro
            case 12: // dezembro
                if (arrayIntData[0] > 31)
                    return false;
                break;
            case 4: // abril
            case 6: // junho
            case 9: // setembro
            case 11: // novembro
                if (arrayIntData[0] > 30)
                    return false;
                break;
            case 2: // fevereiro
                // ano bisexto: 29 dias, senão 28
                if (arrayIntData[2] % 4 == 0 && arrayIntData[0] > 29 ||
                    arrayIntData[0] > 28)
                        return false;
        }
        return true;
    }
    /* Retorna true se a data passada como argumento é válida, false caso contrário.
     * Para ser valida, deve estar no formato dd/MM/yyyy e a data deve ser valida considerando
     * o calendario (arbitrariamente, ano deve estar entre 1700 e 2200). */
    public static boolean validaData(String data) {
        int arrayIntData[] = new int[3];
        String[] arrayData = data.split("/");
        if (arrayData.length != 3) // string não tem exatamente duas "/"
            return false;
        // construcao de arrayIntData, com [0]: dia, [1]: mes, [2]: ano (em inteiro)
        if (! ArrayStringToInt(arrayData, arrayIntData))
            return false; // false quando algum item não é um inteiro
        // ano deve estar entre 1700 e 2200 (escolha arbitrária), mes entre 1 e 12 e dia >= 0
        if (arrayIntData[2] < 1700 || arrayIntData[2] > 2200 ||
                arrayIntData[1] < 1 || arrayIntData[1] > 12 || arrayIntData[0] < 0)
            return false;
        // validacao do dia dependendo do mes e ano
        return diaValido(arrayIntData);
    }

}   