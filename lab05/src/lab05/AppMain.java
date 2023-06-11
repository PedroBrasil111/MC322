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
				// operacaoCadastrarSeguradora();
				break;
			case CADASTRAR_CLIENTE:
				// operacaoCadastrarCliente();
				break;
			case CADASTRAR_VEICULO:
				// operacaoCadastrarVeiculo();
				break;
			case CADASTRAR_FROTA:
				// operacaoCadastrarFrota();
				break;
			case CADASTRAR_CONDUTOR:
				// operacaoCadastrarCondutor();
				break;
			case IMPRIMIR_SEGURADORA:
				// operacaoImprimirSeguradora();
				break;
			case IMPRIMIR_CLIENTE:
				// operacaoImprimirCliente();
				break;
			case IMPRIMIR_CONDUTOR:
				// operacaoImprimirCondutor();
				break;
			case IMPRIMIR_SINISTRO:
				// operacaoImprimirSinistro();
				break;
			case IMPRIMIR_VEICULO:
				// operacaoImprimirVeiculo();
				break;
			case IMPRIMIR_SEGURO:
				// operacaoImprimirSeguro();
				break;
			case IMPRIMIR_FROTA:
				// operacaoImprimirFrota();
				break;
			case GERAR_SINISTRO:
				// operacaoGerarSinistro();
				break; 
			case GERAR_SEGURO:
				// operacaoGerarSeguro();
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
		listaVeiculos.add(v1);
		listaVeiculos.add(v2);
		listaVeiculos.add(v3);
		listaVeiculos.add(v4);
		listaVeiculos.add(v5);
		listaVeiculos.add(v6);
		listaClientes.add(ana);
		listaClientes.add(beto);
		listaClientes.add(meta);
		listaClientes.add(google);
		listaCondutores.add(anaCondutor);
		listaCondutores.add(betoCondutor);
		listaCondutores.add(carlosCondutor);
		listaFrotas.add(frotaMeta1);
		listaFrotas.add(frotaMeta2);
		listaFrotas.add(frotaGoogle);
		// cadastrando frotas
		meta.cadastrarFrota(frotaMeta1);
		meta.atualizarFrota("addVeic", frotaMeta1, v1);
		meta.atualizarFrota("addVeic", frotaMeta1, v2);
		meta.cadastrarFrota(frotaMeta2);
		meta.atualizarFrota("addVeic", frotaMeta2, v3);
		google.cadastrarFrota(frotaGoogle);
		google.atualizarFrota("addVeic", frotaGoogle, v4);
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

	
}