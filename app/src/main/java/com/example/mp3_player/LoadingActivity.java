package com.example.mp3_player;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class LoadingActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        // aula1();

        View button = findViewById(R.id.botao);
        button.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent testeSegundos = new Intent(LoadingActivity.this , MainActivity.class);
                startActivity(testeSegundos);
                finish();            }
        }, 2000);
    }

    public void aula1() {
       View botaoParaClicar = findViewById(R.id.botao);
        botaoParaClicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
