# 🔒 Tela de Login e Cadastro (React/Next.js & Spring Boot + Docker)

Este projeto implementa uma aplicação web full-stack que oferece uma tela de login e cadastro. O frontend é desenvolvido com React e Next.js, enquanto o backend é construído com Spring Boot em Java. A comunicação entre as duas partes é feita via requisições HTTP, e o ambiente de desenvolvimento é orquestrado com Docker Compose, incluindo um banco de dados MySQL.

## Visão Geral

O objetivo principal deste projeto é demonstrar a integração de um frontend (React/Next.js) com um backend (Spring Boot) para gerenciar autenticação de usuários, e (Docker/Docker Compose) para unificar os componentes da aplicação. A aplicação permite que novos usuários se cadastrem (com validação de CPF) e usuários existentes façam login, recebendo um token JWT para futuras interações com rotas protegidas no backend.

## Funcionalidades Principais

### **Frontend (React/Next.js):**
*   **Interface de Usuário:** Tela única que alterna entre formulários de Login e Cadastro.
*   **Login de Usuário:** Permite que usuários existentes se autentiquem com e-mail e senha.
*   **Cadastro de Novo Usuário:** Permite o registro de novos usuários com nome, CPF, e-mail, data de nascimento, endereço e senha.
*   **Máscaras e Validações:**
    *   **Máscara de CPF:** Aplica automaticamente o formato `XXX.XXX.XXX-XX` no campo de CPF.
    *   **Validação de CPF:** Verifica a validade do CPF inserido (estrutura e dígitos verificadores) ao sair do campo.
*   **Gerenciamento de Token:** Utiliza `js-cookie` para armazenar o token JWT recebido do backend, facilitando o uso em futuras requisições.
*   **Estilização:** Tailwind CSS para responsividade.

### **Backend (Spring Boot):**
*   **API RESTful:** Expõe endpoints para login e registro de usuários.
*   **Autenticação JWT:** Gera e verifica JSON Web Tokens para autenticação de usuários.
    *   Tokens contêm o `level` (nível de acesso) do usuário como uma "claim".
    *   Um `SecretKey` estática é usada para assinar e verificar os tokens.
*   **Autorização por Nível:** Implementa um filtro (`AccessFilter`) que intercepta requisições para rotas específicas (ex: `/FippPay/admin/*`, `/FippPay/others/*`) e verifica se o usuário possui o `level` de acesso adequado com base no token JWT.
*   **Gerenciamento de Usuários:** Camadas de Serviço (`SystemUserService`) e Repositório (`SystemUserRepository`) para interagir com o banco de dados.
    *   `SystemUser`: Entidade que mapeia a tabela `system_user` no MySQL, incluindo campos como `id`, `name`, `cpf`, `email`, `dt_nasc`, `address`, `password`, `level`.
    *   `getByEmail`: Consulta um usuário pelo e-mail.
    *   `save`: Salva um novo usuário no banco de dados.
*   **Configuração de Banco de Dados:** Utiliza Spring Data JPA e Hibernate para mapeamento objeto-relacional, conectado ao MySQL. A string de conexão e credenciais são lidas de variáveis de ambiente no Docker.

---

## Configuração do Banco de Dados e Variáveis de Ambiente

As configurações do banco de dados MySQL para o Spring Boot são definidas no `Docker-compose.yml` e no `application.properties`. As credenciais são `root` para usuário e senha, e o banco de dados é `database`. O `ddl-auto` está configurado para `update`, o que significa que o Hibernate tentará criar ou atualizar o esquema do banco de dados automaticamente com base nas entidades Java.

Para a chave secreta JWT no backend (`JWTTokenProvider.java`), ela está atualmente hardcoded:
`private static final SecretKey KEY = Keys.hmacShaKeyFor("SECRET_KEY_EXEMPLE/SECRET_KEY_EXEMPLE".getBytes(StandardCharsets.UTF_8));`
