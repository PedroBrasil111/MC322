package lab05;

public class AppMain {

	public static void main(String[] args) {
		Seguradora s = new Seguradora("1", "A", "1", "B", "C");
		double valor = 10. * (1.25) * (1 + 1./(1 + 2)) * (2 + 3/10.) * (5 + 4/10.);
		// 207
		System.out.println(valor);
		/*
		public double calcularValor() {
        	double qtdeSinistrosPorCondutor = 0.;
        	double valor = CalcSeguro.VALOR_BASE.getValor() *
        	    // FATOR_IDADE -- 
        	    CalcSeguro.values()[Data.calcularIdade(cliente.getDataNasc())/30 + 1].getValor() *
        	    (1 + 1/(cliente.getListaVeiculos().size() + 2)) *
        	    (2 + getSeguradora().getSinistrosPorCliente(cliente).size()/10);
        	for (Condutor condutor: getListaCondutores())
        	    qtdeSinistrosPorCondutor += condutor.getListaSinistros().size();
        	valor *= 5 + qtdeSinistrosPorCondutor/10;
        	return valor;
    	}
		*/
	}

}
