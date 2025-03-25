package com.example.trabalho2025;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProdutoDAO produtoDAO;
    private EditText edtNome, edtTipo, edtPreco;
    private Button btnAdicionar;
    private ListView listViewProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        produtoDAO = new ProdutoDAO(this);
        produtoDAO.open();

        edtNome = findViewById(R.id.edtNome);
        edtTipo = findViewById(R.id.edtTipo);
        edtPreco = findViewById(R.id.edtPreco);
        btnAdicionar = findViewById(R.id.btnAdicionar);
        listViewProdutos = findViewById(R.id.listViewProdutos);

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = edtNome.getText().toString();
                String tipo = edtTipo.getText().toString();
                double preco = Double.parseDouble(edtPreco.getText().toString());

                Produto produto = new Produto(nome, tipo, preco);
                produtoDAO.inserirProduto(produto);

                listarProdutos();
            }
        });

        listarProdutos();
    }

    private void listarProdutos() {
        List<Produto> produtos = produtoDAO.obterTodosProdutos();
        ArrayAdapter<Produto> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, produtos);
        listViewProdutos.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        produtoDAO.close();
        super.onDestroy();
    }
}
