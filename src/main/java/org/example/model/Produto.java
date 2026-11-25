package org.example.model;

public class Produto implements Entidade {

    private int id;
    private String nome;
    private double preco;
    private int quantidade;
    private Categoria categoria;
    private Fornecedor fornecedor;

    public Produto() {
    }

    public Produto(String nome, double preco, int quantidade, Categoria categoria, Fornecedor fornecedor) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.fornecedor = fornecedor;
    }

    public void entrada(int qtd) {
        if (qtd <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        quantidade += qtd;
    }

    public void saida(int qtd) {
        if (qtd <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        if (qtd > quantidade) {
            throw new IllegalArgumentException("Estoque insuficiente. Disponível: " + quantidade);
        }
        quantidade -= qtd;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public String toString() {
        return nome;
    }
}
