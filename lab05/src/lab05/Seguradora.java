package lab05;

import java.util.ArrayList;
import java.util.List;

public class Seguradora {
	// Propriedades
	private final String cnpj;
	private String nome;
	private String telefone;
	private String endereco;
	private String email;
	private final List<Cliente> listaClientes; // lista com clientes que contratam a seguradora
	private final List<Seguro> listaSeguros; // lista com seguros da seguradora

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
    
    public String toString() {
        int i;
        String str = String.format("Seguradora %s\n- Telefone: %s\n- Endereco: %s\n- E-mail: %s\n",
                nome, telefone, endereco, email);
        str += "- Clientes: ";
		if (! listaClientes.isEmpty()) {
        	for (i = 0; i < listaClientes.size() - 1; i++)
				str += listaClientes.get(i).documento() + ", ";
			str += listaClientes.get(listaClientes.size() - 1).documento();
		} else
			str += "Nao ha clientes cadastrados";
		str += "\n- Seguros: ";
		if (! listaSeguros.isEmpty()) {
			for (i = 0; i < listaSeguros.size() - 1; i++)
				str += listaSeguros.get(i).getId() + ", ";
			str += listaSeguros.get(listaSeguros.size() - 1).getId();
		} else
			str += "Nao ha seguros cadastrados";
		return str;
    }

	public boolean listarClientes(String tipoCliente) {
		boolean imprimiu = false;
		for (int i = 0; i < listaClientes.size(); i++) {
			if (tipoCliente == "PF" && listaClientes.get(i).getClass() == ClientePF.class ||
					tipoCliente == "PJ" && listaClientes.get(i).getClass() == ClientePJ.class ||
					tipoCliente == "") {
				System.out.println(i + 1 + " - " + listaClientes.get(i).documento());
				imprimiu = true;
			}
		}
		return imprimiu;
	}
	// TODO 
	public boolean gerarSeguro() {
		return true;
	}
	// TODO 
	public boolean cancelarSeguro() {
		return true;
	}
	// TODO 
	public boolean cadastrarCliente() {
		return true;
	}
	// TODO 
	public boolean removerCliente() {
		return true;
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
	// TODO 
	public double calcularReceita() {
		return 0.;
	}

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
	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

}
