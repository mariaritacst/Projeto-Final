// Define o pacote ao qual essa classe pertence
package com.example.trabalho2025;

// Importa as classes necessárias do Android
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

// Classe LogoutActivity herda de AppCompatActivity, representando uma tela da aplicação
public class LogoutActivity extends AppCompatActivity {

	// Declaração do botão que será usado para realizar o logout
	private Button buttonLogout;

	// Método chamado quando a atividade é criada
	@Override
	protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState); // Chama o método da superclasse para manter o comportamento padrão
    	setContentView(R.layout.activity_logout); // Define o layout XML que será usado por esta atividade

    	// Associa o botão declarado com o componente do layout (com o ID "buttonLogout")
    	buttonLogout = findViewById(R.id.buttonLogout);

    	// Define um listener para capturar cliques no botão de logout
    	buttonLogout.setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v) {
            	// Encerra todas as atividades da aplicação que estão na pilha de atividades
            	// Isso efetivamente "fecha" o app e retorna ao menu inicial do dispositivo
            	finishAffinity();
        	}
    	});
	}
}
