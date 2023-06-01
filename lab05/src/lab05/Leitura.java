package lab05;

/* Classe com métodos estáticos para leitura e tratamento da leitura */

import java.util.Scanner;
import java.util.Date;

public class Leitura {
	/* Uma única instância de scanner */
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
				System.out.println("Erro - valor invalido. Tente digitar novamente: ");
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
			System.out.print("Erro - data invalida ou fora do formato especificado. " +
					"Tente digitar novamente: ");
		} while (true);
		return data;
	}
	/* Lê até que seja dado um número inteiro entre ini e fim (inclusive) e o retorna.
	 * Imprime mensagem de erro se for inválido. */
	public static int lerIntIntervalo(int ini, int fim) {
		int pos;
		do {
			pos = lerInteiro();
			if (pos >= ini && pos <= fim)
				return pos;
			System.out.print("Erro - valor invalido. Tente digitar novamente: ");
		} while (true);
	}
	/* Lê até que um nome válido seja dado (nome sem números) e o retorna.
	 * Imprime mensagem de erro se for inválido. */
	public static String lerNome() {
		String nome;
		do {
			nome = lerString();
			if (Validacao.validaNome(nome))
				break;
			System.out.print("Erro - palavra/nome invalido. Tente digitar novamente: ");
		} while (true);
		return nome;
	}
	/* Lê até que um CNPJ válido seja dado e o retorna.
	 * Imprime mensagem de erro se for inválido. */
	public static String lerCnpj() {
		String cnpj;
		do {
			cnpj = lerString();
			if (! Validacao.validarCNPJ(cnpj))
				System.out.print("Erro - CNPJ invalido. Tente digitar novamente: ");
			else
				break;
		} while (true);
		return cnpj;
	}
	/* Lê até que um CPF válido seja dado e o retorna.
	 * Imprime mensagem de erro se for inválido. */
	public static String lerCpf() {
		String cpf;
		do {
			cpf = Leitura.lerString();
			if (! Validacao.validarCPF(cpf))
				System.out.print("Erro - CPF invalido. Tente digitar novamente: ");
			else
				break;
		} while (true);
		return cpf;
	}
	/* Fecha o scanner */
	public static void fechar() {
		scanner.close();
	}
	/* get */
	public static Scanner getScanner() {
		return scanner;
	}

}