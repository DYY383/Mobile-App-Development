package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TriviaGameActivity extends AppCompatActivity {

    private static final int QUESTION_LIMIT = 50;
    private List<Question> allQuestions = new ArrayList<>();
    private List<Question> selectedQuestions = new ArrayList<>();
    private int currentQuestionIndex = 0;


    TextView txtQuestion, txtScore;
    Button btnOption1, btnOption2, btnOption3, btnOption4;

    int score = 0;
    List<Integer> askedIndexes = new ArrayList<>();


    static class Question {
        String questionText;
        String correctAnswer;
        List<String> options;

        Question(String questionText, String correctAnswer, List<String> wrongAnswers) {
            this.questionText = questionText;
            this.correctAnswer = correctAnswer;
            this.options = new ArrayList<>(wrongAnswers);
            this.options.add(correctAnswer);
            Collections.shuffle(this.options);
        }
    }

    List<Question> questions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_game);

        txtQuestion = findViewById(R.id.txtQuestion);
        txtScore = findViewById(R.id.txtScore);
        btnOption1 = findViewById(R.id.btnOption1);
        btnOption2 = findViewById(R.id.btnOption2);
        btnOption3 = findViewById(R.id.btnOption3);
        btnOption4 = findViewById(R.id.btnOption4);

        loadQuestions();
        showNextQuestion();
    }

    void loadQuestions() {
        allQuestions.add(new Question("Capital of France?", "Paris", List.of("Rome", "Madrid", "Berlin")));
        allQuestions.add(new Question("5 + 3 = ?", "8", List.of("6", "9", "7")));
        allQuestions.add(new Question("Largest ocean?", "Pacific", List.of("Atlantic", "Indian", "Arctic")));
        allQuestions.add(new Question("10 x 3?", "30", List.of("33", "23", "13")));
        allQuestions.add(new Question("Water freezes at?", "0°C", List.of("100°C", "10°C", "50°C")));
        allQuestions.add(new Question("Capital of Italy?", "Rome", List.of("Madrid", "Paris", "Berlin")));
        allQuestions.add(new Question("2 + 2 * 2 = ?", "6", List.of("8", "4", "2")));
        allQuestions.add(new Question("Who wrote 'Hamlet'?", "Shakespeare", List.of("Tolstoy", "Hemingway", "Dickens")));
        allQuestions.add(new Question("Earth is the ___ planet from the sun.", "3rd", List.of("2nd", "4th", "5th")));
        allQuestions.add(new Question("H2O is the formula for?", "Water", List.of("Hydrogen", "Oxygen", "Salt")));
        allQuestions.add(new Question("Square root of 64?", "8", List.of("6", "7", "9")));
        allQuestions.add(new Question("Sun rises from the?", "East", List.of("West", "North", "South")));
        allQuestions.add(new Question("100 ÷ 4 = ?", "25", List.of("20", "30", "40")));
        allQuestions.add(new Question("Largest planet?", "Jupiter", List.of("Mars", "Saturn", "Earth")));
        allQuestions.add(new Question("Fastest land animal?", "Cheetah", List.of("Lion", "Tiger", "Leopard")));
        allQuestions.add(new Question("How many continents?", "7", List.of("6", "8", "5")));
        allQuestions.add(new Question("Boiling point of water?", "100°C", List.of("0°C", "50°C", "120°C")));
        allQuestions.add(new Question("Opposite of 'Cold'?", "Hot", List.of("Warm", "Cool", "Freezing")));
        allQuestions.add(new Question("Hexagon has how many sides?", "6", List.of("5", "7", "8")));
        allQuestions.add(new Question("Capital of Japan?", "Tokyo", List.of("Beijing", "Seoul", "Bangkok")));
        allQuestions.add(new Question("Who painted Mona Lisa?", "Leonardo da Vinci", List.of("Picasso", "Michelangelo", "Van Gogh")));
        allQuestions.add(new Question("What gas do plants absorb?", "Carbon Dioxide", List.of("Oxygen", "Nitrogen", "Hydrogen")));
        allQuestions.add(new Question("Tallest mountain?", "Everest", List.of("K2", "Makalu", "Kilimanjaro")));
        allQuestions.add(new Question("Primary color not in RGB?", "Yellow", List.of("Red", "Green", "Blue")));
        allQuestions.add(new Question("Capital of Canada?", "Ottawa", List.of("Toronto", "Vancouver", "Montreal")));
        allQuestions.add(new Question("12 ÷ 3 = ?", "4", List.of("3", "5", "6")));
        allQuestions.add(new Question("Which is a programming language?", "Python", List.of("Snake", "HTML", "Tiger")));
        allQuestions.add(new Question("First man on the moon?", "Neil Armstrong", List.of("Buzz Aldrin", "Yuri Gagarin", "John Glenn")));
        allQuestions.add(new Question("National animal of USA?", "Bald Eagle", List.of("Lion", "Tiger", "Wolf")));
        allQuestions.add(new Question("Light travels in?", "Straight line", List.of("Circle", "Zigzag", "Spiral")));
        allQuestions.add(new Question("What planet is red?", "Mars", List.of("Venus", "Jupiter", "Saturn")));
        allQuestions.add(new Question("3 x 7 = ?", "21", List.of("24", "18", "27")));
        allQuestions.add(new Question("Which is not a fruit?", "Carrot", List.of("Apple", "Mango", "Banana")));
        allQuestions.add(new Question("Gas used in balloons?", "Helium", List.of("Oxygen", "Nitrogen", "Hydrogen")));
        allQuestions.add(new Question("Shape with 4 equal sides?", "Square", List.of("Rectangle", "Triangle", "Rhombus")));
        allQuestions.add(new Question("How many hours in a day?", "24", List.of("12", "20", "48")));
        allQuestions.add(new Question("Smallest prime number?", "2", List.of("1", "3", "5")));
        allQuestions.add(new Question("Animal with trunk?", "Elephant", List.of("Hippo", "Rhino", "Giraffe")));
        allQuestions.add(new Question("Who discovered gravity?", "Newton", List.of("Einstein", "Tesla", "Galileo")));
        allQuestions.add(new Question("First letter of Greek alphabet?", "Alpha", List.of("Beta", "Gamma", "Omega")));
        allQuestions.add(new Question("Which is a mammal?", "Whale", List.of("Shark", "Octopus", "Tuna")));
        allQuestions.add(new Question("Most spoken language?", "English", List.of("Hindi", "Spanish", "Arabic")));
        allQuestions.add(new Question("What color is the sun?", "White", List.of("Yellow", "Orange", "Red")));
        allQuestions.add(new Question("Currency of Japan?", "Yen", List.of("Won", "Dollar", "Peso")));
        allQuestions.add(new Question("Fastest bird?", "Peregrine Falcon", List.of("Eagle", "Hawk", "Owl")));
        allQuestions.add(new Question("Tallest animal?", "Giraffe", List.of("Elephant", "Camel", "Horse")));
        allQuestions.add(new Question("What is 11 + 22?", "33", List.of("32", "31", "34")));
        allQuestions.add(new Question("Which is a herbivore?", "Cow", List.of("Lion", "Bear", "Wolf")));
        allQuestions.add(new Question("What is the capital of Egypt?", "Cairo", List.of("Alexandria", "Giza", "Luxor")));
        allQuestions.add(new Question("Who invented the light bulb?", "Edison", List.of("Tesla", "Newton", "Franklin")));
        allQuestions.add(new Question("Which is not a planet?", "Pluto", List.of("Neptune", "Mars", "Venus")));

        // You can keep adding up to 100+

        // Shuffle & trim to 50
        Collections.shuffle(allQuestions);
        for (int i = 0; i < Math.min(QUESTION_LIMIT, allQuestions.size()); i++) {
            selectedQuestions.add(allQuestions.get(i));
        }
    }



    void showNextQuestion() {
        if (currentQuestionIndex >= selectedQuestions.size()) {
            Toast.makeText(this, "You answered all questions!", Toast.LENGTH_SHORT).show();
            endGame();
            return;
        }

        Question q = selectedQuestions.get(currentQuestionIndex);
        txtQuestion.setText(q.questionText);
        txtScore.setText("Score: " + score);

        btnOption1.setText(q.options.get(0));
        btnOption2.setText(q.options.get(1));
        btnOption3.setText(q.options.get(2));
        btnOption4.setText(q.options.get(3));

        View.OnClickListener listener = view -> checkAnswer(((Button) view).getText().toString(), q.correctAnswer);
        btnOption1.setOnClickListener(listener);
        btnOption2.setOnClickListener(listener);
        btnOption3.setOnClickListener(listener);
        btnOption4.setOnClickListener(listener);
    }



    void checkAnswer(String selected, String correct) {
        if (selected.equals(correct)) {
            score++;
            currentQuestionIndex++;
            showNextQuestion();
        } else {
            Toast.makeText(this, "Wrong! Game Over", Toast.LENGTH_SHORT).show();
            endGame();
        }
    }


    void endGame() {
        // Example: use logged-in username from a static/global session variable
        String currentUsername = LogSignActivity.loggedInUser;

        DBHelper dbHelper = new DBHelper(this, "accounts_database", null, 2);
        dbHelper.updateHighScore(currentUsername, score);

        Intent intent = new Intent(TriviaGameActivity.this, MainMenuActivity.class);
        intent.putExtra("score", score);
        startActivity(intent);
        finish();
    }

}