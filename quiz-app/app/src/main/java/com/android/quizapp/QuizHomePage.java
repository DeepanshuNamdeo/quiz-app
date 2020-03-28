package com.android.quizapp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class QuizHomePage extends AppCompatActivity {

    private static final String DELIMITER = ",";
    public static int marks = 0, correct = 0, wrong = 0;
    ArrayList<QuestionDataProvider> quesArrayList;
    int flag = 0;
    private Button button_next;
    private Button button_quit;
    private TextView textView_question;
    private RadioGroup radioGroup_options;
    private RadioButton rb1, rb2, rb3, rb4;
    private Context mContext;
    private TextView textView_score;
    private TextView textView_instruction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_home_page);

        initialiseUI();
        setupUIListeners();
        getQuestionData();

    }

    @Override
    public void onBackPressed() {
    }

    private void initialiseUI() {

        button_next = findViewById(R.id.button_next);
        button_quit = findViewById(R.id.button_quit);
        textView_question = findViewById(R.id.textView_question);
        textView_score = findViewById(R.id.textView_score);
        textView_instruction = findViewById(R.id.textView_instruction);

        radioGroup_options = findViewById(R.id.radioGroup_answers);
        rb1 = findViewById(R.id.radioButton);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        rb4 = findViewById(R.id.radioButton4);
        button_next.setText(getString(R.string.start));
        mContext = this;
        radioGroup_options.setVisibility(View.INVISIBLE);
        textView_question.setVisibility(View.INVISIBLE);
    }

    private void setupUIListeners() {


        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                radioGroup_options.setVisibility(View.VISIBLE);
                textView_instruction.setVisibility(View.GONE);
                textView_question.setVisibility(View.VISIBLE);
                if (flag == quesArrayList.size() - 1) {
                    marks = correct;
                    AppNavigator.navigateFromTo(mContext, ResultActivity.class);
                } else {
                    if (button_next.getText() == getText(R.string.start)) {
                        button_next.setText(getText(R.string.next));
                        if (flag == 0) {
                            loadQuestionWithOptions();
                        }
                    } else {

                        if (radioGroup_options.getCheckedRadioButtonId() == -1) {
                            Toast.makeText(getApplicationContext(), "Please select one choice", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        RadioButton uans = findViewById(radioGroup_options.getCheckedRadioButtonId());
                        String ansText = uans.getText().toString();
//                Toast.makeText(getApplicationContext(), ansText, Toast.LENGTH_SHORT).show();
                        if (ansText.equals(quesArrayList.get(flag).getAnswer())) {
                            correct++;
                            Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                        } else {
                            wrong++;
                            Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                        }
                        flag++;
                        loadQuestionWithOptions();
                        radioGroup_options.clearCheck();
                        textView_score.setText(String.valueOf(correct));

                    }
                }
            }
        });

        button_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppNavigator.navigateFromTo(mContext, ResultActivity.class);
            }
        });
    }

    private void loadQuestionWithOptions() {

        for (int i = 0; i < quesArrayList.size(); i++) {
            if (i == flag) {
                textView_question.setText(quesArrayList.get(flag).getQuestions());
                String[] options = quesArrayList.get(flag).getOptions().split(DELIMITER);
                rb1.setText(options[0]);
                rb2.setText(options[1]);
                rb3.setText(options[2]);
                rb4.setText(options[3]);
            }
        }


    }

    public void getQuestionData() {
        quesArrayList = new ArrayList<QuestionDataProvider>();
        QuestionDataProvider questionData;
        try {
            JSONArray jArray = new JSONArray(readJSONFromAsset());
            for (int i = 0; i < jArray.length(); ++i) {
                String question = jArray.getJSONObject(i).getString("question");
                String options = jArray.getJSONObject(i).getString("options");
                String answer = jArray.getJSONObject(i).getString("answer");
                questionData = new QuestionDataProvider(question, options, answer);
                quesArrayList.add(i, questionData);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String readJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("questions.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
