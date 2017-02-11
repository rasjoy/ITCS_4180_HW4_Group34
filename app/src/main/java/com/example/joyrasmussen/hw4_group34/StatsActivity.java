package com.example.joyrasmussen.hw4_group34;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    ArrayList<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        questions = (ArrayList<Question>) getIntent().getSerializableExtra("QUESTIONS");

        for(Question q : questions){

            String text = q.getText();
            ArrayList<String> choices = q.choices;

            String userAnswer = choices.get(q.getUserGuess() + 1);
            String actualAnswer = choices.get(q.getAnswer());

            boolean correct = userAnswer.equals(actualAnswer);


        }


        linearLayout = (LinearLayout) findViewById(R.id.linLayout);

        TextView valueTV = new TextView(this);
        valueTV.setText("hallo hallo");

        valueTV.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT));

        ((LinearLayout) linearLayout).addView(valueTV);



    }
}
