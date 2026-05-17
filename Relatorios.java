public class Relatorios {
    Clinica clinica;

    public Relatorios(){
    }

    public Relatorios(Clinica clinica){
        this.clinica = clinica;
    }

    public void gerarRelatorioGeral(){
        System.out.println("\n========== RELATÓRIO GERAL DE CONSULTAS ==========");
        if(Clinica.totalConsultas == 0){
            System.out.println("Nenhuma consulta registrada.");
            return;
        }
        for(int i = 0; i < Clinica.totalConsultas; i++){
            System.out.printf("\n--- Consulta %d ---\n", i + 1);
            System.out.printf("Paciente: %s (CPF: %s)\n", clinica.consultas[i].paciente.nome, clinica.consultas[i].paciente.cpf);
            System.out.printf("Profissional: %s\n", clinica.consultas[i].profissional.nome);
            System.out.printf("Data: %s | Horário: %s\n", clinica.consultas[i].data, clinica.consultas[i].horario);
            System.out.printf("Tipo: %s | Status: %s\n", clinica.consultas[i].tipo, clinica.consultas[i].status);
            if(clinica.consultas[i].atendimento != null){
                System.out.printf("Observações: %s\n", clinica.consultas[i].atendimento.observacoes);
                if(!clinica.consultas[i].atendimento.diagnostico.isEmpty()){
                    System.out.printf("Diagnóstico: %s\n", clinica.consultas[i].atendimento.diagnostico);
                }
            }
        }
        System.out.println("\n===================================================\n");
    }

    public void gerarRelatorioPorProfissional(String nomeProfissional){
        System.out.println("\n========== RELATÓRIO DE CONSULTAS POR PROFISSIONAL ==========");
        boolean encontrou = false;
        for(int i = 0; i < Clinica.totalConsultas; i++){
            if(clinica.consultas[i].profissional.nome.toLowerCase().equals(nomeProfissional.toLowerCase())){
                if(!encontrou){
                    System.out.printf("Consultas do(a) profissional: %s\n", nomeProfissional);
                    encontrou = true;
                }
                System.out.printf("\n--- Consulta %d ---\n", i + 1);
                System.out.printf("Paciente: %s\n", clinica.consultas[i].paciente.nome);
                System.out.printf("Data: %s | Horário: %s\n", clinica.consultas[i].data, clinica.consultas[i].horario);
                System.out.printf("Tipo: %s | Status: %s\n", clinica.consultas[i].tipo, clinica.consultas[i].status);
                if(clinica.consultas[i].atendimento != null && !clinica.consultas[i].atendimento.diagnostico.isEmpty()){
                    System.out.printf("Diagnóstico: %s\n", clinica.consultas[i].atendimento.diagnostico);
                }
            }
        }
        if(!encontrou){
            System.out.printf("Nenhuma consulta encontrada para o profissional: %s\n", nomeProfissional);
        }
        System.out.println("\n============================================================\n");
    }

    public void gerarRelatorioPorPeriodo(String dataInicio, String dataFim){
        System.out.println("\n========== RELATÓRIO DE CONSULTAS POR PERÍODO ==========");
        int inicioInt = converterDataParaInt(dataInicio);
        int fimInt = converterDataParaInt(dataFim);
        boolean encontrou = false;
        
        for(int i = 0; i < Clinica.totalConsultas; i++){
            int dataConsultaInt = converterDataParaInt(clinica.consultas[i].data);
            if(dataConsultaInt >= inicioInt && dataConsultaInt <= fimInt){
                if(!encontrou){
                    System.out.printf("Consultas entre %s e %s:\n", dataInicio, dataFim);
                    encontrou = true;
                }
                System.out.printf("\n--- Consulta ---\n");
                System.out.printf("Paciente: %s\n", clinica.consultas[i].paciente.nome);
                System.out.printf("Profissional: %s\n", clinica.consultas[i].profissional.nome);
                System.out.printf("Data: %s | Horário: %s\n", clinica.consultas[i].data, clinica.consultas[i].horario);
                System.out.printf("Status: %s\n", clinica.consultas[i].status);
            }
        }
        if(!encontrou){
            System.out.printf("Nenhuma consulta encontrada no período de %s a %s\n", dataInicio, dataFim);
        }
        System.out.println("\n========================================================\n");
    }

    public void gerarResumoFinanceiro(){
        System.out.println("\n========== RESUMO FINANCEIRO ==========");
        int totalConsultasRealizadas = 0;
        double totalFaturado = 0.0;
        int totalCancelamentos = 0;
        double totalMultas = 0.0;
        
        for(int i = 0; i < Clinica.totalConsultas; i++){
            if(clinica.consultas[i].status.equals("realizada")){
                totalConsultasRealizadas++;
                double valorConsulta = clinica.consultas[i].profissional.valorConsulta;
                totalFaturado += valorConsulta;
            }
            if(clinica.consultas[i].status.equals("cancelada")){
                totalCancelamentos++;
                totalMultas += clinica.consultas[i].multa;
            }
        }
        
        System.out.printf("Total de consultas realizadas: %d\n", totalConsultasRealizadas);
        System.out.printf("Total faturado: R$ %.2f\n", totalFaturado);
        System.out.printf("Total de cancelamentos: %d\n", totalCancelamentos);
        System.out.printf("Total arrecadado em multas: R$ %.2f\n", totalMultas);
        System.out.printf("Total geral (faturamento + multas): R$ %.2f\n", totalFaturado + totalMultas);
        System.out.println("\n=====================================\n");
    }

    public int converterDataParaInt(String data){
        String[] partes = data.split("/");
        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int ano = Integer.parseInt(partes[2]);
        return (ano * 10000) + (mes * 100) + dia;
    }
}
