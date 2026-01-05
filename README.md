University Course Management System
Bem-vindo ao Sistema de Gestão Universitária, uma solução robusta em Java desenvolvida para automatizar processos académicos críticos. Este projeto foca-se na integridade dos dados, validação de regras de negócio complexas e reatividade através de padrões de design modernos.
________________________________________
Funcionalidades Principais
•	Gestão de Utilizadores: Criação dinâmica de Alunos e Instrutores via Factory Method.
•	Inscrições Inteligentes: Sistema de matrícula que valida automaticamente a árvore de pré-requisitos (precedências)
•	Deteção de Conflitos: Motor de validação que impede a inscrição em disciplinas com sobreposição de horários (timeslots).
•	Motor de GPA Ponderado: Cálculo automático do desempenho académico utilizando o Strategy Pattern.
•	Notificações Reativas: Sistema de feedback imediato ao aluno após o lançamento de notas via Observer Pattern
________________________________________
Stack Tecnológica
•	Linguagem: Java 17+
•	Build Tool: Maven
•	Testes: JUnit 5 (Jupiter)
•	Cobertura: JaCoCo
________________________________________
Arquitetura e Padrões de Design
O projeto foi desenhado seguindo os princípios SOLID e utiliza três padrões de design principais para garantir flexibilidade e baixo acoplamento:
1.	Factory Method (UserFactory): Centraliza a criação de tipos de utilizadores
2.	Strategy Pattern (GPAStrategy): Isola o algoritmo de cálculo de média, permitindo diferentes lógicas de ponderação.
3.	Observer Pattern (Observer): Implementa a comunicação desacoplada entre a inscrição (Enrollment) e o aluno (Student).
________________________________________
Suite de Testes
O projeto inclui uma cobertura rigorosa com 36 métodos de teste unitário, divididos em módulos lógicos:
•	UserAndModelTest: Validação de modelos e da Factory.
•	EnrollmentAndPrerequisiteTest: Testes de caixa-branca para cadeiras de precedência.
•	GPACalculatorTest: Análise de valor limite para notas entre 0.0 e 20.0 .
•	ScheduleConflictTest: Validação de integridade de horários semanais.
•	ObserverPatternTest: Verificação da reatividade e notificações.
________________________________________
Como Executar
Pré-requisitos
•	JDK 17 ou superior.
•	Maven instalado
________________________________________
Trabalho Realizado por:
Diogo Martinho 230001179
Ricardo Ferreira 230001057
Bernardo Oliveira 230001052
Gonçalo Ferreira 230001170



