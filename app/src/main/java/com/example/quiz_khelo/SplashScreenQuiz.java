package com.example.quiz_khelo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashScreenQuiz extends AppCompatActivity {
    ImageView ivlogo; //hooks

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen_quiz);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

//        Splash screen show up for 4 seconds and redirect to MainActivity then
        new Handler().postDelayed(() -> {
            Intent i = new Intent(SplashScreenQuiz.this, MainActivity.class);
            startActivity(i);
            finish();

        }, 4000);

//        Applying animation on the logo
        Animation logo_fade_in = AnimationUtils.loadAnimation(SplashScreenQuiz.this, R.anim.logo_fade_in);
        ivlogo.startAnimation(logo_fade_in);
    }

    private void init() {
//        connection with backend
        ivlogo = findViewById(R.id.ivlogo);
    }
}