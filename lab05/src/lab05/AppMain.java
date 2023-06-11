package lab05;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* Classe contendo o método main. Implementa o menu e os métodos necessários
 * para ler instrucões do usuário e realizar as operacões desejadas. */

public class AppMain {
	// listas com cada 
	private static List<Seguradora> listaSeguradoras = new ArrayList<Seguradora>();
	private static List<Cliente> listaClientes = new ArrayList<Cliente>();
	private static List<Veiculo> listaVeiculosCadastrados = new ArrayList<Veiculo>();
	private static List<Veiculo> listaVeiculosDisponiveis = new ArrayList<Veiculo>();
	private static List<Frota> listaFrotasCadastradas = new ArrayList<Frota>();
	private static List<Frota> listaFrotasDisponiveis = new ArrayList<Frota>();
	private static List<Condutor> listaCondutores = new ArrayList<Condutor>();

	// classe aninhada para leitura
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
		/* Lê até que seja dada uma data no formato dd/MM/aaaa e retorna o Date equivalente.
		 * Imprime mensagem de erro quando data não é dada no formato */
		public static String lerData() {
			String input;
			do {
				input = Leitura.lerString();
				if (Validacao.validaData(input))
					return input;
				System.out.println("Erro. Data invalida ou fora do formato especificado. " +
						"Tente digitar novamente.");
			} while (true);
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
				System.out.print("Digite uma opcao: ");
				pos = lerInteiro();
				// pos >= 0 e pos < tam
				if (indiceValido(pos, tam))
					return pos;
				System.out.println("Erro. Digite um valor valido: ");
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
	 * por exemplo, vai ser printado "3 - Voltar". */
	private static void exibirSubmenu(MenuOperacoes op) {
		SubmenuOperacoes[] submenu = op.getSubmenu();
		System.out.println("** " + op.getDescricao() + " **");
		for(int i = 1; i < submenu.length; i++)
			System.out.println(i +" - " + submenu[i].getDescricao());
		System.out.println("0 - " + submenu[0].getDescricao());
	}
	//ler opcões do menu externo
	private static MenuOperacoes lerOpcaoMenuExterno() {
		int opUsuario;
		MenuOperacoes opUsuarioConst;
		//System.out.print("Digite uma opcao: ");
		opUsuario = Leitura.lerIndice(MenuOperacoes.values().length);
		opUsuarioConst = MenuOperacoes.values()[opUsuario];
		return opUsuarioConst;
	}
	//ler opcão dos submenus
	private static SubmenuOperacoes lerOpcaoSubmenu(MenuOperacoes op) {
		int opUsuario;
		SubmenuOperacoes opUsuarioConst;
		//System.out.print("Digite uma opcao: ");
		opUsuario = Leitura.lerIndice(op.getSubmenu().length);
		opUsuarioConst = op.getSubmenu()[opUsuario];
		return opUsuarioConst;
	}
	//executar opcões do menu externo
	private static void executarOpcaoMenuExterno(MenuOperacoes op) {
		switch(op) {
			case CADASTROS:
			case ADICIONAR:
			case GERAR:
			case IMPRIMIR:
				executarSubmenu(op);
				break;
			case CALCULAR_RECEITA:
				operacaoCalcularReceita();
				break;
			case SAIR:
				break;
		}
	}
	//executar opcões dos submenus
	private static void executarOpcaoSubMenu(SubmenuOperacoes opSubmenu) {
		System.out.println("** " + opSubmenu.getDescricao() + " **");
		switch(opSubmenu) {
			case CADASTRAR_SEGURADORA:
				operacaoCadastrarSeguradora();
				break;
			case CADASTRAR_CLIENTE:
				operacaoCadastrarCliente();
				break;
			case CADASTRAR_VEICULO:
				operacaoCadastrarVeiculo();
				break;
			case CADASTRAR_FROTA:
				operacaoCadastrarFrota();
				break;
			case CADASTRAR_CONDUTOR:
				operacaoCadastrarCondutor();
				break;
			case ADICIONAR_VEICULO_CLIENTE:
				operacaoAddVeiculoCliente();
				break;
			case ADICIONAR_FROTA_CLIENTE:
				operacaoAddFrotaCliente();
				break;
			case ADICIONAR_VEICULO_FROTA:
				operacaoAddVeiculoFrota();
				break;
			case ADICIONAR_CONDUTOR_SEGURO:
				operacaoAddCondutorSeguro();
				break;
			case GERAR_SINISTRO:
				operacaoGerarSinistro();
				break; 
			case GERAR_SEGURO:
				operacaoGerarSeguro();
				break;
			case IMPRIMIR_SEGURADORA:
				operacaoImprimirSeguradora();
				break;
			case IMPRIMIR_CLIENTE:
				operacaoImprimirCliente();
				break;
			case IMPRIMIR_VEICULO:
				operacaoImprimirVeiculo();
				break;
			case IMPRIMIR_FROTA:
				operacaoImprimirFrota();
				break;
			case IMPRIMIR_CONDUTOR:
				operacaoImprimirCondutor();
				break;
			case IMPRIMIR_SEGURO:
				operacaoImprimirSeguro();
				break;
			case IMPRIMIR_SINISTRO:
				operacaoImprimirSinistro();
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
		// instanciando objetos iniciais
		// é esperado que outros objetos sejam instanciados através do menu,
		// onde é feita validacão dos dados. A instanciacão pelo construtor não valida os dados.
		Seguradora unicampSeg = new Seguradora("68.011.474/0001-72", "Unicamp Seguros",
			"+55(19)1234-5678", "Ciclo Basico", "contato@unicampseguros.com");
		Seguradora uspSeg = new Seguradora("27.567.062/0001-36", "USP Seguros", "+55(19)1421-5930",
			"São Paulo", "contato@uspseguros.com");
		Veiculo v1 = new Veiculo("ABC1D23", "Toyota", "Corolla", 2015);
		Veiculo v2 = new Veiculo("EFG4H56", "Volkswagen", "Jetta", 2014);
		Veiculo v3 = new Veiculo("IJK7L89", "Chevrolet", "Corsa", 2002);
		Veiculo v4 = new Veiculo("MNO1P23", "Volkswagen", "Fusca", 1994);
		Veiculo v5 = new Veiculo("PCA9C20", "Toyota", "Corolla", 2002);
		Veiculo v6 = new Veiculo("OPM1F03", "Volkswagen", "Jetta", 2010);
		ClientePF ana = new ClientePF("Ana", "+55(19)91234-5678", "Instituto de Computacao",
			"ana@gmail.com", "123.456.789-09", "Feminino", "Superior completo", Data.stringToDate("12/10/1995"));
		ClientePF beto = new ClientePF("Beto", "+55(19)98923-1043", "Instutto de Quimica",
			 "beto@gmail.com", "781.812.330-00", "Masculino", "Cursando superior", Data.stringToDate("29/02/2002"));
		ClientePJ meta = new ClientePJ("Meta", "+55(19)98934-2318", "Instituto de Computacao",
			"meta@gmail.com", "12.345.678/0001-95", Data.stringToDate("04/02/2004"));
		Condutor anaCondutor = new Condutor(ana);
		Condutor betoCondutor = new Condutor(beto);
		Condutor carlosCondutor = new Condutor("615.255.980-40", "Carlos", "+55(19)94213-7653",
			"Instituto de Economia", "carlos@gmail.com", Data.stringToDate("29/01/2004"));
		ClientePJ google = new ClientePJ("Google", "+55(19)941042-1953", "FEEC",
			"google@gmail.com", "02.649.275/0001-86", Data.stringToDate("04/09/1998"));
		Frota frotaMeta1 = new Frota();
		Frota frotaMeta2 = new Frota();
		Frota frotaGoogle = new Frota();
		// adicionando às listas
		listaSeguradoras.add(unicampSeg);
		listaSeguradoras.add(uspSeg);
		listaVeiculosCadastrados.add(v1);
		listaVeiculosCadastrados.add(v2);
		listaVeiculosCadastrados.add(v3);
		listaVeiculosCadastrados.add(v4);
		listaVeiculosCadastrados.add(v5);
		listaVeiculosCadastrados.add(v6);
		listaClientes.add(ana);
		listaClientes.add(beto);
		listaClientes.add(meta);
		listaClientes.add(google);
		listaCondutores.add(anaCondutor);
		listaCondutores.add(betoCondutor);
		listaCondutores.add(carlosCondutor);
		listaFrotasCadastradas.add(frotaMeta1);
		listaFrotasCadastradas.add(frotaMeta2);
		listaFrotasCadastradas.add(frotaGoogle);
		// cadastrando frotas e veiculos as para as frotas
		meta.cadastrarFrota(frotaMeta1); 	 
		meta.atualizarFrota(frotaMeta1, v1); 
		meta.atualizarFrota(frotaMeta1, v2); 
		meta.cadastrarFrota(frotaMeta2); 	 
		meta.atualizarFrota(frotaMeta2, v3); 
		google.cadastrarFrota(frotaGoogle);  
		google.atualizarFrota(frotaGoogle, v4);
		// cadastrando veiculos para cada clientePF
		ana.cadastrarVeiculo(v5);
		beto.cadastrarVeiculo(v6);
		// gerando seguros
		unicampSeg.gerarSeguro(Data.stringToDate("12/03/2021"), Data.stringToDate("12/03/2023"), v5, ana);
		Seguro seguroAna = unicampSeg.getListaSeguros().get(0);
		unicampSeg.gerarSeguro(Data.stringToDate("10/09/2021"), Data.stringToDate("10/09/2023"), frotaMeta1, meta);
		Seguro seguroMeta1 = unicampSeg.getListaSeguros().get(1);
		uspSeg.gerarSeguro(Data.stringToDate("10/09/2020"), Data.stringToDate("10/09/2024"), frotaGoogle, google);
		Seguro seguroGoogle = uspSeg.getListaSeguros().get(0);
		uspSeg.gerarSeguro(Data.stringToDate("31/05/2022"), Data.stringToDate("31/05/2023"), v6, beto);
		Seguro seguroBeto = uspSeg.getListaSeguros().get(1);
		uspSeg.gerarSeguro(Data.stringToDate("14/12/2022"), Data.stringToDate("14/12/2024"), frotaMeta2, meta);
		Seguro seguroMeta2 = uspSeg.getListaSeguros().get(2);
		// autorizando condutores
		seguroAna.autorizarCondutor(anaCondutor);
		seguroAna.autorizarCondutor(carlosCondutor);
		seguroMeta1.autorizarCondutor(anaCondutor);
		seguroGoogle.autorizarCondutor(betoCondutor);
		seguroBeto.autorizarCondutor(betoCondutor);
		seguroMeta2.autorizarCondutor(anaCondutor);
		// gerando sinistros
		seguroAna.gerarSinistro(Data.stringToDate("14/02/2022"), carlosCondutor, "IFGW");
		Sinistro sinistroAna = seguroAna.getListaSinistros().get(0);
		seguroAna.gerarSinistro(Data.stringToDate("24/09/2022"), anaCondutor, "Ciclo Basico");
		seguroMeta1.gerarSinistro(Data.stringToDate("12/12/2021"), anaCondutor, "IMECC");
		seguroBeto.gerarSinistro(Data.stringToDate("23/02/2023"), betoCondutor, "Instituto de Biologia");
		// imprimindo
		System.out.println(unicampSeg + "\n\n" + v1 + "\n\n" + beto + "\n\n" + meta + "\n\n" + frotaMeta1 +
			"\n\n" + anaCondutor + "\n\n" + seguroBeto + "\n\n" + seguroMeta1 + "\n\n" + sinistroAna);
	}
	
	public static void main(String[] args) {
		MenuOperacoes op;
		inicializacao(); // Inicializa objetos
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
	/* Imprime "Operacao realizada com sucesso" se operacaoRealizada for true.
	 * Imprime "Ocorreu um erro. Tente novamente." se operacaoRealizada for false. */
	private static void mensagemOperacaoRealizada(boolean operacaoRealizada) {
		if (operacaoRealizada)
			System.out.println("Operacao realizada com sucesso.");
		else
			System.out.println("Ocorreu um erro. Tente novamente.");
	}
	/* Requisita a escolha de uma das seguradoras registradas e a retorna.
	 * Retorna null e imprime mensagem de retorno se nao há seguradoras cadastradas. */
	private static Seguradora requisitarSeguradora() {
		int pos;
		System.out.println("Digite o numero referente a seguradora:");
		if (listarSeguradoras()) {
			pos = Leitura.lerIndice(listaSeguradoras.size()); // leitura da escolha
			return listaSeguradoras.get(pos); // retorna a seguradora
		}
		System.out.println("Nao ha seguradoras cadastradas. Operacao abortada.");
		return null;
	}
	/* Requisita a escolha de um dos clientes registrados em seg e o retorna.
	 * Retorna null e imprime mensagem de retorno se nao há clientes cadastrados. */
	private static Cliente requisitarClienteSeguradora(Seguradora seg, String tipoCliente) {
		int pos;
		System.out.println("Digite o numero referente ao cliente:");
		if (seg.listarClientes(tipoCliente)) {
			pos = Leitura.lerIndice(seg.getListaClientes().size()); // leitura da escolha
			return seg.getListaClientes().get(pos); // retorna o cliente
		}
		System.out.println("Nao ha clientes cadastrados. Operacao abortada.");
		return null;
	}
	private static Cliente requisitarCliente(String tipoCliente) {
		// seguradora temporária para usar o método listarClientes em listaClientes de AppMain
		Seguradora segTemp = new Seguradora(null, null, null, null, null);
		segTemp.setListaClientes(listaClientes);
		return requisitarClienteSeguradora(segTemp, tipoCliente);
	}
	private static Frota requisitarFrotaCliente(ClientePJ cliente) {
		int pos;
		if (cliente.listarFrotas()) {
			pos = Leitura.lerIndice(cliente.getListaFrota().size());
			return cliente.getListaFrota().get(pos);
		}
		System.out.println("Nao ha frotas cadastradas. Operacao abortada.");
		return null;
	}
	private static Frota requisitarFrota() {
		ClientePJ clienteTemp = new ClientePJ(null, null, null, null, null, null);
		clienteTemp.setListaFrota(listaFrotasDisponiveis);
		return requisitarFrotaCliente(clienteTemp);
	}
	private static Veiculo requisitarVeiculoFrota(Frota frota) {
		int pos;
		System.out.println("Digite o numero referente ao veiculo:");
		if (frota.listarVeiculos()) {
			pos = Leitura.lerIndice(frota.getListaVeiculos().size());
			return frota.getListaVeiculos().get(pos);
		}
		System.out.println("Nao ha veiculos cadastrados. Operacao abortada.");
		return null;
	}
	private static Veiculo requisitarVeiculoClientePF(ClientePF cliente) {
		int pos;
		System.out.println("Digite o numero referente ao veiculo:");
		if (cliente.listarVeiculos()) {
			pos = Leitura.lerIndice(cliente.getListaVeiculos().size());
			return cliente.getListaVeiculos().get(pos);
		}
		System.out.println("Nao ha veiculos cadastrados. Operacao abortada.");
		return null;
	}
	private static Veiculo requisitarVeiculoDisponivel() {
		Frota frotaTemp = new Frota();
		frotaTemp.setListaVeiculos(listaVeiculosDisponiveis);
		return requisitarVeiculoFrota(frotaTemp);
	}
	private static Veiculo requisitarVeiculoCadastrado() {
		Frota frotaTemp = new Frota();
		frotaTemp.setListaVeiculos(listaVeiculosCadastrados);
		return requisitarVeiculoFrota(frotaTemp);
	}
	private static Condutor requisitarCondutorSeguro(Seguro seguro) {
		int pos;
		System.out.println("Digite o numero referente ao condutor:");
		if (seguro.listarCondutores()) {
			pos = Leitura.lerIndice(seguro.getListaCondutores().size());
			return seguro.getListaCondutores().get(pos);
		}
		System.out.println("Nao ha condutores cadastrados. Operacao abortada.");
		return null;
	}
	private static Condutor requisitarCondutor() {
		Seguro seguroTemp = new SeguroPF(null, null, null, null, null);
		seguroTemp.setListaCondutores(listaCondutores);
		return requisitarCondutorSeguro(seguroTemp);
	}
	private static Seguro requisitarSeguro() {
		int pos;
		Seguradora seguradora = requisitarSeguradora();
		if (seguradora == null) return null;
		System.out.println("Digite o numero referente ao seguro:");
		if (seguradora.listarSeguros()) {
			pos = Leitura.lerIndice(seguradora.getListaSeguros().size());
			return seguradora.getListaSeguros().get(pos);
		}
		System.out.println("Nao ha seguros cadastrados. Operacao abortada.");
		return null;
	}
	private static Sinistro requisitarSinistro() {
		int pos;
		Seguro seguro = requisitarSeguro();
		if (seguro == null) return null;
		System.out.println("Digite o numero referente ao sinistro:");
		if (seguro.listarSinistros()) {
			pos = Leitura.lerIndice(seguro.getListaSinistros().size());
			return seguro.getListaSinistros().get(pos);
		}
		System.out.println("Nao ha sinistros cadastrados. Operacao abortada.");
		return null;
	}
	/* Lista as seguradoras em listaSeguradoras no formato "i - Seguradora.nome",
	 * onde i é o índice da seguradora na lista. Retorna boolean indicando se imprimiu. */
	private static boolean listarSeguradoras() {
		if (! listaSeguradoras.isEmpty()) {
			for (int i = 0; i < listaSeguradoras.size(); i++)
				System.out.println(String.valueOf(i) + ". " +
						listaSeguradoras.get(i).getNome());
			return true;
		}
		return false;
	}
	

	/* Le nome, telefone, endereco e e-mail */
	private static String[] lerCliente() {
		String atributos[] = {"nome", "telefone", "endereco", "e-mail"};
		String leitura[] = new String[4];
		for (int i = 0; i < atributos.length; i++) {
			System.out.print("Digite o " + atributos[i] + ": ");
			leitura[i] = Leitura.lerString();
		}
		return leitura;
	}
	/* Le atributos especificos de ClientePF e retorna um novo cliente com os atributos */
	private static ClientePF lerClientePF(String nome, String telefone, String endereco, String email) {
		String atributosPF[] = {"o cpf", "o genero", "o nivel de educacao", "a data de nascimento"};
		String leitura[] = new String[4];
		for (int i = 0; i < atributosPF.length; i++) {
			System.out.print("Digite " + atributosPF[i] + ": ");
			if (i == 0)
				leitura[i] = Leitura.lerCpf();
			else if (i == 3)
				leitura[i] = Leitura.lerData();
			else
				leitura[i] = Leitura.lerString();
		}
		return new ClientePF(nome, telefone, endereco, email, leitura[0], leitura[1], leitura[2],
				Data.stringToDate(leitura[3]));	
	}
	/* Le atributos especificos de ClientePJ e retorna um novo cliente com os atributos */
	private static ClientePJ lerClientePJ(String nome, String telefone, String endereco, String email) {
		String cnpj;
		String dataFundacao;
		System.out.print("Digite o CNPJ: ");
		cnpj = Leitura.lerCnpj();
		System.out.print("Digite a data de fundacao: ");
		dataFundacao = Leitura.lerString();
		return new ClientePJ(nome, telefone, endereco, email, cnpj, Data.stringToDate(dataFundacao));	
	}
	/* Le atributos e retorna uma nova seguradora com os atributos */
	private static Seguradora lerSeguradora() {
		String atributos[], cnpj;
		atributos = lerCliente(); // nome, telefone, endereco, email
		cnpj = Leitura.lerCnpj();
		return new Seguradora(cnpj, atributos[0], atributos[1], atributos[2], atributos[3]);
	}
	/* Le atributos e cadastra uma seguradora */
	private static void operacaoCadastrarSeguradora() {
		Seguradora seguradora = lerSeguradora();
		if (! listaSeguradoras.contains(seguradora))
			mensagemOperacaoRealizada(listaSeguradoras.add(seguradora));
		else
			System.out.println("A seguradora nao foi cadastrada. Motivo: ja estava cadastrada");
	}
	/* Le atributos e cadastra um cliente */
	private static void operacaoCadastrarCliente() {
		String atributos[];
		int opcao;
		Cliente cliente;
		Condutor condutor;
		System.out.println("0 - Cadastrar pessoa fisica\n1 - Cadastrar pessoa juridica");
		opcao = Leitura.lerIndice(2);
		atributos = lerCliente(); // nome, telefone, endereco, email
		if (opcao == 1) {
			cliente = lerClientePF(atributos[0], atributos[1], atributos[2], atributos[3]);
			// cadastra automaticamente o cliente como um condutor tambem
			condutor = new Condutor((ClientePF) cliente);
			if (! listaCondutores.contains(condutor))
				listaCondutores.add(condutor);
		} else {
			cliente = lerClientePJ(atributos[0], atributos[1], atributos[2], atributos[3]);
		}
		if (! listaClientes.contains(cliente))
			mensagemOperacaoRealizada(listaClientes.add(cliente));
		else
			System.out.println("O cliente nao foi cadastrado. Motivo: ja estava cadastrado.");
	}
	/* Le atributos e retorna um novo veiculo com os atributos */
	private static Veiculo lerVeiculo() {
		String atributos[] = {"a placa", "a marca", "o modelo", "o ano de fabricacao"};
		String leitura[] = new String[3];
		int anoFabricacao = 0;
		for (int i = 0; i < atributos.length; i++) {
			System.out.print("Digite " + atributos[i] + ": ");
			if (i != 3)
				leitura[i] = Leitura.lerString();
			else
				anoFabricacao = Leitura.lerInteiro();
		}
		return new Veiculo(leitura[0], leitura[1], leitura[2], anoFabricacao);
	}
	/* Le atributos e cadastra um veiculo */
	private static void operacaoCadastrarVeiculo() {
		Veiculo veiculo = lerVeiculo();
		if (! listaVeiculosCadastrados.contains(veiculo))
			mensagemOperacaoRealizada(listaVeiculosCadastrados.add(veiculo));
		else
			System.out.println("O veiculo nao foi cadastrado. Motivo: ja estava cadastrado.");
	}
	/* Cadastra uma frota TODO - eh assim mesmo? */
	private static void operacaoCadastrarFrota() {
		Frota frota = new Frota();
		mensagemOperacaoRealizada(listaFrotasCadastradas.add(frota));
		System.out.println("Code da frota: " + frota.getCode());
	}
	/* Le atributos e retorna um novo condutor com os atributos */
	private static Condutor lerCondutor() {
		String cpf, dataNasc, atributosPF[];
		System.out.print("Digite o cpf: ");
		cpf = Leitura.lerCpf();
		atributosPF = lerCliente(); // nome, telefone, endereco, email
		System.out.print("Digite a data de nascimento: ");
		dataNasc = Leitura.lerData();
		return new Condutor(cpf, atributosPF[0], atributosPF[1], atributosPF[2],
				atributosPF[2], Data.stringToDate(dataNasc));
	}
	/* Le atributos e cadastra um condutor */
	private static void operacaoCadastrarCondutor() {
		Condutor condutor = lerCondutor();
		if (! listaCondutores.contains(condutor))
			mensagemOperacaoRealizada(listaCondutores.add(condutor));
		else
			System.out.println("O condutor nao foi cadastrado. Motivo: ja estava cadastrado.");
	}

	/* TODO - ADICIONAR */
	/* Le opcoes e adiciona um dos veiculo disponiveis a um clientePF */
	private static void operacaoAddVeiculoCliente() {
		Cliente cliente = requisitarCliente("PF");
		if (cliente == null) return;
		Veiculo veiculo = requisitarVeiculoDisponivel();
		if (veiculo == null) return;
		mensagemOperacaoRealizada(((ClientePF) cliente).cadastrarVeiculo(veiculo));
	}
	/* Le opcoes e adiciona uma das frotas disponiveis a um clientePJ */
	private static void operacaoAddFrotaCliente() {
		Cliente cliente = requisitarCliente("PJ");
		if (cliente == null) return;
		Frota frota = requisitarFrota();
		if (frota == null) return;
		mensagemOperacaoRealizada(((ClientePJ) cliente).cadastrarFrota(frota));
	}
	/* Le opcoes e adiciona um dos veiculos disponiveis a uma frota 
	 * TODO - atualizar precos */
	private static void operacaoAddVeiculoFrota() {
		Veiculo veiculo = requisitarVeiculoDisponivel();
		if (veiculo == null) return;
		Frota frota = requisitarFrota();
		if (frota == null) return;
		mensagemOperacaoRealizada(frota.addVeiculo(veiculo));
	}
	/* Le opcoes e adiciona um condutor a um seguro */
	private static void operacaoAddCondutorSeguro() {
		Seguro seguro = requisitarSeguro();
		if (seguro == null) return;
		Condutor condutor = requisitarCondutor();
		if (condutor == null) return;
		seguro.autorizarCondutor(condutor);
	}

	/* TODO - GERAR */
	/* Le e retorna vetor de String com posicoes 0 - dataInicio, 1 - dataFim do seguro */
	private static String[] lerDatasSeguro() {
		String[] datas = new String[2];
		System.out.print("Digite a data de inicio do seguro: ");
		datas[0] = Leitura.lerData();
		System.out.print("Digite a data de fim do seguro: ");
		datas[1] = Leitura.lerData();
		return datas;
	}
	/* Le opcoes e atributos e gera um seguro */
	private static void operacaoGerarSeguro() {
		Veiculo veiculo;
		Frota frota;
		Seguradora seguradora = requisitarSeguradora();
		String[] datas = lerDatasSeguro();
		Cliente cliente = requisitarCliente("");
		if (cliente == null) return;
		if (cliente instanceof ClientePF) { // SeguroPF
			veiculo = requisitarVeiculoClientePF((ClientePF) cliente);
			if (veiculo == null) return;
			mensagemOperacaoRealizada(seguradora.gerarSeguro(Data.stringToDate(datas[0]),
					Data.stringToDate(datas[1]), veiculo, (ClientePF) cliente));
		} else { // SeguroPJ
			frota = requisitarFrotaCliente((ClientePJ) cliente);
			if (frota == null) return;
			mensagemOperacaoRealizada(seguradora.gerarSeguro(Data.stringToDate(datas[0]),
					Data.stringToDate(datas[1]), frota, (ClientePJ) cliente));
		}
	}
	/* Le e retorna vetor de String com posicoes 0 - data, 1 - endereco do sinistro */
	private static String[] lerAtributosSinistro() {
		String[] atributos = new String[2];
		System.out.print("Digite a data do sinistro: ");
		atributos[0] = Leitura.lerData();
		System.out.print("Digite o endereco do sinistro: ");
		atributos[0] = Leitura.lerString();
		return atributos;
	}
	/* Le opcoes e atributos e gera um sinistro */
	private static void operacaoGerarSinistro() {
		Seguro seguro = requisitarSeguro();
		if (seguro == null) return;
		Condutor condutor = requisitarCondutorSeguro(seguro);
		if (condutor == null) return;
		String[] atributos = lerAtributosSinistro(); // data, endereco
		seguro.gerarSinistro(Data.stringToDate(atributos[0]), condutor, atributos[1]);
	}

	/* TODO - VISUALIZAR */
	private static void operacaoImprimirSeguradora() {
		Seguradora seg = requisitarSeguradora();
		if (seg == null) return;
		System.out.println(seg);
	}
	private static void operacaoImprimirCliente() {
		Cliente cliente = requisitarCliente("");
		if (cliente == null) return;
		System.out.println(cliente);
	}
	private static void operacaoImprimirVeiculo() {
		Veiculo veiculo = requisitarVeiculoDisponivel();
		if (veiculo == null) return;
		System.out.println(veiculo);
	}
	private static void operacaoImprimirFrota() {
		Frota frota = requisitarFrota();
		if (frota == null) return;
		System.out.println(frota);
	}
	private static void operacaoImprimirCondutor() {
		Condutor condutor = requisitarCondutor();
		if (condutor == null) return;
		System.out.println(condutor);
	}
	private static void operacaoImprimirSeguro() {
		Seguro seguro = requisitarSeguro();
		if (seguro == null) return;
		System.out.println(seguro);
	}
	private static void operacaoImprimirSinistro() {
		Sinistro sinistro = requisitarSinistro();
		if (sinistro == null) return;
		System.out.println(sinistro);
	}

	/* TODO - CALCULAR RECEITA */
	private static void operacaoCalcularReceita() {
		Seguradora seguradora = requisitarSeguradora();
		if (seguradora == null) return;
		System.out.println("Receita de " + seguradora.getNome() + ": " + seguradora.calcularReceita());
	}
}