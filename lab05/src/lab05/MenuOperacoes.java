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
	LISTAR("Listar", new SubmenuOperacoes[] {
			SubmenuOperacoes.VOLTAR,
			SubmenuOperacoes.LISTAR_CLIENTES_SEGURADORA,
			SubmenuOperacoes.LISTAR_SEGUROS_SEGURADORA,
			SubmenuOperacoes.LISTAR_VEICULOS_CLIENTE_PF,
			SubmenuOperacoes.LISTAR_FROTAS_CLIENTE_PJ,
			SubmenuOperacoes.LISTAR_VEICULOS_FROTA,
			SubmenuOperacoes.LISTAR_SINISTROS_SEGURO,
			SubmenuOperacoes.LISTAR_CONDUTORES_SEGURO,
			SubmenuOperacoes.LISTAR_SINISTROS_CONDUTOR,
	}),
	EXCLUIR("Excluir", new SubmenuOperacoes[] {
		SubmenuOperacoes.VOLTAR,
		SubmenuOperacoes.EXCLUIR_SEGURADORA,
		SubmenuOperacoes.EXCLUIR_SEGURO,
		SubmenuOperacoes.EXCLUIR_CLIENTE,
		SubmenuOperacoes.EXCLUIR_FROTA,
		SubmenuOperacoes.EXCLUIR_VEICULO,
		SubmenuOperacoes.EXCLUIR_SINISTRO,
		SubmenuOperacoes.EXCLUIR_CONDUTOR,
	}),
	IMPRIMIR("Visualizar", new SubmenuOperacoes[] {
		SubmenuOperacoes.VOLTAR,
		SubmenuOperacoes.IMPRIMIR_SEGURADORA,
		SubmenuOperacoes.IMPRIMIR_CLIENTE,
		SubmenuOperacoes.IMPRIMIR_CONDUTOR,
		SubmenuOperacoes.IMPRIMIR_SINISTRO,
		SubmenuOperacoes.IMPRIMIR_VEICULO,
		SubmenuOperacoes.IMPRIMIR_SEGURO,
		SubmenuOperacoes.IMPRIMIR_FROTA,
	}),
	GERAR("Gerar", new SubmenuOperacoes[] {
		SubmenuOperacoes.VOLTAR,
		SubmenuOperacoes.GERAR_SINISTRO,
		SubmenuOperacoes.GERAR_SEGURO,
	}),
	CALCULAR_RECEITA("Calcular Receita", new SubmenuOperacoes[] {SubmenuOperacoes.VOLTAR});

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