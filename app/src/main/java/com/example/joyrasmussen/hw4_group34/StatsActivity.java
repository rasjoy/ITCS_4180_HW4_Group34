package com.example.joyrasmussen.hw4_group34;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    ArrayList<Question> questions;
    int correctAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        correctAnswers = 0;

        questions = (ArrayList<Question>) getIntent().getSerializableExtra("QUESTIONS");

        for (Question q : questions) {

            String text = q.getText();
            ArrayList<String> choices = q.choices;
            String userAnswer;
            if(q.getUserGuess() <= 0){
                userAnswer = "";
            }
             else{
             userAnswer = choices.get(q.getUserGuess() -1 );

            }
            String actualAnswer = choices.get(q.getAnswer() - 1);

            makeText(text, userAnswer, actualAnswer);

        }

        updatePercentage();

    }

    public void makeText(String text, String userAnswer, String actualAnswer) {

        boolean correct = userAnswer.equals(actualAnswer);

        if (correct) {
            correctAnswers++;
        }

        linearLayout = (LinearLayout) findViewById(R.id.linLayout);

        TextView textTextView = new TextView(this);
        textTextView.setText(text);
        linearLayout.addView(textTextView);

        TextView choiceTextView = new TextView(this);
        choiceTextView.setText("Your guess: " + userAnswer);
        if (!correct) {
            choiceTextView.setBackgroundColor(Color.RED);
        }
        linearLayout.addView(choiceTextView);

        TextView answerTextView = new TextView(this);
        answerTextView.setText("Answer: " + actualAnswer);
        linearLayout.addView(answerTextView);

        View ruler = new View(this);
        ruler.setBackgroundColor(Color.BLACK);
        linearLayout.addView(ruler,
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2));
    }

    public void updatePercentage() {

        TextView percentageTV = (TextView) findViewById(R.id.percentageTextView);

        int percent = Math.round(correctAnswers * 100 / 16);
        percentageTV.setText(percent + "%");

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setProgress(percent);

    }

    public void finishStats(View view){
        finish();
    }
}
