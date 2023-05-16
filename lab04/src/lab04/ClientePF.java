package lab04;

import java.util.Date;

public class ClientePF extends Cliente {
    // Propriedades
    private final String cpf;
    private String genero;
    private Date dataLicenca;
    private String educacao;
    private Date dataNascimento;
    private String classeEconomica;

    // Construtor
    public ClientePF(String nome, String endereco, String cpf, String genero, Date dataLicenca,
            String educacao, Date dataNascimento, String classeEconomica,
            Veiculo...veiculos) {
        super(nome, endereco, veiculos);
        this.cpf = cpf;
        this.genero = genero;
        this.educacao = educacao;
        this.classeEconomica = classeEconomica;
        this.dataLicenca = dataLicenca;
        this.dataNascimento = dataNascimento;
    }
    
    // toString()
    /* ClientePF - <nome>:
     * - Endereco: <endereco>
     * - Valor do seguro: <valorSeguro>
	 * - Veiculo(s): Nenhum veiculo cadastrado OU <veiculo1.placa>, <veiculo2.placa>, ...
     * - CPF: <cpf>
     * - Data de nascimento: <dataNascimento>
     * - Data da licenca: <dataLicenca>
     * - Genero: <genero>
     * - Educacao: <educacao>
     * - Classe economica: <classeEconomica> */
    public String toString() {
        String str = super.toString().replace("Cliente", "ClientePF");
        str += String.format("\n- CPF: %s\n- Data de nascimento: %s\n- Data da licenca: %s" + 
                "\n- Genero: %s\n- Educacao: %s\n- Classe economica: %s", cpf,
                Data.dateToString(dataNascimento), Data.dateToString(dataLicenca), genero,
                educacao, classeEconomica);
        return str;
    }

    @Override
    /* Retorna o valor do score para o cliente. Para pessoa jurídica, o score é dado por
     * (VALOR_BASE * FATOR_IDADE * quantidadeCarros). FATOR_BASE varia com a idade do cliente. */
    public double calculaScore() {
        CalcSeguro fatorIdade;
        int idade = Data.calcularIdade(dataNascimento);
        if (idade >= 18 && idade <= 90) {
            fatorIdade = CalcSeguro.values()[(idade / 30) + 1]; // fator se altera a cada 30 anos
            return CalcSeguro.VALOR_BASE.getValor() * fatorIdade.getValor() *
                    getListaVeiculos().size();
        } else // idades inválidas / cálculo não especificado -- retorna valor base
            return CalcSeguro.VALOR_BASE.getValor();
    }

    //Getters e setters
    public String getCpf() {
        return cpf;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public Date getDataLicenca() {
        return dataLicenca;
    }
    public void setDataLicenca(Date dataLicenca) {
        this.dataLicenca = dataLicenca;
    }
    public String getEducacao() {
        return educacao;
    }
    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }
    public Date getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public String getClasseEconomica() {
        return classeEconomica;
    }
    public void setClasseEconomica(String classeEconomica) {
        this.classeEconomica = classeEconomica;
    }

}