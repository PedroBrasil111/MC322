package lab05;

import java.util.ArrayList;
import java.util.List;

public class Frota {
    // Atributos
    private String code;
    private List<Veiculo> listaVeiculos;

    // Construtor
    public Frota() {
        this.code = String.valueOf(hashCode());
        this.listaVeiculos = new ArrayList<Veiculo>();
    }

    // toString()
    /* Frota - code <code>:
     * - Veiculos: "Nenhum veiculo cadastrado" OU <veiculo1.placa>, <veiculo2.placa>, ... */
    public String toString() {
        String str = String.format("Frota - code %s\n- Veiculos: ", code);
        if (listaVeiculos.isEmpty()) {
            str += "Nenhum veiculo cadastrado";
        } else {
            for (int i = 0; i < listaVeiculos.size(); i++) {
                str += listaVeiculos.get(i).getPlaca();
                if (i != listaVeiculos.size() - 1)
                    str += ", ";
            }
        }
        return str;
    }
    // TODO - comentar (espelhar ClientePF)
    public boolean listarVeiculos() {
		if (listaVeiculos.isEmpty()) // lista vazia
			return false;
		for (int i = 0; i < listaVeiculos.size(); i++)
			System.out.println(i + " - " + listaVeiculos.get(i).getPlaca());
		return true;
    }
    // Adiciona o veiculo v a listaVeiculos, retorna boolean indicando se adicionou
    public boolean addVeiculo(Veiculo v) {
        return listaVeiculos.add(v);
    }
	// Remove o veiculo v de listaVeiculos, retorna boolean indicando se removeu 
    public boolean removeVeiculo(Veiculo v) {
        return listaVeiculos.remove(v);
    }

    // Getters e setters
    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }
    public List<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }
    public void setListaVeiculos(List<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

}
