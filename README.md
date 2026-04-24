# EcoRoute — Sistema de Caronas Sustentáveis

Este repositório apresenta o projeto **EcoRoute**, desenvolvido para a disciplina de **Banco de Dados**, com foco na integração entre uma aplicação Java e um banco de dados relacional PostgreSQL.

O sistema foi construído com interface gráfica em **Java Swing** e permite realizar operações de **CRUD**, autenticação de usuários e consultas com **INNER JOIN** e **LEFT JOIN**, atendendo aos requisitos do trabalho acadêmico.

---

## 📌 Sobre o Projeto

O **EcoRoute** é um sistema de caronas sustentáveis voltado para o ambiente universitário.  
Seu objetivo é gerenciar:

- usuários
- veículos
- caronas
- reservas

A proposta do sistema é permitir que motoristas cadastrem veículos e caronas, enquanto passageiros possam realizar reservas, promovendo uma solução organizada de mobilidade compartilhada.

---

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java
- **Interface Gráfica:** Java Swing
- **Banco de Dados:** PostgreSQL
- **Conexão com Banco:** JDBC
- **Gerenciador de Dependências:** Maven
- **IDE utilizada:** NetBeans

---

## 📂 Estrutura do Repositório

A organização do projeto pode ser feita assim:

- `/src/main/java/com/mycompany/ecoroute` → código-fonte Java
- `/sql` → scripts SQL do banco de dados
- `/prints` → imagens do sistema para demonstração
- `pom.xml` → configuração do Maven e dependências
- `README.md` → documentação principal do projeto

---

## 🗄️ Estrutura do Banco de Dados

O banco de dados do sistema é composto pelas seguintes tabelas principais:

- **usuario**
- **veiculo**
- **carona**
- **reserva**

### Relacionamentos
- Um **usuário** pode ter um ou mais **veículos**
- Um **usuário** pode cadastrar uma ou mais **caronas**
- Uma **carona** está associada a um **veículo**
- Um **usuário** pode realizar uma ou mais **reservas**
- Uma **reserva** está vinculada a uma **carona**

---

## ⚙️ Funcionalidades do Sistema

O sistema possui as seguintes funcionalidades:

### Autenticação
- Login com validação no banco de dados

### Cadastro
- Cadastro de veículos
- Cadastro de caronas
- Cadastro de reservas

### Listagem
- Listagem de usuários
- Listagem de veículos
- Listagem de caronas
- Listagem de reservas

### Atualização
- Atualização do status da reserva

### Exclusão
- Exclusão de reservas

---

## 🔎 Consultas SQL com JOIN

O sistema realiza consultas com junção entre tabelas para exibir informações completas.

### INNER JOIN
Utilizado para listar:

- caronas com nome do motorista e veículo
- reservas com nome do passageiro e dados da carona
- veículos com nome do motorista

### LEFT JOIN
Utilizado para listar:

- motoristas mesmo que ainda não tenham cadastrado caronas
- caronas mesmo que ainda não tenham reservas

---

## 📸 Demonstração do Sistema

Adicione aqui os prints do seu sistema.

## 📸 Tela de Login
<img width="1920" height="1080" alt="Captura de tela de 2026-04-24 10-48-42" src="https://github.com/user-attachments/assets/0b728248-b609-4119-9eb1-0c037432b067" />


## 📸 Menu Principal
<img width="1920" height="1080" alt="Captura de tela de 2026-04-24 10-59-50" src="https://github.com/user-attachments/assets/558b4d08-b45e-4e10-9a1c-79c5caf06a0e" />


## 📸 Lista de Caronas
<img width="1920" height="1080" alt="Captura de tela de 2026-04-24 11-13-47" src="https://github.com/user-attachments/assets/bf09f2c3-d5c4-43a7-9b51-c131786a587c" />
