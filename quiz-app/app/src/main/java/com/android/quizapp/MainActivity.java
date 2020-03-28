package com.android.quizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button_start_quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialiseUI();
        setupUIListeners();
    }


    private void initialiseUI() {
        button_start_quiz = findViewById(R.id.button_start_quiz);
    }

    private void setupUIListeners() {
        button_start_quiz.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_start_quiz) {
            AppNavigator.navigateFromTo(this, QuizHomePage.class);
        }
    }
}

















