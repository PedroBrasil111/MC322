package lab05;

import java.util.ArrayList;
import java.util.List;

public class Frota {
    private String code;
    private final List<Veiculo> listaVeiculos;

    public Frota(String code) {
        this.code = code;
        this.listaVeiculos = new ArrayList<Veiculo>();
    }

    // TODO -- remover sobrecarga?
    public boolean addVeiculo(Veiculo v) {
        return listaVeiculos.add(v);
    }
    public boolean addVeiculo(String placa, String marca, String modelo, int anoFabricacao) {
        return addVeiculo(new Veiculo(placa, marca, modelo, anoFabricacao));
    }
    // TODO -- remover sobrecarga?
    public boolean removeVeiculo(Veiculo v) {
        return listaVeiculos.remove(v);
    }
    public boolean removeVeiculo(String placa) {
        for (int i = 0; i < listaVeiculos.size(); i++)
            if (listaVeiculos.get(i).getPlaca() == placa)
                return removeVeiculo(listaVeiculos.get(i));
        return false;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }
    public List<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

}
