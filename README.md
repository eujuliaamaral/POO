#  Sistema de Gerenciamento de Clínica de Consultas

Um sistema em Java desenvolvido com **Programação Orientada a Objetos (POO)** para gerenciar consultas, pacientes, profissionais de saúde e pagamentos em uma clínica.

 ** Projeto Educacional**  
 Este é um projeto desenvolvido para a disciplina de **Programação Orientada a Objetos**, com o objetivo de demonstrar os princípios e conceitos fundamentais de POO em Java.

##  Funcionalidades

-  Gerenciamento de Pacientes
  - Cadastro de novos pacientes
  - Registro de informações pessoais (nome, CPF, idade, telefone)
  - Suporte a convênios médicos
  - Ativação/desativação de pacientes

-  Gerenciamento de Profissionais
  - Cadastro de profissionais de saúde
  - Registro de especialidades
  - Definição de valores de consultas
  - Configuração de dias de atendimento

- Agendamento de Consultas
  - Agendar consultas com pacientes e profissionais
  - Registro de data e horário
  - Tipos de consultas (inicial ou acompanhamento)

- Registro de Atendimento
  - Observações durante a consulta
  - Registro de diagnóstico
  - Documentação de procedimentos realizados

-  Gerenciamento de Pagamentos
  - Registro e controle de pagamentos
  - Suporte a diferentes formas de pagamento

-  Relatórios
  - Geração de relatórios gerais de consultas
  - Visualização de informações de pacientes, profissionais e atendimentos
  - Filtros e estatísticas do sistema

##  Como Usar

### Requisitos
- Java 8 ou superior instalado

### Executar o Programa
```bash
javac *.java
java Main
```

### Menu Principal
Ao iniciar, o programa exibirá um menu com as seguintes opções:
1. **Pacientes** - Gerenciar dados de pacientes
2. **Profissionais** - Gerenciar profissionais de saúde
3. **Consultas** - Agendar consultas
4. **Pagamentos** - Gerenciar pagamentos
5. **Relatórios** - Visualizar relatórios do sistema
0. **Sair** - Encerrar o programa

##  Exemplo de Uso

### Cadastrar um Paciente
- Selecione a opção "1 - Pacientes"
- Escolha cadastrar novo paciente
- Preencha as informações solicitadas (nome, CPF, idade, telefone, convênio)

### Cadastrar um Profissional
- Selecione a opção "2 - Profissionais"
- Escolha cadastrar novo profissional
- Informe nome, especialidade, valor da consulta e dias de atendimento

### Agendar uma Consulta
- Selecione a opção "3 - Consultas"
- Escolha agendar nova consulta
- Informe o paciente, profissional, data e horário desejados
- O sistema verificará disponibilidade do profissional

### Registrar Atendimento
- Durante uma consulta, você pode registrar:
  - Observações do paciente
  - Diagnóstico do profissional
  - Procedimentos realizados

### Gerenciar Pagamentos
- Selecione a opção "4 - Pagamentos"
- Registre pagamentos de consultas
- Informe a forma de pagamento e valor

### Visualizar Relatórios
- Selecione a opção "5 - Relatórios"
- Visualize todas as consultas cadastradas
- Verifique informações de pacientes, profissionais e atendimentos

##  Autores
Davi Medeiros Dantas Soares 

Kaio Souto Rodrigues 

Maria Júlia Amaral Lacerda

Mikael Abdias de Lima Santos

Pedro Henrique de Almeida Araujo
