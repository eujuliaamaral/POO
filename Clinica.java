public class Clinica {
    Paciente[] pacientes = new Paciente[100];
    Profissionais[] profissionais = new Profissionais[100];
    Consultas[] consultas = new Consultas[200];
    Pagamentos[] pagamentos = new Pagamentos[200];

    public static int totalPacientes = 0;
    public static int totalProfissionais = 0;
    public static int totalConsultas = 0;
    public static int totalPagamentos = 0;

    public Paciente pacienteBusca(String cpf){
        for(int i = 0; i < totalPacientes; i++){
            if(pacientes[i].cpf.equals(cpf)){
                return pacientes[i];
            }
        }
        System.out.printf("\nCpf não encontrado\n");
        return null;
    }

    public Profissionais profissionaisBusca(String nome){
        for(int i = 0; i < totalProfissionais; i++){
            if(profissionais[i].nome.equals(nome)){
                return profissionais[i];
            }
        }
        System.out.printf("\nProfissional não encontrado\n");
        return null;
    }

    public Profissionais profissionaisBusca(String nome, String data, String horario){
        for(int i = 0; i < totalProfissionais; i++){
            if(profissionais[i].nome.equals(nome)){
                if(profissionais[i].nome.trim().equalsIgnoreCase(nome.trim())){
                    return profissionais[i];
                }
            }
        }
        return null;
    }

    public Profissionais profissionaisBuscaEsp(String esp, String data, String horario){
        for(int i = 0; i < totalProfissionais; i++){
            if(profissionais[i].especialidade.equals(esp)){
                if(!profissionalOcupado(profissionais[i], data, horario)){
                    return profissionais[i];
                }
            }
        }
        return null;
    }

    public boolean profissionalOcupado(Profissionais profissionalre, String datare, String horariore){
        for(int i = 0; i < totalConsultas; i++){
            if(consultas[i].profissional.registroPro.equals(profissionalre.registroPro) && consultas[i].data.equals(datare) && consultas[i].horario.equals(horariore)){
                return true;
            }
        }
        return false;
    }

    public String horarioOcupado(Profissionais profissional, String data, String horario){
            
        String[] partes = horario.split(":");
        int horaInicial = Integer.parseInt(partes[0]);
        
        for(int i = horaInicial + 1; i < 18; i++){
            String horariofinal;

            if(i < 10){
                horariofinal = "0" + i + ":00";
            }else{
                horariofinal = i + ":00";
            }

            if(!profissionalOcupado(profissional, data, horariofinal)){
                return horariofinal;
            }
        }
        System.out.printf("\nHorario indisponível\n");
        return null;
    }

    public void listarConsultas(){
        if(totalConsultas == 0){
            System.out.printf("Nenhuma consulta agendada.\n");
            return;
        }
        for(int i = 0; i < totalConsultas; i++){
            System.out.printf("\n--- Consulta %d ---\n", i + 1);
            System.out.printf("Paciente: " + consultas[i].paciente.nome + "\n");
            System.out.printf("CPF: " + consultas[i].paciente.cpf + "\n");
            System.out.printf("Profissional: " + consultas[i].profissional.nome + "\n");
            System.out.printf("Data: " + consultas[i].data + "\n");
            System.out.printf("Horario: " + consultas[i].horario + "\n");
            System.out.printf("Tipo: " + consultas[i].tipo + "\n");
            System.out.printf("Status: " + consultas[i].status + "\n");
            if(consultas[i].atendimento != null && !consultas[i].atendimento.diagnostico.isEmpty()){
                System.out.printf("Diagnostico: " + consultas[i].atendimento.diagnostico + "\n");
            }
        }
    }

    public void listarConsultas(String cpfP){
        boolean achou = false;
        for(int i = 0; i < totalConsultas; i++){
            if(consultas[i].paciente.cpf.equals(cpfP)){
                System.out.printf("\n--- Consulta ---\n");
                System.out.printf("Paciente: " + consultas[i].paciente.nome + "\n");
                System.out.printf("Profissional: " + consultas[i].profissional.nome + "\n");
                System.out.printf("Data: " + consultas[i].data + "\n");
                System.out.printf("Horario: " + consultas[i].horario + "\n");
                System.out.printf("Tipo: " + consultas[i].tipo + "\n");
                System.out.printf("Status: " + consultas[i].status + "\n");
                if(consultas[i].atendimento != null && !consultas[i].atendimento.diagnostico.isEmpty()){
                    System.out.printf("Diagnostico: " + consultas[i].atendimento.diagnostico + "\n");
                }
                achou = true;
            }
        }
        if(!achou){
            System.out.printf("\nNenhuma consulta encontrada para este CPF.\n");
        }
    }

    public void listarPacientes(){
        if(Clinica.totalPacientes == 0){
            System.out.printf("Nenhum paciente cadastrado.\n");
        }else{
            for(int i = 0; i < Clinica.totalPacientes; i++){
                System.out.printf("\n\nPaciente: " + pacientes[i].nome + "\nCpf: " + pacientes[i].cpf + "\n");
            }
        }
    }

    public void listarPacientes(String cpfP){
        boolean achou = false;
        for(int i = 0; i < Clinica.totalPacientes; i++){
            if(pacientes[i].cpf.equals(cpfP)){
            System.out.printf("\n\nPaciente: " + pacientes[i].nome + "\nCpf: " + pacientes[i].cpf +
            "\nIdade: " + pacientes[i].idade + "\nTelefone: " + pacientes[i].telefone + "\nTipo de Convenio: " + pacientes[i].tipoconvenio + "\n");
                achou = true;
            }
        }
        if(!achou){
        System.out.printf("\nNenhum paciente encontrado.\n");
        }
    }

    public void listarProfissionais(){
        if(Clinica.totalProfissionais == 0){
            System.out.printf("Nenhum profissional cadastrado.\n");
        }else{
            for(int i = 0; i < Clinica.totalProfissionais; i++){
                String diasFormatados = String.join(", ", profissionais[i].diasAtendimento);
                System.out.printf("\n\nNome: " + profissionais[i].nome + "\nEspecialidade: " + profissionais[i].especialidade + "\nRegistro Profissional: " + profissionais[i].registroPro + 
                "\nValor da Consulta:" + profissionais[i].valorConsulta + "\nDias de Atendimento:" + diasFormatados +"\n");
            }
        }
    }

    public void listarProfissionais(String especialidade){
        boolean achou = false;
        for(int i = 0; i < Clinica.totalProfissionais; i++){
            if(profissionais[i].especialidade.equals(especialidade)){
            System.out.printf("\n\nNome: " + profissionais[i].nome + "\nEspecialidade: " + profissionais[i].especialidade + "\nRegistro Profissional: " + profissionais[i].registroPro + "\n");
                achou = true;
            }
        }
        if(!achou){
        System.out.printf("\nNenhum profissional encontrado.\n");
        }
    }

    public Consultas consultaBusca(String cpf, String data, String horario){
        for(int i = 0; i < totalConsultas; i++){
            if(consultas[i].paciente.cpf.equals(cpf) && consultas[i].data.equals(data) && consultas[i].horario.equals(horario)){
                return consultas[i];
            }
        }
        return null;
    }

    public double calcularMulta(String horarioConsulta, String dataCancelamento, String horarioCancelamento){
        int horaConsulta = Integer.parseInt(horarioConsulta.substring(0, 2));
        int minConsulta = Integer.parseInt(horarioConsulta.substring(3, 5));
        int horaCancelamento = Integer.parseInt(horarioCancelamento.substring(0, 2));
        int minCancelamento = Integer.parseInt(horarioCancelamento.substring(3, 5));
        
        int minutosAte = (horaConsulta * 60 + minConsulta) - (horaCancelamento * 60 + minCancelamento);
        
        if(minutosAte < 120){
            return 50.0;
        }
        return 0.0;
    }

    public Consultas consultaBuscaParaRemarcar(String cpf, String data, String horario){
        for(int i = 0; i < totalConsultas; i++){
            if(consultas[i].paciente.cpf.equals(cpf) && consultas[i].data.equals(data) && consultas[i].horario.equals(horario)){
                if(consultas[i].status.equals("agendada")){
                    return consultas[i];
                }
            }
        }
        return null;
    }

    public Consultas consultaBuscaParaAtendimento(String cpf, String data, String horario){
        for(int i = 0; i < totalConsultas; i++){
            if(consultas[i].paciente.cpf.equals(cpf) && consultas[i].data.equals(data) && consultas[i].horario.equals(horario)){
                if(consultas[i].status.equals("agendada")){
                    return consultas[i];
                }
            }
        }
        return null;
    }

    public void listarPagamentos(){
        if(Clinica.totalPagamentos == 0){
            System.out.printf("nenhum pagamento registrado.\n");
        }else{
            for(int i = 0; i < Clinica.totalPagamentos; i++){
                System.out.printf("\n--- Pagamento %d ---\n", i + 1);
                System.out.printf("paciente: " + pagamentos[i].consulta.paciente.nome + "\n");
                System.out.printf("profissional: " + pagamentos[i].consulta.profissional.nome + "\n");
                System.out.printf("valor: R$ %.2f\n", pagamentos[i].valorFinal);
                System.out.printf("Metodo: " + pagamentos[i].metodoPagamento + "\n");
                System.out.printf("status: " + pagamentos[i].status + "\n");
            }
        }
    }
    public boolean validarProfissionalComValor(Profissionais prof){
        return prof.valorConsulta > 0;
    }

    public boolean validarProfissionalNodia(Profissionais prof, String data){
        if(prof.diasAtendimento == null || prof.diasAtendimento.length == 0){
            return false;
        }
        int dia = Integer.parseInt(data.substring(0, 2));
        int mes = Integer.parseInt(data.substring(3, 5));
        int ano = Integer.parseInt(data.substring(6, 10));
        
        int diaSemana = calcularDiaSemana(dia, mes, ano);
        String[] diasSemana = {"domingo", "segunda", "terça", "quarta", "quinta", "sexta", "sábado"};
        String diaAtual = diasSemana[diaSemana];
        
        for(int i = 0; i < prof.diasAtendimento.length; i++){
            if(prof.diasAtendimento[i] != null && prof.diasAtendimento[i].toLowerCase().equals(diaAtual.toLowerCase())){
                return true;
            }
        }
        return false;
    }

    public int calcularDiaSemana(int dia, int mes, int ano){
        if(mes < 3){
            mes += 12;
            ano -= 1;
        }
        int k = ano % 100;
        int j = ano / 100;
        int h = (dia + ((13 * (mes + 1)) / 5) + k + (k / 4) + (j / 4) - (2 * j)) % 7;
        int diaSemana = (h + 6) % 7;
        return diaSemana;
    }
}