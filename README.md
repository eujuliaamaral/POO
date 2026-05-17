# Clínica VidaPlena - Sistema de Gerenciamento

Um sistema em Java desenvolvido com **Programação Orientada a Objetos** para gerenciar consultas, pacientes, profissionais de saúde, pagamentos e relatórios em uma clínica multidisciplinar.

## 📋 Sobre o Projeto

A **Clínica VidaPlena** é uma clínica multidisciplinar que atende nas áreas de:
- Clínica Geral
- Fisioterapia
- Psicologia
- Nutrição

**Este é um projeto educacional** desenvolvido para a disciplina de Programação Orientada a Objetos, demonstrando os conceitos fundamentais de POO em Java.

## Conceitos de POO Aplicados

- **Sobrecarga de construtores** (≥4 classes obrigatoriamente)
- **Sobrecarga de métodos** (≥4 classes obrigatoriamente)
- **Estruturas de decisão e repetição** (if/else, for, while)
- **Arrays com tamanho fixo** e variáveis contadoras
- **Métodos com e sem retorno**

## Restrições e Conformidades

O projeto segue rigorosamente as seguintes restrições:
- **Sem herança, classes abstratas ou interfaces**
- **Sem sobrescrita ou ligação dinâmica**
- **Sem Collections** (ArrayList, HashMap, etc.)
- **Sem exceções** tratadas com try/catch
- **Sem encapsulamento com getters/setters** - todos os atributos são públicos
- **Sem frameworks ou bibliotecas externas**
- **Todos os atributos são públicos** (sem private)
- **Scanner para entrada de dados** no console
- **Datas em formato texto** "DD/MM/AAAA" com .equals()

## Funcionalidades Principais

### Gerenciamento de Pacientes
- Cadastro com dados mínimos (nome e CPF) ou dados completos
- Complementação de dados em etapas (idade, telefone, convênio)
- Ativação/desativação de pacientes
- Listagem e busca de pacientes por CPF
- Validação de duplicação por CPF

### Gerenciamento de Profissionais
- Cadastro de profissionais com especialidade validada
- Especialidades aceitas: Clínica Geral, Fisioterapia, Psicologia, Nutrição
- Registro profissional e valor de consulta
- Configuração de dias de atendimento (segunda a sexta)
- Listagem e filtro por especialidade
- Bloqueio de agendamentos para profissionais sem valor definido

### Agendamento de Consultas
- Agendamento com paciente, profissional, data e horário
- Tipos de consulta: "inicial", "retorno", "avaliação"
- Busca automática de profissional por especialidade e disponibilidade
- Validação de conflitos de horário
- Sugestão automática de próximo horário livre (de hora em hora, 08h-18h)
- Verificação de disponibilidade do profissional no dia da semana

### Registro de Atendimento
Três formas de registro:
1. **Simples**: apenas observações gerais
2. **Intermediário**: observações + diagnóstico
3. **Completo**: observações + diagnóstico + procedimentos (até 10)

Procedimentos podem ser adicionados um por vez ou vários simultaneamente.
Ao registrar, a consulta é marcada automaticamente como realizada.

### Cancelamento de Consultas
- Cancelamento simples (sem multa)
- Cancelamento com justificativa
- **Multa automática de R$ 50,00** para cancelamentos com menos de 2 horas de antecedência
- Histórico com justificativa do cancelamento

### Remarcação de Consultas
- Remarcação no mesmo dia (novo horário)
- Remarcação para data diferente
- Validação de disponibilidade do profissional no novo dia

### Gerenciamento de Pagamentos
Formas de pagamento aceitas: dinheiro, cartão, convênio

**Cálculo automático com:**
- Desconto de retorno: **20%** do valor base
- Cobertura de convênio: **40%** do valor base
- Multas pendentes de cancelamentos
- Parcelamento em até 3 vezes (cartão apenas, sem juros)
- Mínimo nunca negativo (0 é o piso)

**Três formas de cálculo:**
1. Sem considerar nada extra
2. Considerando apenas desconto
3. Considerando desconto e multa

### Relatórios
- **Relatório Geral**: todas as consultas com paciente, profissional, data, hora, tipo, status e diagnóstico
- **Filtro por Profissional**: consultas de um profissional específico
- **Filtro por Período**: consultas dentro de um intervalo de datas
- **Resumo Financeiro**: total de atendimentos, faturamento total, quantidade de cancelamentos e total arrecadado em multas

## Como Usar

### Requisitos
- Java 8 ou superior instalado

### Compilar e Executar
```bash
javac *.java
java Main
```

### Menu Principal
O sistema oferece as seguintes opções:
1. **Pacientes** - Gerenciar cadastro de pacientes
2. **Profissionais** - Gerenciar profissionais de saúde
3. **Consultas** - Agendar, cancelar, remarcar e registrar consultas
4. **Pagamentos** - Registrar e listar pagamentos
5. **Relatórios** - Gerar relatórios do sistema
0. **Sair** - Encerrar o programa

## Jornadas de Usuário Principais

### 1. Cadastro Rápido e Complementação de Paciente
Ana registra apenas nome e CPF. Mais tarde, complementa com idade, telefone e dados do convênio.

### 2. Cadastro Completo de Paciente
Ana realiza cadastro completo (nome, CPF, idade, telefone, convênio) em uma única operação.

### 3. Tentativa de Duplicação
Ao tentar cadastrar um paciente com CPF já registrado, o sistema recusa a operação.

### 4. Cadastro e Atualização de Profissional
Profissional é cadastrado com dados mínimos, depois atualizado com registro profissional, valor de consulta e dias de atendimento.

### 5. Agendamento com Busca Automática de Profissional
Paciente fornece apenas especialidade, data e horário; o sistema busca automaticamente um profissional disponível.

### 6. Agendamento com Conflito e Sugestão de Horário
Ao detectar conflito de horário, o sistema sugere automaticamente o próximo horário livre naquele dia.

### 7. Atendimento Simples
Profissional registra apenas observações gerais durante o atendimento.

### 8. Atendimento Completo
Profissional registra observações, diagnóstico e múltiplos procedimentos.

### 9. Cancelamento sem Multa
Cancelamento com mais de 2 horas de antecedência - sem multa aplicada.

### 10. Cancelamento com Multa
Cancelamento com menos de 2 horas - multa de R$ 50,00 aplicada automaticamente.

### 11. Pagamento Automático com Desconto e Convênio
Sistema calcula automaticamente desconto de retorno (20%) e cobertura de convênio (40%).

### 12. Desativação e Tentativa de Agendamento
Paciente inativo não pode agendar novas consultas.

### Visualizar Relatórios
- Selecione a opção "5 - Relatórios"
- Opções disponíveis:
  - **Relatório Geral**: Todas as consultas cadastradas
  - **Relatório por Profissional**: Consultas de um profissional específico
  - **Relatório por Período**: Consultas entre duas datas
  - **Resumo Financeiro**: Total de faturamento, cancelamentos e multas

##  Autores
Davi Medeiros Dantas Soares 

Kaio Souto Rodrigues 

Maria Júlia Amaral Lacerda

Mikael Abdias de Lima Santos

Pedro Henrique de Almeida Araujo
