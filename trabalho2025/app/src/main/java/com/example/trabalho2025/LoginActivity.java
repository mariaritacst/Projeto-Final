package com.example.trabalho2025;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.trabalho2025.databinding.ActivityLoginBinding;

// Classe responsável pela tela de login
public class LoginActivity extends AppCompatActivity {

	// Variável para o binding, que facilita a manipulação dos elementos da interface
	private ActivityLoginBinding binding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);

    	// Infla o layout usando ViewBinding
    	binding = ActivityLoginBinding.inflate(getLayoutInflater());
    	setContentView(binding.getRoot()); // Define o layout da activity

    	// Configuração do clique no botão de login
    	binding.btnLogin.setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v) {
            	// Cria uma intenção para navegar para a MainActivity
            	Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            	startActivity(intent); // Inicia a MainActivity
            	finish(); // Fecha a LoginActivity para que o usuário não volte ao login ao pressionar "Voltar"
        	}
    	});
	}
}
