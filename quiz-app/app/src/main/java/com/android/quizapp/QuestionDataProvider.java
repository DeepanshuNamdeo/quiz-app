package com.android.quizapp;

public class QuestionDataProvider {

    private String questions;
    private String options;
    private String answer;

    public QuestionDataProvider(String question, String options, String answer) {
        this.questions = question;
        this.answer = answer;
        this.options = options;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "QuestionDataProvider{" +
                "questions='" + questions + '\'' +
                ", options='" + options + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
