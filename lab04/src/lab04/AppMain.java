package lab04;

import java.util.Scanner;
import java.util.Date;

public class AppMain {
	/* Classe de leitura (para só instanciar um scanner) */
	private static class Leitura {
		public static Scanner scanner = new Scanner(System.in);
		/* Lê até que seja dado um inteiro e o retorna */
		public static int lerInteiro() {
			String input;
			int num;
			do {
				input = scanner.nextLine();
				try {
					num = Integer.parseInt(input);
					return num;
				} catch (NumberFormatException e) { // não é inteiro
					System.out.println("Erro. Digite um numero inteiro.");
				}
			} while (true);
		}
		/* Lê uma string e a retorna */
		public static String lerString() {
			return scanner.nextLine();
		}
		/* Lê até que seja dada uma data no formato dd/MM/yyyy e retorna o Date equivalente */
		public static Date lerData() {
			Date data;
			do {
				data = Data.stringToDate(Leitura.lerString());
				if (data == null)
					System.out.println("Erro. Digite a data no formato especificado.");
				else
					break;
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
				if (! indiceValido(pos, tam))
					System.out.println("Erro. Digite um valor valido.");
				else
					break;
			} while (true);
			return pos;
		}
	}

	/* Métodos para os menus */
	//exibir menu externo
	private static void exibirMenuExterno() {
		MenuOperacoes menuOpcoes[] = MenuOperacoes.values();
		System.out.println("Menu principal");
		for(MenuOperacoes op: menuOpcoes) {
			System.out.println(op.ordinal() + " - " + op.getDescricao());
		}
	}
	/* exibir submenus
	 * se a lista de constantes do submenu for percorrida da mesma forma que o meu externo, a opcão Voltar
	 * é printada com a posicão que está na lista do enum (9 - Voltar). Por isso, a lista é percorrida 
	 * de forma diferente, tendo i como o inteiro correspondente. Assim, para listar o submenu de cadastros,
	 * por exemplo, vai ser printado "3 - Voltar".
	 */
	private static void exibirSubmenu(MenuOperacoes op) {
		SubmenuOperacoes[] submenu = op.getSubmenu();
		System.out.println(op.getDescricao());
		for(int i=0; i<submenu.length; i++) {
			System.out.println(i +" - " + submenu[i].getDescricao());
		}
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
			case LISTAR_SINISTROS:
				operacaoListarSinistros();
				break;
			case LISTAR_VEICULOS:
				operacaoListarVeiculos();
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

	/* Inicializa uma seguradora e objetos associados e chama alguns dos métodos */
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
	}

	public static void main(String[] args) {
		MenuOperacoes op;
		inicializacao(); // Inicializa uma seguradora
		// Usando o menu
		do {
			exibirMenuExterno();
			op = lerOpcaoMenuExterno();
			executarOpcaoMenuExterno(op);
		} while (op != MenuOperacoes.SAIR);
		System.out.println("Saiu do sistema.");
		Leitura.scanner.close();
	}

