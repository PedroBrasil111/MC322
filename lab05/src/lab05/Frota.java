package lab05;

import java.util.ArrayList;
import java.util.List;

public class Frota {
    // Atributos
    private final String code;
    private List<Veiculo> listaVeiculos;

    // Construtor
    public Frota() {
        this.code = gerarCode();
        this.listaVeiculos = new ArrayList<Veiculo>();
    }

    // toString()
    /* Frota - code <code>:
     * - Veiculos: "Nenhum veiculo cadastrado" OU <veiculo1.placa>, <veiculo2.placa>, ... */
    public String toString() {
        String str = String.format("Frota - code %s:\n- Veiculos: ", code);
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

    /* Gera um código único para o objeto. */
    private String gerarCode() {
		// Gera um id aleatório baseado no endereco de memória (único p/ cada objeto).
		// Porém, esse id varia a cada iteracão do programa.
        String str = String.valueOf(hashCode());
        return str;
    }
    /* Retorna boolean indicando se a frota f é igual. Comparacão é feita por code. */
    public boolean equals(Frota f) {
        if (f == null || ! code.equals(f.getCode()))
            return false;
        return true;
    }
    /* Lista os veiculos cadastrados para a frota no formato "i - <veiculo[i].placa>,"
     * onde i é o índice do veiculo na lista. Retorna boolean indicando se imprimiu. */
    public boolean listarVeiculos() {
		if (listaVeiculos.isEmpty()) // lista vazia
			return false;
		for (int i = 0; i < listaVeiculos.size(); i++)
			System.out.println(i + " - " + listaVeiculos.get(i).getPlaca());
		return true;
    }
    /* Adiciona o veiculo v (se for único) a listaVeiculos, retorna boolean indicando se adicionou. */
    public boolean addVeiculo(Veiculo v) {
        if (listaVeiculos.contains(v))
            return false;
        return listaVeiculos.add(v);
    }
	/* Remove o veiculo v de listaVeiculos, retorna boolean indicando se removeu. */
    public boolean removeVeiculo(Veiculo v) {
        return listaVeiculos.remove(v);
    }

    // Getters e setters
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