# web-spring

Aplicacao web da biblioteca desenvolvida com Spring Boot, Thymeleaf e MongoDB.

## Visao geral

O projeto esta organizado em camadas:
- Controllers: entrada web e orquestracao de fluxo
- Service: regras de negocio
- Repository: acesso ao MongoDB com Spring Data
- Model: entidade de dominio

## Requisitos

- Java 21
- Maven
- Acesso ao MongoDB Atlas ou uma URI valida em `MONGODB_URI`

## Configuracao do banco

A conexao com o banco fica em [src/main/resources/application.properties](src/main/resources/application.properties).

Se a variavel de ambiente `MONGODB_URI` estiver definida, ela tem prioridade. Caso contrario, a aplicacao usa a URI padrao configurada no arquivo.

## Execucao

1. Compilar o projeto: `mvn clean package`
2. Iniciar a aplicacao: `mvn spring-boot:run`
3. Acessar no navegador: `http://localhost:8080/livros`

## Funcionalidade atual

- Listagem de livros
- Busca por titulo
- Cadastro de livros

## Documentacao

Diagramas disponiveis em `docs/`:
- `docs/diagrama-atividade-biblioteca.md`
- `docs/diagrama-classes-biblioteca.md`
- `docs/diagrama-classes-biblioteca-academico.md`
- `docs/diagrama-classes-biblioteca-academico.mmd`

## Observacao

Se o MongoDB estiver indisponivel ou a URI estiver incorreta, a aplicacao nao conseguira persistir os livros.
