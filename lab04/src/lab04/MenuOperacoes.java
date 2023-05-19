package lab04;

/* Adaptado de https://github.com/rebecapadovani/ExemploEnumMenu
 * enum para menu externo.
 * Cada constante é vista como uma descrição e uma lista de outras constantes (que são as constantes do submenu)
 * new SubmenuOperacoes[]{} cria uma lista de constantes do submenu. */

public enum MenuOperacoes {
	SAIR("Sair", new SubmenuOperacoes[] {}),
	CADASTROS("Cadastros", new SubmenuOperacoes[] {
			SubmenuOperacoes.VOLTAR,
			SubmenuOperacoes.CADASTRAR_CLIENTE,
			SubmenuOperacoes.CADASTRAR_VEICULO,
			SubmenuOperacoes.CADASTRAR_SEGURADORA
	}),
	LISTAR("Listar", new SubmenuOperacoes[] {
			SubmenuOperacoes.VOLTAR,
			SubmenuOperacoes.LISTAR_CLIENTES,
			SubmenuOperacoes.LISTAR_SINISTROS_SEGURADORA,
			SubmenuOperacoes.LISTAR_SINISTROS_CLIENTE,
			SubmenuOperacoes.LISTAR_VEICULOS_SEGURADORA,
			SubmenuOperacoes.LISTAR_VEICULOS_CLIENTE
	}),
	EXCLUIR("Excluir", new SubmenuOperacoes[] {
			SubmenuOperacoes.VOLTAR,
			SubmenuOperacoes.EXCLUIR_SEGURADORA,
			SubmenuOperacoes.EXCLUIR_CLIENTE,
			SubmenuOperacoes.EXCLUIR_VEICULO,
			SubmenuOperacoes.EXCLUIR_SINISTRO
	}),
	VISUALIZAR("Visualizar", new SubmenuOperacoes[] {
			SubmenuOperacoes.VOLTAR,
			SubmenuOperacoes.IMPRIMIR_SEGURADORA,
			SubmenuOperacoes.IMPRIMIR_CLIENTE,
			SubmenuOperacoes.IMPRIMIR_VEICULO,
			SubmenuOperacoes.IMPRIMIR_SINISTRO
	}),
	GERAR_SINISTRO("Gerar Sinistro", new SubmenuOperacoes[] {SubmenuOperacoes.VOLTAR}),
	TRANSFERIR_SEGURO("Transferir Seguro", new SubmenuOperacoes[] {SubmenuOperacoes.VOLTAR}),
	CALCULAR_RECEITA("Calcular Receita", new SubmenuOperacoes[] {SubmenuOperacoes.VOLTAR});
	
	//atributos
	private final String descricao;
	private final SubmenuOperacoes[] submenu;
	
	//Construtor
	MenuOperacoes(String descricao, SubmenuOperacoes[] submenu){
		this.descricao = descricao;
		this.submenu = submenu;
	}
	
	//getters
	public String getDescricao() {
		return descricao;
	}
	
	public SubmenuOperacoes[] getSubmenu() {
		return submenu;
	}

}