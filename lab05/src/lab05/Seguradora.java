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
        String str = String.format("Seguradora %s\n- Telefone: %s\n- Endereco: %s\n- E-mail: %s\n",
                nome, telefone, endereco, email);
        str += "- Clientes: ";
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

	// TODO - comentar
	public boolean listarClientes(String tipoCliente) {
		boolean imprimiu = false;
		for (int i = 0; i < listaClientes.size(); i++) {
			if (tipoCliente == "PF" && listaClientes.get(i).getClass() == ClientePF.class ||
					tipoCliente == "PJ" && listaClientes.get(i).getClass() == ClientePJ.class ||
					tipoCliente == "") {
				System.out.println(i + " - " + listaClientes.get(i).strDocumento());
				imprimiu = true;
			}
		}
		return imprimiu;
	}
	// TODO - comentar
	public boolean listarSeguros() {
		if (listaSeguros.isEmpty()) // lista vazia
			return false;
		for (int i = 0; i < listaSeguros.size(); i++)
			System.out.println(i + " - " + listaSeguros.get(i).getId());
		return true;
	}
	// TODO  - comentar
	public boolean cadastrarCliente(Cliente cliente) {
		// não cadastra se já está na lista
		if (listaClientes.contains(cliente))
			return false;
		return listaClientes.add(cliente); // true se adicionou, senão false
	}
	// TODO  - comentar
	public boolean removerCliente(Cliente cliente) {
		return listaClientes.remove(cliente); // true se removeu, senão false
	}
	// TODO - comentar
	public boolean gerarSeguro(Date dataInicio, Date dataFim, Frota frota, ClientePJ cliente) {
		// tenta adicionar o cliente a listaClientes, retorna false e não gera o seguro
		// se não conseguir
		if (! cadastrarCliente(cliente))
			return false;
		// retorna true se adicionou, senão false
		return listaSeguros.add(new SeguroPJ(dataInicio, dataFim, this, frota, cliente));
	}
	// TODO - comentar
	public boolean gerarSeguro(Date dataInicio, Date dataFim, Veiculo veiculo, ClientePF cliente) {
		// tenta adicionar o cliente a listaClientes, retorna false e não gera o seguro
		// se não conseguir
		if (! cadastrarCliente(cliente))
			return false;
		// retorna true se adicionou, senão false
		return listaSeguros.add(new SeguroPF(dataInicio, dataFim, this, veiculo, cliente));
	}
	// TODO  - comentar
	public boolean cancelarSeguro(Seguro seguro) {
		return listaSeguros.remove(seguro); // true se removeu, senão false
	}
	// TODO - comentar
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
	// TODO - comentar
	public List<Veiculo> getVeiculosPorCliente(ClientePF cliente) {
		List<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
		for (Seguro s: listaSeguros)
			if (s.getClass() == SeguroPF.class && ((SeguroPF) s).getCliente().equals(cliente))
				listaVeiculos.add(((SeguroPF) s).getVeiculo());
		return listaVeiculos;
	}
	// TODO - comentar
	public List<Sinistro> getSinistrosPorCliente(Cliente cliente) {
		List<Sinistro> listaSinistros = new ArrayList<Sinistro>();
		for (Seguro seguro: getSegurosPorCliente(cliente))
			listaSinistros.addAll(seguro.getListaSinistros());
		return listaSinistros;
	}
	// TODO  - comentar
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
