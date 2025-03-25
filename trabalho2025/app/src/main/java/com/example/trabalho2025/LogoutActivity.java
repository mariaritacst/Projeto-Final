
package com.example.trabalho2025;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class LogoutActivity extends AppCompatActivity {

	// Declaração do botão de logout
	private Button buttonLogout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_logout); // Define o layout da atividade

    	// Inicializa o botão de logout associando-o ao ID do layout
    	buttonLogout = findViewById(R.id.buttonLogout);

    	// Define um ouvinte de clique para o botão de logout
    	buttonLogout.setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v) {
            	// Finaliza todas as atividades da pilha atual, encerrando o aplicativo
            	finishAffinity();
        	}
    	});
	}
}
