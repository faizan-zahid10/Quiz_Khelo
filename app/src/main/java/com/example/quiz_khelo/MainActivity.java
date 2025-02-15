package com.example.quiz_khelo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    //    hooks
    Button btnStart;
    EditText etname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        // setting start button click listener
        btnStart.setOnClickListener(v -> {
            String entered_name = etname.getText().toString().trim(); // Get text input

            // checking if name is entered
            if (!entered_name.isEmpty()) {
                // redirect to QuizActivity
                Intent i = new Intent(MainActivity.this, Quiz.class);
                i.putExtra("user_name", entered_name);
                startActivity(i);
                finish();
            } else {
                etname.setError("Please enter your name!");
            }
        });
    }

    private void init() {
//        connection establishing
        btnStart = findViewById(R.id.btnStart);
        etname = findViewById(R.id.etname);
    }
}