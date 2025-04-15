package com.example.trabalho2025;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	// Declaração do objeto responsável pelo acesso ao banco de dados
	private ProdutoDAO produtoDAO;

	// Declaração dos elementos da interface do usuário
	private EditText edtNome, edtTipo, edtPreco;
	private Button btnAdicionar;
	private ListView listViewProdutos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_main); // Define o layout da atividade

    	// Inicializa a instância do DAO para manipulação do banco de dados
    	produtoDAO = new ProdutoDAO(this);
    	produtoDAO.open(); // Abre a conexão com o banco de dados

    	// Associa os elementos da interface aos componentes do layout
    	edtNome = findViewById(R.id.edtNome);
    	edtTipo = findViewById(R.id.edtTipo);
    	edtPreco = findViewById(R.id.edtPreco);
    	btnAdicionar = findViewById(R.id.btnAdicionar);
    	listViewProdutos = findViewById(R.id.listViewProdutos);

    	// Define um listener para o botão de adicionar produto
    	btnAdicionar.setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v) {
            	// Obtém os valores digitados pelo usuário
            	String nome = edtNome.getText().toString();
            	String tipo = edtTipo.getText().toString();
           	 
            	// Converte o valor do preço para double
            	double preco = Double.parseDouble(edtPreco.getText().toString());

            	// Cria um novo objeto Produto com os dados inseridos
            	Produto produto = new Produto(nome, tipo, preco);

            	// Insere o novo produto no banco de dados
            	produtoDAO.inserirProduto(produto);

            	// Atualiza a lista de produtos na interface
            	listarProdutos();
        	}
    	});

    	// Chama o método para exibir a lista de produtos ao iniciar a atividade
    	listarProdutos();
	}

	/**
 	* Método responsável por recuperar todos os produtos do banco de dados
 	* e exibi-los na ListView.
 	*/
	private void listarProdutos() {
    	// Obtém todos os produtos armazenados no banco de dados
    	List<Produto> produtos = produtoDAO.obterTodosProdutos();

    	// Cria um adaptador para exibir os produtos na ListView
    	ArrayAdapter<Produto> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, produtos);

    	// Define o adaptador na ListView
    	listViewProdutos.setAdapter(adapter);
	}

	@Override
	protected void onDestroy() {
    	// Fecha a conexão com o banco de dados para evitar vazamento de memória
    	produtoDAO.close();
    	super.onDestroy();
	}
}
