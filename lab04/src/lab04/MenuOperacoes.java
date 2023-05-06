package lab04;

import java.util.Scanner;
import java.text.ParseException;
import java.util.Date;

public enum MenuOperacoes {
    CADASTROS (1),
    CADASTRAR_CLIENTE (11),
    CADASTRAR_VEICULO (12),
    CADASTRAR_SEGURADORA (13),

    LISTAR (2),
    LISTAR_CLIENTE_SEG (21),
    LISTAR_SINISTRO_SEG (22),
    LISTAR_SINISTRO_CLIENTE (23),
    LISTAR_VEICULO_CLIENTE (24),
    LISTAR_VEICULO_SEG (25),

    EXCLUIR (3),
    GERAR_SINISTRO (4),
    TRASNFERIR_SEGURO (5),
    CALC_RECEITA_SEG (6),

    SAIR (0);

    public final int operacao;

    MenuOperacoes(int operacao) {
        this.operacao = operacao;
    }

    public int getOperacao () {
        return this.operacao;
    }

    private void mensagemErroValorInvalido() {
        System.out.println("Erro: valor invalido");
    }

    private int lerInteiro(Scanner scanner) {
        String input = scanner.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
        //try {
        //    return scanner.nextInt();
        //} catch (InputMismatchException e) {
        //    return -1;
        //}
    }

    private void menuPrincipal(Scanner scanner, Seguradora seg) {
        int opcao;
        System.out.println("1. Cadastros\n2. Listar\n3. Excluir\n4. Gerar Sinsitro\n" +
                "5. Transferir Seguro\n6. Calcular Receita Seguradora\n0. Sair");
        do {
            opcao = lerInteiro(scanner);
            if (opcao >= 0 && opcao <= 5) {
                MenuOperacoes.values()[opcao].menu(scanner, seg);
                break;
            }
            else
                mensagemErroValorInvalido();
        } while (true);
    }

    private void menuCadastros(Scanner scanner, Seguradora seg) {
		int opcao;
        do {
            System.out.println("*Menu de cadastros*\n1. Cadastrar cliente\n" +
            "2. Cadastrar veiculo\n3. Cadastrar Seguradora\n0. Voltar");
            opcao = lerInteiro(scanner);
            if (opcao >= 0 && opcao <= 3) {
                MenuOperacoes.values()[10 + opcao].menu(scanner, seg);
                break;
            } else
                mensagemErroValorInvalido();
        } while (true);
    }

    /*
     * ARRUMAR:
     * forma de gerar datas
     * add modo de retornar?
     * deixar como boolean mesmo? nao estou retornando false
     */
    private boolean lerCliente(Scanner scanner, Seguradora seg, int opcao) {
        String nome, endereco, genero, educacao, classeEconomica, documento;
        Date data1, data2;
        int qtdeFuncionarios;
        System.out.println("Nome");
        do {
            nome = scanner.nextLine();
            if (! Validacao.validaNome(nome))
                System.out.println("Nome invalido");
            else
                break;
        } while (true);
        System.out.println("Endereco");
        endereco = scanner.nextLine();
        if (opcao == 1) { // Cliente PF
            System.out.println("CPF");
            do {
                documento = scanner.nextLine();
                if (! Validacao.validarCPF(documento))
                    System.out.println("CPF invalido");
                else
                    break;
            } while (true);
            System.out.println("Genero");
            genero = scanner.nextLine();
            System.out.println("Data da licenca");
            do {
                try {
                    data1 = Validacao.parseDate(scanner.nextLine());
                    break;
                } catch (ParseException e) {
                    System.out.println("Formato invalido");
                }
            } while (true);
            System.out.println("Educacao");
            educacao = scanner.nextLine();
            System.out.println("Data de nascimento");
            do {
                try {
                    data2 = Validacao.parseDate(scanner.nextLine());
                    break;
                } catch (ParseException e) {
                    System.out.println("Formato invalido");
                }
            } while (true);
            System.out.println("Classe economica");
            classeEconomica = scanner.nextLine();
            seg.cadastrarCliente(new ClientePF(nome, endereco, documento, genero, data1,
                    educacao, data2, classeEconomica));
        } else { // Cliente PJ
            System.out.println("CNPJ");
            documento = scanner.nextLine();
            System.out.println("Data de fundacao");
            do {
                try {
                    data1 = Validacao.parseDate(scanner.nextLine());
                    break;
                } catch (ParseException e) {
                    System.out.println("Formato invalido");
                }
            } while (true);
            System.out.println("Quantidade de funcionarios");
            do {
                qtdeFuncionarios = lerInteiro(scanner);
                if (qtdeFuncionarios > 0)
                    break;
                else
                    mensagemErroValorInvalido();
            } while (true);
            seg.cadastrarCliente(new ClientePJ(nome, endereco, documento, data1, qtdeFuncionarios));
        }
        return true;
    }

    private void menuCadastroCliente(Scanner scanner, Seguradora seg) {
        int opcao;
        do {
            System.out.println("*Cadastro de cliente*\n1. Cliente PF\n2. Cliente PJ\n0. Voltar");
            opcao = lerInteiro(scanner);
            if (opcao < 0)
                mensagemErroValorInvalido();
            else if (opcao == 0)
                return;
            else if (lerCliente(scanner, seg, opcao))
                System.out.println("Cadastro realizado com sucesso.");
        } while (true);
    }

    private void menuCadastroVeiculo(Scanner scanner, Seguradora seg) {
        return;
    }

    private void menuCadastroSeguradora(Scanner scanner, Seguradora seg) {
        return;
    }

    private void menuCadastroVeiculo(Scanner scanner, Seguradora[] seg) {

    }

    public void menu(Scanner scanner, Seguradora seg) {
        switch (operacao) {
            case 1:
                menuCadastros(scanner, seg);
                break;
            case 11:
                menuCadastroCliente(scanner, seg);
                break;
            case 12:
                menuCadastroVeiculo(scanner, seg);
                break;
            case 13:
                menuCadastroSeguradora(scanner, seg);
                break;
            default:
                return;
        }
    }
}