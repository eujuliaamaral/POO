import java.util.Scanner;

public class Main {

    public static Scanner sc = new Scanner(System.in);
    public static Clinica clinica = new Clinica();
    public static Consultas consulta = new Consultas();
    public static Paciente paciente = new Paciente();
    public static Profissionais profissional = new Profissionais();
    
    public static void main(String[] args) {

        int opcao;

        do {

            System.out.println("\nClinica de Consultas");
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

            System.out.println("\nMenu Pacientes");
            System.out.println("1 - Cadastrar paciente");
            System.out.println("2 - Buscar paciente");
            System.out.println("3 - Listar pacientes");
            System.out.println("4 - Cadastro complementar");
            System.out.println("5 - Desativar paciente");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");

            op = lerInt();

            switch(op) {

                case 1:
                    System.out.println("\n--- Cadastrar paciente ---\n");
                    System.out.printf("Digite o nome do paciente:");
                    String nome = sc.nextLine();
                    System.out.printf("Digite o cpf do paciente:");
                    String cpf = sc.nextLine();
                    boolean pacienteDuplicado = false;
                    for(int i = 0; i < Clinica.totalProfissionais; i++){
                        if(clinica.pacientes[i].cpf.equals(cpf)){
                            System.out.printf("\nCadastro já foi feito!\n");
                            pacienteDuplicado = true;
                            break;
                        }
                    }
                    if(pacienteDuplicado){
                        break;
                    }
                    System.out.printf("Digite a idade do paciente:");
                    String idadeTexto = sc.nextLine();
                    System.out.printf("Digite o telefone do paciente:");
                    String telefone = sc.nextLine();
                    System.out.printf("O paciente possui convenio (true ou false):");
                    boolean convenio = sc.nextBoolean();
                    sc.nextLine();

                    String tipoconvenio;

                    if (convenio){
                        System.out.printf("Digite o convenio do paciente:");
                        tipoconvenio = sc.nextLine();
                    }else{
                        tipoconvenio = "Não possui";
                    }

                    int np = Clinica.totalPacientes;
                    if(idadeTexto.trim().isEmpty()){
                        clinica.pacientes[np] = paciente.cadastro(nome, cpf);
                    }else{
                        int idade = Integer.parseInt(idadeTexto.trim());
                        clinica.pacientes[np] = paciente.cadastro(nome, cpf, idade, telefone, convenio, tipoconvenio);
                    }
                    break;

                case 2:
                    System.out.println("\n--- Buscar paciente ---\n");
                    System.out.printf("Digite o cpf do paciente:");
                    String cpfbuscado = sc.nextLine();
                    clinica.listarPacientes(cpfbuscado);
                    break;

                case 3:
                    System.out.println("\n--- Listar pacientes ---\n");
                    clinica.listarPacientes();
                    break;

                case 4:
                    System.out.println("\n--- Complementar cadastro ---\n");
                    System.out.print("Digite o CPF do paciente que deseja atualizar: ");
                    String cpfBusca = sc.nextLine();

                    Paciente pacienteEncontrado = clinica.pacienteBusca(cpfBusca);

                    if(pacienteEncontrado != null){
                        System.out.println("Paciente encontrado: " + pacienteEncontrado.nome);
                        System.out.println("Escolha o tipo de atualização:");
                        System.out.println("1 - Atualização Rápida (Idade e Telefone)");
                        System.out.println("2 - Atualização Completa (Idade, Telefone e Convênio)");
                        System.out.print("Opção: ");
                        int opcaoAtualizar = sc.nextInt();
                        sc.nextLine();

                        if(opcaoAtualizar == 1){
                            paciente.cadastroComplementarRapido(pacienteEncontrado);
                            System.out.println("Cadastro atualizado com sucesso!");
                            
                        }else if (opcaoAtualizar == 2){
                            paciente.cadastroComplementarCompleto(pacienteEncontrado);
                            System.out.println("Cadastro completado com sucesso!");
                        }else{
                            System.out.println("Opção inválida.");
                        }
                    }
                    break;

                case 5:
                    System.out.println("\n--- Desativar cadastro ---\n");
                    System.out.print("Digite o CPF do paciente que deseja desativar: ");
                    String cpfDesativa = sc.nextLine();

                    Paciente pacienteDesativa = clinica.pacienteBusca(cpfDesativa);

                    if (pacienteDesativa != null) {
                        pacienteDesativa.ativo = false;
                        System.out.println("\nO cadastro de " + pacienteDesativa.nome + " foi desativado com sucesso!");
                    }

                    break;

                case 0:
                    break;

                default:
                    System.out.println("\nOpcao invalida.\n");
            }

        } while(op != 0);
    }

