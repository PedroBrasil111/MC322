package lab05;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Seguradora {
	// Atributos
	private final String cnpj;
	private String nome;
	private String telefone;
	private String endereco;
	private String email;
	private List<Cliente> listaClientes; // lista com clientes que contratam a seguradora
	private List<Seguro> listaSeguros; // lista com seguros da seguradora

    // Construtor
	public Seguradora(String cnpj, String nome, String telefone, String endereco, String email) {
		this.cnpj = cnpj;
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
		listaSeguros = new ArrayList<Seguro>();
		listaClientes = new ArrayList<Cliente>();
	}

	// toString()
	/* Seguradora <nome>:
	 * - Telefone: <telefone>
	 * - Endereco: <endereco>
	 * - E-mail: <email>
	 * - Clientes: "Nenhum cliente cadastrado" OU <cliente1.strDocumento()>, <cliente2.strDocumento()>, ...
	 * - Seguros: "Nenhum seguro cadastrado" OU <seguro1.id>, <seguro2.id>, ... */
    public String toString() {
        int i;
        String str = String.format("Seguradora - %s:\n- Telefone: %s\n- Endereco: %s\n- E-mail: %s\n" +
                "- Clientes: ", nome, telefone, endereco, email);
		if (! listaClientes.isEmpty()) {
        	for (i = 0; i < listaClientes.size() - 1; i++)
				str += listaClientes.get(i).strDocumento() + ", ";
			str += listaClientes.get(listaClientes.size() - 1).strDocumento();
		} else
			str += "Nenum cliente cadastrado";
		str += "\n- Seguros: ";
		if (! listaSeguros.isEmpty()) {
			for (i = 0; i < listaSeguros.size() - 1; i++)
				str += listaSeguros.get(i).getId() + ", ";
			str += listaSeguros.get(listaSeguros.size() - 1).getId();
		} else
			str += "Nenhum seguro cadastrado";
		return str;
    }

    /* Retorna boolean indicando se o objeto obj eh igual. Comparacão é feita por CNPJ. */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
        if (obj == null || getClass() != obj.getClass())
			return false;
        return cnpj.equals(((Seguradora) obj).getCnpj());
	}
    /* Lista os clientes que contratam a seguradora no formato "i - <cliente[i].strDocumento()>,"
     * onde i é o índice do cliente na lista. Se "tipoCliente" for:
	 * "PJ": lista somente os clientes PJ
	 * "PF": lista somente os clientes PF
	 * "": lista todos os clientes
	 * Retorna boolean indicando se imprimiu. */
	public boolean listarClientes(String tipoCliente) {
		boolean imprimiu = false;
		for (int i = 0; i < listaClientes.size(); i++) {
			if (tipoCliente == "PF" && listaClientes.get(i).getClass() == ClientePF.class ||
					tipoCliente == "PJ" && listaClientes.get(i).getClass() == ClientePJ.class ||
					tipoCliente == "") {
				System.out.println(i + " - " + listaClientes.get(i).getNome() + " - "
						+ listaClientes.get(i).strDocumento());
				imprimiu = true;
			}
		}
		return imprimiu;
	}
    /* Lista os seguros cadastradas para o cliente no formato "i - <seguro[i].id>,"
     * onde i é o índice do seguro na lista. Retorna boolean indicando se imprimiu. */
	public boolean listarSeguros() {
		if (listaSeguros.isEmpty()) // lista vazia
			return false;
		for (int i = 0; i < listaSeguros.size(); i++)
			System.out.println(i + " - " + listaSeguros.get(i).getId());
		return true;
	}
	/* Adiciona o cliente a listaClientes, retorna boolean indicando se adicionou. */
	public boolean cadastrarCliente(Cliente cliente) {
		// não cadastra se já está na lista
		if (listaClientes.contains(cliente))
			return false;
		return listaClientes.add(cliente); // true se adicionou, senão false
	}
	/* Remove o cliente de listaClientes, retorna boolean indicando se removeu. */
	public boolean removerCliente(Cliente cliente) {
		boolean removeu = listaClientes.remove(cliente); // true se removeu, senão false
		for (Seguro seguro: getSegurosPorCliente(cliente))
			cancelarSeguro(seguro);
		return removeu;
	}
	/* Atualiza o valor de todos os seguros */
	public void atualizarValoresSeguro() {
		for (Seguro seguro: listaSeguros)
			seguro.calcularValor();
	}
	/* Gera um novo seguroPJ a partir dos parâmetros. Retorna boolean indicando se adicionou. */
	public boolean gerarSeguro(Date dataInicio, Date dataFim, Frota frota, ClientePJ cliente) {
		boolean gerou;
		cadastrarCliente(cliente); // cadastra o cliente
		gerou = listaSeguros.add(new SeguroPJ(dataInicio, dataFim, this, frota, cliente));
		// TODO - necessário atualizar todos ? - a favor: pode ser q um condutor esteja em outro seguro
		atualizarValoresSeguro();
		// retorna true se adicionou, senão false
		return gerou;
	}
	/* Gera um novo seguroPF a partir dos parâmetros. Retorna boolean indicando se adicionou. */
	public boolean gerarSeguro(Date dataInicio, Date dataFim, Veiculo veiculo, ClientePF cliente) {
		boolean gerou;
		// tenta adicionar o cliente a listaClientes, retorna false e não gera o seguro
		// se não conseguir
		cadastrarCliente(cliente); // cadastra o cliente
		gerou = listaSeguros.add(new SeguroPF(dataInicio, dataFim, this, veiculo, cliente));
		// TODO - espelhar método acima
		atualizarValoresSeguro();
		// retorna true se adicionou, senão false
		return gerou;
	}
	/* Remove o seguro de listaSeguros, retorna boolean indicando se removeu. */
	public boolean cancelarSeguro(Seguro seguro) {
		boolean cancelou = listaSeguros.remove(seguro); // remove o seguro da lista
		// remove os sinistros cobertos da lista de cada condutor
		for (Sinistro sinistro: seguro.getListaSinistros())
			sinistro.getCondutor().removerSinistro(sinistro);
		// remove o cliente de listaClientes se ele nao possuir seguros
		if (getSegurosPorCliente(seguro.getCliente()).size() == 0)
			listaClientes.remove(seguro.getCliente());
		// TODO - necessário mudar todos?
		atualizarValoresSeguro();
		return cancelou; // true se removeu, senão false
	}
	/* Retorna lista com os seguros contratados pelo cliente na seguradora */
	public List<Seguro> getSegurosPorCliente(Cliente cliente) {
		List<Seguro> listaSeguros = new ArrayList<Seguro>();
		for (Seguro seguro: this.listaSeguros) {
			if (seguro.getClass() == SeguroPF.class && ((SeguroPF) seguro).getCliente().equals(cliente) ||
					seguro.getClass() == SeguroPJ.class && ((SeguroPJ) seguro).getCliente().equals(cliente)) {
				listaSeguros.add(seguro);
			}
		}
		return listaSeguros;
	}
	/* Retorna lista com os seguros nos quais o condutor está cadastrado na seguradora */
	public List<Seguro> getSegurosPorCondutor(Condutor condutor) {
		List<Seguro> listaSeguros = new ArrayList<Seguro>();
		for (Seguro seguro: listaSeguros)
			if (seguro.getListaCondutores().contains(condutor))
				listaSeguros.add(seguro);
		return listaSeguros;
	}
	/* Retorna lista com os veículos cobertos pelos seguros do cliente na seguradora */
	public List<Veiculo> getVeiculosPorCliente(ClientePF cliente) {
		List<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
		for (Seguro s: listaSeguros)
			if (s.getClass() == SeguroPF.class && ((SeguroPF) s).getCliente().equals(cliente))
				listaVeiculos.add(((SeguroPF) s).getVeiculo());
		return listaVeiculos;
	}
	/* Retorna lista com os sinistros do cliente cobertos pela seguradora. */
	public List<Sinistro> getSinistrosPorCliente(Cliente cliente) {
		List<Sinistro> listaSinistros = new ArrayList<Sinistro>();
		for (Seguro seguro: getSegurosPorCliente(cliente))
			listaSinistros.addAll(seguro.getListaSinistros());
		return listaSinistros;
	}
	/* Retorna lista com os segurosPJ que contêm a frota. */
	public List<Seguro> getSegurosPorFrota(Frota frota) {
		List<Seguro> listaSeguros = new ArrayList<Seguro>();
		for (Seguro seguro: listaSeguros)
			if (seguro.getClass() == SeguroPJ.class &&
					((SeguroPJ) seguro).getFrota().equals(frota))
				listaSeguros.add(seguro);
		return listaSeguros;
	}
	/* Retorna lista com os segurosPF que contêm o veículo. */
	public List<Seguro> getSegurosPorVeiculo(Veiculo veiculo) {
		List<Seguro> listaSeguros = new ArrayList<Seguro>();
		for (Seguro seguro: listaSeguros)
			if (seguro.getClass() == SeguroPF.class &&
					((SeguroPF) seguro).getVeiculo().equals(veiculo))
				listaSeguros.add(seguro);
		return listaSeguros;
	}
	/* Retorna a receita total dos seguros */
	public double calcularReceita() {
		double receita = 0.;
		for (Seguro seguro: listaSeguros)
			receita += seguro.getValorMensal();
		return receita;
	}

	// Getters e setters
	public String getCnpj() {
		return cnpj;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Seguro> getListaSeguros() {
		return listaSeguros;
	}
	public void setListaSeguros(List<Seguro> listaSeguros) {
		this.listaSeguros = listaSeguros;
	}
	public List<Cliente> getListaClientes() {
		return listaClientes;
	}
	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

}