package com.example.quiz_khelo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class Quiz extends AppCompatActivity {

    private List<Mcq> questionList;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private TextView tvQuestion;
    private RadioGroup rgOptions;
    private RadioButton rbOption1, rbOption2, rbOption3, rbOption4;
    private Button btnNext;
    private TextView tvQuestionNumber;
    private ImageView btnPrevious;
    String userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("user_name")) {
            userName = intent.getStringExtra("user_name");
        } else {
            userName = "Unnamed Player";
        }

        Toast.makeText(this, "Welcome user " + userName, Toast.LENGTH_SHORT).show();

        init();
        loadQuestions();
        showQuestion();


        // Handle next button click
        btnNext.setOnClickListener(v -> handleNextQuestion());
        btnPrevious.setOnClickListener(v -> handlePreviousClicking());
    }

    private void handlePreviousClicking() {
        if (currentQuestionIndex != questionList.size()) {
            btnNext.setText(R.string.next_btn_text);
        }
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--;
            showQuestion();
        }
    }

    private void init() {
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        tvQuestion = findViewById(R.id.tvQuestion);
        rgOptions = findViewById(R.id.rgOptions);
        rbOption1 = findViewById(R.id.rbOption1);
        rbOption2 = findViewById(R.id.rbOption2);
        rbOption3 = findViewById(R.id.rbOption3);
        rbOption4 = findViewById(R.id.rbOption4);
        btnNext = findViewById(R.id.btnNext);
        btnPrevious = findViewById(R.id.btnPrevious);
    }

    private void loadQuestions() {
        questionList = new ArrayList<>();
        questionList.add(new Mcq("Which team won the ICC Cricket World Cup 2023?", new String[]{"India", "England", "Australia", "Pakistan"}, 2));
        questionList.add(new Mcq("Who is the current captain of the Indian Test cricket team (as of 2024)?", new String[]{"Virat Kohli", "Rohit Sharma", "KL Rahul", "Hardik Pandya"}, 1));
        questionList.add(new Mcq("Which Java feature allows a class to inherit from another class?", new String[]{"Encapsulation", "Polymorphism", "Inheritance", "Abstraction"}, 2));
        questionList.add(new Mcq("What is the national animal of Pakistan?", new String[]{"Lion", "Markhor", "Tiger", "Elephant"}, 1));
        questionList.add(new Mcq("Which fruit is known as the 'King of Fruits'?", new String[]{"Apple", "Mango", "Banana", "Orange"}, 1));
        questionList.add(new Mcq("Who was Pakistan's captain when they won the 1992 Cricket World Cup?", new String[]{"Javed Miandad", "Wasim Akram", "Imran Khan", "Inzamam-ul-Haq"}, 2));
        questionList.add(new Mcq("Which Java collection allows duplicate elements?", new String[]{"Set", "List", "Map", "Queue"}, 1));
        questionList.add(new Mcq("Which country won the ICC T20 World Cup 2009?", new String[]{"India", "Pakistan", "Sri Lanka", "Australia"}, 1));
        questionList.add(new Mcq("What is the capital of Pakistan?", new String[]{"Lahore", "Islamabad", "Karachi", "Peshawar"}, 1));
        questionList.add(new Mcq("Who is current white-ball captain of Pakistan Team'?", new String[]{"Babar Azam", "Fakhar Zaman", "Muhammad Rizwan", "Agha Salman"}, 2));
    }

    private void showQuestion() {
        if (currentQuestionIndex < questionList.size() && currentQuestionIndex > 0) {
            Mcq q = questionList.get(currentQuestionIndex);
            tvQuestionNumber.setText(String.valueOf(currentQuestionIndex + 1) + "/" + questionList.size());
            tvQuestion.setText(q.getQuestionText());
            rbOption1.setText(q.getOptions()[0]);
            rbOption2.setText(q.getOptions()[1]);
            rbOption3.setText(q.getOptions()[2]);
            rbOption4.setText(q.getOptions()[3]);
            btnPrevious.setClickable(true);
            btnPrevious.setAlpha(1f);
            // Clear previous selection
            rgOptions.clearCheck();
        } else if (currentQuestionIndex < questionList.size() && currentQuestionIndex == 0) {
            Mcq q = questionList.get(currentQuestionIndex);
            tvQuestionNumber.setText(String.valueOf(currentQuestionIndex + 1) + "/" + questionList.size());
            tvQuestion.setText(q.getQuestionText());
            rbOption1.setText(q.getOptions()[0]);
            rbOption2.setText(q.getOptions()[1]);
            rbOption3.setText(q.getOptions()[2]);
            rbOption4.setText(q.getOptions()[3]);
            btnPrevious.setClickable(false);
            btnPrevious.setAlpha(0.3f);
            // Clear previous selection
            rgOptions.clearCheck();
        } else {
            // Redirect to result page
            Intent intent = new Intent(this, ScoreCard.class);
            intent.putExtra("final_score", score);
            intent.putExtra("name_of_user", userName);
            intent.putExtra("total_questions_count", questionList.size());
            startActivity(intent);
            finish();
        }
    }

    private int getSelectedOption() {
        int selectedId = rgOptions.getCheckedRadioButtonId();
        if (selectedId == rbOption1.getId()) return 0;
        if (selectedId == rbOption2.getId()) return 1;
        if (selectedId == rbOption3.getId()) return 2;
        if (selectedId == rbOption4.getId()) return 3;
        return -1; // No option selected
    }

    private void handleNextQuestion() {
        int selectedOptionIndex = getSelectedOption();
        if (selectedOptionIndex == -1) {
            Toast.makeText(this, "Please select an option!", Toast.LENGTH_SHORT).show();
            return;
        }

        int correctAnswerIndex = questionList.get(currentQuestionIndex).getCorrectAnswerIndex();
        if (selectedOptionIndex == correctAnswerIndex) {
            score++;
        }

        currentQuestionIndex++;

        if (currentQuestionIndex == questionList.size() - 1) {
            btnNext.setText(R.string.finish_btn_text);
        }
        showQuestion();
    }
}