    public static void menuProfissionais() {

        int op;

        do {

            System.out.println("\nMenu Profissionais");
            System.out.println("1 - Cadastrar profissional");
            System.out.println("2 - Listar profissionais");
            System.out.println("3 - Listar profissionais por especialidade");
            System.out.println("4 - Cadastro complementar");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");

            op = lerInt();

            switch(op) {

                case 1:
                    System.out.println("\n--- Cadastrar profissional ---\n");
                    System.out.printf("Digite o nome do profissional:");
                    String nome = sc.nextLine();
                    System.out.printf("Digite a especialidade(clinica geral, fisioterapia, psicologia e nutrição):");
                    String especialidade = sc.nextLine();
                    if (!especialidade.equals("clinica geral") && 
                        !especialidade.equals("fisioterapia") && 
                        !especialidade.equals("nutrição")) {
                        
                        System.out.println("Especialidade inválida!");
                        break;
                    }
                    System.out.printf("Digite o registro profissional:");
                    String registroPro = sc.nextLine();
                    boolean profissionalDuplicado = false;
                    for(int i = 0; i < Clinica.totalProfissionais; i++){
                        if(clinica.profissionais[i].registroPro.equals(registroPro)){
                            System.out.printf("\nCadastro já foi feito!\n");
                            profissionalDuplicado = true;
                            break;
                        }
                    }
                    if(profissionalDuplicado){
                        break;
                    }
                    System.out.printf("Digite o valor da Cosulta:");
                    double valorConsulta = sc.nextDouble();
                    sc.nextLine();
                    System.out.printf("Digite a quantidade de dias de atendimento:");
                    int l = sc.nextInt();
                    sc.nextLine();

                    String[] diasAtendimento = new String[l];

                    for(int i = 0; i < l; i++){
                        System.out.printf("Digite o dia %d:", i + 1);
                    diasAtendimento[i] = sc.nextLine();
                    }

                    int np = Clinica.totalProfissionais;

                    if(registroPro.trim().isEmpty()){
                        clinica.profissionais[np] = profissional.cadastro(nome, especialidade);
                    }else{
                        clinica.profissionais[np] = profissional.cadastro(nome, especialidade, registroPro, valorConsulta, diasAtendimento);
                    }
                    break;

                case 2:
                    System.out.println("\n--- Listar profissionais ---\n");
                    clinica.listarProfissionais();
                    break;

                case 3:
                    System.out.println("\n--- Listar profissionais por especialidade ---");
                    System.out.printf("Digite a especialidade:");
                    String especialidadeBuscado = sc.nextLine();
                    clinica.listarProfissionais(especialidadeBuscado);
                    break;    

                case 4:
                    System.out.println("\n--- Complementar Cadastro ---\n");
                    System.out.print("Digite o nome do profissional que deseja atualizar: ");
                    String nomeBusca = sc.nextLine();

                    Profissionais profissionalEncontrado = clinica.profissionaisBusca(nomeBusca);

                    if(profissionalEncontrado != null){
                        System.out.println("Profissional encontrado: " + profissionalEncontrado.nome);
                        System.out.println("Escolha o tipo de atualização:");
                        System.out.println("1 - Atualização Rápida (registro profissional e valor da consulta)");
                        System.out.println("2 - Atualização Completa (registro profissional, valor da consulta e dia de atendimento)");
                        System.out.print("Opção: ");
                        int opcaoAtualizar = sc.nextInt();
                        sc.nextLine();

                        if(opcaoAtualizar == 1){
                            profissional.cadastroComplementarRapido(profissionalEncontrado);
                            System.out.println("Cadastro atualizado com sucesso!");
                            
                        }else if (opcaoAtualizar == 2){
                            profissional.cadastroComplementarCompleto(profissionalEncontrado);
                            System.out.println("Cadastro completado com sucesso!");
                        }else{
                            System.out.println("Opção inválida.");
                        }
                    }
                    break;

                case 0:
                    break;

                default:
                    System.out.println("\nOpcao invalida.\n");
            }

        } while(op != 0);
    }

