package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenuActivity extends AppCompatActivity {

    Button btnStartTrivia,btnStartPractice,btnLeaderboard,btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        btnStartTrivia = findViewById(R.id.btnStartTrivia);
        btnStartPractice = findViewById(R.id.btnStartPractice);
        btnLeaderboard = findViewById(R.id.btnLeaderboard);
        btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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

        btnLeaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, LeaderboardActivity.class);
                startActivity(intent);
            }
        });
    }
}
