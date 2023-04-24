package lab03;

import java.util.Date;
import java.text.ParseException;

public class ClientePJ extends Cliente {

    // Propriedades
    private final String cnpj;
    private Date dataFundacao;

    // Construtor
    public ClientePJ(String nome, String endereco, String cnpj, String dataFundacao,
                     Veiculo...veiculos) {
        super(nome, endereco, veiculos);
        this.cnpj = cnpj;
        Date data;
        try {
            data = parseDate(dataFundacao);
            this.dataFundacao = data;
        } catch (ParseException e) { //erro quando dataFundacao não está no formato dd/MM/yyyy
            e.printStackTrace();
        }
    }

    // toString
    @Override
    public String toString() {
		String str = String.format("Cliente PJ - %s:\n- CNPJ: %s;\n- Endereco: %s;\n- Data de fundacao: %s;" + 
                "\n- Veiculos:", getNome(), cnpj, getEndereco(), parseString(dataFundacao));
        if (getListaVeiculos().isEmpty())
            str += "\n- Nenhum veiculo cadastrado";
		for (int i = 0; i < getListaVeiculos().size(); i++)
			str += "\n- " + (i + 1) + ". " + getListaVeiculos().get(i).getPlaca();
		return str;
    }

    // Métodos privados
    /* Calcula  e retorna o primeiro dígito verificador de String cnpj se numDigito == 0,
     * e o segundo dígito se numDigito == 1. */
    private static int digitoVerificadorCNPJ(String cnpj, int numDigito) {
        int i, aux = 0; // aux guarda os resultados das operacões com os dígitos
        // Multiplica primeiros dígitos sequencialmente por 5, 4, 3, ..., 1 se numDigito == 0,
        // ou por 6, 4, 3, ..., 1, se numDigito == 1, e soma em aux:
        for (i = 0; i < (4 + numDigito); i++)
            aux += charAtToInt(cnpj, i) * (5 + numDigito - i);
        // Multiplica 5° dígito em diante por 9, 8, 7, ..., 2 e soma em aux:
        for (i = 4 + numDigito; i < (12 + numDigito); i++)
            aux += charAtToInt(cnpj, i) * (13 + numDigito - i);
        aux %= 11;
        return aux < 2 ? 0 : 11 - aux; // se aux == (0 ou 1): dígito = 0, senão: dígito = 11 - aux
    }
    /* Retorna true se os dígitos verificadores do CNPJ passado como argumento são válidos,
     * false caso contrário. */
    private static boolean digitosValidosCNPJ(String cnpj) {
        if (digitoVerificadorCNPJ(cnpj, 0) == charAtToInt(cnpj, 12) &&
                digitoVerificadorCNPJ(cnpj, 1) == charAtToInt(cnpj, 13))
            return true;
        return false;
    }

    // Métodos públicos
	/* Retorna true se o CNPJ for válido, false caso contrário. Para que um CNPJ seja válido,
	 * é necessário que ele tenha 14 caracteres numéricos, sendo ao menos um número diferente
	 * dos demais e que os digitos verificadores sejam válidos. */
    public static boolean validarCNPJ(String cnpj) {
        cnpj = cnpj.replaceAll("[^0-9]", ""); // Remove caracteres não numéricos
        if (cnpj.length() != 14 || todosCharIguais(cnpj) || ! digitosValidosCNPJ(cnpj))
            return false;
        return true;
    }

    // Getters e setters
    public String getCnpj() {
        return cnpj;
    }
    public Date getDataFundacao() {
        return dataFundacao;
    }
    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

}
