package com.example.quiz_khelo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ScoreCard extends AppCompatActivity {

    TextView tvSampleName, tvScore;
    ImageView btn_Scoreboard_Previous;
    Button btn_share_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_score_card);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        String name_of_user = getIntent().getStringExtra("name_of_user");
        int final_score = getIntent().getIntExtra("final_score", 0);
        String final_score_count = String.valueOf(final_score);
        int total_questions = getIntent().getIntExtra("total_questions_count", 0);
        String total_questions_count = String.valueOf(total_questions);

        tvSampleName.setText(name_of_user);
        tvScore.setText(final_score_count + "/" + total_questions_count);

        String finalMessage = "I played QuizKhelo and scored " + tvScore.getText().toString();

        btn_Scoreboard_Previous.setOnClickListener(v -> handle_result_previous());
        btn_share_score.setOnClickListener(v -> handle_share_score(finalMessage));
    }

    private void handle_result_previous() {
        Intent i = new Intent(ScoreCard.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void handle_share_score(String share_message) {

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_TEXT, share_message);

        startActivity(Intent.createChooser(i, "Share score by"));
    }

    private void init() {
        tvSampleName = findViewById(R.id.tvSampleName);
        tvScore = findViewById(R.id.tvScore);
        btn_Scoreboard_Previous = findViewById(R.id.btn_Scoreboard_Previous);
        btn_share_score = findViewById(R.id.btn_share_score);
    }
}