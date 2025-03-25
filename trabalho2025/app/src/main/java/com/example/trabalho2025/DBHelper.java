DBHelper.java
package com.example.trabalho2025;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// Classe responsável pelo gerenciamento do banco de dados
public class DBHelper extends SQLiteOpenHelper {

	// Nome do banco de dados e versão
	private static final String DATABASE_NAME = "produtos_cabelo.db";
	private static final int DATABASE_VERSION = 1;

	// Nome da tabela e colunas
	public static final String TABELA_PRODUTOS = "produtos";
	public static final String COLUNA_ID = "id";
	public static final String COLUNA_NOME = "nome";
	public static final String COLUNA_TIPO = "tipo";
	public static final String COLUNA_PRECO = "preco";

	// Comando SQL para criar a tabela de produtos
	private static final String CRIAR_TABELA_PRODUTOS = "CREATE TABLE " + TABELA_PRODUTOS + " ("
        	+ COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " // ID autoincrementado
        	+ COLUNA_NOME + " TEXT, " // Nome do produto
        	+ COLUNA_TIPO + " TEXT, " // Tipo do produto
        	+ COLUNA_PRECO + " REAL)"; // Preço do produto

	// Construtor da classe
	public DBHelper(Context context) {
    	super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Método chamado na criação do banco de dados
	@Override
	public void onCreate(SQLiteDatabase db) {
    	db.execSQL(CRIAR_TABELA_PRODUTOS); // Executa o comando SQL para criar a tabela
	}

	// Método chamado quando a versão do banco de dados é alterada
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	db.execSQL("DROP TABLE IF EXISTS " + TABELA_PRODUTOS); // Remove a tabela antiga
    	onCreate(db); // Cria a nova estrutura
	}

	// Método para adicionar um novo produto ao banco de dados
	public long adicionarProduto(String nome, String tipo, double preco) {
    	SQLiteDatabase db = this.getWritableDatabase(); // Obtém o banco de dados em modo escrita

    	ContentValues values = new ContentValues();
    	values.put(COLUNA_NOME, nome);
    	values.put(COLUNA_TIPO, tipo);
    	values.put(COLUNA_PRECO, preco);

    	// Insere o produto na tabela e retorna o ID do novo registro
    	return db.insert(TABELA_PRODUTOS, null, values);
	}

	// Método para obter todos os produtos cadastrados no banco de dados
	public Cursor exibirProdutos() {
    	SQLiteDatabase db = this.getReadableDatabase(); // Obtém o banco de dados em modo leitura
    	return db.rawQuery("SELECT * FROM " + TABELA_PRODUTOS, null); // Retorna todos os produtos
	}

	// Método para alterar um produto já cadastrado
	public boolean alterarProduto(int id, String nome, String tipo, double preco) {
    	SQLiteDatabase db = this.getWritableDatabase(); // Obtém o banco de dados em modo escrita

    	ContentValues values = new ContentValues();
    	values.put(COLUNA_NOME, nome);
    	values.put(COLUNA_TIPO, tipo);
    	values.put(COLUNA_PRECO, preco);

    	// Atualiza o produto no banco de dados
    	int result = db.update(TABELA_PRODUTOS, values, COLUNA_ID + " = ?", new String[]{String.valueOf(id)});

    	// Retorna verdadeiro se alguma linha foi afetada, senão retorna falso
    	return result > 0;
	}

	// Método para excluir um produto pelo ID
	public boolean excluirProduto(int id) {
    	SQLiteDatabase db = this.getWritableDatabase(); // Obtém o banco de dados em modo escrita

    	// Exclui o produto da tabela
    	int result = db.delete(TABELA_PRODUTOS, COLUNA_ID + " = ?", new String[]{String.valueOf(id)});

    	// Retorna verdadeiro se algum registro foi excluído, senão retorna falso
    	return result > 0;
	}
}
