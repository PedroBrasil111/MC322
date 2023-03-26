package lab02;

public class Main {
    public static void main(String[] args) {
        Cliente cliente1, cliente2;
        Seguradora seguradora;
        Sinistro sinistro1, sinistro2;
        Veiculo veiculo;

        // Teste Cliente
        cliente1 = new Cliente("Ana", "123.456.789-09", "26/03/2003",
            20, "Instituto de Computacao");
        cliente2 = new Cliente("Beto", "123.456.789-01", "26/03/1993",
            30, "Faculdade de Engenharia Eletrica e de Computacao");
        System.out.println(cliente1);
        System.out.println(cliente2);
        System.out.println("CPF de " + cliente1.getNome() + ": " + cliente1.validarCPF(cliente1.getCpf()));
        System.out.println("CPF de " + cliente2.getNome() + ": " + cliente2.validarCPF(cliente2.getCpf()));
        System.out.println("Nomes antes: " + cliente1.getNome() + ", " + cliente2.getNome());
        cliente2.setNome("Clarice");
        System.out.println("Nomes depois: " + cliente1.getNome() + ", " + cliente2.getNome());
        System.out.println("CPF 111.111.111-11 eh valido? " + cliente1.validarCPF("111.111.111-11"));
        System.out.println("CPF 123.456.78-9 eh valido? " + cliente1.validarCPF("123.456.78-9") + "\n");

        // Teste Seguradora
        seguradora = new Seguradora("Carsafe", "+55(19)1234-5678",
            "carsafe@gmail.com", "Ciclo Basico");
        System.out.println(seguradora);
        System.out.println("E-mail antes: " + seguradora.getEmail());
        seguradora.setEmail("carsafecontato@gmail.com");
        System.out.println("E-mail depois: " + seguradora.getEmail() + "\n");

        // Teste Sinistro
        sinistro1 = new Sinistro("26/03/2013", "Praca da Paz");
        sinistro2 = new Sinistro("26/03/2023", "Ciclo Basico II");
        System.out.println(sinistro1);
        System.out.println(sinistro2);
        System.out.println("ids iguais? " + (sinistro1.getId() == sinistro2.getId()) + "\n");

        // Teste Veiculo
        veiculo = new Veiculo("ABC1D23", "Toyota", "Corolla");
        System.out.println(veiculo);
    }

}
