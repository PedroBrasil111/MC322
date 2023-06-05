package lab05;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Condutor {
    // Atributos
    private final String cpf;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private Date dataNasc;
    private List<Sinistro> listaSinistros;

    // Construtor
    public Condutor(String cpf, String nome, String telefone, String endereco, String email,
            Date dataNasc) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.dataNasc = dataNasc;
        listaSinistros = new ArrayList<Sinistro>();
    }

    // toString()
    /* Condutor - <nome>:
     * - CPF: <cpf>
     * - Telefone: <telefone>
     * - Endereco: <endereco>
     * - E-mail: <email>
     * - Data de nascimento: <dataNasc [dd/MM/aaaa]>
     * - Sinistros: "Nenhum sinistro cadastrado" ou <sinistro1.id>, <sinistro2.id>, ...
     */
    public String toString() {
        String str = String.format("Condutor - %s:\n- CPF: %s\n- Telefone: %s\n- Endereco: %s\n" +
                "- E-mail: %s\n- Data de nascimento: %s\n- Sinistros: ", nome, cpf, telefone,
                endereco, email, Data.dateToString(dataNasc));
        if (! listaSinistros.isEmpty()) {
            str += "Nenhum sinistro cadastrado";
        } else {
            for (int i = 0; i < listaSinistros.size() - 1; i++)
                str += listaSinistros.get(i).getId() + ", ";
            str += listaSinistros.get(listaSinistros.size() - 1).getId();
        }
        return str;
    }

    // TODO - comentar
    public boolean adicinarSinistro(Sinistro s) {
        return listaSinistros.add(s);
    }
    // TODO - comentar
    public List<Sinistro> getSinistrosPorSeguradora(Seguradora seguradora) {
        ArrayList<Sinistro> listaRetorno = new ArrayList<Sinistro>();
        for (Sinistro sinistro: listaSinistros)
            if (sinistro.getSeguro().getSeguradora().equals(seguradora))
                listaRetorno.add(sinistro);
        return listaRetorno;
    }
    public boolean listarSinistros() {
		if (listaSinistros.isEmpty()) // lista vazia
			return false;
		for (int i = 0; i < listaSinistros.size(); i++)
			System.out.println(i + " - " +  listaSinistros.get(i).getId());
		return true;
    }

    // Getters e setters
    public String getCpf() {
        return cpf;
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
    public Date getDataNasc() {
        return dataNasc;
    }
    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }
    public List<Sinistro> getListaSinistros() {
        return listaSinistros;
    }
    public void setListaSinistros(List<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }

}
