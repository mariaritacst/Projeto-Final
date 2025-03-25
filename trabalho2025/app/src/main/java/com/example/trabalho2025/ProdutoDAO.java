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
    private SQLiteDatabase database;
    private final DBHelper dbHelper;

    public ProdutoDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    // Inserir um novo produto
    public void inserirProduto(Produto produto) {
        ContentValues valores = new ContentValues();
        valores.put(DBHelper.COLUNA_NOME, produto.getNome());
        valores.put(DBHelper.COLUNA_TIPO, produto.getTipo());
        valores.put(DBHelper.COLUNA_PRECO, produto.getPreco());

        database.insert(DBHelper.TABELA_PRODUTOS, null, valores);
    }

    // Obter todos os produtos
    @SuppressLint("Range")
    public List<Produto> obterTodosProdutos() {
        List<Produto> produtos = new ArrayList<>();

        Cursor cursor = database.query(DBHelper.TABELA_PRODUTOS, null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") Produto produto = new Produto(
                        cursor.getString(cursor.getColumnIndex(DBHelper.COLUNA_NOME)),
                        cursor.getString(cursor.getColumnIndex(DBHelper.COLUNA_TIPO)),
                        cursor.getDouble(cursor.getColumnIndex(DBHelper.COLUNA_PRECO))
                );
                produto.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.COLUNA_ID)));
                produtos.add(produto);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return produtos;
    }
}
