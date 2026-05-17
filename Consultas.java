public class Consultas {
    Paciente paciente;
    Profissionais profissional;
    String data;
    String horario;
    String tipo;
    String status;
    double multa;
    String justificativaCancelamento;
    Atendimento atendimento;

    public Consultas(){
    }

    public Consultas(Paciente paciente, Profissionais profissional, String data, String horario){
        this.paciente = paciente;
        this.profissional = profissional;
        this.data = data;
        this.horario = horario;
        this.tipo = "Consulta inicial";
        this.status = "agendada";
        this.multa = 0.0;
        this.justificativaCancelamento = "";
        this.atendimento = null;
    }

    public Consultas(Paciente paciente, Profissionais profissional, String data, String horario, String tipo){
        this.paciente = paciente;
        this.profissional = profissional;
        this.data = data;
        this.horario = horario;
        this.tipo = tipo;
        this.status = "agendada";
        this.multa = 0.0;
        this.justificativaCancelamento = "";
        this.atendimento = null;
    }

    public Consultas agendar(Paciente paciente, Profissionais profissional, String data, String hora, String tipo){
        Clinica.totalConsultas++;
        return new Consultas(paciente, profissional, data, hora, tipo);
    }

    public Consultas agendar(Paciente paciente, Profissionais profissional, String data, String hora){
        Clinica.totalConsultas++;
        return new Consultas(paciente, profissional, data, hora);
    }
    
    public void cancelar(double multa, String justificativa){
        this.status = "cancelada";
        this.multa = multa;
        this.justificativaCancelamento = justificativa;
    }
    
    public void cancelar(double multa){
        this.status = "cancelada";
        this.multa = multa;
        this.justificativaCancelamento = "";
    }
    
    public void remarcar(){
        this.status = "remarcada";
    }
    
    public void registrarAtendimento(Atendimento atendimento){
        if(this.status.equals("agendada")){
            this.atendimento = atendimento;
            this.status = "realizada";
        }else{
            System.out.println("Não é possível registrar atendimento em uma consulta com status: " + this.status);
        }
    }
}
