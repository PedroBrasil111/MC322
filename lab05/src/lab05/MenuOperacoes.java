package lab05;

/* Adaptado de https://github.com/rebecapadovani/ExemploEnumMenu
 * enum para menu externo.
 * Cada constante é vista como uma descrição e uma lista de outras constantes (que são as constantes do submenu)
 * new SubmenuOperacoes[]{} cria uma lista de constantes do submenu. */

public enum MenuOperacoes {
	SAIR("Sair", new SubmenuOperacoes[] {}),
	CADASTROS("Cadastros", new SubmenuOperacoes[] {
		SubmenuOperacoes.VOLTAR,
		SubmenuOperacoes.CADASTRAR_SEGURADORA,
		SubmenuOperacoes.CADASTRAR_CLIENTE,
		SubmenuOperacoes.CADASTRAR_VEICULO,
		SubmenuOperacoes.CADASTRAR_FROTA,
		SubmenuOperacoes.CADASTRAR_CONDUTOR,
	}),
	ADICIONAR("Adicionar", new SubmenuOperacoes[] {
		SubmenuOperacoes.VOLTAR,
		SubmenuOperacoes.ADICIONAR_VEICULO_CLIENTE,
		SubmenuOperacoes.ADICIONAR_FROTA_CLIENTE,
		SubmenuOperacoes.ADICIONAR_VEICULO_FROTA,
		SubmenuOperacoes.ADICIONAR_CONDUTOR_SEGURO
	}),
	GERAR("Gerar", new SubmenuOperacoes[] {
		SubmenuOperacoes.VOLTAR,
		SubmenuOperacoes.GERAR_SEGURO,
		SubmenuOperacoes.GERAR_SINISTRO
	}),
	IMPRIMIR("Visualizar", new SubmenuOperacoes[] {
		SubmenuOperacoes.VOLTAR,
		SubmenuOperacoes.IMPRIMIR_SEGURADORA,
		SubmenuOperacoes.IMPRIMIR_CLIENTE,
		SubmenuOperacoes.IMPRIMIR_VEICULO,
		SubmenuOperacoes.IMPRIMIR_FROTA,
		SubmenuOperacoes.IMPRIMIR_CONDUTOR,
		SubmenuOperacoes.IMPRIMIR_SEGURO,
		SubmenuOperacoes.IMPRIMIR_SINISTRO
	}),
	CALCULAR_RECEITA("Calcular receita", null);

	// Atributos
	private final String descricao;
	private final SubmenuOperacoes[] submenu;

	// Construtor
	MenuOperacoes(String descricao, SubmenuOperacoes[] submenu){
		this.descricao = descricao;
		this.submenu = submenu;
	}
	
	// Getters
	public String getDescricao() {
		return descricao;
	}
	
	public SubmenuOperacoes[] getSubmenu() {
		return submenu;
	}

}