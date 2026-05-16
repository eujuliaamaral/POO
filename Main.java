import java.util.Scanner;

public class Main {

    public static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {

        int opcao;

        do {

            System.out.println("Clinica de Consultas");
            System.out.println("Escolha uma opcao digitando o numero correspondente:");
            System.out.println("1 - Pacientes");
            System.out.println("2 - Profissionais");
            System.out.println("3 - Consultas");
            System.out.println("4 - Pagamentos");
            System.out.println("0 - Sair");
            System.out.print("Opcao: ");

            opcao = lerInt();

            switch(opcao) {

                case 1:
                    menuPacientes();
                    break;

                case 2:
                    menuProfissionais();
                    break;

                case 3:
                    menuConsultas();
                    break;

                case 4:
                    menuPagamentos();
                    break;

                case 0:
                    System.out.println("Obrigado por utilizar o sistema!");
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }

        } while(opcao != 0);

        sc.close();
    }

    public static void menuPacientes() {

        int op;

        do {

            System.out.println("Menu Pacientes");
            System.out.println("1 - Cadastrar paciente");
            System.out.println("2 - Buscar paciente");
            System.out.println("3 - Listar pacientes");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");

            op = lerInt();

            switch(op) {

                case 1:
                    System.out.println("Cadastrar paciente...");
                    break;

                case 2:
                    System.out.println("Buscar paciente...");
                    break;

                case 3:
                    System.out.println("Listar pacientes...");
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }

        } while(op != 0);
    }

    public static void menuProfissionais() {

        int op;

        do {

            System.out.println("Menu Profissionais");
            System.out.println("1 - Cadastrar profissional");
            System.out.println("2 - Listar profissionais");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");

            op = lerInt();

            switch(op) {

                case 1:
                    System.out.println("Cadastrar profissional...");
                    break;

                case 2:
                    System.out.println("Listar profissionais...");
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }

        } while(op != 0);
    }

    public static void menuConsultas() {

        int op;

        do {

            System.out.println("Menu Consultas");
            System.out.println("1 - Agendar consulta");
            System.out.println("2 - Cancelar consulta");
            System.out.println("3 - Remarcar consulta");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");

            op = lerInt();

            switch(op) {

                case 1:
                    System.out.println("Agendar consulta...");
                    break;

                case 2:
                    System.out.println("Cancelar consulta...");
                    break;

                case 3:
                    System.out.println("Remarcar consulta...");
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }

        } while(op != 0);
    }

    public static void menuPagamentos() {

        int op;

        do {

            System.out.println("Menu Pagamentos");
            System.out.println("1 - Registrar pagamento");
            System.out.println("2 - Listar pagamentos");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");

            op = lerInt();

            switch(op) {

                case 1:
                    System.out.println("Registrar pagamento...");
                    break;

                case 2:
                    System.out.println("Listar pagamentos...");
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }

        } while(op != 0);
    }

    public static int lerInt() {

        int valor = sc.nextInt();
        sc.nextLine();
        return valor;
    }
}

