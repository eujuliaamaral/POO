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

                    if(!clinica.validarProfissionalComValor(profissionalEncontrado)){
                        System.out.println("Este profissional não tem valor de consulta definido. Consulte a administração.");
                        break;
                    }

                    if(!clinica.validarProfissionalNodia(profissionalEncontrado, data)){
                        System.out.println("Este profissional não atende no dia solicitado.");
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

                    System.out.println("Consulta confirmada e agendada com sucesso!");
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
                        System.out.println("Não é possível cancelar uma consulta que já foi " + consultaCancelamento.status + ".");
                        break;
                    }
                    
                    System.out.print("Deseja informar uma justificativa? (S/N): ");
                    String temJustificativa = sc.nextLine();
                    String justificativa = "";
                    
                    if(temJustificativa.equals("S") || temJustificativa.equals("s")){
                        System.out.print("Digite a justificativa: ");
                        justificativa = sc.nextLine();
                    }
                    
                    System.out.print("Digite a hora atual (HH:MM) para calcular multa: ");
                    String horaAtual = sc.nextLine();
                    
                    double multaCancelamento = clinica.calcularMulta(consultaCancelamento.horario, dataCancelamento, horaAtual);
                    
                    if(multaCancelamento > 0){
                        System.out.println("Multa aplicada: R$ " + multaCancelamento);
                    }else{
                        System.out.println("Sem multa (cancelamento com mais de 2 horas de antecedência).");
                    }
                    
                    consultaCancelamento.cancelar(multaCancelamento, justificativa);
                    System.out.println("Consulta cancelada com sucesso!");
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
                        System.out.println("Consulta não encontrada ou não está agendada.");
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
                    
                    if(clinica.profissionalOcupado(consultaRemarcar.profissional, novaData, novoHorario)){
                        System.out.println("Horário indisponível para a nova data/hora.");
                        break;
                    }
                    
                    consultaRemarcar.remarcar();
                    
                    int novaConsultaIndex = Clinica.totalConsultas;
                    Consultas novaConsulta = new Consultas(consultaRemarcar.paciente, consultaRemarcar.profissional, novaData, novoHorario, consultaRemarcar.tipo);
                    clinica.consultas[novaConsultaIndex] = novaConsulta;
                    
                    System.out.println("Consulta remarcada com sucesso!");
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
                                System.out.print("Digite o procedimento (ou 'sair' para finalizar): ");
                                procedimento = sc.nextLine();
                                if(!procedimento.equals("sair")){
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
                    System.out.println("Atendimento registrado com sucesso!");
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
                        System.out.println("Consulta não encontrada.");
                        break;
                    }
                    
                    if(!consultaPagamento.status.equals("realizada")){
                        System.out.println("Apenas consultas realizadas podem ser pagas.");
                        break;
                    }
                    
                    System.out.print("Deseja usar cálculo automático? (S/N): ");
                    String automatico = sc.nextLine();
                    
                    double valorFinal = consultaPagamento.profissional.valorConsulta;
                    
                    if(automatico.equals("S") || automatico.equals("s")){
                        double desconto = 0.0;
                        double convenio = 0.0;
                        
                        if(consultaPagamento.tipo.equals("retorno") || consultaPagamento.tipo.equals("Retorno")){
                            desconto = 20.0;
                        }
                        
                        if(consultaPagamento.paciente.convenio){
                            convenio = 40.0;
                        }
                        
                        if(desconto > 0 && convenio > 0){
                            valorFinal = consultaPagamento.profissional.valorConsulta;
                            double descontoValor = valorFinal * (desconto / 100.0);
                            double convenioValor = consultaPagamento.profissional.valorConsulta * (convenio / 100.0);
                            valorFinal = valorFinal - descontoValor - convenioValor;
                        }else if(desconto > 0){
                            valorFinal = consultaPagamento.profissional.valorConsulta * (1 - desconto / 100.0);
                        }else if(convenio > 0){
                            valorFinal = consultaPagamento.profissional.valorConsulta * (1 - convenio / 100.0);
                        }
                        
                        if(consultaPagamento.multa > 0){
                            valorFinal = valorFinal + consultaPagamento.multa;
                        }
                        
                        if(valorFinal < 0){
                            valorFinal = 0.0;
                        }
                    }else{
                        System.out.print("Digite o valor a pagar: R$ ");
                        valorFinal = sc.nextDouble();
                        sc.nextLine();
                    }
                    
                    System.out.print("Digite o método de pagamento (dinheiro/cartao/convenio): ");
                    String metodo = sc.nextLine();
                    
                    int npag = Clinica.totalPagamentos;
                    Pagamentos pagamentoNovo = new Pagamentos(consultaPagamento, valorFinal, metodo);
                    
                    if(metodo.equals("cartao") || metodo.equals("cartão")){
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
                    System.out.println("Pagamento registrado com sucesso!");
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

            System.out.println("\n========== MENU RELATÓRIOS ==========");
            System.out.println("1 - Relatório Geral de Consultas");
            System.out.println("2 - Relatório por Profissional");
            System.out.println("3 - Relatório por Período");
            System.out.println("4 - Resumo Financeiro");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");

            op = lerInt();

            switch(op) {

                case 1:
                    relatorios.gerarRelatorioGeral();
                    break;

                case 2:
                    System.out.print("Digite o nome do profissional: ");
                    String nomeProfissional = sc.nextLine();
                    relatorios.gerarRelatorioPorProfissional(nomeProfissional);
                    break;

                case 3:
                    System.out.print("Digite a data inicial (DD/MM/AAAA): ");
                    String dataInicio = sc.nextLine();
                    System.out.print("Digite a data final (DD/MM/AAAA): ");
                    String dataFim = sc.nextLine();
                    relatorios.gerarRelatorioPorPeriodo(dataInicio, dataFim);
                    break;

                case 4:
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
