# Store CRUD

Aplicação desktop simples em Java para gerenciar produtos em um banco de dados MySQL, com interface gráfica feita em Swing. Projeto desenvolvido para as aulas de Desenvolvimento de Sistemas.

## Funcionalidades

- Inserir produtos (nome e preço)
- Listar todos os produtos cadastrados
- Atualizar nome e/ou preço de um produto existente
- Remover um produto (com confirmação)

Todas as operações são feitas através de janelas de diálogo (`JOptionPane`).

## Tecnologias

- Java (Swing/AWT para a interface)
- MySQL (via JDBC)
- Conector MySQL incluído em `lib/mysql-conn.jar`

## Estrutura do projeto

```
CRUD/
├── src/
│   ├── Main.java                 # Ponto de entrada, monta a janela principal
│   ├── InterfaceComponents.java  # Componentes visuais reutilizáveis (botões, textos)
│   ├── InterfaceLogic.java       # Lógica de cada operação (insert, list, update, delete)
│   ├── DbConnection.java         # Conexão com o banco de dados
│   ├── ProductDAO.java           # Acesso aos dados (queries SQL)
│   └── Product.java              # Modelo de produto
├── lib/
│   └── mysql-conn.jar            # Driver JDBC do MySQL
└── bin/                          # Classes compiladas
```

## Banco de dados

A aplicação espera um banco MySQL local, na porta padrão, com o seguinte schema:

```sql
CREATE DATABASE loja;

USE loja;

CREATE TABLE produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    preco DOUBLE NOT NULL
);
```

## Credenciais do banco

O usuário e a senha do MySQL **não estão fixos no código**. Na primeira operação que acessa o banco, a aplicação abre um formulário pedindo usuário e senha (a senha fica mascarada). As credenciais ficam apenas em memória durante a execução (não são salvas em nenhum arquivo).

Isso significa que qualquer pessoa com um MySQL local, na porta padrão, com o banco `loja` e a tabela `produtos` criados, consegue rodar a aplicação usando a própria senha.

Se a senha informada estiver incorreta, a aplicação mostra o erro retornado pelo banco e pede as credenciais novamente na próxima tentativa.

## Como executar

1. Ter o MySQL instalado e rodando localmente, com o banco `loja` e a tabela `produtos` criados (ver seção acima).
2. Compilar o projeto incluindo o driver JDBC no classpath:
   ```bash
   javac -cp lib/mysql-conn.jar -d bin src/*.java
   ```
3. Executar:
   ```bash
   java -cp bin:lib/mysql-conn.jar Main
   ```
   No Windows, use `;` no lugar de `:` no classpath:
   ```bash
   java -cp "bin;lib/mysql-conn.jar" Main
   ```
4. Informar usuário e senha do MySQL quando solicitado.

## Observações

- A URL de conexão (host, porta e nome do banco) está fixa em `DbConnection.java`. Apenas usuário e senha são solicitados em tempo de execução.