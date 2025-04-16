package com.example.trabalho2025;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// Classe responsável pelo gerenciamento do banco de dados SQLite no Android
public class DBHelper extends SQLiteOpenHelper {

    // Nome do banco de dados e sua versão
    private static final String DATABASE_NAME = "produtos_cabelo.db";  // Nome do arquivo do banco de dados
    private static final int DATABASE_VERSION = 1;  // Versão inicial do banco de dados

    // Nome da tabela e colunas do banco de dados
    public static final String TABELA_PRODUTOS = "produtos";  // Nome da tabela de produtos
    public static final String COLUNA_ID = "id";  // Coluna para armazenar o ID do produto
    public static final String COLUNA_NOME = "nome";  // Coluna para armazenar o nome do produto
    public static final String COLUNA_TIPO = "tipo";  // Coluna para armazenar o tipo do produto
    public static final String COLUNA_PRECO = "preco";  // Coluna para armazenar o preço do produto

    // Comando SQL para criar a tabela de produtos
    private static final String CRIAR_TABELA_PRODUTOS = "CREATE TABLE " + TABELA_PRODUTOS + " ("
            + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "  // ID autoincrementado como chave primária
            + COLUNA_NOME + " TEXT, "  // Coluna para o nome do produto
            + COLUNA_TIPO + " TEXT, "  // Coluna para o tipo do produto
            + COLUNA_PRECO + " REAL)";  // Coluna para o preço do produto

    // Construtor da classe DBHelper que chama o construtor da superclasse SQLiteOpenHelper
    public DBHelper(Context context) {
        // Passa o contexto, nome do banco de dados, cursor factory (null) e versão para o super construtor
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Método chamado na criação do banco de dados
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Executa o comando SQL para criar a tabela de produtos
        db.execSQL(CRIAR_TABELA_PRODUTOS);
    }

    // Método chamado quando a versão do banco de dados é alterada
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Se a versão for alterada, remove a tabela antiga
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_PRODUTOS);
        // Chama o onCreate para criar a nova estrutura do banco
        onCreate(db);
    }

    // Método para adicionar um novo produto ao banco de dados
    public long adicionarProduto(String nome, String tipo, double preco) {
        // Obtém o banco de dados em modo escrita
        SQLiteDatabase db = this.getWritableDatabase();

        // Cria um objeto ContentValues para armazenar os dados do novo produto
        ContentValues values = new ContentValues();
        values.put(COLUNA_NOME, nome);  // Adiciona o nome do produto
        values.put(COLUNA_TIPO, tipo);  // Adiciona o tipo do produto
        values.put(COLUNA_PRECO, preco);  // Adiciona o preço do produto

        // Insere os dados na tabela e retorna o ID do novo produto inserido
        return db.insert(TABELA_PRODUTOS, null, values);
    }

    // Método para obter todos os produtos cadastrados no banco de dados
    public Cursor exibirProdutos() {
        // Obtém o banco de dados em modo leitura
        SQLiteDatabase db = this.getReadableDatabase();
        // Retorna todos os produtos usando uma consulta SQL
        return db.rawQuery("SELECT * FROM " + TABELA_PRODUTOS, null);
    }

    // Método para alterar os dados de um produto já cadastrado
    public boolean alterarProduto(int id, String nome, String tipo, double preco) {
        // Obtém o banco de dados em modo escrita
        SQLiteDatabase db = this.getWritableDatabase();

        // Cria um objeto ContentValues com os novos dados para o produto
        ContentValues values = new ContentValues();
        values.put(COLUNA_NOME, nome);  // Novo nome do produto
        values.put(COLUNA_TIPO, tipo);  // Novo tipo do produto
        values.put(COLUNA_PRECO, preco);  // Novo preço do produto

        // Atualiza o produto no banco de dados com base no ID
        int result = db.update(TABELA_PRODUTOS, values, COLUNA_ID + " = ?", new String[]{String.valueOf(id)});

        // Retorna verdadeiro se a atualização foi bem-sucedida (ou seja, se uma linha foi afetada)
        return result > 0;
    }

    // Método para excluir um produto do banco de dados
    public boolean excluirProduto(int id) {
        // Obtém o banco de dados em modo escrita
        SQLiteDatabase db = this.getWritableDatabase();

        // Exclui o produto com base no ID
        int result = db.delete(TABELA_PRODUTOS, COLUNA_ID + " = ?", new String[]{String.valueOf(id)});

        // Retorna verdadeiro se a exclusão foi bem-sucedida (ou seja, se uma linha foi excluída)
        return result > 0;
    }
}
