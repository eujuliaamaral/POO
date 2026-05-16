public class Consultas {
    Paciente paciente;
    Profissionais profissional;
    String data;
    String horario;
    String tipo;

    public Consultas(){
    }

    public Consultas(Paciente paciente, Profissionais profissional, String data, String horario){
        this.paciente = paciente;
        this.profissional = profissional;
        this.data = data;
        this.horario = horario;
        this.tipo = "Consulta incial";
    }

    public Consultas(Paciente paciente, Profissionais profissional, String data, String horario, String tipo){
        this.paciente = paciente;
        this.profissional = profissional;
        this.data = data;
        this.horario = horario;
        this.tipo = tipo;
    }

    public Consultas agendar(Paciente paciente, Profissionais profissional, String data, String hora, String tipo){
        Clinica.totalConsultas++;
        return new Consultas(paciente, profissional, data, hora, tipo);
    }

    public Consultas agendar(Paciente paciente, Profissionais profissional, String data, String hora){
        Clinica.totalConsultas++;
        return new Consultas(paciente, profissional, data, hora);
    }
    
}
