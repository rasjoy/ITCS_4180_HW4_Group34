package com.example.joyrasmussen.hw4_group34;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Trivia extends AppCompatActivity {
    Button prevButton;
    Button nextButton;
    TextView questionNum;
    int index;
    ArrayList<Question> questionArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        questionArray = new ArrayList<Question>();
        //initialize the app
        prevButton = (Button) findViewById(R.id.previousButton);
        prevButton.setEnabled(false);
        index = 0;

        nextButton = (Button) findViewById(R.id.nextButton);
        questionNum = (TextView) findViewById(R.id.qNumText);







    }

    public void onPreviousListener(View v){
            index--;
        if(index == 0){
            prevButton.setEnabled(false);
        }
        if(index == questionArray.size() -1 ){
            nextButton.setText("Next");

        }



    }

    public void onNextListener(View v){
        if(index == questionArray.size()){
            //finish the app hear


        }
        index--;

        if(index == questionArray.size()){
            //finish the app hear
            nextButton.setText("Finish");

        }


    }
}
