public class Pagamentos {
    Consultas consulta;
    double valorBase;
    double valorFinal;
    String dataPagamento;
    String metodoPagamento;
    String status;
    double multa;
    int parcelas;
    double valorParcela;

    public Pagamentos(){
    }

    public Pagamentos(Consultas consulta, double valor, String metodoPagamento){
        this.consulta = consulta;
        this.valorBase = valor;
        this.valorFinal = valor;
        this.metodoPagamento = metodoPagamento;
        this.status = "pendente";
        this.multa = 0.0;
        this.parcelas = 1;
        this.valorParcela = valor;
        this.dataPagamento = "";
    }

    public Pagamentos(Consultas consulta, double valor, String dataPagamento, String metodoPagamento, String status){
        this.consulta = consulta;
        this.valorBase = valor;
        this.valorFinal = valor;
        this.dataPagamento = dataPagamento;
        this.metodoPagamento = metodoPagamento;
        this.status = status;
        this.multa = 0.0;
        this.parcelas = 1;
        this.valorParcela = valor;
    }

    public Pagamentos registrar(Consultas consulta, double valor, String metodoPagamento){
        return new Pagamentos(consulta, valor, metodoPagamento);
    }

    public Pagamentos registrar(Consultas consulta, double valor, String dataPagamento, String metodoPagamento){
        return new Pagamentos(consulta, valor, dataPagamento, metodoPagamento, "pago");
    }

    public void calcularComDesconto(double descontoPercentual){
        double desconto = valorBase * (descontoPercentual / 100.0);
        this.valorFinal = valorBase - desconto;
    }

    public void calcularComDescontoEConvenio(double descontoPercentual, double convenioPercentual){
        double desconto = valorBase * (descontoPercentual / 100.0);
        double cobertura = valorBase * (convenioPercentual / 100.0);
        this.valorFinal = valorBase - desconto - cobertura;
        if(this.valorFinal < 0){
            this.valorFinal = 0.0;
        }
    }

    public void calcularComDescontoConvenioEMulta(double descontoPercentual, double convenioPercentual, double multa){
        double desconto = valorBase * (descontoPercentual / 100.0);
        double cobertura = valorBase * (convenioPercentual / 100.0);
        this.valorFinal = valorBase - desconto - cobertura + multa;
        if(this.valorFinal < 0){
            this.valorFinal = 0.0;
        }
    }

    public void aplicarMulta(double valor){
        this.multa = valor;
        this.valorFinal = this.valorFinal + valor;
    }

    public void parcelar(int numeroParcelas){
        if(numeroParcelas > 3){
            System.out.println("Parcelamento máximo em 3 vezes.");
            this.parcelas = 3;
        }else if(numeroParcelas < 1){
            System.out.println("Número de parcelas inválido.");
            this.parcelas = 1;
        }else{
            this.parcelas = numeroParcelas;
        }
        this.valorParcela = this.valorFinal / this.parcelas;
    }

    public void efetuarPagamento(String data){
        this.dataPagamento = data;
        this.status = "pago";
    }

    public void exibirResumo(){
        System.out.println("\n--- Resumo do pagamento ---");
        System.out.println("Paciente: " + consulta.paciente.nome);
        System.out.println("Profissional: " + consulta.profissional.nome);
        System.out.println("Valor base: R$ " + String.format("%.2f", valorBase));
        System.out.println("Valor final: R$ " + String.format("%.2f", valorFinal));
        if(multa > 0){
            System.out.println("Multa: R$ " + String.format("%.2f", multa));
        }
        System.out.println("Método de pagamento: " + metodoPagamento);
        if(parcelas > 1){
            System.out.println("Parcelas: " + parcelas + "x de R$ " + String.format("%.2f", valorParcela));
        }
        System.out.println("Data: " + dataPagamento);
        System.out.println("Status: " + status);
    }
}
