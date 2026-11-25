package org.example.dados;

import org.example.model.Categoria;
import org.example.model.Fornecedor;
import org.example.model.Produto;

public class BancoDeDados {

    public static final Tabela<Categoria> categorias = new Tabela<>();
    public static final Tabela<Fornecedor> fornecedores = new Tabela<>();
    public static final Tabela<Produto> produtos = new Tabela<>();

    static {
        Categoria bebidas = categorias.inserir(new Categoria("Bebidas", "Refrigerantes, sucos e água"));
        Categoria limpeza = categorias.inserir(new Categoria("Limpeza", "Produtos de limpeza em geral"));

        Fornecedor distribuidora = fornecedores.inserir(
                new Fornecedor("Distribuidora Central", "12.345.678/0001-90", "(11) 3333-4444"));
        Fornecedor atacadao = fornecedores.inserir(
                new Fornecedor("Atacado Bom Preço", "98.765.432/0001-10", "(11) 5555-6666"));

        produtos.inserir(new Produto("Refrigerante 2L", 9.90, 20, bebidas, distribuidora));
        produtos.inserir(new Produto("Água Mineral 500ml", 2.50, 50, bebidas, distribuidora));
        produtos.inserir(new Produto("Detergente 500ml", 3.20, 3, limpeza, atacadao));
    }

    private BancoDeDados() {
    }
}
