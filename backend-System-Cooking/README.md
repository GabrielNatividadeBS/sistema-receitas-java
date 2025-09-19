# 🍲 Sistema de Gerenciamento de Receitas Culinárias

## 📘 1. Introdução

Este projeto tem como finalidade auxiliar cozinheiros, estudantes de gastronomia e entusiastas da culinária a **organizar e gerenciar receitas de forma prática**. A aplicação foi desenvolvida em **Java**, utilizando **Swing** para a interface gráfica e **MySQL** como banco de dados.

A integração entre a aplicação e o banco de dados é feita com **JDBC** (Java Database Connectivity), e o projeto é desenvolvido na IDE **Apache NetBeans**.

---

## 🎯 2. Objetivos

### 🎯 Objetivo Geral

Desenvolver um sistema desktop que permita o gerenciamento de receitas culinárias por meio de **operações CRUD**.

### ✅ Objetivos Específicos

- Criar uma interface gráfica simples e intuitiva com Java Swing.
- Implementar o cadastro de receitas com os campos:
  - Código
  - Nome
  - Ingredientes
  - Modo de preparo
  - Tempo de preparo
  - Categoria
- Permitir a **edição**, **exclusão** e **consulta** de receitas.
- Garantir que todas as receitas sejam salvas em um banco de dados **MySQL**.
- Aplicar conceitos de **Programação Orientada a Objetos** (POO) e **tratamento de exceções**.

---

## 📋 3. Requisitos

### ✅ 3.1 Requisitos Funcionais

- Cadastrar receitas com todos os dados.
- Listar receitas cadastradas.
- Alterar dados de uma receita existente.
- Excluir receitas.
- Pesquisar receitas por nome ou categoria.

### ⚙️ 3.2 Requisitos Não Funcionais

- Interface gráfica amigável e de fácil uso.
- Linguagem de programação: **Java (Swing)**.
- Banco de dados: **MySQL**.
- Implementação de tratamento de exceções.
- Estrutura de código com conceitos de **POO**.

---

## 🏛️ 4. Arquitetura do Sistema

### 🔷 Camada de Apresentação
- Interface gráfica desenvolvida com **Java Swing**.

### 🔶 Camada de Negócio
- Classes Java responsáveis pela lógica de negócio e manipulação de receitas.

### 🔸 Camada de Persistência
- Acesso ao banco de dados **MySQL** utilizando **JDBC**.

### 🗃️ Banco de Dados: Estrutura da Tabela `receitas`

| Campo          | Tipo        | Descrição                          |
|----------------|-------------|------------------------------------|
| `id`           | INT (PK)    | Identificador único (auto incremento) |
| `nome`         | VARCHAR     | Nome da receita                    |
| `ingredientes` | TEXT        | Ingredientes utilizados            |
| `modo_preparo` | TEXT        | Modo de preparo                    |
| `tempo_preparo`| INT         | Tempo em minutos                   |
| `categoria`    | VARCHAR     | Categoria da receita (ex: sobremesa) |

---

## 🚧 5. Etapas de Desenvolvimento

### 📌 Etapa 1 – Preparação

- Instalar **MySQL** e criar o banco de dados.
- Instalar e configurar o **NetBeans** com suporte a Java.
- Baixar o conector JDBC: `mysql-connector-java`.

### 📌 Etapa 2 – Estrutura do Código

Criação das seguintes classes:

- `Receita` → classe modelo com atributos, getters e setters.
- `ReceitaDAO` → acesso ao banco de dados (CRUD).
- `ConexaoBD` → gerencia a conexão com o banco.
- `TelaPrincipal` → interface Swing com botões, tabelas e formulários.

### 📌 Etapa 3 – Funcionalidades

- Formulário Swing para **cadastrar receitas**.
- Tabela para **listar receitas** do banco.
- Botões para **alterar**, **excluir** e **consultar**.
- Tratamento de exceções com mensagens para o usuário.

### 📌 Etapa 4 – Testes

- Testar todas as operações CRUD.
- Testar falhas de conexão e entradas inválidas.

---

## 👨‍💻 6. Divisão de Tarefas

| Integrante     | Responsabilidade                                                                 |
|----------------|-----------------------------------------------------------------------------------|
| **Gabriel N**  | Banco de Dados (tabela, integração com JDBC)                                     |
| **Victor Cesar** | Desenvolvimento da Interface Gráfica (Swing)                                  |
| **Liédson**    | Classe `Receita` e lógica de negócio (POO)                                       |
| **Israel**     | Tratamento de exceções e testes                                                  |

---

## ✅ 7. Conclusão

O **Sistema de Gerenciamento de Receitas Culinárias** é uma aplicação desktop desenvolvida em **Java**, com interface gráfica em **Swing** e persistência de dados em **MySQL**.  

Além de cumprir os requisitos da disciplina de Linguagem de Programação II, o projeto proporciona uma aplicação prática de conceitos como:

- Programação Orientada a Objetos
- Tratamento de Exceções
- Integração com Banco de Dados

---

## 🛠️ Tecnologias Utilizadas

- Java 8+
- Swing (Java GUI)
- MySQL
- JDBC
- Apache NetBeans
- Git / GitHub

---

## 🚀 Como Executar o Projeto

1. Clone este repositório:
   ```bash
   git clone https://github.com/liedsonpaiva/Sistema-de-Gerenciamento-de-Receitas-Culin-rias.git

