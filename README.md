# Mini Controle de Estoque

Sistema desktop em Java (Swing) para controlar o estoque de uma loja. Permite cadastrar categorias, fornecedores e produtos, e registrar entradas e saídas de estoque. Os dados ficam em memória, em uma tabela simulada, e são perdidos ao fechar o programa.

## Dependências

| Item | Versão | Observação |
|---|---|---|
| JDK | 25 | Obrigatório. O `pom.xml` compila para o Java 25. |
| Maven | 3.x | Opcional. Só é necessário para compilar pela linha de comando com `mvn`. |
| IDE | IntelliJ IDEA ou NetBeans | Opcional. Necessária apenas para editar as telas no editor visual (arquivos `.form`). |

O projeto não usa bibliotecas externas: tudo é feito com a biblioteca padrão do Java (Swing e `java.util`).

## Como iniciar

A classe principal é `org.example.Main`. Ela abre a tela principal do sistema.

### Pela IDE

1. Abra a pasta do projeto no IntelliJ IDEA ou no NetBeans (ela é reconhecida como projeto Maven).
2. Configure o JDK 25 como SDK do projeto.
3. Execute a classe `src/main/java/org/example/Main.java`.

### Pela linha de comando, sem Maven

Na raiz do projeto:

```bash
javac -encoding UTF-8 -d out $(find src/main/java -name "*.java")
java -cp out org.example.Main
```

No PowerShell, troque a primeira linha por:

```powershell
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse src/main/java -Filter *.java).FullName
```

### Pela linha de comando, com Maven

```bash
mvn compile
java -cp target/classes org.example.Main
```

## Como usar

Na tela principal, use o menu:

- **Cadastros > Categorias / Fornecedores / Produtos**: inclui, altera, exclui e consulta registros. Clique em uma linha da tabela para carregar os dados nos campos.
- **Estoque > Movimentação**: escolha um produto, informe a quantidade, marque Entrada ou Saída e clique em Confirmar. Os produtos com menos de 5 unidades aparecem destacados em vermelho.
- **Sistema > Sair**: encerra o programa.

Fluxo sugerido para testar: cadastrar uma categoria, depois um fornecedor, depois um produto e, por fim, dar entrada e saída no estoque desse produto.

O sistema já inicia com alguns dados de exemplo (2 categorias, 2 fornecedores e 3 produtos).

### Regras de negócio

- A quantidade de um produto só muda pela tela de Movimentação. Na tela de Produto ela é somente leitura.
- A saída não pode deixar o estoque negativo.
- Não é possível excluir uma categoria ou um fornecedor que esteja vinculado a algum produto.
- Nome é obrigatório em todos os cadastros. No produto, o preço deve ser maior que zero e a categoria e o fornecedor devem ser selecionados.

## Arquivos do projeto

```
Mini_Controle_de_Estoque/
├── pom.xml                         Configuração do Maven (Java 25, UTF-8)
├── DIVISAO_DE_TAREFAS.md           Divisão do trabalho entre os integrantes
├── README.md                       Este arquivo
└── src/main/java/org/example/
    ├── Main.java                   Ponto de entrada; abre a TelaPrincipal
    ├── model/                      Classes de domínio
    │   ├── Entidade.java           Interface com getId/setId, usada pela Tabela
    │   ├── Categoria.java          id, nome, descricao
    │   ├── Fornecedor.java         id, nome, cnpj, telefone
    │   └── Produto.java            id, nome, preco, quantidade, categoria, fornecedor;
    │                               métodos entrada(qtd) e saida(qtd)
    ├── dados/                      Banco de dados simulado
    │   ├── Tabela.java             Tabela genérica em memória com id auto-incremento
    │   │                           (inserir, alterar, excluir, buscarPorId, listarTodos)
    │   └── BancoDeDados.java       Uma Tabela para cada entidade e os dados iniciais
    └── view/                       Telas (Swing); cada .form é o arquivo do editor visual
        ├── TelaPrincipal.java / .form      Menu principal
        ├── TelaCategoria.java / .form      CRUD de Categoria
        ├── TelaFornecedor.java / .form     CRUD de Fornecedor
        ├── TelaProduto.java / .form        CRUD de Produto
        └── TelaMovimentacao.java / .form   Tela de negócio: entrada e saída de estoque
```

As pastas `target/` e `out/` são geradas na compilação e não fazem parte da entrega.
