package com.example.trabalho2025;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class LogoutActivity extends AppCompatActivity {

    private Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        // Inicializando o botão de logout
        buttonLogout = findViewById(R.id.buttonLogout);

        // Ação do botão de logout
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sai do aplicativo
                finishAffinity();  // Fecha todas as atividades
            }
        });
    }
}
