package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenuActivity extends AppCompatActivity {

    Button btnStartTrivia,btnStartPractice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        btnStartTrivia = findViewById(R.id.btnStartTrivia);
        btnStartPractice = findViewById(R.id.btnStartPractice);

        btnStartTrivia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, TriviaGameActivity.class);
                startActivity(intent);
            }
        });

        btnStartPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, PracticeModeActivity.class);
                startActivity(intent);
            }
        });
    }
}