	/* Retorna "<cliente.cpf>" ou "<cliente.cnpj>" quando cliente é da classe
	 * ClientePF ou ClientePJ, respectivamente. Retorna "Nao possui documento cadastrado"
	 * quando é da classe Cliente. */
	private static String documento(Cliente cliente) {
		String str = "Nao possui documento cadastrado"; // valor padrão (classe Cliente)
		if (cliente instanceof ClientePF)
			str = ((ClientePF) cliente).getCpf();
		else if (cliente instanceof ClientePJ)
			str = ((ClientePJ) cliente).getCnpj();
		return str;
	}
	/* Lista as seguradoras em Seguradora.listaSeguradoras no formato "i - Seguradora.nome",
	 * onde i é o índice da seguradora na lista. */
	public static void listarSeguradoras() {
		for (int i = 0; i < Seguradora.getListaSeguradoras().size(); i++) {
			System.out.println(String.valueOf(i) + " - " +
					Seguradora.getListaSeguradoras().get(i).getNome());
		}
	}
	/* Requisita a escolha de uma das seguradoras registradas e a retorna */
	public static Seguradora requisitarSeguradora() {
		int pos;
		listarSeguradoras();
		pos = Leitura.lerIndice(Seguradora.getListaSeguradoras().size());
		return Seguradora.getListaSeguradoras().get(pos);
	}
	/* Requisita a escolha de um dos clientes registrados em seg */
	public static Cliente requisitarCliente(Seguradora seg) {
		int pos;
		seg.listarClientes(""); // lista todos os clientes
		pos = Leitura.lerIndice(seg.getListaClientes().size());
		return seg.getListaClientes().get(pos);
	}
	/* Requisita a escolha de um dos veiculos registrados sob o cliente */
	public static Veiculo requisitarVeiculo(Cliente cliente) {
		int pos;
		for (int i = 0; i < cliente.getListaVeiculos().size(); i++) {
			System.out.println(String.valueOf(i) + " - Placa " +
					cliente.getListaVeiculos().get(i).getPlaca());
		}
		pos = Leitura.lerIndice(cliente.getListaVeiculos().size());
		return cliente.getListaVeiculos().get(pos);
	}
	public static void mensagemOperacaoRealizada(boolean cadastrou) {
		if (cadastrou)
			System.out.println("Operacao realizada com sucesso.");
		else
			System.out.println("Ocorreu um erro. Tente novamente.");
	}

	// Gerar sinistro
	public static void operacaoGerarSinistro() {
		Date data;
		String endereco;
		Seguradora seg;
		Cliente cliente;
		Veiculo veiculo;
		System.out.println("Geracao de sinistro");
		System.out.println("Digite o numero referente a seguradora na qual deseja cadastrar o sinistro:");
		seg = requisitarSeguradora();
		System.out.println("Digite a data de ocorrencia (no formato dia/Mês/ano - dd/MM/yyyy):");
		data = Leitura.lerData();
		System.out.println("Digite o endereco:");
		endereco = Leitura.lerString();
		System.out.println("Digite o numero referente ao cliente:");
		cliente = requisitarCliente(seg);
		System.out.println("Digite o numero referente ao veiculo:");
		veiculo = requisitarVeiculo(cliente);
		seg.gerarSinistro(data, endereco, veiculo, cliente);
		System.out.println("Sinistro gerado com sucesso.");
	}

	// Transferir seguro
	public static void operacaoTransferirSeguro() {
		Seguradora seg;
		Cliente clienteTransf, clienteReceb;
		System.out.println("Transferencia de seguro");
		System.out.println("Digite o numero referente a seguradora na qual deseja realizar a transferencia:");
		seg = requisitarSeguradora();
		System.out.println("Digite o numero referente ao cliente cujo seguro sera transferido:");
		clienteTransf = requisitarCliente(seg);
		System.out.println("Digite o numero referente ao cliente que recebera o seguro:");
		clienteReceb = requisitarCliente(seg);
		if (seg.transferirSeguro(clienteTransf, clienteReceb))
			System.out.println("Seguro transferido com sucesso.");
		else // clienteTransf.equals(clienteReceb)
			System.out.println("Os clientes selecionados sao os mesmos. Abortando");
	}

	// Calcular receita
	public static void operacaoCalcularReceita() {
		Seguradora seg;
		System.out.println("Calculo de receita");
		System.out.println("Digite o numero referente a seguradora da qual deseja calcular a receita:");
		seg = requisitarSeguradora();
		System.out.println("Receita de " + seg.getNome() + ": " + seg.calcularReceita());
	}

