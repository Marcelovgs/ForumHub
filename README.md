# Fórum Hub API

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-blue?logo=java" alt="Java 17">
  <img src="https://img.shields.io/badge/Spring_Boot-3.x-green?logo=spring" alt="Spring Boot">
  <img src="https://img.shields.io/badge/PostgreSQL-15-blue?logo=postgresql" alt="PostgreSQL">
  <img src="https://img.shields.io/badge/Status-Concluído-brightgreen" alt="Status Concluído">
</p>

API REST para um fórum de discussão, desenvolvida como parte de um desafio de programação do Oracle Next Education (ONE). A aplicação permite que usuários se cadastrem, autentiquem e interajam criando, lendo, atualizando e deletando tópicos de discussão de forma segura.

## ✨ Funcionalidades

* **Autenticação de Usuários:** Sistema seguro de login com Tokens JWT (JSON Web Tokens).
* **Cadastro de Usuários:** Endpoint público para registro de novos usuários com criptografia de senhas (BCrypt).
* **CRUD de Tópicos:** Operações completas de Criar, Ler, Atualizar e Deletar tópicos.
* **Regras de Autorização:** Um usuário só pode atualizar ou deletar os tópicos que ele mesmo criou.
* **Validações:** Validação dos dados de entrada conforme as regras de negócio.
* **Paginação:** A listagem de tópicos é paginada para otimizar a performance.

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 17
* **Framework:** Spring Boot 3
* **Segurança:** Spring Security 6
* **Banco de Dados:**
    * Spring Data JPA
    * Hibernate
    * PostgreSQL
* **Autenticação:** JSON Web Token (JWT)
* **Utilitários:**
    * Lombok
    * Flyway ou Migrações via Hibernate (`ddl-auto`)
* **Build Tool:** Gradle

## ⚙️ API Endpoints

| Método | Endpoint             | Protegido? | Descrição                                    |
| :----- | :------------------- | :--------- | :------------------------------------------- |
| `POST` | `/login`             | Não        | Autentica um usuário e retorna um token JWT. |
| `POST` | `/usuarios`          | Não        | Cadastra um novo usuário.                    |
| `GET`  | `/topicos`           | Sim        | Lista todos os tópicos de forma paginada.    |
| `GET`  | `/topicos/{id}`      | Sim        | Detalha um tópico específico pelo seu ID.    |
| `POST` | `/topicos`           | Sim        | Cria um novo tópico.                         |
| `PUT`  | `/topicos/{id}`      | Sim        | Atualiza um tópico existente.                |
| `DELETE`| `/topicos/{id}`      | Sim        | Deleta um tópico existente.                  |

## 🚀 Como Executar o Projeto

**Pré-requisitos:**
* Java 17 (ou superior)
* Gradle
* PostgreSQL instalado e rodando

1.  **Clone o repositório:**
    ```bash
    git clone [https://seulink.para.o.github/forum-hub.git](https://seulink.para.o.github/forum-hub.git)
    cd forum-hub
    ```

2.  **Crie o Banco de Dados:**
    * Abra seu cliente PostgreSQL (pgAdmin, DBeaver, etc.).
    * Crie um novo banco de dados com o nome `forumhub`.

3.  **Configure as Variáveis de Ambiente:**
    * Abra o arquivo `src/main/resources/application.properties`.
    * Altere as propriedades `spring.datasource.username` e `spring.datasource.password` com suas credenciais do PostgreSQL.

4.  **Execute a Aplicação:**
    * Pelo terminal, na raiz do projeto, execute:
    ```bash
    ./gradlew bootRun
    ```
    * A API estará disponível em `http://localhost:8080`.

---
Autor: Marcelo Varela
