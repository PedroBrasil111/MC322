package lab04;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Seguradora {
	// Propriedades
	private String nome;
	private String telefone;
	private String email;
	private String endereco;
	private List<Sinistro> listaSinistros; // lista com sinistros cobertos pela seguradora
	private List<Cliente> listaClientes; // lista com clientes que contratam a seguradora

	// Construtor
	public Seguradora(String nome, String telefone, String email, String endereco) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
		listaSinistros = new ArrayList<Sinistro>();
		listaClientes = new ArrayList<Cliente>();
	}

	// toString()
	/* Seguradora - <nome>:
	 * - Telefone: <telefone>
	 * - E-mail: <email>
	 * - Endereco: <endereco>
	 * - Cliente(s): Nenhum cliente cadastrado OU <cliente1.nome>, <cliente2.nome>, ...
	 * - Sinistro(s): Nenhum sinistro cadastrado OU <sinistro1.id>, <sinistro2.id>, ... */
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
	private boolean comparaDocumento(Cliente cliente, String documento) {
		// checando classe com instanceof para então fazer casting e usar getCpf ou getCnpj
		return cliente instanceof ClientePF && ((ClientePF) cliente).getCpf().equals(documento) ||
		cliente instanceof ClientePJ && ((ClientePJ) cliente).getCnpj().equals(documento);
	}
	/* Retorna uma string no formato "<cliente.nome> - <tipoDocumento>: <cliente.documento>"
	* onde tipoDocumento é CPF ou CNPJ e cliente.documento é seu valor. Se cliente for do
	* tipo Cliente, restorna "<cliente.nome> - Nao possui documento cadastrado" */
	private String strClienteDocumento(Cliente cliente) {
		String str = cliente.getNome();
		if (cliente instanceof ClientePF)
			str += " - CPF: " + ((ClientePF) cliente).getCpf();
		else if (cliente instanceof ClientePJ)
			str += " - CNPJ: " + ((ClientePJ) cliente).getCnpj();
		else // tipo Cliente
			str += " - Nao possui documento cadastrado ";
		return str;
	}

	// Métodos para valor do seguro
	/* Calcula e atribui o valor para c.valorSeguro. Valor é dado por 
	 * c.calculaScore() * (1 + this.listaSinistros.size()). */
	public void calcularPrecoSeguroCliente(Cliente c) {
		c.setValorSeguro(c.calculaScore() * (1 + listaSinistros.size()));
	}
	/* Atualiza o preco do seguro para todos os clientes. Deve ser chamado manualmente se
	 * for removido ou adicionado um veículo a listaClientes de algum cliente ou
	 * alterada a quantidade de funcionarios de algum ClientePF */
	public void atualizarValoresSeguro() {
		for (Cliente c: listaClientes) {
			calcularPrecoSeguroCliente(c);
		}
	}
	/* Retorna a receita total da seguradora. */
	public double calcularReceita() {
		double soma = 0;
		for (Cliente c: listaClientes)
			soma += c.getValorSeguro();
		return soma;
	}

	// Métodos para clientes
	/* Adiciona o cliente a listaClientes. Retorna um boolean indicando se foi adicionado. */
	public boolean cadastrarCliente(Cliente cliente) {
		calcularPrecoSeguroCliente(cliente); // atribui o valor do seguro para o cliente
		return listaClientes.add(cliente); // boolean indicando se adicionou à lista
	}
	/* Remove o primeiro cliente de listaClientes com o documento especificado.
	* Retorna true se removeu algo, false caso contrário. */
	public boolean removerCliente(String documento) {
		for (int i = 0; i < listaClientes.size(); i++) {
			if (comparaDocumento(listaClientes.get(i), documento)) {
				listaClientes.remove(i);
				return true;
			}
		}
		return false;
	}
	/* Imprime "i. <cliente.nome> - <cliente.documento>\n" para cada cliente cadastrado com 
	 * tipoCliente especificado. <cliente. documento> é a string "CPF/CNPJ" e <cliente.documento>
	 * o número do documento. i vai do intervalo de 0 até o núm. total de clientes de
	 * tipoCliente - 1. tipoCliente == "PF" para listar ClientePF, tipoCliente == "PJ" para listar
	 * ClientePJ, e se tipoCliente for diferente dos casos citados, lista todos os clientes. 
	 * Retorna boolean indicando se imprimiu alguma vez. */
	public boolean listarClientes(String tipoCliente) {
		int i, cont = 0;
		boolean imprimiu = false;
		if (tipoCliente.equals("PF")) {
			for (i = 0; i < listaClientes.size(); i++)
				if (listaClientes.get(i) instanceof ClientePF) {
					System.out.println(String.valueOf(cont++) + ". " +
							strClienteDocumento(listaClientes.get(i)));
					imprimiu = true;
				}
		} else if (tipoCliente.equals("PJ")) {
				for (i = 0; i < listaClientes.size(); i++)
				if (listaClientes.get(i) instanceof ClientePJ) {
					System.out.println(String.valueOf(cont++) + ". " +
							strClienteDocumento(listaClientes.get(i)));
					imprimiu = true;
				}
		} else { // imprime todos os clientes
			for (i = 0; i < listaClientes.size(); i++) {
				System.out.println(String.valueOf(i) + ". " +
						strClienteDocumento(listaClientes.get(i)));
				imprimiu = true;
			}
		}
		return imprimiu;
	}
	/* Transfere o seguro de clienteTransf para clienteReceb. Retorna boolean indicando se
	 * a transferencia ocorreu (nao ocorre quando clienteTransf.equals(clienteReceb)).
	 * Atualiza os precos dos seguros automaticamente. */
	public boolean transferirSeguro(Cliente clienteTransf, Cliente clienteReceb) {
		if (clienteTransf.equals(clienteReceb))
			return false;
		// adiciona os veículos de clienteTransf aos de clienteReceb
		for (int i = 0; i < clienteTransf.getListaVeiculos().size(); i++)
			clienteReceb.getListaVeiculos().add(clienteTransf.getListaVeiculos().get(i));
		// remove os veículos de clienteTransf
		clienteTransf.getListaVeiculos().clear();
		// recalcula os precos dos seguros (clienteTransf.valorSeguro será 0)
		calcularPrecoSeguroCliente(clienteTransf);
		calcularPrecoSeguroCliente(clienteReceb);
		return true;
	}
	/* Adiciona o veiculo v a c.listaVeiculos, retorna boolean indicando se adicionou.
	 * Atualiza automaticamente c.valorSeguro */
	public boolean adicionarVeiculoCliente(Cliente c, Veiculo v) {
		boolean adicionou = c.adicionarVeiculo(v);
		calcularPrecoSeguroCliente(c); // recalcula preco pro cliente (pois adicionou veiculo)
		return adicionou;
	}
	/* Remove o veiculo v de c.listaVeiculos, retorna boolean indicando se removeu.
	 * Atualiza automaticamente c.valorSeguro */
	public boolean removerVeiculoCliente(Cliente c, Veiculo v) {
		boolean removeu = c.removerVeiculo(v);
		calcularPrecoSeguroCliente(c); // recalcula preco pro cliente (pois removeu veiculo)
		return removeu;
	}
	/* Lista todos os veiculos cadastrados na seguradora, no formato "<i>. Placa <veiculo.placa>",
	 * i vai de 0 ao número de veículos - 1. Retorna boolean indicando se houve impressão. */
	public boolean listarVeiculos() {
		int cont = 0;
		if (! listaClientes.isEmpty()) {
			for (Cliente c: listaClientes) 
				for (Veiculo v: c.getListaVeiculos())
					System.out.println(String.valueOf(cont++) + ". Placa " + v.getPlaca());
			return true;
		}
		return false;
	}

	// Métodos para sinistros
	/* Adiciona um sinistro com os dados passados como argumento à seguradora. Retorna um boolean
	 * indicando se adicionou. Atualiza automaticamente o preco do seguro para cada cliente. */
	public boolean gerarSinistro(Date data, String endereco, Veiculo veiculo, Cliente cliente) {
		boolean add = listaSinistros.add(new Sinistro(data, endereco, this, veiculo, cliente));
		atualizarValoresSeguro(); // adicionar sinistro muda o preco de seguro p/ todos clientes
		return add;
	}
	/* Remove o sinistro s de listaSinistros e retorna um boolean indicando se removeu.
	 * Atualiza automaticamente o preco do seguro para cada cliente. */
	public boolean removerSinistro(Sinistro s) {
		boolean removeu = listaSinistros.remove(s);
		atualizarValoresSeguro();
		return removeu;
	}
	/* Imprime todos os sinistros relacionados ao cliente com o documento passado como argumento
	 * na seguradora, no formato de "<i>. id <sinistro.id>\n", onde i vai de 0 ao número de 
	 * sinistros - 1. Retorna true caso imprima algum sinistro, false caso contrário. */
	public boolean visualizarSinistro(String documento) {
		boolean encontrou = false; // se encontrar o documento, passa a ser true
		int numSinistros = 0;
		for (int i = 0; i < listaSinistros.size(); i++) {
			if (comparaDocumento(listaSinistros.get(i).getCliente(), documento)) {
				System.out.println(numSinistros++ + ". " + listaSinistros.get(i).getId());
				encontrou = true;
			}
		}
		return encontrou;
	}
	/* Imprime "<i>. id <sinistro.id>\n" dos sinistros na ordem em que foram cadastrados, onde
	 * i é o índice do sinistro em listaSinistros. Retorna boolean indicando se houve impressão. */
	public boolean listarSinistros() {
		if (! listaSinistros.isEmpty()) {
			for (int i = 0; i < listaSinistros.size(); i++)
				System.out.println(String.valueOf(i) + ". id " + listaSinistros.get(i).getId());
			return true;
		}
		return false;
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