	// Cadastrar
	public static void operacaoCadastrarCliente() {
		int opcao;
		Seguradora seg;
		String nome, endereco;
		System.out.println("Cadastro de cliente");
		System.out.println("Qual tipo de cliente deseja cadastrar?\n" + 
				"0 - Pessoa Fisica\n1 - Pessoa Juridica");
		opcao = Leitura.lerIndice(2);
		System.out.println("Em qual seguradora deseja cadastrar o cliente?");
		seg = requisitarSeguradora();
		System.out.println("Digite o nome do cliente:");
		do {
			nome = Leitura.lerString();
			if (! Validacao.validaNome(nome))
				System.out.println("Nome invalido. Digite novamente.");
			else
				break;
		} while (true);
		System.out.println("Digite o endereco de residencia:");
		endereco = Leitura.lerString();
		if (opcao == 0) // ClientePF
			mensagemOperacaoRealizada(operacaoCadastrarClientePF(seg, nome, endereco));
		else // ClientePJ
			mensagemOperacaoRealizada(operacaoCadastrarClientePJ(seg, nome, endereco));
	}
	public static boolean operacaoCadastrarClientePJ(Seguradora seg, String nome, String endereco) {
		String cnpj;
		Date dataFundacao;
		int qtdeFuncionarios;
		System.out.println("Digite o CNPJ do cliente:");
		do {
			cnpj = Leitura.lerString();
			if (! Validacao.validarCNPJ(cnpj))
				System.out.println("CNPJ invalido. Tente digitar novamente.");
			else
				break;
		} while (true);
		System.out.println("Digite a data de fundacao (no formato dia/Mês/ano - dd/MM/yyyy):");
		dataFundacao = Leitura.lerData();
		System.out.println("Digite a quantidade de funcionarios:");
		qtdeFuncionarios = Leitura.lerInteiro();
		// cadastro inicial sem carros
		return seg.cadastrarCliente(new ClientePJ(nome, endereco, cnpj, dataFundacao,
				qtdeFuncionarios));
	}
	public static boolean operacaoCadastrarClientePF(Seguradora seg, String nome, String endereco) {
		String cpf, genero, educacao, classeEconomica;
		Date dataLicenca, dataNascimento;
		System.out.println("Digite o CPF do cliente:");
		do {
			cpf = Leitura.lerString();
			if (! Validacao.validarCPF(cpf))
				System.out.println("CPF invalido. Tente digitar novamente.");
			else
				break;
		} while (true);
		System.out.println("Digite o genero do cliente:");
		genero = Leitura.lerString();
		System.out.println("Digite o nivel de educacao do cliente:");
		educacao = Leitura.lerString();
		System.out.println("Digite a classe economica do cliente:");
		classeEconomica = Leitura.lerString();
		System.out.println("Digite a data da licenca de motorista do cliente" +
				"(no formato dia/Mês/ano - dd/MM/yyyy):");
		dataLicenca = Leitura.lerData();
		System.out.println("Digite a data de nascimento do cliente" + 
				"(no formato dia/Mês/ano - dd/MM/yyyy):");
		dataNascimento = Leitura.lerData();
		// cadastro inicial sem carros
		return seg.cadastrarCliente(new ClientePF(nome, endereco, cpf, genero, dataLicenca,
				educacao, dataNascimento, classeEconomica));
	}
	public static void operacaoCadastrarVeiculo() {
		Seguradora seg;
		Cliente cliente; // cliente que recebe o veículo que está sendo cadastrado
		String placa, marca, modelo;
		int anoFabricacao;
		System.out.println("Cadastro de veiculo");
		System.out.println("Digite o numero referente a seguradora na qual o cliente" +
				"dono do veículo esta cadastrado:");
		seg = requisitarSeguradora();
		System.out.println("Digite o numero referente ao cliente dono do veiculo:");
		cliente = requisitarCliente(seg);
		System.out.println("Digite a placa do veiculo:");
		placa = Leitura.lerString();
		System.out.println("Digite a marca do veiculo:");
		marca = Leitura.lerString();
		System.out.println("Digite o modelo do veiculo:");
		modelo = Leitura.lerString();
		System.out.println("Digite o ano de fabricacao do veiculo:");
		anoFabricacao = Leitura.lerInteiro();
		mensagemOperacaoRealizada(cliente.getListaVeiculos().add(new Veiculo(
				placa, marca, modelo, anoFabricacao)));
		seg.atualizarValoresSeguro();		
	}
	public static void operacaoCadastrarSeguradora() {
		String nome, telefone, email, endereco;
		System.out.println("Cadastro de seguradora");
		System.out.println("Digite o nome da seguradora:");
		nome = Leitura.lerString(); // sem validacao pois nome de seguradora pode conter numeros
		System.out.println("Digite o telefone da seguradora:");
		telefone = Leitura.lerString();
		System.out.println("Digite o e-mail da seguradora:");
		email = Leitura.lerString();
		System.out.println("Digite o endereco da seguradora:");
		endereco = Leitura.lerString();
		// cadastro inicial sem clientes e sinistros
		mensagemOperacaoRealizada(Seguradora.getListaSeguradoras().add(new Seguradora(
				nome, telefone, email, endereco)));
	}

