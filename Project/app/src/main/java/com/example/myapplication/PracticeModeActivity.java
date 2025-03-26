package com.example.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class PracticeModeActivity extends AppCompatActivity {

    private TextView questionText;
    private Button[] answerButtons = new Button[4];
    private int currentIndex = 0;

    private Question[] questions = new Question[]{
            new Question("What is the capital of Canada?", new String[]{"Toronto", "Ottawa", "Vancouver", "Montreal"}, 1),
            new Question("What is 5 + 3?", new String[]{"6", "7", "8", "9"}, 2),
            new Question("What color is the sky?", new String[]{"Red", "Green", "Blue", "Yellow"}, 2)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_mode);

        questionText = findViewById(R.id.questionText);
        answerButtons[0] = findViewById(R.id.answerBtn1);
        answerButtons[1] = findViewById(R.id.answerBtn2);
        answerButtons[2] = findViewById(R.id.answerBtn3);
        answerButtons[3] = findViewById(R.id.answerBtn4);

        loadQuestion();

        for (int i = 0; i < answerButtons.length; i++) {
            final int index = i;
            answerButtons[i].setOnClickListener(v -> {
                boolean isCorrect = (index == questions[currentIndex].getCorrectIndex());
                Toast.makeText(this, isCorrect ? "Correct!" : "Wrong!", Toast.LENGTH_SHORT).show();

                currentIndex++;
                if (currentIndex < questions.length) {
                    loadQuestion();
                } else {
                    showCompletionDialog();
                }
            });
        }
    }

    private void loadQuestion() {
        Question q = questions[currentIndex];
        questionText.setText(q.getQuestion());
        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i].setText(q.getAnswers()[i]);
        }
    }

    private void showCompletionDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Practice Complete")
                .setMessage("You've completed all questions.")
                .setPositiveButton("OK", (dialog, which) -> finish())
                .show();
    }
}
