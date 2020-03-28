package com.android.quizapp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView textView_correct;
    private Button button_done;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        initialiseUI();
        setupUIListeners();
        mContext = this;
    }

    private void initialiseUI() {
        textView_correct = findViewById(R.id.textView_correct);
        button_done = findViewById(R.id.button_done);
        textView_correct.setText(String.format(getResources().getString(R.string.correct_answer), QuizHomePage.correct));

    }

    @Override
    public void onBackPressed() {
    }

    private void setupUIListeners() {
        button_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuizHomePage.correct = 0;
                AppNavigator.navigateFromTo(mContext, MainActivity.class);
            }
        });
    }
}