    public static void menuConsultas() {

        int op;

        do {

            System.out.println("\nMenu Consultas");
            System.out.println("1 - Agendar consulta");
            System.out.println("2 - Cancelar consulta");
            System.out.println("3 - Remarcar consulta");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");

            op = lerInt();

            switch(op) {

                case 1:
                    System.out.println("\n--- Agendar Consulta ---\n");
                    System.out.print("Digite o CPF do paciente: ");
                    String cpf = sc.nextLine();
                    Paciente pacienteEncontrado = clinica.pacienteBusca(cpf);

                    if(pacienteEncontrado == null){
                        break; 
                    }
                    if(pacienteEncontrado.ativo == false){
                        System.out.print("\nO cpf esta desativado.\n");
                        break;
                    }

                    System.out.print("Digite a data da consulta (DD/MM/AAAA): ");
                    String data = sc.nextLine();
                    System.out.print("Digite o horario da consulta (ex: 09:00): ");
                    String hora = sc.nextLine();
                    System.out.print("Sabe o nome do profissional? (S/N): ");
                    String sabeNome = sc.nextLine();
                    Profissionais profissionalEncontrado = null;

                    if(sabeNome.equals("N")){
                        System.out.print("Digite a especialidade necessária (ex: Psicologia): ");
                        String esp = sc.nextLine();
                        
                        profissionalEncontrado = clinica.profissionaisBuscaEsp(esp, data, hora);
                        
                        if(profissionalEncontrado == null){
                            System.out.println("Nenhum profissional desta especialidade livre neste horário!");
                            break;
                        }
                        System.out.println("Profissional disponível encontrado: " + profissionalEncontrado.nome);
                        
                    }else{
                        System.out.print("Digite o nome exato do profissional: ");
                        String nome = sc.nextLine();
                        
                        profissionalEncontrado = clinica.profissionaisBusca(nome, data, hora);
                        
                        if(profissionalEncontrado == null){
                            System.out.println("Profissional não encontrado no sistema.");
                            break;
                        }

                        if(clinica.profissionalOcupado(profissionalEncontrado, data, hora)){
                            System.out.println("Aviso: " + profissionalEncontrado.nome + " já está ocupado às " + hora);
                            
                            String sugestao = clinica.horarioOcupado(profissionalEncontrado, data);
                            if(sugestao != null){
                                System.out.print("Deseja agendar para o horário sugerido de às " + sugestao + "? (S/N): ");
                                String aceitou = sc.nextLine();
                                if(aceitou.equals("S")){
                                    hora = sugestao;
                                }else{
                                    System.out.println("Agendamento cancelado.");
                                    break;
                                }
                            }else{
                                break;
                            }
                        }
                    }

                    System.out.print("Digite o tipo da consulta (Retorno/Avaliação/Deixe em branco para Inicial): ");
                    String tipo = sc.nextLine();

                    int np = Clinica.totalConsultas;

                    if(tipo.trim().isEmpty()){
                        clinica.consultas[np] = consulta.agendar(pacienteEncontrado, profissionalEncontrado, data, hora);
                    }else{
                        clinica.consultas[np] = consulta.agendar(pacienteEncontrado, profissionalEncontrado, data, hora, tipo);
                    }

                    System.out.println("Consulta confirmada e agendada com sucesso!");
                    break;

                case 2:
                    System.out.println("--- Cancelar consulta ---");
                    break;

                case 3:
                    System.out.println("--- Remarcar consulta ---");
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
                    System.out.println("--- Registrar pagamento ---");
                    break;

                case 2:
                    System.out.println("--- Listar pagamentos ---");
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