	// Listar
	public static void operacaoListarClientes() {
		Seguradora seg;
		int opcao;
		System.out.println("Listagem de clientes");
		System.out.println("Digite o numero referente a seguradora da qual deseja listar os clientes:");
		seg = requisitarSeguradora();
		System.out.println("Qual tipo de cliente deseja listar?\n0 - Todos\n1 - Pessoa Física" +
				"\n2 - Pessoa Jurídica");
		opcao = Leitura.lerIndice(3);
		switch (opcao) {
			case 0: // todos
				System.out.println("Clientes de " + seg.getNome() + ":");
				seg.listarClientes("");
				break;
			case 1: // PF
				System.out.println("Clientes pessoa fisica de " + seg.getNome() + ":");
				seg.listarClientes("PF");
				break;
			case 2: // PJ
				System.out.println("Clientes pessoa juridica de " + seg.getNome() + ":");
				seg.listarClientes("PJ");
				break;
		}
	}
	public static void operacaoListarSinistros() {
		Seguradora seg;
		System.out.println("Listagem de sinistros");
		System.out.println("Digite o numero referente a seguradora da qual deseja listar os sinistros");
		seg = requisitarSeguradora();
		System.out.println("Sinistros de " + seg.getNome() + ":");
		seg.listarSinistros();
	}
	public static void operacaoListarVeiculos() {
		Seguradora seg;
		Cliente cliente;
		System.out.println("Listagem de veiculos");
		System.out.println("Digite o numero referente a seguradora na qual o cliente " +
				"do qual deseja listar os veiculos esta cadastrado");
		seg = requisitarSeguradora();
		System.out.println("Digite o numero referente ao cliente:");
		cliente = requisitarCliente(seg);
		System.out.println("Veiculos de " + cliente.getNome() + ":");
		for (int i = 0; i < cliente.getListaVeiculos().size(); i++) {
			// imprime "i - veiculo.toString()"
			System.out.println(String.valueOf(i) + " - " + cliente.getListaVeiculos().get(i));
		}
	}

	// Excluir
	public static void operacaoExcluirCliente() {
		Seguradora seg;
		Cliente cliente;
		System.out.println("Exclusao de cliente");
		System.out.println("Digite o numero referente a seguradora na qual o cliente " +
				"que deseja remover esta cadastrado");
		seg = requisitarSeguradora();
		System.out.println("Digite o numero referente ao cliente:");
		cliente = requisitarCliente(seg);
		mensagemOperacaoRealizada(seg.removerCliente(documento(cliente)));
	}
	public static void operacaoExcluirVeiculo() {
		Seguradora seg;
		Cliente cliente;
		Veiculo veiculo;
		System.out.println("Exclusao de veiculo");
		System.out.println("Digite o numero referente a seguradora na qual o cliente " +
				"dono do veiculo que deseja remover esta cadastrado");
		seg = requisitarSeguradora();
		System.out.println("Digite o numero referente ao cliente:");
		cliente = requisitarCliente(seg);
		System.out.println("Digite o numero referente ao veiculo que deseja remover:");
		veiculo = requisitarVeiculo(cliente);
		mensagemOperacaoRealizada(cliente.getListaVeiculos().remove(veiculo));
		seg.atualizarValoresSeguro();
	}
	public static void operacaoExcluirSinistro() {
		Seguradora seg;
		int indexRemov; // indice do sinistro removido em seg.listaSinistros
		System.out.println("Exclusao de sinistro");
		System.out.println("Digite o numero referente a seguradora que cobre o " +
				"sinistro que deseja remover");
		seg = requisitarSeguradora();
		System.out.println("Digite o numero referente ao sinistro que deseja remover");
		seg.listarSinistros();
		indexRemov = Leitura.lerIndice(seg.getListaSinistros().size());
		seg.getListaSinistros().remove(seg.getListaSinistros().get(indexRemov));
	}

}