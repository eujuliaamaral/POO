import java.util.Scanner;

public class Main {

    public static Scanner sc = new Scanner(System.in);
    public static Clinica clinica = new Clinica();
    public static Consultas consulta = new Consultas();
    public static Paciente paciente = new Paciente();
    public static Profissionais profissional = new Profissionais();
    public static Pagamentos pagamento = new Pagamentos();
    public static Relatorios relatorios = new Relatorios(clinica);
    
    public static void main(String[] args) {

        int opcao;

        do {

            System.out.println("\nClinica de Consultas");
            System.out.println("Escolha uma opcao digitando o numero correspondente:");
            System.out.println("1 - Pacientes");
            System.out.println("2 - Profissionais");
            System.out.println("3 - Consultas");
            System.out.println("4 - Pagamentos");
            System.out.println("5 - Relatórios");
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

                case 5:
                    menuRelatorios();
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
            System.out.println("6 - Listar pacientes inativos");
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
                    for(int i = 0; i < Clinica.totalPacientes; i++){
                        if(clinica.pacientes[i] != null && clinica.pacientes[i].cpf.equals(cpf)){
                            System.out.println("\nCadastro já foi feito!");
                            pacienteDuplicado = true;
                            break;
                        }
                    }
                    if(pacienteDuplicado){
                        break;
                    }
                    
                    System.out.print("Digite a idade do paciente (ou deixe em branco para cadastro rápido):");
                    String idadeTexto = sc.nextLine();
                    
                    int np = Clinica.totalPacientes;
                    
                    if(idadeTexto.trim().isEmpty()){
                        clinica.pacientes[np] = paciente.cadastro(nome, cpf);
                        clinica.pacientes[np].telefone = "Não informado";
                        clinica.pacientes[np].convenio = false;
                        clinica.pacientes[np].tipoconvenio = "Não possui";
                        
                        System.out.println("\nCadastro rápido realizado com sucesso!");
                    }else{
                        System.out.print("Digite o telefone do paciente:");
                        String telefone = sc.nextLine();
                        
                        System.out.print("O paciente possui convenio (S/N):");
                        String temConvenioStr = sc.nextLine();
                        
                        boolean convenio = temConvenioStr.equalsIgnoreCase("S");
                        String tipoconvenio;
                        
                        if (convenio){
                            System.out.print("Digite o convenio do paciente:");
                            tipoconvenio = sc.nextLine();
                        }else{
                            tipoconvenio = "Não possui";
                        }
                        
                        int idade = Integer.parseInt(idadeTexto.trim());
                        clinica.pacientes[np] = paciente.cadastro(nome, cpf, idade, telefone, convenio, tipoconvenio);
                        
                        System.out.println("\nCadastro completo realizado com sucesso!");
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
                            System.out.println("\nCadastro atualizado com sucesso!");
                            
                        }else if (opcaoAtualizar == 2){
                            paciente.cadastroComplementarCompleto(pacienteEncontrado);
                            System.out.println("\nCadastro completado com sucesso!");
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

                case 6:
                    System.out.println("\n--- Listar Pacientes inativos ---\n");
                    clinica.listarPacientesInativos();

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
                    System.out.printf("Digite a especialidade(clinica geral, fisioterapia, psicologia e nutricao):");
                    String especialidade = sc.nextLine();

                    if (!especialidade.equals("clinica geral") && 
                        !especialidade.equals("fisioterapia") &&
                        !especialidade.equals("psicologia") && 
                        !especialidade.equals("nutricao")) {
                        
                        System.out.println("Especialidade inválida!");
                        break;
                    }
                    System.out.printf("Digite o registro profissional (ou deixe em branco para cadastro rápido):");
                    String registroPro = sc.nextLine();

                    int np = Clinica.totalProfissionais;

                    if(registroPro.isEmpty()){
                        clinica.profissionais[np] = profissional.cadastro(nome, especialidade);
                        
                        clinica.profissionais[np].registroPro = "Não informado";
                        clinica.profissionais[np].valorConsulta = 0.0;
                        clinica.profissionais[np].diasAtendimento = new String[0];
                        
                        System.out.println("\nCadastro rápido de profissional realizado com sucesso!");
                    }else{

                        boolean profissionalDuplicado = false;
                        for(int i = 0; i < Clinica.totalProfissionais; i++){
                            if(clinica.profissionais[i] != null && 
                               clinica.profissionais[i].registroPro != null && 
                               clinica.profissionais[i].registroPro.equals(registroPro)){
                                System.out.println("\nCadastro já foi feito com este registro profissional!");
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

                        clinica.profissionais[np] = profissional.cadastro(nome, especialidade, registroPro, valorConsulta, diasAtendimento);
                        System.out.println("\nCadastro completo de profissional realizado com sucesso!");
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
                            System.out.println("\nCadastro atualizado com sucesso!");
                            
                        }else if (opcaoAtualizar == 2){
                            profissional.cadastroComplementarCompleto(profissionalEncontrado);
                            System.out.println("\nCadastro completado com sucesso!");
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
            System.out.println("4 - Registrar atendimento");
            System.out.println("5 - Listar todas as consultas");
            System.out.println("6 - Buscar consultas por CPF");
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
                        System.out.println("Paciente não encontrado.");
                        break; 
                    }
                    if(pacienteEncontrado.ativo == false){
                        System.out.println("Este paciente está desativado e não pode agendar consultas.");
                        break;
                    }
                    if(pacienteEncontrado.telefone.equals("Não informado")){
                        System.out.println("\nEste paciente está com o cadastro incompleto e não pode agendar consultas.");
                        break;
                    }

                    System.out.print("Digite a data da consulta (DD/MM/AAAA): ");
                    String data = sc.nextLine();
                    System.out.print("Digite o horario da consulta (ex: 09:00): ");
                    String hora = sc.nextLine();

                    String[] partesHora = hora.split(":");
                    int horaInicial = Integer.parseInt(partesHora[0]);
                    int horaFinal =  Integer.parseInt(partesHora[1]);

                    if(horaFinal < 0 || horaFinal > 59 || horaInicial < 8 || horaInicial > 18){
                        System.out.print("\nHorário inválido!\n");
                        break;
                    }
                    if(horaInicial == 18 && horaFinal > 60){
                        System.out.print("\nHorário inválido!\n");
                        break;
                    }

                    System.out.print("Sabe o nome do profissional? (S/N): ");
                    String sabeNome = sc.nextLine();
                    Profissionais profissionalEncontrado = null;

                    if(sabeNome.trim().equalsIgnoreCase("N")){
                        System.out.print("Digite a especialidade necessária (ex: Psicologia): ");
                        String esp = sc.nextLine();
                        
                        profissionalEncontrado = clinica.profissionaisBuscaEsp(esp, data, hora);
                        
                        if(profissionalEncontrado == null){
                            
                            for(int i = 0; i < Clinica.totalProfissionais; i++) {
                                if(clinica.profissionais[i].especialidade.trim().equalsIgnoreCase(esp.trim())) {
                                    profissionalEncontrado = clinica.profissionais[i];
                                    break;
                                }
                            }
                            
                            if(profissionalEncontrado == null) {
                                System.out.println("\nNenhum profissional cadastrado para esta especialidade!");
                                break;
                            }
                            
                            System.out.println("\nO profissional de " + esp + " (" + profissionalEncontrado.nome + ") já está ocupado às " + hora);
                            
                            String sugestao = clinica.horarioOcupado(profissionalEncontrado, data, hora);
                            if(sugestao != null){
                                System.out.print("Deseja agendar com " + profissionalEncontrado.nome + " para o horário sugerido de às " + sugestao + "? (S/N): ");
                                String aceitou = sc.nextLine();
                                if(aceitou.trim().equalsIgnoreCase("S")){
                                    hora = sugestao;
                                } else {
                                    System.out.println("\nAgendamento cancelado.");
                                    break;
                                }
                            } else {
                                System.out.println("\nNão há outros horários disponíveis para este profissional hoje.");
                                break;
                            }
                        
                        } else {
                            System.out.println("Profissional disponível encontrado: " + profissionalEncontrado.nome);
                        }
                        
                    }else{
                        System.out.print("Digite o nome exato do profissional: ");
                        String nome = sc.nextLine();
                        
                        profissionalEncontrado = clinica.profissionaisBusca(nome, data, hora);
                        
                        if(profissionalEncontrado == null){
                            System.out.println("\nProfissional não encontrado no sistema.");
                            break;
                        }

                        if(clinica.profissionalOcupado(profissionalEncontrado, data, hora)){
                            System.out.println("Aviso: " + profissionalEncontrado.nome + " já está ocupado às " + hora);
                            
                            String sugestao = clinica.horarioOcupado(profissionalEncontrado, data, hora);
                            if(sugestao != null){
                                System.out.print("Deseja agendar para o horário sugerido de às " + sugestao + "? (S/N): ");
                                String aceitou = sc.nextLine();
                                if(aceitou.equals("S")){
                                    hora = sugestao;
                                }else{
                                    System.out.println("\nAgendamento cancelado.");
                                    break;
                                }
                            }else{
                                break;
                            }
                        }
                    }

                    if(!clinica.validarProfissionalComValor(profissionalEncontrado)){
                        System.out.println("\nEste profissional não tem valor de consulta definido. Consulte a administração.");
                        break;
                    }

                    if(!clinica.validarProfissionalNodia(profissionalEncontrado, data)){
                        System.out.println("\nEste profissional não atende no dia solicitado.");
                        break;
                    }

                    System.out.print("Digite o tipo da consulta (Retorno/Avaliação/Deixe em branco para Inicial): ");
                    String tipo = sc.nextLine();

                    int np = Clinica.totalConsultas;

                    if(tipo.trim().isEmpty()){
                        clinica.consultas[np] = consulta.agendar(pacienteEncontrado, profissionalEncontrado, data, hora);
                    }else{
                        clinica.consultas[np] = consulta.agendar(pacienteEncontrado, profissionalEncontrado, data, hora, tipo);
                    }

                    System.out.println("\nFirmada e agendada com sucesso!");
                    break;

                case 2:
                    System.out.println("\n--- Cancelar consulta ---\n");
                    System.out.print("Digite o CPF do paciente: ");
                    String cpfCancelamento = sc.nextLine();
                    System.out.print("Digite a data da consulta (DD/MM/AAAA): ");
                    String dataCancelamento = sc.nextLine();
                    System.out.print("Digite o horario da consulta (ex: 09:00): ");
                    String horarioCancelamento = sc.nextLine();
                    
                    Consultas consultaCancelamento = clinica.consultaBusca(cpfCancelamento, dataCancelamento, horarioCancelamento);
                    
                    if(consultaCancelamento == null){
                        System.out.println("Consulta não encontrada.");
                        break;
                    }
                    
                    if(!consultaCancelamento.status.equals("agendada")){
                        System.out.println("\nNão é possível cancelar uma consulta que já foi " + consultaCancelamento.status + ".");
                        break;
                    }
                    
                    System.out.print("Deseja informar uma justificativa? (S/N): ");
                    String temJustificativa = sc.nextLine();
                    String justificativa = "";
                    
                    if(temJustificativa.trim().equalsIgnoreCase("S")){
                        System.out.print("Digite a justificativa: ");
                        justificativa = sc.nextLine();
                    }
                    
                    System.out.print("Digite a hora atual (HH:MM) para calcular multa: ");
                    String horaAtual = sc.nextLine();
                    
                    double multaCancelamento = clinica.calcularMulta(consultaCancelamento.horario, dataCancelamento, horaAtual);
                    if (!justificativa.trim().isEmpty()) {
                        multaCancelamento = 0.0;
                        System.out.println("\nJustificativa apresentada. Multa ISENTA pelo sistema!");
                    }
                    
                    if(multaCancelamento > 0){
                        System.out.println("Multa aplicada: R$ " + multaCancelamento);
                    } else if (justificativa.trim().isEmpty()) { 
                        System.out.println("\nSem multa (cancelamento com mais de 2 horas de antecedência).");
                    }
                    
                    consultaCancelamento.cancelar(multaCancelamento, justificativa);
                    System.out.println("\nConsulta cancelada com sucesso!");
                    break;

                case 3:
                    System.out.println("\n--- Remarcar consulta ---\n");
                    System.out.print("Digite o CPF do paciente: ");
                    String cpfRemarcar = sc.nextLine();
                    System.out.print("Digite a data atual da consulta (DD/MM/AAAA): ");
                    String dataAtualRemarcar = sc.nextLine();
                    System.out.print("Digite o horario atual da consulta (ex: 09:00): ");
                    String horarioAtualRemarcar = sc.nextLine();
                    
                    Consultas consultaRemarcar = clinica.consultaBuscaParaRemarcar(cpfRemarcar, dataAtualRemarcar, horarioAtualRemarcar);
                    
                    if(consultaRemarcar == null){
                        System.out.println("\nConsulta não encontrada ou não está agendada.");
                        break;
                    }
                    
                    System.out.print("Digite a nova data (DD/MM/AAAA) ou deixe em branco para manter: ");
                    String novaData = sc.nextLine();
                    System.out.print("Digite o novo horario (ex: 09:00) ou deixe em branco para manter: ");
                    String novoHorario = sc.nextLine();
                    
                    if(novaData.trim().isEmpty()){
                        novaData = dataAtualRemarcar;
                    }
                    if(novoHorario.trim().isEmpty()){
                        novoHorario = horarioAtualRemarcar;
                    }
                    
                    if (!clinica.validarProfissionalNodia(consultaRemarcar.profissional, novaData)) {
                        System.out.println("\nO profissional " + consultaRemarcar.profissional.nome + " não atende no dia da semana desta nova data.");
                        break;
                    }
                    
                    boolean mudouHorario = !novaData.equals(dataAtualRemarcar) || !novoHorario.equals(horarioAtualRemarcar);
                    
                    if (mudouHorario && clinica.profissionalOcupado(consultaRemarcar.profissional, novaData, novoHorario)) {
                        System.out.println("\nHorário indisponível para a nova data/hora.");
                        break;
                    }
                    
                    consultaRemarcar.remarcar();
                    
                    int novaConsultaIndex = Clinica.totalConsultas;
                    Consultas novaConsulta = new Consultas(consultaRemarcar.paciente, consultaRemarcar.profissional, novaData, novoHorario, consultaRemarcar.tipo);
                    
                    clinica.consultas[novaConsultaIndex] = novaConsulta;
                    Clinica.totalConsultas++;
                    
                    System.out.println("\nConsulta remarcada com sucesso!");
                    System.out.println("Nova data: " + novaData);
                    System.out.println("Novo horário: " + novoHorario);
                    break;

                case 4:
                    System.out.println("\n--- Registrar atendimento ---\n");
                    System.out.print("Digite o CPF do paciente: ");
                    String cpfAtendimento = sc.nextLine();
                    System.out.print("Digite a data da consulta (DD/MM/AAAA): ");
                    String dataAtendimento = sc.nextLine();
                    System.out.print("Digite o horario da consulta (ex: 09:00): ");
                    String horarioAtendimento = sc.nextLine();
                    
                    Consultas consultaAtendimento = clinica.consultaBuscaParaAtendimento(cpfAtendimento, dataAtendimento, horarioAtendimento);
                    
                    if(consultaAtendimento == null){
                        System.out.println("Consulta não encontrada ou não está agendada.");
                        break;
                    }
                    
                    System.out.print("Digite as observacoes do atendimento: ");
                    String observacoes = sc.nextLine();
                    
                    System.out.print("Deseja registrar diagnóstico? (S/N): ");
                    String temDiagnostico = sc.nextLine();
                    String diagnostico = "";
                    
                    if(temDiagnostico.equals("S") || temDiagnostico.equals("s")){
                        System.out.print("Digite o diagnóstico: ");
                        diagnostico = sc.nextLine();
                    }
                    
                    Atendimento atendimento = null;
                    
                    if(diagnostico.isEmpty()){
                        atendimento = new Atendimento(observacoes);
                    }else{
                        atendimento = new Atendimento(observacoes, diagnostico);
                    }
                    
                    System.out.print("Deseja registrar procedimentos? (S/N): ");
                    String temProcedimentos = sc.nextLine();
                    
                    if(temProcedimentos.equals("S") || temProcedimentos.equals("s")){
                        System.out.print("Registrar um procedimento por vez (1) ou vários de uma vez (2)? ");
                        int opcaoProcedimentos = sc.nextInt();
                        sc.nextLine();
                        
                        if(opcaoProcedimentos == 1){
                            String procedimento = "";
                            while(!procedimento.equals("sair")){
                                System.out.print("Digite o procedimento (ou deixe em branco para finalizar): ");
                                procedimento = sc.nextLine();
                                if(!procedimento.equals(null)){
                                    atendimento.adicionarProcedimento(procedimento);
                                }
                            }
                        }else if(opcaoProcedimentos == 2){
                            System.out.print("Digite a quantidade de procedimentos: ");
                            int qtdProc = sc.nextInt();
                            sc.nextLine();
                            
                            String[] procs = new String[qtdProc];
                            for(int i = 0; i < qtdProc; i++){
                                System.out.print("Digite o procedimento " + (i + 1) + ": ");
                                procs[i] = sc.nextLine();
                            }
                            atendimento.adicionarMultiplosProcedimentos(procs);
                        }
                    }
                    
                    consultaAtendimento.registrarAtendimento(atendimento);
                    atendimento.exibirResumo();
                    System.out.println("\nAtendimento registrado com sucesso!");
                    break;

                case 5:
                    System.out.println("\n--- liistar todas as consultas ---\n");
                    clinica.listarConsultas();
                    break;

                case 6:
                    System.out.println("\n--- buscar consultas por CPF ---\n");
                    System.out.print("Digite o CPF do paciente: ");
                    String cpfBuscaConsulta = sc.nextLine();
                    clinica.listarConsultas(cpfBuscaConsulta);
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

            System.out.println("\nMenu Pagamentos");
            System.out.println("1 - Registrar pagamento");
            System.out.println("2 - Listar pagamentos");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");

            op = lerInt();

            switch(op) {

                case 1:
                    System.out.println("\n--- Registrar pagamento ---\n");
                    System.out.print("Digite o CPF do paciente: ");
                    String cpfPagamento = sc.nextLine();
                    System.out.print("Digite a data da consulta (DD/MM/AAAA): ");
                    String dataPagamento = sc.nextLine();
                    System.out.print("Digite o horario da consulta (ex: 09:00): ");
                    String horarioPagamento = sc.nextLine();
                    
                    Consultas consultaPagamento = clinica.consultaBusca(cpfPagamento, dataPagamento, horarioPagamento);
                    
                    if(consultaPagamento == null){
                        System.out.println("\nConsulta não encontrada.");
                        break;
                    }
                    
                    if(!consultaPagamento.status.equals("realizada")){
                        System.out.println("\nApenas consultas realizadas podem ser pagas.");
                        break;
                    }
                    
                    System.out.print("Digite o método de pagamento (dinheiro/cartao/convenio): ");
                    String metodo = sc.nextLine();
                    
                    double valorBaseConsulta = consultaPagamento.profissional.valorConsulta;
                    int npag = Clinica.totalPagamentos;
                    Pagamentos pagamentoNovo = new Pagamentos(consultaPagamento, valorBaseConsulta, metodo);
                    
                    System.out.print("Deseja usar cálculo automático? (S/N): ");
                    String automatico = sc.nextLine();
                    
                    if(automatico.trim().equalsIgnoreCase("S")){
                        double desconto = 0.0;
                        double convenio = 0.0;
                        
                        if(consultaPagamento.tipo.trim().equalsIgnoreCase("retorno")){
                            desconto = 20.0;
                        }
                        
                        if(consultaPagamento.paciente.convenio){
                            convenio = 40.0;
                        }
                        
                        pagamentoNovo.calcularComDescontoConvenioEMulta(desconto, convenio, consultaPagamento.multa);
                        
                    } else {
                        System.out.print("Digite o valor final a pagar: R$ ");
                        double valorManual = sc.nextDouble();
                        sc.nextLine();
                        pagamentoNovo.valorFinal = valorManual;
                        pagamentoNovo.valorParcela = valorManual;
                    }
                    
                    if(metodo.trim().equalsIgnoreCase("cartao") || metodo.trim().equalsIgnoreCase("cartão")){
                        System.out.print("Digite o número de parcelas (1-3): ");
                        int parcelas = sc.nextInt();
                        sc.nextLine();
                        pagamentoNovo.parcelar(parcelas);
                    }
                    
                    System.out.print("Digite a data do pagamento (DD/MM/AAAA): ");
                    String dataEfetuado = sc.nextLine();
                    pagamentoNovo.efetuarPagamento(dataEfetuado);
                    
                    clinica.pagamentos[npag] = pagamentoNovo;
                    Clinica.totalPagamentos++;
                    pagamentoNovo.exibirResumo();
                    System.out.println("\nPagamento registrado com sucesso!");
                    break;

                case 2:
                    System.out.println("\n--- Listar pagamentos ---\n");
                    clinica.listarPagamentos();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }

        } while(op != 0);
    }

    public static void menuRelatorios(){
        int op;

        do {

            System.out.println("\nMenu relatórios");
            System.out.println("1 - Relatório Geral de Consultas");
            System.out.println("2 - Relatório por Profissional");
            System.out.println("3 - Relatório por Período");
            System.out.println("4 - Resumo Financeiro");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");

            op = lerInt();

            switch(op) {

                case 1:
                    System.out.println("\n--- Gerar relatório geral de consultas ---\n");
                    relatorios.gerarRelatorioGeral();
                    break;

                case 2:
                    System.out.println("\n--- Gerar relatório por profissional ---\n");
                    System.out.print("Digite o nome do profissional: ");
                    String nomeProfissional = sc.nextLine();
                    relatorios.gerarRelatorioPorProfissional(nomeProfissional);
                    break;

                case 3:
                    System.out.println("\n--- Gerar relatório por período ---\n");
                    System.out.print("Digite a data inicial (DD/MM/AAAA): ");
                    String dataInicio = sc.nextLine();
                    System.out.print("Digite a data final (DD/MM/AAAA): ");
                    String dataFim = sc.nextLine();
                    relatorios.gerarRelatorioPorPeriodo(dataInicio, dataFim);
                    break;

                case 4:
                    System.out.println("\n--- Gerar resumo financeiro ---\n");
                    relatorios.gerarResumoFinanceiro();
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
