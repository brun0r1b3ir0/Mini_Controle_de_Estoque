# Mini Controle de Estoque: divisão de tarefas

Pessoal, separei o trabalho em 4 partes, uma para cada um. Nosso prazo é **28/11 (A2)** no Teams, e lembrando que **cada um de nós precisa postar o projeto completo individualmente**.

## Visão geral do sistema

Vamos fazer um sistema simples em Java (Swing) para controlar o estoque de uma loja.

> **Versão do Java:** estamos usando o **Java 27**. Instalem o JDK 27 e configurem no projeto antes de começar, senão vai dar erro de compilação.

### Requisitos do enunciado e onde cada um entra

| Requisito | Como vamos atender | Responsável |
|---|---|---|
| Mini mundo com 2 ou 3 classes de domínio | `Categoria`, `Fornecedor`, `Produto` | M1 |
| CRUD de todas as classes de domínio | Uma tela de cadastro por classe | M2 e M3 |
| Tela de negócio | Tela de **Movimentação de Estoque** (entrada e saída de produtos) | M4 |
| Simular base de dados em uma tabela | Classe `Tabela<T>` que guarda os registros em uma lista na memória | M1 |
| Arquivo de interface gráfica (.frm) | Telas feitas no GUI Builder (`JFrame Form`), que gera o arquivo de formulário junto do `.java` | M2, M3 e M4 |

> **Sobre o .frm:** o editor visual (NetBeans ou IntelliJ) gera arquivos `.form`. acho que precisa confirmar se é isso que ele quer. De qualquer forma, vamos montar todas as telas no editor visual, para que o arquivo de formulário exista e vá junto na entrega.

### Mini mundo

- **Categoria**: agrupa produtos (ex.: Bebidas, Limpeza).
  - `id`, `nome`, `descricao`
- **Fornecedor**: quem fornece os produtos.
  - `id`, `nome`, `cnpj`, `telefone`
- **Produto**: item do estoque.
  - `id`, `nome`, `preco`, `quantidade`, `categoria` (Categoria), `fornecedor` (Fornecedor)

Regra de negócio: a quantidade de um produto **só muda pela tela de Movimentação** (entrada soma, saída subtrai, e a saída não pode deixar o estoque negativo).

### Estrutura de pacotes

Vamos seguir essa estrutura para tudo se encaixar no final:

```
src/main/java/org/example/
├── Main.java                 -> abre a TelaPrincipal (M4)
├── model/                    -> classes de domínio (M1)
│   ├── Categoria.java
│   ├── Fornecedor.java
│   └── Produto.java
├── dados/                    -> "banco de dados" simulado (M1)
│   ├── Tabela.java
│   └── BancoDeDados.java
└── view/                     -> telas (M2, M3, M4)
    ├── TelaPrincipal.java / .form
    ├── TelaCategoria.java / .form
    ├── TelaFornecedor.java / .form
    ├── TelaProduto.java / .form
    └── TelaMovimentacao.java / .form
```

---

## M1: Classes de domínio e banco de dados simulado

Essa parte precisa sair primeiro, porque as telas dependem dela. Meta: **25/11**.

Atividades:

1. **Criar as classes de domínio** no pacote `model`:
   - `Categoria`, `Fornecedor` e `Produto`, com atributos privados, construtor, getters e setters (encapsulamento).
   - Sobrescrever `toString()` retornando o nome (para aparecer certinho nos `JComboBox`).
   - Em `Produto`, criar os métodos de negócio:
     - `entrada(int qtd)`: soma à quantidade (qtd deve ser > 0).
     - `saida(int qtd)`: subtrai da quantidade e lança `IllegalArgumentException` se não houver estoque suficiente.
2. **Criar a classe genérica `Tabela<T>`** no pacote `dados`, que simula uma tabela do banco:
   - Guarda os registros em um `ArrayList<T>` e controla um **id auto-incremento**.
   - Métodos: `inserir(T obj)`, `alterar(T obj)`, `excluir(int id)`, `buscarPorId(int id)`, `listarTodos()`.
   - Dica: criar uma interface `Entidade` com `getId()`/`setId()` e fazer as três classes implementarem, assim a `Tabela<T extends Entidade>` funciona para todas.
3. **Criar a classe `BancoDeDados`** com uma tabela estática para cada entidade:
   ```java
   public static final Tabela<Categoria> categorias = new Tabela<>();
   public static final Tabela<Fornecedor> fornecedores = new Tabela<>();
   public static final Tabela<Produto> produtos = new Tabela<>();
   ```
   - Incluir alguns dados iniciais (2 categorias, 2 fornecedores, 3 produtos) para facilitar os testes.
