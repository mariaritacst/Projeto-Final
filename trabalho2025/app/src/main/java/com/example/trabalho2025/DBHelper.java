package com.example.trabalho2025;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "produtos_cabelo.db";
    private static final int DATABASE_VERSION = 1;


    public static final String TABELA_PRODUTOS = "produtos";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_NOME = "nome";
    public static final String COLUNA_TIPO = "tipo";
    public static final String COLUNA_PRECO = "preco";


    private static final String CRIAR_TABELA_PRODUTOS = "CREATE TABLE " + TABELA_PRODUTOS + " ("
            + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUNA_NOME + " TEXT, "
            + COLUNA_TIPO + " TEXT, "
            + COLUNA_PRECO + " REAL)";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CRIAR_TABELA_PRODUTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_PRODUTOS);
        onCreate(db);
    }
}
