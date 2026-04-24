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

O sistema **EcoRoute** utiliza consultas SQL com `INNER JOIN` e `LEFT JOIN` para integrar informações entre as tabelas `usuario`, `veiculo`, `carona` e `reserva`.

Essas consultas permitem exibir dados completos do sistema, como:

- veículos com o nome do motorista
- caronas com motorista e veículo
- reservas com passageiro e destino
- relatórios completos com passageiro, motorista e veículo
- motoristas mesmo sem caronas cadastradas

### 1. Veículos com nome do motorista
```sql
SELECT 
    v.id_veiculo,
    v.modelo,
    v.placa,
    v.cor,
    v.capacidade,
    u.nome AS motorista
FROM veiculo v
INNER JOIN usuario u ON v.id_usuario = u.id_usuario
WHERE u.tipo_usuario = 'motorista';
```
### 2. Caronas com motorista e veículo
```sql
SELECT 
    c.id_carona,
    u.nome AS motorista,
    v.modelo AS veiculo,
    v.placa,
    c.origem,
    c.destino,
    c.data_saida,
    c.horario_saida,
    c.vagas_disponiveis,
    c.valor,
    c.status
FROM carona c
INNER JOIN usuario u ON c.id_usuario = u.id_usuario
INNER JOIN veiculo v ON c.id_veiculo = v.id_veiculo
WHERE u.tipo_usuario = 'motorista';
```
### 3. Reservas com passageiro e destino
```sql
SELECT 
    r.id_reserva,
    u.nome AS passageiro,
    c.origem,
    c.destino,
    c.data_saida,
    r.data_reserva,
    r.status_reserva
FROM reserva r
INNER JOIN usuario u ON r.id_usuario = u.id_usuario
INNER JOIN carona c ON r.id_carona = c.id_carona
WHERE u.tipo_usuario = 'passageiro';
```
### 4.Relatório completo com passageiro, motorista e veículo
```sql
SELECT 
    r.id_reserva,
    passageiro.nome AS passageiro,
    motorista.nome AS motorista,
    v.modelo AS veiculo,
    v.placa,
    c.origem,
    c.destino,
    c.data_saida,
    c.horario_saida,
    r.status_reserva
FROM reserva r
INNER JOIN usuario passageiro ON r.id_usuario = passageiro.id_usuario
INNER JOIN carona c ON r.id_carona = c.id_carona
INNER JOIN usuario motorista ON c.id_usuario = motorista.id_usuario
INNER JOIN veiculo v ON c.id_veiculo = v.id_veiculo
WHERE passageiro.tipo_usuario = 'passageiro'
  AND motorista.tipo_usuario = 'motorista';
```
### 5. Motoristas mesmo sem carona cadastrada
```sql
SELECT 
    u.id_usuario,
    u.nome AS motorista,
    c.id_carona,
    c.destino
FROM usuario u
LEFT JOIN carona c ON u.id_usuario = c.id_usuario
WHERE u.tipo_usuario = 'motorista';
```

## 📸 Demonstração do Sistema

Adicione aqui os prints do seu sistema.

## 📸 Tela de Login
<img width="1920" height="1080" alt="Captura de tela de 2026-04-24 10-48-42" src="https://github.com/user-attachments/assets/0b728248-b609-4119-9eb1-0c037432b067" />


## 📸 Menu Principal
<img width="1920" height="1080" alt="Captura de tela de 2026-04-24 10-59-50" src="https://github.com/user-attachments/assets/558b4d08-b45e-4e10-9a1c-79c5caf06a0e" />


## 📸 Lista de Caronas
<img width="1920" height="1080" alt="Captura de tela de 2026-04-24 11-13-47" src="https://github.com/user-attachments/assets/bf09f2c3-d5c4-43a7-9b51-c131786a587c" />

## 📸 Lista de Reservas

<img width="1911" height="1013" alt="Captura de tela 2026-04-24 155641" src="https://github.com/user-attachments/assets/c565976d-cc22-4d48-927e-446c3be35fc3" />

## 📸 Lista de Veículo
<img width="1911" height="1008" alt="Captura de tela 2026-04-24 155902" src="https://github.com/user-attachments/assets/c3fb1d6a-73ca-4f60-9b8e-6d7db6cc87f6" />

## 📸 Cadastro de Carona

## 📸 Cadastro de Reserva

## 📸 Atualização de Reserva

## 📸 Exclusão de Reserva


