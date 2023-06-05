package lab05;

import java.util.ArrayList;
import java.util.List;

/* Classe contendo o método main. Implementa o menu e os métodos necessários
 * para ler instrucões do usuário e realizar as operacões desejadas. */

public class AppMain {
	private static List<Seguradora> listaSeguradoras = new ArrayList<Seguradora>();
	private static List<Cliente> listaClientes = new ArrayList<Cliente>();
	private static List<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
	private static List<Frota> listaFrotas = new ArrayList<Frota>();
	private static List<Condutor> listaCondutores = new ArrayList<Condutor>();

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
			case IMPRIMIR:
			case GERAR:
				executarSubmenu(op);
				break;
			case CALCULAR_RECEITA:
				// operacaoCalcularReceita();
				break;
			case SAIR:
				break;
		}
	}
	//executar opcões dos submenus
	private static void executarOpcaoSubMenu(SubmenuOperacoes opSubmenu) {
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
			case IMPRIMIR_SEGURADORA:
				operacaoImprimirSeguradora();
				break;
			case IMPRIMIR_CLIENTE:
				operacaoImprimirCliente();
				break;
			case IMPRIMIR_CONDUTOR:
				operacaoImprimirCondutor();
				break;
			case IMPRIMIR_SINISTRO:
				operacaoImprimirSinistro();
				break;
			case IMPRIMIR_VEICULO:
				operacaoImprimirVeiculo();
				break;
			case IMPRIMIR_SEGURO:
				operacaoImprimirSeguro();
				break;
			case IMPRIMIR_FROTA:
				operacaoImprimirFrota();
				break;
			case GERAR_SINISTRO:
				operacaoGerarSinistro();
				break; 
			case GERAR_SEGURO:
				operacaoGerarSeguro();
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

	public static void main(String[] args) {
		MenuOperacoes op;
		// inicializacao(); // Inicializa uma seguradora
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
	private static Seguradora requisitarSeguradora(boolean imprimirMensagem) {
		int pos;
		if (imprimirMensagem)
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
	private static Cliente requisitarClienteSeguradora(Seguradora seg, String tipoCliente,
			boolean imprimirMensagem) {
		int pos;
		if (imprimirMensagem)
			System.out.println("Digite o numero referente ao cliente:");
		if (seg.listarClientes(tipoCliente)) {
			pos = Leitura.lerIndice(seg.getListaClientes().size()); // leitura da escolha
			return seg.getListaClientes().get(pos); // retorna o cliente
		}
		System.out.println("Nao ha clientes cadastrados. Operacao abortada.");
		return null;
	}
	private static Cliente requisitarCliente(String tipoCliente, boolean imprimirMensagem) {
		// seguradora temporária para usar o método listarClientes em listaClientes de AppMain
		Seguradora segTemp = new Seguradora(null, null, null, null, null);
		segTemp.setListaClientes(listaClientes);
		return requisitarClienteSeguradora(segTemp, tipoCliente, true);
	}
	private static Frota requisitarFrotaCliente(ClientePJ cliente, boolean imprimirMensagem) {
		int pos;
		if (imprimirMensagem)
			System.out.println("Digite o numero referente a frota:");
		if (cliente.listarFrotas()) {
			pos = Leitura.lerIndice(cliente.getListaFrota().size());
			return cliente.getListaFrota().get(pos);
		}
		System.out.println("Nao ha frotas cadastradas. Operacao abortada.");
		return null;
	}
	private static Frota requisitarFrota(boolean imprimirMensagem) {
		ClientePJ clienteTemp = new ClientePJ(null, null, null, null, null, null);
		clienteTemp.setListaFrota(listaFrotas);
		return requisitarFrotaCliente(clienteTemp, imprimirMensagem);
	}
	private static Veiculo requisitarVeiculoFrota(Frota frota, boolean imprimirMensagem) {
		int pos;
		if (imprimirMensagem)
			System.out.println("Digite o numero referente ao veiculo:");
		if (frota.listarVeiculos()) {
			pos = Leitura.lerIndice(frota.getListaVeiculos().size());
			return frota.getListaVeiculos().get(pos);
		}
		System.out.println("Nao ha veiculos cadastrados. Operacao abortada.");
		return null;
	}
	private static Veiculo requisitarVeiculo(boolean imprimirMensagem) {
		Frota frotaTemp = new Frota();
		frotaTemp.setListaVeiculos(listaVeiculos);
		return requisitarVeiculoFrota(frotaTemp, imprimirMensagem);
	}
	private static Condutor requisitarCondutorSeguro(Seguro seguro, boolean imprimirMensagem) {
		int pos;
		if (imprimirMensagem)
			System.out.println("Digite o numero referente ao condutor:");
		if (seguro.listarCondutores()) {
			pos = Leitura.lerIndice(seguro.getListaCondutores().size());
			return seguro.getListaCondutores().get(pos);
		}
		System.out.println("Nao ha condutores cadastrados. Operacao abortada.");
		return null;
	}
	private static Condutor requisitarCondutor(boolean imprimirMensagem) {
		Seguro seguroTemp = new SeguroPF(null, null, null, null, null);
		seguroTemp.setListaCondutores(listaCondutores);
		return requisitarCondutorSeguro(seguroTemp, imprimirMensagem);
	}
	private static Seguro requisitarSeguroSeguradora(Seguradora seguradora, boolean imprimirMensagem) {
		int pos;
		if (imprimirMensagem)
			System.out.println("Digite o numero referente ao seguro:");
		if (seguradora.listarSeguros()) {
			pos = Leitura.lerIndice(seguradora.getListaSeguros().size());
			return seguradora.getListaSeguros().get(pos);
		}
		System.out.println("Nao ha seguros cadastrados. Operacao abortada.");
		return null;
	}
	private static Sinistro requisitarSinistroSeguro(Seguro seguro, boolean imprimirMensagem) {
		int pos;
		if (imprimirMensagem)
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

	/* CADASTRO */
	//TODO - comentar
	private static void operacaoCadastrarSeguradora() {
		Seguradora seg;
		String atributos[] = {"o CNPJ", "o nome", "o telefone", "o endereco", "o e-mail"};
		String leitura[] = new String[5];
		System.out.println("** Cadastro de seguradora **");
		for (int i = 0; i < atributos.length; i++) {
			System.out.print("Digite " + atributos[i] + " da seguradora: ");
			if (i == 0)
				leitura[i] = Leitura.lerCnpj();
			else if (i == 1)
				leitura[i] = Leitura.lerNome();
			else
				leitura[i] = Leitura.lerString();
		}
		seg = new Seguradora(leitura[0], leitura[1], leitura[2], leitura[3], leitura[4]);
		// checa se já existe seguradora com o mesmo cpf
		if (! listaSeguradoras.contains(seg))
			listaSeguradoras.add(seg);
	}
	//TODO - comentar
	private static void operacaoCadastrarCliente() {
		int opcao;
		Cliente cliente;
		Seguradora seg;
		String[] atributos = {"o nome", "o telefone", "o endereco", "o email"};
		String[] leitura = new String[4];
		System.out.println("** Cadastro de cliente **");
		System.out.println("Qual tipo de cliente deseja cadastrar?\n" + 
				"0 - Pessoa Fisica\n1 - Pessoa Juridica");
		opcao = Leitura.lerIndice(2);
		for (int i = 0; i < atributos.length; i++) {
			System.out.print("Digite " + atributos[i] + " do cliente: ");
			if (i == 0)
				leitura[i] = Leitura.lerNome();
			else
				leitura[i] = Leitura.lerString();
		}
		if (opcao == 0)
			cliente = cadastroClientePF(leitura[0], leitura[1], leitura[2], leitura[3]);
		else
			cliente = cadastroClientePJ(leitura[0], leitura[1], leitura[2], leitura[3]);
		if (listaClientes.contains(cliente)) {
			System.out.println("Erro: o cliente ja havia sido cadastrado.");
			return;
		}
		mensagemOperacaoRealizada(listaClientes.add(cliente));
	}
	private static ClientePF cadastroClientePF(String nome, String telefone, 
			String endereco, String email) {
		String[] atributos = {"o CPF", "o genero", "o nivel de educacao",
				"a data de nascimento (dd/MM/aaaa)"};
		String leitura[] = new String[4];
		for (int i = 0; i < atributos.length; i++) {
			System.out.print("Digite " + atributos[i] + " do cliente: ");
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
	private static ClientePJ cadastroClientePJ(String nome, String telefone, 
			String endereco, String email) {
		String[] atributos = {"o CNPJ", "a data de fundacao"};
		String leitura[] = new String[2];
		for (int i = 0; i < atributos.length; i++) {
			System.out.print("Digite o " + atributos[i] + " do cliente: ");
			if (i == 0)
				leitura[i] = Leitura.lerCnpj();
			else
				leitura[i] = Leitura.lerData();
		}
		return new ClientePJ(nome, telefone, endereco, email, leitura[0],
				Data.stringToDate(leitura[1]));
	}
	private static void operacaoCadastrarVeiculo() {
		int opcao;
		Cliente cliente;
		Frota frota;
		Veiculo veiculo;
		System.out.println("** Cadastro de veiculo **");
		veiculo = cadastroVeiculoNovo();
		if (veiculo == null) return;
		System.out.println("Deseja cadastrar o veiculo em uma frota ou cliente pessoa fisica?\n" +
				"0 - Frota\n1 - Cliente pessoa fisica");
		opcao = Leitura.lerIndice(2);
		if (opcao == 0) {
			frota = requisitarFrota(true);
			if (frota == null) return;
			mensagemOperacaoRealizada(frota.addVeiculo(veiculo));
		} else {
			cliente = requisitarCliente("PF", true);
			if (cliente == null) return;
			mensagemOperacaoRealizada(cliente.cadastrarVeiculo(veiculo));
		}
	}
	private static void cadastroVeiculoNovo() {
		String[] atributos = {"a placa", "a marca", "o modelo", "o ano de fabricacao"};
		String leitura[] = new String[3];
		int anoFabricacao = 0;
		Veiculo veiculo;
		for (int i = 0; i < atributos.length; i++) {
			System.out.print("Digite " + atributos[i] + " do veiculo: ");
			if (i == 3)
				anoFabricacao = Leitura.lerIndice(2200);
			else
				leitura[i] = Leitura.lerString();
		}
		veiculo = new Veiculo(leitura[0], leitura[1], leitura[2], anoFabricacao)
		if (listaVeiculos.contains(veiculo)) {
			System.out.println("Erro: O veiculo ja havia sido cadastrado.");
			return null;
		}
		mensagemOperacaoRealizada(listaVeiculos.add(new Veiculo(leitura[0], leitura[1],
				leitura[2], anoFabricacao)));
		return veiculo;
	}
	private static void operacaoCadastrarFrota() {
		Cliente cliente;
		Frota frota;
		int opcao;
		System.out.println("** Cadastro de frota **");
		opcao = Leitura.lerIndice(2);
		if (opcao == 0) {
			frota = new Frota();
			mensagemOperacaoRealizada(listaFrotas.add(frota));
			System.out.println("Codigo da frota: " + frota.getCode());
		}
		System.out.println("Para qual cliente deseja cadastrar a frota?");
		cliente = requisitarCliente("PJ", false);
		if (cliente == null) return;
	}
	private static void operacaoCadastrarCondutor() {
		Condutor condutor;
		int opcao;
		Seguro seguro;
		Seguradora seg;
		System.out.println("** Cadastro de condutor **");
		condutor = cadastroCondutorNovo();
		if (listaCondutores.contains(condutor)) {
			System.out.println("O condutor ja esta cadastrado. Operacao abortada.");
			return;
		}
		System.out.println("Digite o numero referente a seguradora que cobre o seguro do condutor:");
		seg = requisitarSeguradora(false);
		if (seg == null) return;
		seguro = requisitarSeguroSeguradora(seg, true);
		if (seguro == null) return;
		mensagemOperacaoRealizada(seguro.autorizarCondutor(condutor));
	}
	private static Condutor cadastroCondutorNovo() {
		String atributos[] = {"o CPF", "o nome", "o telefone", "o endereco", "o email", 
		"a data de nascimento (dd/MM/aaaa)"};
		String leitura[] = new String[6];
		Condutor condutor;
		for (int i = 0; i < atributos.length; i++) {
			System.out.print("Digite " + atributos[i] + " do condutor: ");
			if (i == 0)
				leitura[i] = Leitura.lerCpf();
			else if (i == 1)
				leitura[i] = Leitura.lerNome();
			else if (i == 5)
				leitura[i] = Leitura.lerData();
			else
				leitura[i] = Leitura.lerString();
		}
		condutor = new Condutor(leitura[0], leitura[1], leitura[2], leitura[3], leitura[4],
				Data.stringToDate(leitura[5]));
		if (listaCondutores.contains(condutor)) {
			System.out.println("Erro: O condutor ja havia sido cadastrado.");
			return null;
		}
		mensagemOperacaoRealizada(listaCondutores.add(condutor));
		return condutor;
	}

	/* IMPRIMIR */
	private static void operacaoImprimirSeguradora() {
		Seguradora seg;
		System.out.println("** Visualizar seguradora **");
		seg = requisitarSeguradora(true);
		if (seg == null) return;
		System.out.println(seg);
	}
	private static void operacaoImprimirCliente() {
		Cliente cliente;
		System.out.println("** Visualizar cliente **");
		cliente = requisitarCliente("", true);
		if (cliente == null) return;
		System.out.println(cliente);
	}
	private static void operacaoImprimirCondutor() {
		Condutor condutor;
		System.out.println("** Visualizar condutor **");
		condutor = requisitarCondutor(true);
		if (condutor == null) return;
		System.out.println(condutor);
	}
	private static void operacaoImprimirSinistro() {
		Sinistro sinistro;
		Seguradora seg;
		Seguro seguro;
		System.out.println("** Visualizar sinistro **");
		seg = requisitarSeguradora(true);
		if (seg == null) return;
		seguro = requisitarSeguroSeguradora(seg, true);
		if (seguro == null) return;
		sinistro = requisitarSinistroSeguro(seguro, true);
		if (sinistro == null) return;
		System.out.println(sinistro);
	}
	private static void operacaoImprimirVeiculo() {
		Veiculo veiculo;
		System.out.println("** Visualizar veiculo **");
		veiculo = requisitarVeiculo(true);
		if (veiculo == null) return;
		System.out.println(veiculo);
	}
	private static void operacaoImprimirSeguro() {
		Seguradora seg;
		Seguro seguro;
		System.out.println("** Visualizar seguro **");
		seg = requisitarSeguradora(true);
		if (seg == null) return;
		seguro = requisitarSeguroSeguradora(seg, true);
		if (seguro == null) return;
		System.out.println(seguro);
	}
	private static void operacaoImprimirFrota() {
		Frota frota;
		System.out.println("** Visualizar frota **");
		frota = requisitarFrota(true);
		if (frota == null) return;
		System.out.println(frota);
	}

	/* GERAR */
	private static void operacaoGerarSinistro() {
		
	}
	private static void operacaoGerarSeguro() {
		Seguradora seg;
		System.out.println("** Gerar seguro **");
		
	}
	
}