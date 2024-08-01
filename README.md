## Sistema de RH

Bem-vindo ao Sistema de RH, um projeto desenvolvido para gerenciar vagas, funcionários, dependentes e candidatos. Este sistema utiliza Spring Boot e Thymeleaf para construir uma aplicação web eficiente e fácil de usar.

## Funcionalidades Principais

Cadastro de Vagas: Permite a criação, visualização, edição e exclusão de vagas de emprego.
Cadastro de Funcionários: Gerencia o cadastro de funcionários, incluindo a edição e visualização de detalhes.
Gerenciamento de Dependentes: Associa dependentes aos funcionários, com opções de adicionar, editar e excluir dependentes.
Cadastro de Candidatos: Gerencia os candidatos às vagas, incluindo suas informações pessoais e histórico de candidaturas.

## Modelo de Dados

```mermaid
classDiagram
    class Vaga {
        +Long id
        +String titulo
        +String descricao
        +LocalDate dataCriacao
        +LocalDate dataEncerramento
    }

    class Funcionario {
        +Long id
        +String nome
        +String cpf
        +LocalDate dataNascimento
        +String email
        +List~Dependente~ dependentes
    }

    class Dependente {
        +Long id
        +String nome
        +String cpf
        +LocalDate dataNascimento
        +String parentesco
    }

    class Candidato {
        +Long id
        +String nome
        +String cpf
        +String email
        +List~Vaga~ vagas
    }

    Vaga "1" --> "*" Candidato : "Candidaturas"
    Funcionario "1" --> "*" Dependente : "Dependentes"
```

## Funcionalidades CRUD

- Vagas
  
1) Criar Vaga: Formulário para cadastro de novas vagas.
2) Listar Vagas: Visualiza todas as vagas cadastradas.
3) Editar Vaga: Permite atualizar as informações de uma vaga existente.
4) Excluir Vaga: Remove uma vaga do sistema.

- Funcionários

1) Cadastrar Funcionário: Formulário para adicionar novos funcionários.
2) Listar Funcionários: Exibe todos os funcionários cadastrados.
3) Editar Funcionário: Atualiza os dados de um funcionário existente.
4) Excluir Funcionário: Remove um funcionário do sistema.

- Dependentes

1) Adicionar Dependente: Formulário para adicionar dependentes aos funcionários.
2) Listar Dependentes: Visualiza todos os dependentes cadastrados.
3) Editar Dependente: Atualiza os dados de um dependente.
4) Excluir Dependente: Remove um dependente do sistema.

- Candidatos

1) Cadastrar Candidato: Formulário para cadastro de novos candidatos.
2) Listar Candidatos: Exibe todos os candidatos cadastrados.
3) Editar Candidato: Atualiza os dados de um candidato.
4) Excluir Candidato: Remove um candidato do sistema.

## Tecnologias Utilizadas

* Java 17
* Spring Boot 3.x
* Thymeleaf
* Bootstrap
* JPA/Hibernate
* H2 Database

## Contribuições
Sinta-se à vontade para contribuir com o projeto através de pull requests. Todas as contribuições são bem-vindas!
