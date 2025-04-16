// Define o pacote ao qual essa classe pertence
package com.example.trabalho2025;

// Importações necessárias para a atividade e elementos da interface
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	// Objeto responsável pela manipulação do banco de dados (DAO = Data Access Object)
	private ProdutoDAO produtoDAO;

	// Elementos da interface (campos de texto, botão e lista)
	private EditText edtNome, edtTipo, edtPreco;
	private Button btnAdicionar;
	private ListView listViewProdutos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_main); // Define o layout da tela principal

    	// Inicializa o DAO e abre conexão com o banco de dados
    	produtoDAO = new ProdutoDAO(this);
    	produtoDAO.open();

    	// Mapeia os elementos da interface com os IDs do layout XML
    	edtNome = findViewById(R.id.edtNome);
    	edtTipo = findViewById(R.id.edtTipo);
    	edtPreco = findViewById(R.id.edtPreco);
    	btnAdicionar = findViewById(R.id.btnAdicionar);
    	listViewProdutos = findViewById(R.id.listViewProdutos);

    	// Define a ação que ocorre quando o botão de adicionar é clicado
    	btnAdicionar.setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v) {
            	// Coleta os dados digitados nos campos de texto
            	String nome = edtNome.getText().toString();
            	String tipo = edtTipo.getText().toString();
            	double preco = Double.parseDouble(edtPreco.getText().toString()); // Converte string para double

            	// Cria um objeto Produto com os dados fornecidos
            	Produto produto = new Produto(nome, tipo, preco);

            	// Insere o produto no banco de dados usando o DAO
            	produtoDAO.inserirProduto(produto);

            	// Atualiza a exibição da lista com os novos dados
            	listarProdutos();
        	}
    	});

    	// Mostra os produtos existentes assim que a atividade é iniciada
    	listarProdutos();
	}

	/**
 	* Recupera os produtos do banco de dados e atualiza a ListView.
 	*/
	private void listarProdutos() {
    	// Recupera todos os produtos armazenados
    	List<Produto> produtos = produtoDAO.obterTodosProdutos();

    	// Cria um adaptador para exibir os produtos em uma lista simples
    	ArrayAdapter<Produto> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, produtos);

    	// Define o adaptador na ListView
    	listViewProdutos.setAdapter(adapter);
	}

	@Override
	protected void onDestroy() {
    	// Fecha a conexão com o banco de dados ao destruir a atividade
    	produtoDAO.close();
    	super.onDestroy();
	}
}
