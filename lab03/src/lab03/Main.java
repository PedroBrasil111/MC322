package lab03;

import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
	// Métodos auxiliares
	/* Imprime "Erro: valor invalido" e retorna false */
	private static boolean mensagemErroValorInvalido() {
		System.out.println("Erro: valor invalido");
		return false;
	}
	/* Se o indice passado for um índice válido na lista com size = tamanhoLista,
	 * retorna true. Caso contrário, retorna false. */
	private static boolean indiceValidoLista(int indice, int tamanhoLista) {
		if (indice < 0 || indice >= tamanhoLista)
			return false;
		return true;
	}
	/* Retorna "CPF <cliente.cpf>" ou "CNPJ <cliente.cnpj>" quando cliente é da classe
	 * ClientePF ou ClientePJ, respectivamente. Retorna "Nao possui documento cadastrado"
	 * quando é da classe Cliente. */
	private static String documento(Cliente cliente) {
		String str = "Nao possui documento cadastrado"; // valor padrão
		if (cliente instanceof ClientePF)
			str = "CPF " + ((ClientePF) cliente).getCpf();
		else if (cliente instanceof ClientePJ)
			str = "CNPJ " + ((ClientePJ) cliente).getCnpj();
		return str;
	}

	// Métodos de leitura (scanner como parâmetro para poder abrir e fechar no main)
	/* Imprime instrucões e lê as informacões necessárias para gerar um sinistro,
	 * e o adiciona à listaSinistros de Seguradora seg. Retorna true se o cadastro
	 * foi realizado, false caso contrário, imprimindo mensagem de erro. */
	private static boolean lerSinistro(Seguradora seg, Scanner scanner) {
		int i, numCliente, numVeiculo; // i para iterar, numCliente e numVeiculo para leitura
		String data, endereco; // ambas para leitura
		List<Veiculo> veiculos; // será a listaVeiculos do cliente selecionado
		try {
			System.out.println("Digite a data do sinistro:");
			data = scanner.nextLine();
			System.out.println("Digite o endereco do sinistro:");
			endereco = scanner.nextLine();
			System.out.println("Digite o numero referente ao cliente:");
			for (i = 0; i < seg.getListaClientes().size(); i++)
				System.out.println(String.valueOf(i + 1) + ". " + // i + 1 para comecar do 1
						seg.getListaClientes().get(i).getNome() + " - " + 
						documento(seg.getListaClientes().get(i)));
			numCliente = scanner.nextInt() - 1; // numCliente - 1 para a volta
			// checando se indice esta na lista
			if (! indiceValidoLista(numCliente, seg.getListaClientes().size()))
				return mensagemErroValorInvalido();
			scanner.nextLine(); // remover '\n'
			veiculos = seg.getListaClientes().get(numCliente).getListaVeiculos();
			System.out.println("Digite o numero referente ao veiculo:");
			for (i = 0; i < veiculos.size(); i++)
				System.out.println(String.valueOf(i + 1) + ". " + // i + 1 para comecar do 1
						veiculos.get(i).getMarca() + " " + veiculos.get(i).getModelo() + " - " +
						"Placa: " + veiculos.get(i).getPlaca());
			numVeiculo = scanner.nextInt() - 1; // numVeiculo - 1 para a volta
			// checando se indice esta na lista
			if (! indiceValidoLista(numVeiculo, veiculos.size()))
				return mensagemErroValorInvalido();
			scanner.nextLine(); // remover '\n'
			// gerarSinistro() cria e adiciona o sinistro a seg.listaSinistros
			return seg.gerarSinistro(data, endereco, veiculos.get(numVeiculo),
					seg.getListaClientes().get(numCliente));
		} catch (InputMismatchException e) { // não digitou um inteiro em scanner.nextInt()
			return mensagemErroValorInvalido();
		}
	}
	/* Imprime instrucões e lê informacões necessarias para usar o método
	 * seg.visualizarSinistro() */
	private static void lerVisualizacao(Seguradora seg, Scanner scanner) {
		String documento;
		System.out.println("Digite o documento do qual deseja visualizar " +
				"os sinistros:");
		documento = scanner.nextLine(); // deve ser escrito no mesmo formato que o cadastrado
		System.out.println("Sinistros registrados para " + documento + ":");
		if (! seg.visualizarSinistro(documento))
			System.out.println("Nao ha sinistros cadastrados para esse documento.");
	}
	/* Menu com instrucões para operacões: Gerar sinistro, Visualizar sinistros e 
	 * Listar sinistros para Seguradora seg. */
	private static void menuSeguradora(Seguradora seg, Scanner scanner) {
		String operacao;
		do {
			System.out.println("Digite o numero referente a operacao desejada\n1. Gerar sinistro,\n" + 
					"2. Visualizar sinistros,\n3. Listar Sinistros,\n4. Sair.");
			operacao = scanner.nextLine();
			switch (operacao) {
				case "1": // gerar sinistro
					if (! lerSinistro(seg, scanner))
						System.out.println("Tente novamente.");
					else
						System.out.println("Cadastro realizado com sucesso.");
					break;
				case "2": // visualizar sinistros
					lerVisualizacao(seg, scanner);
					break;
				case "3": // listar sinistros
					System.out.println("Sinistros registrados em " + seg.getNome() + ":");
					seg.listarSinistros();
					break;
				case "4": // sair
					break;
				default: // outras entradas
					System.out.println("Entrada invalida");
					break;
			}
		} while (! operacao.equals("4"));
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		// Instanciando objetos
		Seguradora seg = new Seguradora("CarSafe", "+55(19)1234-5678",
				"contato@carsafe.com.br", "Ciclo Basico");
		Veiculo v1 = new Veiculo("ABC1D23", "Toyota", "Corolla", 2015);
		Veiculo v2 = new Veiculo("EFG4H56", "Volkswagen", "Jetta", 2014);
		Veiculo v3 = new Veiculo("IJK7L89", "Chevrolet", "Corsa", 2002);
		Veiculo v4 = new Veiculo("MNO1P23", "Volkswagen", "Fusca", 1994);
		Cliente cliente0 = new Cliente("Ze", "Instituto de Computacao");
		ClientePF cliente1 = new ClientePF("Ana", "Instituto de Computacao", "123.456.789-09",
				"Feminino", "15/04/2023", "Superior completo", "12/10/1995", "Media", v1);
		ClientePJ cliente2 = new ClientePJ("Meta", "Instituto de Computacao",
				"12.345.678/0001-95", "04/02/2004", v2);
		ClientePJ cliente3 = new ClientePJ("Google", "FEEC",
				"12.345.678/0001-99", "04/09/1998", v3, v4);

		// Testando validacao de CPF e CNPJ
		System.out.println("------------------ Validacao CPF e CNPJ ------------------");
		System.out.println("CPF de " + cliente1.getNome() + " eh valido: "
				+ ClientePF.validarCPF(cliente1.getCpf()));
		System.out.println("CNPJ de " + cliente2.getNome() + " eh valido: "
				+ ClientePJ.validarCNPJ(cliente2.getCnpj()));
		System.out.println("CNPJ de " + cliente3.getNome() + " eh valido: "
				+ ClientePJ.validarCNPJ(cliente3.getCnpj()) + "\n");

		// Cadastro e remocao de clientes da seguradora, usando listarClientes()
		System.out.println("------------------ Cadastro e remocao ------------------");
		System.out.println("Cadastro de " + cliente1.getNome() + " realizado com sucesso: " +
				seg.cadastrarCliente(cliente1));
		System.out.println("Cadastro de " + cliente2.getNome() + " realizado com sucesso: " +
				seg.cadastrarCliente(cliente2));
		System.out.println("Cadastro de " + cliente3.getNome() + " realizado com sucesso: " +
				seg.cadastrarCliente(cliente3));
		System.out.println("Clientes PF:");
		seg.listarClientes("PF"); // mostra cliente1
		System.out.println("Clientes PJ:");
		seg.listarClientes("PJ"); // mostra cliente2 e cliente3
		System.out.println("Remocao de " + cliente3.getNome() + " realizada com sucesso: " + 
				seg.removerCliente(cliente3.getCnpj())); // remove cliente3
		System.out.println("Clientes PJ:");
		seg.listarClientes("PJ"); // mostra cliente2
		System.out.println();

		// Usando gerarSinistro(), visualizarSinistro() e listarSinistro() pelo menu
		System.out.println("------------------ Menu ------------------");
		menuSeguradora(seg, scanner);
		System.out.println();

		// toString() restantes
		System.out.println("------------------ toString() ------------------");
		System.out.println(cliente0);
		System.out.println(cliente1);
		System.out.println(cliente3);
		System.out.println(v1);
		System.out.println(seg);

		scanner.close();
	}

}