package lab03;

import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
	public static void menuSeguradora(Seguradora seg, Scanner scanner) {
		/* Ao criar e fechar o scanner nessa funcão, estava ocorrendo
		 * erro de NoSuchElementException, então resolvi passar o scanner
		 * como parâmetro, embora no enunciado a funcão não tenha parâmetro. */
		int operacao, i, numCliente, numVeiculo;
		String data, endereco, documento;
		List<Veiculo> veiculos;
		Sinistro sinistro;
		System.out.println("Digite o numero referente a operacao desejada\n1. Gerar sinistro,\n" + 
			"2. Visualizar sinistros,\n3. Listar Sinistros,\n4. Sair.");
		operacao = scanner.nextInt();
		scanner.nextLine();
		switch (operacao) {
			case 1:
				try {
					System.out.println("Digite a data do sinistro:");
					data = scanner.nextLine();
					System.out.println("Digite o endereco do sinistro:");
					endereco = scanner.nextLine();
					System.out.println("Digite o numero referente ao cliente:");
					for (i = 0; i < seg.getListaClientes().size(); i++) {
						System.out.println(String.valueOf(i + 1) + ". " +
							seg.getListaClientes().get(i).getNome());
					}
					numCliente = scanner.nextInt();
					scanner.nextLine();
					veiculos = seg.getListaClientes().get(numCliente - 1).getListaVeiculos();
					System.out.println("Digite o numero referente ao veículo:");
					for (i = 0; i < veiculos.size(); i++) {
						System.out.println(String.valueOf(i + 1) + ". " +
							veiculos.get(i).getPlaca());
					}
					numVeiculo = scanner.nextInt();
					scanner.nextLine();
					sinistro = new Sinistro(data, endereco, seg, veiculos.get(numVeiculo - 1),
											seg.getListaClientes().get(numCliente - 1));
					seg.gerarSinistro(sinistro);
					seg.getListaSinistros().add(sinistro);
				} catch (InputMismatchException e) { // Não digitou um inteiro em scanner.nextInt()
					System.out.println("Erro: valor invalido.");
				} catch (IndexOutOfBoundsException e) { // Digitou índice que não está na lista
					System.out.println("Erro: valor invalido.");
				}
				break;
			case 2:
				System.out.println("Digite o documento do qual deseja buscar os sinistros:");
				documento = scanner.nextLine();
				if (! seg.visualizarSinistro(documento));
					System.out.println("Nao ha sinistros cadastrados nesse documento");
				break;
			case 3:
				break;
			case 4:
				break;
		}
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
			"Feminino", "15/04/2023", "Cursando superior", "14/12/2002", "Media", v1, v2);
		ClientePJ cliente2 = new ClientePJ("Empresa Um", "Instituto de Biologia",
			"12.345.678/0001-95", "16/04/2022", v3);
		ClientePJ cliente3 = new ClientePJ("Empresa Tres", "Instituto de Educacao",
			"12.345.678/0001-99", "23/08/2002", v4);

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
		seg.listarClientes("PF"); // cliente1
		seg.listarClientes("PJ"); // cliente2 e cliente3
		System.out.println("Remocao de " + cliente3.getNome() + " realizada com sucesso: " + 
			seg.removerCliente(cliente3.getCnpj()));
		seg.listarClientes("PJ"); //cliente2
		System.out.println();

		// Usando gerarSinistro(), visualizarSinistro() e listarSinistro()
		menuSeguradora(seg, scanner);

		// Chamando toString() restantes
		System.out.println("------------------ toString() ------------------");
		System.out.println(cliente0);
		System.out.println(cliente1);
		System.out.println(cliente2);
		System.out.println(cliente1.getListaVeiculos().get(0));
		System.out.println(seg);
		
		scanner.close();
	}

}