4. Fazer um teste rápido no `Main` (inserir, listar, alterar, excluir) e subir para o repositório.

---

## M2: CRUD de Categoria e Fornecedor (telas)

Atividades:

1. **Criar a `TelaCategoria`** (JFrame Form, no editor visual):
   - Campos: `id` (somente leitura), `nome`, `descricao`.
   - Uma `JTable` listando todas as categorias.
   - Botões: **Novo**, **Salvar** (inclui ou altera), **Excluir**, **Limpar**.
   - Ao clicar numa linha da tabela, carregar os dados nos campos (consulta).
   - Usar `BancoDeDados.categorias` para todas as operações.
2. **Criar a `TelaFornecedor`** seguindo o mesmo padrão:
   - Campos: `id`, `nome`, `cnpj`, `telefone`.
3. **Validações simples**: nome obrigatório, confirmar antes de excluir (`JOptionPane.showConfirmDialog`).
4. **Regra de exclusão**: não permitir excluir categoria/fornecedor que esteja sendo usado por algum produto (mostrar mensagem).
5. Testar as duas telas e subir para o repositório.

> Enquanto o M1 termina as classes, já dá para ir montando o layout das telas no editor visual.

---

## M3: CRUD de Produto (tela)

Atividades:

1. **Criar a `TelaProduto`** (JFrame Form, no editor visual):
   - Campos: `id` (somente leitura), `nome`, `preco`.
   - `quantidade` **somente leitura** (ela só muda na tela de movimentação).
   - `JComboBox` de **Categoria** e de **Fornecedor**, preenchidos a partir do `BancoDeDados`.
   - `JTable` listando os produtos (id, nome, categoria, fornecedor, preço, quantidade).
   - Botões: **Novo**, **Salvar**, **Excluir**, **Limpar**.
   - Ao clicar numa linha, carregar os dados nos campos (inclusive selecionar a categoria e o fornecedor certos nos combos).
2. **Validações**: nome obrigatório, preço numérico e maior que zero, categoria e fornecedor selecionados. Tratar `NumberFormatException` com mensagem amigável.
3. Recarregar os combos quando a tela for aberta (para pegar categorias/fornecedores novos).
4. Testar e subir para o repositório.

---

## M4: Tela de negócio, menu principal e entrega

Atividades:

1. **Criar a `TelaMovimentacao`** (tela de negócio, JFrame Form):
   - `JComboBox` de produtos, mostrando o estoque atual do produto selecionado.
   - Campo de **quantidade** e opção **Entrada / Saída** (`JRadioButton`).
   - Botão **Confirmar**: chama `produto.entrada(qtd)` ou `produto.saida(qtd)` e salva com `BancoDeDados.produtos.alterar(...)`.
   - Mostrar erro com `JOptionPane` se a saída for maior que o estoque.
   - `JTable` com os produtos e suas quantidades, destacando os com **estoque baixo** (ex.: menos de 5 unidades), opcionalmente com um rótulo "Itens com estoque baixo: X".
2. **Criar a `TelaPrincipal`** (JFrame Form) com um `JMenuBar` ou botões:
   - Cadastros → Categorias, Fornecedores, Produtos.
   - Estoque → Movimentação.
   - Sair.
3. **Alterar o `Main`** para abrir a `TelaPrincipal` (usar `public static void main(String[] args)` e `java.awt.EventQueue.invokeLater`).
4. **Integração final**: juntar as partes, testar o fluxo completo (cadastrar categoria → fornecedor → produto → dar entrada → dar saída) e corrigir o que faltar.
5. **Preparar a entrega**: compactar o projeto (sem a pasta `target/`) e mandar no grupo para todo mundo postar no Teams.

---

## Cronograma

| Data | O que precisamos ter pronto |
|---|---|
| 25/11 | M1 finaliza model + banco simulado. M2, M3 e M4 montam os layouts das telas. |
| 26/11 | M2, M3 e M4 ligam as telas ao `BancoDeDados`. |
| 27/11 | M4 junta tudo; todos testamos o fluxo completo e corrigimos os bugs. |
| 28/11 | Cada um posta o projeto no Teams. |

## Combinados

- Não mexer no código de outro integrante sem avisar no grupo.
- Cada um trabalha na sua branch (`m1-model`, `m2-crud-cat-forn`, `m3-crud-produto`, `m4-negocio`) e faz merge na `main` quando terminar.
- Vamos usar exatamente os nomes de classes, pacotes e métodos que estão aqui, para não dar problema na hora de juntar.
