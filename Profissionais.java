import java.util.Scanner;

public class Profissionais {
    String nome;
    String especialidade;
    String registroPro;
    double valorConsulta;
    String[] diasAtendimento;

        Scanner scanner = new Scanner(System.in);

    public Profissionais(String nome, String especialidade){
        this.nome = nome;
        this.especialidade = especialidade;
    }

    public Profissionais(String nome, String especialidade, String registroPro, double valorConsulta, String[] diasAtendimento){
        this.nome = nome;
        this.especialidade = especialidade;
        this.registroPro = registroPro;
        this.valorConsulta = valorConsulta;
        this.diasAtendimento = diasAtendimento;
    }

    
    public Profissionais cadastro(String nome, String especialidade, String registroPro, double valorConsulta, String[] diasAtendimento){
        return new Profissionais(nome, especialidade, registroPro, valorConsulta, diasAtendimento);
    }

    public Profissionais cadastro(String nome, String especialidade){
        return new Profissionais(nome, especialidade);
    }

    public Profissionais cadastroComplementarCompleto(Profissionais profissional){
        System.out.printf("Digite o registro profissional:");
        profissional.registroPro = scanner.nextLine();
        System.out.printf("Digite o valor da Cosulta:");
        profissional.valorConsulta = scanner.nextDouble();
        scanner.nextLine();
        System.out.printf("Digite a quantidade de dias de atendimento:");
        int l = scanner.nextInt();
        scanner.nextLine();

        profissional.diasAtendimento = new String[l];

        for(int i = 0; i < l; i++){
            System.out.printf("Digite o dia %d:", i + 1);
           profissional.diasAtendimento[i] = scanner.nextLine();
        }

        return profissional;
    }

    public Profissionais cadastroComplementarRapido(Profissionais profissional){
        System.out.printf("Digite o registro profissional:");
        profissional.registroPro = scanner.nextLine();
        System.out.printf("Digite o valor da Cosulta:");
        profissional.valorConsulta = scanner.nextDouble();

        return profissional;
    }
}
