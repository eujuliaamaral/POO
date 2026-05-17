public class Atendimento {
    String observacoes;
    String diagnostico;
    String[] procedimentos;
    int totalProcedimentos;

    public Atendimento(){
        this.procedimentos = new String[10];
        this.totalProcedimentos = 0;
        this.observacoes = "";
        this.diagnostico = "";
    }

    public Atendimento(String observacoes){
        this.observacoes = observacoes;
        this.diagnostico = "";
        this.procedimentos = new String[10];
        this.totalProcedimentos = 0;
    }

    public Atendimento(String observacoes, String diagnostico){
        this.observacoes = observacoes;
        this.diagnostico = diagnostico;
        this.procedimentos = new String[10];
        this.totalProcedimentos = 0;
    }

    public Atendimento registrar(String observacoes){
        return new Atendimento(observacoes);
    }

    public Atendimento registrar(String observacoes, String diagnostico){
        return new Atendimento(observacoes, diagnostico);
    }

    public void adicionarProcedimento(String procedimento){
        if(totalProcedimentos < 10){
            this.procedimentos[totalProcedimentos] = procedimento;
            this.totalProcedimentos++;
        }else{
            System.out.println("Limite de 10 procedimentos atingido.");
        }
    }

    public void adicionarMultiplosProcedimentos(String[] procs){
        for(int i = 0; i < procs.length; i++){
            if(totalProcedimentos < 10){
                this.procedimentos[totalProcedimentos] = procs[i];
                this.totalProcedimentos++;
            }else{
                System.out.println("Limite de 10 procedimentos atingido.");
                break;
            }
        }
    }

    public void exibirResumo(){
        System.out.println("\n--- Resumo do atendimento ---");
        System.out.println("Observacoes: " + observacoes);
        if(!diagnostico.isEmpty()){
            System.out.println("Diagnostico: " + diagnostico);
        }
        if(totalProcedimentos > 0){
            System.out.println("Procedimentos realizados: ");
            for(int i = 0; i < totalProcedimentos; i++){
                System.out.println("  " + (i + 1) + ". " + procedimentos[i]);
            }
        }
    }
}
