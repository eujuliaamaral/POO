public class Clinica {
    Paciente[] pacientes = new Paciente[100];
    Profissionais[] profissionais = new Profissionais[100];
    Consultas[] consultas = new Consultas[200];

    public static int totalPacientes = 0;
    public static int totalProfissionais = 0;
    public static int totalConsultas = 0;

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
                if(!profissionalOcupado(profissionais[i], data, horario)){
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

    public String horarioOcupado(Profissionais profissional, String data){
        for(int i = 8; i < 18; i++){
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
        for(int i = 0; i < totalConsultas; i++){
            System.out.printf("\n\nPaciente: " + consultas[i].paciente + "\nProfissional: " + consultas[i]. profissional +
            "\nData: " + consultas[i].data + "\nHorario: " + consultas[i].horario + "\nTipo: " + consultas[i].tipo + "\n");
        }

    }

    public void listarConsultas(String cpfP){
        boolean achou = false;
        for(int i = 0; i <totalConsultas; i++){
            if(consultas[i].paciente.cpf.equals(cpfP)){
                System.out.printf("\n\nPaciente: " + consultas[i].paciente + "\nProfissional: " + consultas[i]. profissional +
                "\nData: " + consultas[i].data + "\nHorario: " + consultas[i].horario + "\nTipo: " + consultas[i].tipo + "\n");
            
                achou = true;
            }
        }
        if(!achou){
        System.out.printf("\nNenhum agendamento encontrado.\n");
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
                System.out.printf("\n\nNome: " + profissionais[i].nome + "\nEspecialidade: " + profissionais[i].especialidade + "\nRegistro Profissional: " + profissionais[i].registroPro + "\n");
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

}
