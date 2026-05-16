import java.util.Scanner;

public class Paciente {
    String nome;
    String cpf;
    int idade;
    String telefone;
    boolean convenio;
    String tipoconvenio;
    boolean ativo;

    Scanner scanner = new Scanner(System.in);

    public Paciente(){
    }

    public Paciente(String nome, String cpf, boolean ativo){
        this.nome = nome;
        this.cpf = cpf;
        this.ativo = ativo;
    }

    public Paciente(String nome, String cpf, int idade, String telefone, boolean convenio, String tipoconvenio, boolean ativo){
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.telefone = telefone;
        this.convenio = convenio;
        this.tipoconvenio = tipoconvenio;
        this.ativo = ativo;
    }

    
    public Paciente cadastro(String nome, String cpf, int idade, String telefone, boolean convenio, String tipoconvenio){
        boolean ativo = true;
        Clinica.totalPacientes++;
        return new Paciente(nome, cpf, idade, telefone, convenio, tipoconvenio, ativo);
    }

    public Paciente cadastro(String nome, String cpf){
        boolean ativo = true;
        Clinica.totalPacientes++;
        return new Paciente(nome, cpf, ativo);
    }

    public Paciente cadastroComplementarCompleto(Paciente paciente){
        System.out.printf("Digite a idade do paciente:");
        paciente.idade = scanner.nextInt();
        scanner.nextLine();
        System.out.printf("Digite o telefone do paciente:");
        paciente.telefone = scanner.nextLine();
        scanner.nextLine();
        System.out.printf("O paciente possui convenio (true ou false):");
        paciente.convenio = scanner.nextBoolean();
        scanner.nextLine();

        if (paciente.convenio){
            System.out.printf("Digite o convenio do paciente:");
            paciente.tipoconvenio = scanner.nextLine();
        }else{
            paciente.tipoconvenio = "Não possui";
        }

        return paciente;
    }

    public Paciente cadastroComplementarRapido(Paciente paciente){
        System.out.printf("Digite a idade do paciente:");
        paciente.idade = scanner.nextInt();
        scanner.nextLine();
        System.out.printf("Digite o telefone do paciente:");
        paciente.telefone = scanner.nextLine();

        return paciente;
    }
    

}
