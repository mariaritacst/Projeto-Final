ProdutoDAO.java

package com.example.trabalho2025;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
	private SQLiteDatabase database; // Referência ao banco de dados SQLite
	private final DBHelper dbHelper; // Instância do DBHelper para gerenciar o banco de dados

	/**
 	* Construtor da classe ProdutoDAO.
 	* Inicializa o DBHelper para gerenciar a conexão com o banco de dados.
 	*
 	* @param context O contexto da aplicação.
 	*/
	public ProdutoDAO(Context context) {
    	dbHelper = new DBHelper(context);
	}

	/**
 	* Abre a conexão com o banco de dados em modo escrita.
 	* Deve ser chamado antes de realizar operações no banco.
 	*
 	* @throws SQLException Se ocorrer um erro ao abrir o banco de dados.
 	*/
	public void open() throws SQLException {
    	database = dbHelper.getWritableDatabase();
	}

	/**
 	* Fecha a conexão com o banco de dados.
 	* Deve ser chamado quando o banco não for mais necessário.
 	*/
	public void close() {
    	dbHelper.close();
	}

	// Inserir um novo produto
	/**
 	* Insere um novo produto no banco de dados.
 	*
 	* @param produto Objeto Produto a ser inserido.
 	*/
	public void inserirProduto(Produto produto) {
    	ContentValues valores = new ContentValues();
    	valores.put(DBHelper.COLUNA_NOME, produto.getNome());  // Insere o nome do produto
    	valores.put(DBHelper.COLUNA_TIPO, produto.getTipo());  // Insere o tipo do produto
    	valores.put(DBHelper.COLUNA_PRECO, produto.getPreco()); // Insere o preço do produto

    	// Insere os valores na tabela de produtos
    	database.insert(DBHelper.TABELA_PRODUTOS, null, valores);
	}

	// Obter todos os produtos
	/**
 	* Obtém a lista de todos os produtos armazenados no banco de dados.
 	*
 	* @return Lista de objetos Produto.
 	*/
	@SuppressLint("Range")
	public List<Produto> obterTodosProdutos() {
    	List<Produto> produtos = new ArrayList<>();

    	// Consulta todos os registros da tabela de produtos
    	Cursor cursor = database.query(DBHelper.TABELA_PRODUTOS, null, null, null, null, null, null);

    	// Verifica se a consulta retornou resultados e move para o primeiro registro
    	if (cursor != null && cursor.moveToFirst()) {
        	do {
            	// Cria um objeto Produto a partir dos dados obtidos no cursor
            	@SuppressLint("Range") Produto produto = new Produto(
                    	cursor.getString(cursor.getColumnIndex(DBHelper.COLUNA_NOME)),  // Obtém o nome do produto
                    	cursor.getString(cursor.getColumnIndex(DBHelper.COLUNA_TIPO)),  // Obtém o tipo do produto
                    	cursor.getDouble(cursor.getColumnIndex(DBHelper.COLUNA_PRECO))  // Obtém o preço do produto
            	);

            	// Define o ID do produto usando os dados do cursor
            	produto.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.COLUNA_ID)));

            	// Adiciona o produto à lista de produtos
            	produtos.add(produto);
        	} while (cursor.moveToNext()); // Move para o próximo registro

        	// Fecha o cursor após a leitura dos dados
        	cursor.close();
    	}

    	return produtos; // Retorna a lista de produtos
	}
}

