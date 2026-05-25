# AppAgenda 📱

Aplicativo Android desenvolvido no Android Studio utilizando Java e SQLite para cadastro de pessoas.

O sistema permite:

* Cadastrar pessoas
* Listar registros
* Pesquisar pessoas
* Atualizar dados
* Excluir registros
* Armazenar informações em banco de dados SQLite

---

# Tecnologias Utilizadas

* Java
* Android Studio
* SQLite
* ConstraintLayout
* ListView
* SearchView

---

# Funcionalidades

✅ Cadastro de pessoas
✅ Banco de dados SQLite
✅ Pesquisa por nome
✅ Atualização de cadastro
✅ Exclusão de cadastro
✅ Menu principal
✅ Menu de contexto

---

# Estrutura do Projeto

```plaintext
AppAgenda
│
├── MainActivity.java
├── ListarPessoasActitivity.java
├── Pessoa.java
├── PessoaDAO.java
├── Conexao.java
│
├── activity_main.xml
├── activity_listar_pessoa_activity.xml
│
├── menu_principal.xml
└── menu_contexto.xml
```

---

# Banco de Dados

O aplicativo utiliza SQLite para armazenar os dados localmente.

Tabela criada:

```sql
CREATE TABLE pessoa(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome VARCHAR(50),
    cpf VARCHAR(50),
    telefone VARCHAR(50)
)
```

---

# Telas do Aplicativo

## Tela de Cadastro

A tela principal permite:

* Digitar nome
* Digitar CPF
* Digitar telefone
* Salvar os dados

---

## Tela de Listagem

A tela de listagem permite:

* Visualizar pessoas cadastradas
* Pesquisar nomes
* Atualizar cadastro
* Excluir cadastro

---

# CRUD Implementado

O projeto utiliza o conceito CRUD:

| Operação | Função             |
| -------- | ------------------ |
| Create   | Inserir pessoas    |
| Read     | Listar pessoas     |
| Update   | Atualizar cadastro |
| Delete   | Excluir cadastro   |

---

# Como Executar

1. Abrir o Android Studio
2. Selecionar:

   ```plaintext
   Open Project
   ```
3. Abrir a pasta do projeto `AppAgenda`
4. Aguardar sincronização do Gradle
5. Executar o aplicativo

---

# Dependências

Adicionar no arquivo `build.gradle`:

```gradle
implementation 'androidx.appcompat:appcompat:1.6.1'
implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
```

---

# Autor

Projeto desenvolvido para atividade prática de Android Studio utilizando SQLite e Java.
