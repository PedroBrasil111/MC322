package lab05;

/* Adaptado de https://github.com/rebecapadovani/ExemploEnumMenu
 * Define as constantes dos submenus */

public enum SubmenuOperacoes {
	VOLTAR("Voltar"),
	CADASTRAR_SEGURADORA("Cadastrar seguradora"),
	CADASTRAR_CLIENTE("Cadastrar cliente"),
	CADASTRAR_VEICULO("Cadastrar veiculo"),
	CADASTRAR_FROTA("Cadastrar frota"),
	CADASTRAR_CONDUTOR("Cadastrar condutor"),

	LISTAR_CLIENTES_SEGURADORA("Listar clientes por seguradora"),
	LISTAR_SEGUROS_SEGURADORA("Listar seguros por seguradora"),
	LISTAR_VEICULOS_CLIENTE_PF("Listar veiculos por cliente pessoa fisica"),
	LISTAR_FROTAS_CLIENTE_PJ("Listar frotas por clientes pessoa juridica"),
	LISTAR_VEICULOS_FROTA("Listar veiculos por frota"),
	LISTAR_SINISTROS_SEGURO("Listar sinistros por seguro"),
	LISTAR_CONDUTORES_SEGURO("Listar condutores por seguro"),
	LISTAR_SINISTROS_CONDUTOR("Listar sinistros por condutor"),

	GERAR_SINISTRO("Gerar sinistro"),
	GERAR_SEGURO("Gerar seguro"),

	EXCLUIR_SEGURADORA("Excluir seguradora"),
	EXCLUIR_SEGURO("Excluir seguro"),
	EXCLUIR_CLIENTE("Excluir cliente"),
	EXCLUIR_FROTA("Excluir frota"),
	EXCLUIR_VEICULO("Excluir veiculo"),
	EXCLUIR_SINISTRO("Excluir sininstro"),
	EXCLUIR_CONDUTOR("Excluir condutor"),

	IMPRIMIR_SEGURADORA("Visualizar seguradora"),
	IMPRIMIR_CLIENTE("Visualizar cliente"),
	IMPRIMIR_CONDUTOR("Visualizar condutor"),
	IMPRIMIR_SINISTRO("Visualizar sinistro"),
	IMPRIMIR_VEICULO("Visualizar veiculo"),
	IMPRIMIR_SEGURO("Visualizar seguro"),
	IMPRIMIR_FROTA("Visualizar frota");
	
	// Atributo
	private final String descricao;
	
	//Construtor
	SubmenuOperacoes(String descricao){
		this.descricao = descricao;
	}
	
	// Getter
	public String getDescricao() {
		return descricao;
	}
}