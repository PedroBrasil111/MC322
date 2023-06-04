package lab04;

/* Classe de leitura (para só instanciar um scanner) */

import java.util.Scanner;
import java.util.Date;

public class Leitura {
	private static Scanner scanner = new Scanner(System.in);
	/* Lê até que seja dado um inteiro e o retorna.
	 * Imprime mensagem de erro quando não é inteiro. */
	public static int lerInteiro() {
		String input;
		do {
			input = scanner.nextLine();
			try {
				return Integer.parseInt(input);
			} catch (NumberFormatException e) { // não é inteiro
				System.out.println("Erro. Digite um numero inteiro valido.");
			}
		} while (true);
	}
	/* Lê uma string e a retorna */
	public static String lerString() {
		return scanner.nextLine();
	}
	/* Lê até que seja dada uma data no formato dd/MM/aaaa e retorna o Date equivalente.
	 * Imprime mensagem de erro quando data não é dada no formato */
	public static Date lerData() {
		String input;
		Date data;
		do {
			input = Leitura.lerString();
			if (Validacao.validaData(input)) {
				data = Data.stringToDate(input);
				break;
			}
			System.out.println("Erro. Data invalida ou fora do formato especificado. " +
					"Tente digitar novamente.");
		} while (true);
		return data;
	}
	/* Dado o tamanho (tam) de uma lista ou array, retorna se i é um índice válido do iterável. */
	private static boolean indiceValido(int i, int tam) {
		if (i < 0 || i >= tam)
			return false;
		return true;
	}
	public static int lerIndice(int tam) {
		int pos;
		do {
			pos = lerInteiro();
			// pos >= 0 e pos < tam
			if (indiceValido(pos, tam))
				return pos;
			System.out.println("Erro. Digite um valor valido.");
		} while (true);
	}
	/* Lê até que um nome válido seja dado (nome sem números) e o retorna */
	public static String lerNome() {
		String nome;
		do {
			nome = lerString();
			if (Validacao.validaNome(nome))
				break;
			System.out.println("Erro. Digite um nome valido.");
		} while (true);
		return nome;
	}
	/* Lê até que um CNPJ válido seja dado e o reteorna */
	public static String lerCnpj() {
		String cnpj;
		do {
			cnpj = lerString();
			if (! Validacao.validarCNPJ(cnpj))
				System.out.println("CNPJ invalido. Tente digitar novamente.");
			else
				break;
		} while (true);
		return cnpj;
	}
	/* Lê até que um CPF válido seja dado e o retorna */
	public static String lerCpf() {
		String cpf;
		do {
			cpf = Leitura.lerString();
			if (! Validacao.validarCPF(cpf))
				System.out.println("CPF invalido. Tente digitar novamente.");
			else
				break;
		} while (true);
		return cpf;
	}
	public static void fechar() {
		scanner.close();
	}
}