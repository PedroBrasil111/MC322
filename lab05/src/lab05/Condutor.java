package lab05;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Condutor {
    private final String cpf;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private Date dataNasc;
    private final List<Sinistro> listaSinistros;

    public Condutor(ClientePF cliente) {
        cpf = cliente.getCpf();
        nome = cliente.getNome();
        telefone = cliente.getTelefone();
        endereco = cliente.getEndereco();
        email = cliente.getEmail();
        dataNasc = cliente.getDataNasc();
        listaSinistros = new ArrayList<Sinistro>();
    }

    public String toString() {
        String str = String.format("Condutor - %s:\n- CPF: %s\n- Telefone: %s\n- Endereco: %s\n" +
                "- E-mail: %s\n- Data de nascimento: %s\n- Sinistros: ", nome, cpf, telefone,
                endereco, email, Data.dateToString(dataNasc));
        if (! listaSinistros.isEmpty()) {
            str += "Nao ha sinistros cadastrados";
        } else {
            for (int i = 0; i < listaSinistros.size() - 1; i++)
                str += listaSinistros.get(i).getId() + ", ";
            str += listaSinistros.get(listaSinistros.size() - 1).getId();
        }
        return str;
    }

    public boolean adicinarSinistro(Sinistro s) {
        return listaSinistros.add(s);
    }

    public List<Sinistro> getSinistrosPorSeguradora(Seguradora seguradora) {
        ArrayList<Sinistro> listaRetorno = new ArrayList<Sinistro>();
        for (Sinistro sinistro: listaSinistros)
            if (sinistro.getSeguro().getSeguradora().equals(seguradora))
                listaRetorno.add(sinistro);
        return listaRetorno;

    }

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

}
