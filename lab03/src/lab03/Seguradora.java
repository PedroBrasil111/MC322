package lab03;

import java.util.ArrayList;
import java.util.List;

public class Seguradora {
	// Propriedades
	private String nome;
	private String telefone;
	private String email;
	private String endereco;
	private List<Sinistro> listaSinistros;
	private List<Cliente> listaClientes;

	// Construtor
	public Seguradora(String nome, String telefone, String email, String endereco) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
		listaSinistros = new ArrayList<Sinistro>();
		listaClientes = new ArrayList<Cliente>();
	}

	// toString
	public String toString() {
		int i;
		String str = String.format("Seguradora - %s:\n- Telefone: %s\n" +
			"- E-mail: %s\n- Endereco: %s", nome, telefone, email, endereco);
		// adicionando os clientes
		str += "\n- Cliente(s):";
		if (listaClientes.isEmpty())
			str += " Nenhum cliente cadastrado";
		else
			for (i = 0; i < listaClientes.size(); i++) {
				str += " " + listaClientes.get(i).getNome();
				if (i != listaClientes.size() - 1)
					str += ",";
			}
		// adicionando os sinistros
		str += "\n- Sinistro(s):";
		if (listaSinistros.isEmpty())
			str += " Nenhum sinistro cadastrado";
		else
			for (i = 0; i < listaSinistros.size(); i++) {
				str += " " + listaSinistros.get(i).getId();
				if (i != listaSinistros.size() - 1)
					str += ",";
			}
		return str;
	}

	// Métodos privados
	/* Retorna true se o documento for igual ao CPF/CNPJ de cliente, false caso contrario */
	private boolean checaDocumento(Cliente cliente, String documento) {
		return cliente instanceof ClientePF && ((ClientePF) cliente).getCpf().equals(documento) ||
			cliente instanceof ClientePJ && ((ClientePJ) cliente).getCnpj().equals(documento);
	}

	// Métodos da classe
	/* Adiciona o cliente a listaClientes. Retorna um boolean indicando se foi adicionado. */
	public boolean cadastrarCliente(Cliente cliente) {
		return listaClientes.add(cliente);
	}
	/* Remove o primeiro cliente de listaClientes com o documento especificado.
	 * Retorna true se removeu algo, false caso contrário. */
	public boolean removerCliente(String documento) {
		Cliente cliente;
		for (int i = 0; i < listaClientes.size(); i++) {
			cliente = listaClientes.get(i);
			if (checaDocumento(cliente, documento)) {
				listaClientes.remove(i);
				return true;
			}
		}
		return false;
	}
	/* Imprime "i. <nome do cliente>\n" dos clientes registrados com tipoCliente especificado.
	 * i vai do intervalo de 1 até o núm. total de clientes de tipoCliente.
	 * tipoCliente == "PF" para listar ClientePF, e tipoCliente == "PJ" para listar ClientePJ. */
	public void listarClientes(String tipoCliente) {
		int i, cont = 0;
		if (tipoCliente.equals("PF")) {
			for (i = 0; i < listaClientes.size(); i++)
				if (listaClientes.get(i) instanceof ClientePF)
					System.out.println(String.valueOf(++cont) + ". " +
						listaClientes.get(i).getNome());
		} else if (tipoCliente.equals("PJ")) {
			for (i = 0; i < listaClientes.size(); i++)
				if (listaClientes.get(i) instanceof ClientePJ)
					System.out.println(String.valueOf(++cont) + ". " + 
						listaClientes.get(i).getNome());
		}
		// Não imprime se tipoCliente != "PF" ou "PJ"
	}
	/* Adiciona o Sinistro s a listaSinistros. Retorna um boolean indicando se adicionou. */
	public boolean gerarSinistro(String data, String endereco, Veiculo veiculo, Cliente cliente) {
		return listaSinistros.add(new Sinistro(data, endereco, this, veiculo, cliente));
	}
	/* Imprime todos os sinistros relacionados ao cliente com o documento passado como argumento
	 * na seguradora, no formato de Sinistro.toString(). Retorna true caso ache algum sinistro,
	 * false caso contrário. */
	public boolean visualizarSinistro(String documento) {
		boolean encontrou = false;
		int numSinistros = 0;
		Cliente cliente;
		for (int i = 0; i < listaSinistros.size(); i++) {
			cliente = listaSinistros.get(i).getCliente();
			if (checaDocumento(cliente, documento)) {
				System.out.println(++numSinistros + ". " + listaSinistros.get(i));
				encontrou = true;
			}
		}
		return encontrou;
	}
	/* Imprime "i. <id do sinistro>\n" dos sinistros na ordem em que foram registrados,
	 * onde i é o índice + 1 do sinistro em listaSinistros. */
	public void listarSinistros() {
		if (listaSinistros.isEmpty()) {
			System.out.println("Nao ha sinistros cadastrados.");
		}
		else {
			for (int i = 0; i < listaSinistros.size(); i++)
				System.out.println(String.valueOf(i + 1) + ". " + listaSinistros.get(i).getId());
		}
	}

	// Getters e setters
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public List<Sinistro> getListaSinistros() {
		return listaSinistros;
	}
	public void setListaSinistros(List<Sinistro> listaSinistros) {
		this.listaSinistros = listaSinistros;
	}
	public List<Cliente> getListaClientes() {
		return listaClientes;
	}
	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

}