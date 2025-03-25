package com.example.trabalho2025;

public class Produto {
    private int id;
    private String nome;
    private String tipo;
    private double preco;

    // Construtor com id e validação de preço
    public Produto(int id, String nome, String tipo, double preco) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;

        // Validação do preço (não pode ser negativo)
        if (preco < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo.");
        }
        this.preco = preco;
    }

    // Construtor sem ID (o ID pode ser gerado automaticamente, por exemplo, com um contador)
    public Produto(String nome, String tipo, double preco) {
        this(0, nome, tipo, preco); // Chama o construtor principal com id 0
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo.");
        }
        this.preco = preco;
    }

    // Método toString para exibir uma representação legível do produto
    @Override
    public String toString() {
        return "Nome do Produto: " + nome + " \nTipo do Produto: " + tipo + " \nPreço: " + preco;
    }
}
