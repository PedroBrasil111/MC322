package lab04;

import java.util.Scanner;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/* Classe contendo o método main. Implementa o menu e os métodos necessários
 * para ler instrucões do usuário e realizar as operacões desejadas. */

public class AppMain {
	/* Lista contendo todas as seguradoras instanciadas (base para utilizar os menus) */
	private static List<Seguradora> listaSeguradoras = new ArrayList<Seguradora>();

	/* Classe de leitura (para só instanciar um scanner) */
	private static class Leitura {
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
		/* Lê até que seja dada uma data no formato dd/MM/yyyy e retorna o Date equivalente.
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
				System.out.println("Erro. Data invalida ou fora do formato especificado." +
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
		/* Dado o tamanho (tam) de um iterável, lê até que seja dado um índice válido e o retorna */
		public static int lerIndice(int tam) {
			int pos;
			do {
				pos = lerInteiro();
				// pos >= 0 e pos < tam
				if (indiceValido(pos, tam))
					break;
				System.out.println("Erro. Digite um valor valido.");
			} while (true);
			return pos;
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

	/* Métodos para os menus (adaptados de https://github.com/rebecapadovani/ExemploEnumMenu) */
	//exibir menu externo
	private static void exibirMenuExterno() {
		MenuOperacoes menuOpcoes[] = MenuOperacoes.values();
		System.out.println("** Menu principal **");
		for (int i = 1; i < menuOpcoes.length; i++)
			System.out.println(String.valueOf(i) + " - " + menuOpcoes[i].getDescricao());
		System.out.println("0 - " + menuOpcoes[0].getDescricao());
	}
	/* exibir submenus
	 * se a lista de constantes do submenu for percorrida da mesma forma que o meu externo, a opcão Voltar
	 * é printada com a posicão que está na lista do enum (9 - Voltar). Por isso, a lista é percorrida 
	 * de forma diferente, tendo i como o inteiro correspondente. Assim, para listar o submenu de cadastros,
	 * por exemplo, vai ser printado "3 - Voltar".
	 */
	private static void exibirSubmenu(MenuOperacoes op) {
		SubmenuOperacoes[] submenu = op.getSubmenu();
		System.out.println("** " + op.getDescricao() + " **");
		for(int i = 1; i < submenu.length; i++) {
			System.out.println(i +" - " + submenu[i].getDescricao());
		}
		System.out.println("0 - " + submenu[0].getDescricao());
	}
	//ler opcões do menu externo
	private static MenuOperacoes lerOpcaoMenuExterno() {
		int opUsuario;
		MenuOperacoes opUsuarioConst;
		System.out.println("Digite uma opcao: ");
		opUsuario = Leitura.lerIndice(MenuOperacoes.values().length);
		opUsuarioConst = MenuOperacoes.values()[opUsuario];
		return opUsuarioConst;
	}
	//ler opcão dos submenus
	private static SubmenuOperacoes lerOpcaoSubmenu(MenuOperacoes op) {
		int opUsuario;
		SubmenuOperacoes opUsuarioConst;
		System.out.println("Digite uma opcao: ");
		opUsuario = Leitura.lerIndice(op.getSubmenu().length);
		opUsuarioConst = op.getSubmenu()[opUsuario];
		return opUsuarioConst;
	}
	//executar opcões do menu externo
	private static void executarOpcaoMenuExterno(MenuOperacoes op) {
		switch(op) {
			case CADASTROS:
			case LISTAR:
			case EXCLUIR:
				executarSubmenu(op);
				break;
			case GERAR_SINISTRO:
				operacaoGerarSinistro();
				break;
			case TRANSFERIR_SEGURO:
				operacaoTransferirSeguro();
				break;
			case CALCULAR_RECEITA:
				operacaoCalcularReceita();
				break;
			case SAIR:
				break;
		}
	}
	//executar opcões dos submenus
	public static void executarOpcaoSubMenu(SubmenuOperacoes opSubmenu) {
		switch(opSubmenu) {
			case CADASTRAR_CLIENTE:
				operacaoCadastrarCliente();
				break;
			case CADASTRAR_VEICULO:
				operacaoCadastrarVeiculo();
				break;
			case CADASTRAR_SEGURADORA:
				operacaoCadastrarSeguradora();
				break;
			case LISTAR_CLIENTES:
				operacaoListarClientes();
				break;
			case LISTAR_SINISTROS_SEGURADORA:
				operacaoListarSinistrosSeguradora();
				break;
			case LISTAR_SINISTROS_CLIENTE:
				operacaoListarSinistrosCliente();
				break;
			case LISTAR_VEICULOS_SEGURADORA:
				operacaoListarVeiculosSeguradora();
				break;
			case LISTAR_VEICULOS_CLIENTE:
				operacaoListarVeiculosCliente();
				break;
			case EXCLUIR_CLIENTE:
				operacaoExcluirCliente();
				break;
			case EXCLUIR_VEICULO:
				operacaoExcluirVeiculo();
				break;
			case EXCLUIR_SINISTRO:
				operacaoExcluirSinistro();
				break;
			case VOLTAR:
				break;
		}
	}
	//executa os submenus: exibicão do menu, leitura da opcão e execucão dos métodos
	private static void executarSubmenu(MenuOperacoes op) {
		SubmenuOperacoes opSubmenu;
		do {
			exibirSubmenu(op);
			opSubmenu = lerOpcaoSubmenu(op);
			executarOpcaoSubMenu(opSubmenu);
		} while (opSubmenu != SubmenuOperacoes.VOLTAR);
	}

	/* Metodo de inicializacao.
	 * Inicializa uma seguradora e outros objetos associados e chama alguns dos métodos */
	public static void inicializacao() {
		// Instanciando objetos iniciais
		Seguradora seg = new Seguradora("Unicamp Seguros", "+55(19)1234-5678",
				"contato@unicampseguros.com.br", "Ciclo Basico");
		Veiculo v1 = new Veiculo("ABC1D23", "Toyota", "Corolla", 2015);
		Veiculo v2 = new Veiculo("EFG4H56", "Volkswagen", "Jetta", 2014);
		Veiculo v3 = new Veiculo("IJK7L89", "Chevrolet", "Corsa", 2002);
		Veiculo v4 = new Veiculo("MNO1P23", "Volkswagen", "Fusca", 1994);
		ClientePF cliente1 = new ClientePF("Ana", "Instituto de Computacao", "123.456.789-09",
				"Feminino", Data.stringToDate("15/04/2023"), "Superior completo",
				Data.stringToDate("12/10/1995"), "Media", v1);
		ClientePJ cliente2 = new ClientePJ("Meta", "Instituto de Computacao",
				"12.345.678/0001-95", Data.stringToDate("04/02/2004"), 5, v2);
		ClientePJ cliente3 = new ClientePJ("Google", "FEEC",
				"02.649.275/0001-86", Data.stringToDate("04/09/1998"), 13, v3, v4);

		// Chamando métodos da seguradora
		System.out.println("------------------ Metodos seguradora ------------------");
		// Cadastro
		System.out.println("Cadastro de " + cliente1.getNome() + " realizado com sucesso: " +
				seg.cadastrarCliente(cliente1));
		System.out.println("Cadastro de " + cliente2.getNome() + " realizado com sucesso: " +
				seg.cadastrarCliente(cliente2));
		System.out.println("Cadastro de " + cliente3.getNome() + " realizado com sucesso: " +
				seg.cadastrarCliente(cliente3));
		// Gerando sinistros
		// cliente1, v1
		seg.gerarSinistro(Data.stringToDate("14/05/2023"), "Instituto de Educacao",
				seg.getListaClientes().get(0).getListaVeiculos().get(0),
				seg.getListaClientes().get(0));
		// cliente3, v3
		seg.gerarSinistro(Data.stringToDate("15/05/2023"), "Instituto de Economia",
				seg.getListaClientes().get(2).getListaVeiculos().get(0),
				seg.getListaClientes().get(2));
		// listarClientes(), visualizarSinistro(), listarSinistros(), calcularReceita()
		System.out.println("Listando clientes PJ:");
		seg.listarClientes("PJ");
		System.out.println("Sinistros de " + seg.getListaClientes().get(0).getNome() +
				" na seguradora " + seg.getNome() + ": ");
		seg.visualizarSinistro(documento(seg.getListaClientes().get(0))); // Sinistros de cliente1
		System.out.println("Sinistros da seguradora " + seg.getNome() + ": ");
		seg.listarSinistros();
		System.out.println("Receita da seguradora " + seg.getNome() + ": " + seg.calcularReceita());
		listaSeguradoras.add(seg);
	}

	/* Métodos para requisicão e impressão de dados. Todas as operacões pedem para que seja
	 * selecionada uma seguradora e, se necessario, clientes e veiculos para serem realizadas.*/
	/* Requisita a escolha de uma das seguradoras registradas e a retorna.
	 * Retorna null e imprime mensagem de retorno se nao há seguradoras cadastradas. */
	public static Seguradora requisitarSeguradora() {
		int pos;
		if (listarSeguradoras()) {
			pos = Leitura.lerIndice(listaSeguradoras.size()); // leitura da escolha
			return listaSeguradoras.get(pos); // retorna a seguradora
		}
		System.out.println("Nao ha seguradoras cadastradas. Retornando.");
		return null;
	}
	/* Requisita a escolha de um dos clientes registrados em seg e o retorna.
	 * Retorna null e imprime mensagem de retorno se nao há clientes cadastrados. */
	public static Cliente requisitarCliente(Seguradora seg) {
		int pos;
		if (seg.listarClientes("")) { // lista todos os clientes
			pos = Leitura.lerIndice(seg.getListaClientes().size()); // leitura da escolha
			return seg.getListaClientes().get(pos); // retorna o cliente
		}
		System.out.println("Nao ha clientes cadastrados na seguradora. Retornando");
		return null;
	}
	/* Requisita a escolha de um dos veiculos registrados sob o cliente.
	 * Retorna null e imprime mensagem de retorno se nao há veiculos cadastrados. */
	public static Veiculo requisitarVeiculo(Cliente cliente) {
		int pos;
		if (cliente.listarVeiculos()) {
			pos = Leitura.lerIndice(cliente.getListaVeiculos().size()); // leitura da escolha
			return cliente.getListaVeiculos().get(pos); // retorna o veiculo
		}
		System.out.println("Nao ha veiculos cadastrados para esse cliente. Retornando.");
		return null;
	}
	/* Imprime "Operacao realizada com sucesso" se operacaoRealizada for true.
	 * Imprime "Ocorreu um erro. Tente novamente." se operacaoRealizada for false.
	 * Usado ao final de algumas operacoes. */
	public static void mensagemOperacaoRealizada(boolean operacaoRealizada) {
		if (operacaoRealizada)
			System.out.println("Operacao realizada com sucesso.");
		else
			System.out.println("Ocorreu um erro. Tente novamente.");
	}
	/* Retorna "<cliente.cpf>" ou "<cliente.cnpj>" quando cliente é da classe
	 * ClientePF ou ClientePJ, respectivamente. Retorna "Nao possui documento cadastrado"
	 * quando é da classe Cliente. */
	public static String documento(Cliente cliente) {
		String str = "Nao possui documento cadastrado"; // valor padrão (classe Cliente)
		if (cliente instanceof ClientePF)
			str = ((ClientePF) cliente).getCpf();
		else if (cliente instanceof ClientePJ)
			str = ((ClientePJ) cliente).getCnpj();
		return str;
	}
	/* Lista as seguradoras em Seguradora.listaSeguradoras no formato "i - Seguradora.nome",
	 * onde i é o índice da seguradora na lista. Retorna boolean indicando se imprimiu. */
	public static boolean listarSeguradoras() {
		if (! listaSeguradoras.isEmpty()) {
			for (int i = 0; i < listaSeguradoras.size(); i++)
				System.out.println(String.valueOf(i) + " - " +
						listaSeguradoras.get(i).getNome());
			return true;
		}
		return false;
	}

	/* Métodos para as operacões dos menus */
	// Gerar sinistro
	public static void operacaoGerarSinistro() {
		Seguradora seg;
		Date data;
		String endereco;
		Cliente cliente;
		Veiculo veiculo;
		System.out.println("** Geracao de sinistro **");
		// leitura
		System.out.println("Digite o numero referente a seguradora na qual deseja cadastrar o sinistro:");
		seg = requisitarSeguradora();
		if (seg == null) return;
		System.out.print("Digite a data de ocorrencia (no formato dia/Mês/ano - dd/MM/yyyy): ");
		data = Leitura.lerData();
		System.out.print("Digite o endereco: ");
		endereco = Leitura.lerString();
		System.out.println("Digite o numero referente ao cliente:");
		cliente = requisitarCliente(seg);
		if (cliente == null) return;
		System.out.println("Digite o numero referente ao veiculo:");
		veiculo = requisitarVeiculo(cliente);
		if (veiculo == null) return;
		// cria sinistro com informacões lidas + impressão do resultado
		mensagemOperacaoRealizada(seg.gerarSinistro(data, endereco, veiculo, cliente));
	}

	// Transferir seguro
	public static void operacaoTransferirSeguro() {
		Seguradora seg;
		Cliente clienteTransf, clienteReceb;
		System.out.println("** Transferencia de seguro **");
		// leitura
		System.out.println("Digite o numero referente a seguradora na qual deseja realizar a transferencia:");
		seg = requisitarSeguradora();
		if (seg == null) return;
		System.out.println("Digite o numero referente ao cliente cujo seguro sera transferido:");
		clienteTransf = requisitarCliente(seg);
		if (clienteTransf == null) return;
		System.out.println("Digite o numero referente ao cliente que recebera o seguro:");
		clienteReceb = requisitarCliente(seg);
		// transferência e impressão do resultado
		if (seg.transferirSeguro(clienteTransf, clienteReceb))
			System.out.println("Seguro transferido com sucesso.");
		else // quando clienteTransf.equals(clienteReceb)
			System.out.println("Os clientes selecionados sao os mesmos. Abortando");
	}

	// Calcular receita
	public static void operacaoCalcularReceita() {
		Seguradora seg;
		System.out.println("** Calculo de receita **");
		// leitura
		System.out.println("Digite o numero referente a seguradora da qual deseja calcular a receita:");
		seg = requisitarSeguradora();
		if (seg == null) return;
		// impressão do resultado
		System.out.println("Receita de " + seg.getNome() + ": " + seg.calcularReceita());
	}

	// Cadastrar cliente
	/* Pede as informacões necessárias para cadastrar um cliente (exceto veiculos)
	 * e o cadastra na seguradora pelo usuário. */
	public static void operacaoCadastrarCliente() {
		// pede primeiro nome e endereco (atributos de Cliente)
		int opcao;
		Seguradora seg; // seguradora em que será cadastrado
		String nome, endereco;
		System.out.println("** Cadastro de cliente **");
		// leitura
		System.out.println("Qual tipo de cliente deseja cadastrar?\n" + 
				"0 - Pessoa Fisica\n1 - Pessoa Juridica");
		opcao = Leitura.lerIndice(2); // opcões só podem ser 1 ou 2
		System.out.println("Digite o numero referente a seguradora na qual deseja cadastrar");
		seg = requisitarSeguradora();
		if (seg == null) return;
		System.out.print("Digite o nome do cliente: ");
		nome = Leitura.lerNome();
		System.out.print("Digite o endereco de residencia: ");
		endereco = Leitura.lerString();
		// cadastro e impressão do resultado, separa em cadastro de ClientePF ou ClientePJ
		if (opcao == 0) // ClientePF
			mensagemOperacaoRealizada(operacaoCadastrarClientePF(seg, nome, endereco));
		else // ClientePJ
			mensagemOperacaoRealizada(operacaoCadastrarClientePJ(seg, nome, endereco));
	}
	/* Pede as informacões necessárias para cadastrar um clientePJ. A seguradora em que será cadastrado,
	 * o nome e o endereco são passados como argumentos em operacaoCadastrarCliente(). */
	private static boolean operacaoCadastrarClientePJ(Seguradora seg, String nome, String endereco) {
		String cnpj;
		Date dataFundacao;
		int qtdeFuncionarios;
		// leitura
		System.out.print("Digite o CNPJ do cliente: ");
		cnpj = Leitura.lerCnpj();
		System.out.print("Digite a data de fundacao (no formato dia/Mês/ano - dd/MM/yyyy): ");
		dataFundacao = Leitura.lerData();
		System.out.print("Digite a quantidade de funcionarios: ");
		// qtde funcionários deve estar entre 0 e 99999 (arbitrário)
		qtdeFuncionarios = Leitura.lerIndice(10000);
		// cadastro inicial sem carros
		return seg.cadastrarCliente(new ClientePJ(nome, endereco, cnpj, dataFundacao,
				qtdeFuncionarios));
	}
	/* Pede as informacões necessárias para cadastrar um clientePF. A seguradora em que será cadastrado,
	 * o nome e o endereco são passados como argumentos em operacaoCadastrarCliente(). */
	private static boolean operacaoCadastrarClientePF(Seguradora seg, String nome, String endereco) {
		String cpf, genero, educacao, classeEconomica;
		Date dataLicenca, dataNascimento;
		// leitura
		System.out.print("Digite o CPF do cliente: ");
		cpf = Leitura.lerCpf();
		System.out.print("Digite o genero do cliente: ");
		genero = Leitura.lerString();
		System.out.print("Digite o nivel de educacao do cliente: ");
		educacao = Leitura.lerString();
		System.out.print("Digite a classe economica do cliente: ");
		classeEconomica = Leitura.lerString();
		System.out.print("Digite a data da licenca de motorista do cliente " +
				"(no formato dia/Mês/ano - dd/MM/yyyy): ");
		dataLicenca = Leitura.lerData();
		System.out.print("Digite a data de nascimento do cliente " + 
				"(no formato dia/Mês/ano - dd/MM/yyyy): ");
		dataNascimento = Leitura.lerData();
		// cadastro inicial sem carros
		return seg.cadastrarCliente(new ClientePF(nome, endereco, cpf, genero, dataLicenca,
				educacao, dataNascimento, classeEconomica));
	}

	// Cadastrar veiculo
	public static void operacaoCadastrarVeiculo() {
		Seguradora seg; // seguradora em que está o dono do veículo
		Cliente cliente; // cliente que recebe o veículo que está sendo cadastrado
		String placa, marca, modelo;
		int anoFabricacao;
		System.out.println("** Cadastro de veiculo **");
		// leitura
		System.out.println("Digite o numero referente a seguradora na qual o cliente" +
				"dono do veículo esta cadastrado:");
		seg = requisitarSeguradora();
		if (seg == null) return;
		System.out.println("Digite o numero referente ao dono do veiculo:");
		cliente = requisitarCliente(seg);
		if (cliente == null) return;
		System.out.print("Digite a placa do veiculo: ");
		placa = Leitura.lerString();
		System.out.print("Digite a marca do veiculo: ");
		marca = Leitura.lerString();
		System.out.print("Digite o modelo do veiculo: ");
		modelo = Leitura.lerString();
		System.out.print("Digite o ano de fabricacao do veiculo: ");
		// anoFabricacao deve estar entre 0 e 2199 (arbitrário)
		anoFabricacao = Leitura.lerIndice(2200);
		// cadastro na lista do cliente + impressão do resultado
		mensagemOperacaoRealizada(seg.adicionarVeiculoCliente(cliente,
				new Veiculo(placa, marca, modelo, anoFabricacao)));
	}

	// Cadastrar seguradora
	public static void operacaoCadastrarSeguradora() {
		String nome, telefone, email, endereco;
		System.out.println("** Cadastro de seguradora **");
		// leitura
		System.out.print("Digite o nome da seguradora: ");
		nome = Leitura.lerNome();
		System.out.print("Digite o telefone da seguradora: ");
		telefone = Leitura.lerString();
		System.out.print("Digite o e-mail da seguradora: ");
		email = Leitura.lerString();
		System.out.print("Digite o endereco da seguradora: ");
		endereco = Leitura.lerString();
		// cadastro inicial sem clientes e sinistros + impressão do resultado
		mensagemOperacaoRealizada(listaSeguradoras.add(new Seguradora(
				nome, telefone, email, endereco)));
	}

	// Listar clientes por seguradora
	public static void operacaoListarClientes() {
		Seguradora seg;
		int opcao;
		boolean imprimiu = false; // para imprimir mensagem se nao tiver clientes
		System.out.println("** Listagem de clientes **");
		// leitura
		System.out.println("Digite o numero referente a seguradora da qual deseja listar os clientes:");
		seg = requisitarSeguradora();
		if (seg == null) return;
		System.out.println("Qual tipo de cliente deseja listar?\n0 - Todos\n1 - Pessoa Física" +
				"\n2 - Pessoa Jurídica");
		opcao = Leitura.lerIndice(3);
		// impressão do resultado
		switch (opcao) {
			case 0: // todos
				System.out.println("Clientes de " + seg.getNome() + ":");
				imprimiu = seg.listarClientes("");
				break;
			case 1: // PF
				System.out.println("Clientes pessoa fisica de " + seg.getNome() + ":");
				imprimiu = seg.listarClientes("PF");
				break;
			case 2: // PJ
				System.out.println("Clientes pessoa juridica de " + seg.getNome() + ":");
				imprimiu = seg.listarClientes("PJ");
				break;
		}
		if (! imprimiu && opcao > 0) // nao imprimiu p/ opcao clientePF ou clientePJ
			System.out.println("Nao ha clientes do tipo especificado cadastrados.");
		else if (! imprimiu) // nao imprimiu p/ opcao "todos"
			System.out.println("Nao ha clientes cadastrados.");
	}
	
	// Listar sinistros por seguradora
	public static void operacaoListarSinistrosSeguradora() {
		Seguradora seg;
		System.out.println("** Listagem de sinistros por seguradora **");
		// leitura
		System.out.println("Digite o numero referente a seguradora da qual deseja listar os sinistros");
		seg = requisitarSeguradora();
		if (seg == null) return;
		// impressão do resultado
		System.out.println("Sinistros de " + seg.getNome() + ":");
		if(! seg.listarSinistros())
			System.out.println("Nao ha sinistros cadastrados");
	}

	// Listar sinistros por cliente
	public static void operacaoListarSinistrosCliente() {
		Seguradora seg;
		Cliente cliente;
		System.out.println("** Listagem de sinistros **");
		// leitura
		System.out.println("Digite o numero referente a seguradora contratada pelo cliente" +
				" do qual deseja listar os sinistros:");
		seg = requisitarSeguradora();
		if (seg == null) return;
		System.out.println("Digite o numero referente ao cliente:");
		cliente = requisitarCliente(seg);
		if (cliente == null) return;
		// impressão do resultado
		System.out.println("Sinistros de " + cliente.getNome() + ": ");
		if (! seg.visualizarSinistro(documento(cliente)))
			System.out.println("Nao ha sinistros cadastrados para esse cliente.");
	}

	// Listar veiculos por cliente
	public static void operacaoListarVeiculosCliente() {
		Seguradora seg;
		Cliente cliente;
		System.out.println("** Listagem de veiculos por cliente **");
		// leitura
		System.out.println("Digite o numero referente a seguradora contratada pelo cliente " +
				"do qual deseja listar:");
		seg = requisitarSeguradora();
		if (seg == null) return;
		System.out.println("Digite o numero referente ao cliente:");
		cliente = requisitarCliente(seg);
		if (cliente == null) return;
		System.out.println("Veiculos de " + cliente.getNome() + ":");
		// impressão do resultado
		if (! cliente.listarVeiculos())
			System.out.println("Nao ha veiculos cadastrados.");
	}

	// Listar veiculos por seguradora
	public static void operacaoListarVeiculosSeguradora() {
		Seguradora seg;
		System.out.println("** Listagem de veiculo por seguradora **");
		// leitura
		System.out.println("Digite o numero referente a seguradora da qual deseja listar:");
		seg = requisitarSeguradora();
		if (seg == null) return;
		// impressão do resultado
		System.out.println("Veiculos cadastrados em " + seg.getNome() + ":");
		if (! seg.listarVeiculos())
			System.out.println("Nao ha veiculos cadastrados.");
	}

	// Excluir cliente da seguradora
	public static void operacaoExcluirCliente() {
		Seguradora seg;
		Cliente cliente;
		System.out.println("** Exclusao de cliente **");
		// leitura
		System.out.println("Digite o numero referente a seguradora na qual o cliente " +
				"que deseja remover esta cadastrado");
		seg = requisitarSeguradora();
		if (seg == null) return;
		System.out.println("Digite o numero referente ao cliente:");
		cliente = requisitarCliente(seg);
		if (cliente == null) return;
		// remocão + impressão do resultado
		mensagemOperacaoRealizada(seg.removerCliente(documento(cliente)));
	}

	// Excluir veiculo do cliente
	public static void operacaoExcluirVeiculo() {
		Seguradora seg;
		Cliente cliente;
		Veiculo veiculo;
		System.out.println("** Exclusao de veiculo **");
		// leitura
		System.out.println("Digite o numero referente a seguradora na qual o " +
				"dono do veiculo que deseja remover esta cadastrado");
		seg = requisitarSeguradora();
		if (seg == null) return;
		System.out.println("Digite o numero referente ao cliente:");
		cliente = requisitarCliente(seg);
		if (cliente == null) return;
		System.out.println("Digite o numero referente ao veiculo que deseja remover:");
		veiculo = requisitarVeiculo(cliente);
		if (veiculo == null) return;
		// remocão + impressão do resultado
		mensagemOperacaoRealizada(seg.removerVeiculoCliente(cliente, veiculo));
	}

	// Excluir sinistro da seguradora
	public static void operacaoExcluirSinistro() {
		Seguradora seg;
		int indexRemov; // indice do sinistro removido em seg.listaSinistros
		System.out.println("** Exclusao de sinistro **");
		// leitura
		System.out.println("Digite o numero referente a seguradora cujo " +
				"sinistro deseja remover");
		seg = requisitarSeguradora();
		if (seg == null) return;
		System.out.println("Digite o numero referente ao sinistro que deseja remover");
		seg.listarSinistros();
		indexRemov = Leitura.lerIndice(seg.getListaSinistros().size());
		// remocão + impressão do resultado
		mensagemOperacaoRealizada(seg.removerSinistro(seg.getListaSinistros().get(indexRemov)));
	}

	public static void main(String[] args) {
		MenuOperacoes op;
		inicializacao(); // Inicializa uma seguradora
		// Usando o menu
		System.out.println("-------------- Menu --------------");
		do {
			exibirMenuExterno();
			op = lerOpcaoMenuExterno();
			executarOpcaoMenuExterno(op);
		} while (op != MenuOperacoes.SAIR);
		System.out.println("Saiu do sistema.");
		Leitura.fechar();
	}

}