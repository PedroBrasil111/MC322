package lab05;

import java.util.ArrayList;
import java.util.List;

public class Frota {
    private String code;
    private List<Veiculo> listaVeiculos = new ArrayList<Veiculo>();

    public boolean addVeiculo(String placa, String marca, String modelo, int anoFabricacao) {
        return listaVeiculos.add(new Veiculo(placa, marca, modelo, anoFabricacao));
    }

    public boolean removeVeiculo(String placa) {
        for (int i = 0; i < listaVeiculos.size(); i++)
            if (listaVeiculos.get(i).getPlaca() == placa)
                return listaVeiculos.remove(listaVeiculos.get(i));
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
