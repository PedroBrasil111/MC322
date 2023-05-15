package lab04;

/* Adaptado de https://github.com/rebecapadovani/ExemploEnumMenu
 * enum para menu externo.
 * Cada constante é vista como uma descrição e uma lista de outras constantes (que são as constantes do submenu)
 * new SubmenuOperacoes[]{} cria uma lista de constantes do submenu. */

public enum MenuOperacoes {
	CADASTROS("Cadastros", new SubmenuOperacoes[] {
			SubmenuOperacoes.CADASTRAR_CLIENTE,
			SubmenuOperacoes.CADASTRAR_VEICULO,
			SubmenuOperacoes.CADASTRAR_SEGURADORA,
			SubmenuOperacoes.VOLTAR
	}),
	LISTAR("Listar", new SubmenuOperacoes[] {
			SubmenuOperacoes.LISTAR_CLIENTES,
			SubmenuOperacoes.LISTAR_SINISTROS_SEGURADORA,
			SubmenuOperacoes.LISTAR_SINISTROS_CLIENTE,
			SubmenuOperacoes.LISTAR_VEICULOS_SEGURADORA,
			SubmenuOperacoes.LISTAR_VEICULOS_CLIENTE,
			SubmenuOperacoes.VOLTAR
	}),
	EXCLUIR("Excluir", new SubmenuOperacoes[] {
			SubmenuOperacoes.EXCLUIR_CLIENTE,
			SubmenuOperacoes.EXCLUIR_VEICULO,
			SubmenuOperacoes.EXCLUIR_SINISTRO,
			SubmenuOperacoes.VOLTAR}),
	GERAR_SINISTRO("Gerar Sinistro", new SubmenuOperacoes[] {SubmenuOperacoes.VOLTAR}),
	TRANSFERIR_SEGURO("Transferir Seguro", new SubmenuOperacoes[] {SubmenuOperacoes.VOLTAR}),
	CALCULAR_RECEITA("Calcular Receita", new SubmenuOperacoes[] {SubmenuOperacoes.VOLTAR}),
	SAIR("Sair", new SubmenuOperacoes[] {});
	
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