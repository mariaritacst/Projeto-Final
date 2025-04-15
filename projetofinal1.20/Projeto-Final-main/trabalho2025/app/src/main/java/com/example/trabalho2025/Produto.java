package com.example.trabalho2025;

public class Produto {
	// Atributos privados da classe Produto
	private int id;     	// Identificador único do produto
	private String nome;	// Nome do produto
	private String tipo;	// Tipo ou categoria do produto
	private double preco;   // Preço do produto

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

	/**
 	* Retorna o ID do produto.
 	* @return ID do produto.
 	*/
	public int getId() {
    	return id;
	}

	/**
 	* Define o ID do produto.
 	* @param id Novo ID do produto.
 	*/
	public void setId(int id) {
    	this.id = id;
	}

	/**
 	* Retorna o nome do produto.
 	* @return Nome do produto.
 	*/
	public String getNome() {
    	return nome;
	}

	/**
 	* Define o nome do produto.
 	* @param nome Novo nome do produto.
 	*/
	public void setNome(String nome) {
    	this.nome = nome;
	}

	/**
 	* Retorna o tipo do produto.
 	* @return Tipo do produto.
 	*/
	public String getTipo() {
    	return tipo;
	}

	/**
 	* Define o tipo do produto.
 	* @param tipo Novo tipo do produto.
 	*/
	public void setTipo(String tipo) {
    	this.tipo = tipo;
	}

	/**
 	* Retorna o preço do produto.
 	* @return Preço do produto.
 	*/
	public double getPreco() {
    	return preco;
	}

	/**
 	* Define o preço do produto, garantindo que não seja negativo.
 	* @param preco Novo preço do produto.
 	* @throws IllegalArgumentException Se o preço for negativo.
 	*/
	public void setPreco(double preco) {
    	if (preco < 0) {
        	throw new IllegalArgumentException("O preço não pode ser negativo.");
    	}
    	this.preco = preco;
	}

	// Método toString para exibir uma representação legível do produto
	/**
 	* Retorna uma representação textual do objeto Produto.
 	* @return String formatada com as informações do produto.
 	*/
	@Override
	public String toString() {
    	return "Nome do Produto: " + nome +
           	" \nTipo do Produto: " + tipo +
           	" \nPreço: " + preco;
	}
}
