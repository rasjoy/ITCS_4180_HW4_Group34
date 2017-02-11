package com.example.joyrasmussen.hw4_group34;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Trivia extends AppCompatActivity {
    Button prevButton;
    Button nextButton;
    TextView questionNum;
    TextView questionText;
    TextView countDown;
    TextView loadImage;
    ImageView image;
    ProgressBar progressBar;
    int index;
    int arraySize; //creating this since array.size is called so many times.
    ArrayList<Question> questionArray;
    RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        questionArray = (ArrayList<Question>) getIntent().getSerializableExtra("QUESTIONS");
        Log.d("Array", questionArray.size()+"");
        arraySize = questionArray.size();
        //initialize the app
        prevButton = (Button) findViewById(R.id.previousButton);
        prevButton.setEnabled(false);
        index = 0;

        nextButton = (Button) findViewById(R.id.nextButton);
        questionNum = (TextView) findViewById(R.id.qNumText);
        questionText = (TextView) findViewById(R.id.questionText);
        countDown = (TextView) findViewById(R.id.timerText);
        radioGroup = (RadioGroup) findViewById(R.id.questionChoice);
        image = (ImageView) findViewById(R.id.imageView);
        progressBar = (ProgressBar) findViewById(R.id.loadingProgress);
        loadImage = (TextView) findViewById(R.id.loadingImageText);

        displayQuestion();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });
        new CountDownTimer(120000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                countDown.setText("Time Left: " + millisUntilFinished /1000 + " seconds");
            }

            @Override
            public void onFinish() {
                //also start Stats class here
                Intent intent = new Intent(Trivia.this, StatsActivity.class);
                intent.putExtra("QUESTIONS", questionArray);
                startActivity(intent);


            }
        }.start();

    }

    public void onPreviousListener(View v){
        setUserAnswer();
        index--;
        image.setVisibility(View.INVISIBLE);
        loadImage.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        if(index == 0){
            prevButton.setEnabled(false);
        }
        else if(nextButton.getText().toString().equals("Finish")){
            nextButton.setText("Next");

        }
        displayQuestion();



    }

    public void onNextListener(View v){
        setUserAnswer();
        if(index == arraySize - 1){

            //start stats
            Intent intent = new Intent(Trivia.this, StatsActivity.class);
            intent.putExtra("QUESTIONS", questionArray);
            startActivity(intent);
            finish();
            return;
        }else {
            image.setVisibility(View.INVISIBLE);
            loadImage.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            index++;
            displayQuestion();
            if (index == 1) {
                prevButton.setEnabled(true);
            }
            if (index == arraySize - 1) {
                //finish the app hear
                nextButton.setText("Finish");

            }
        }

    }
    public void displayQuestion(){
        //since it is called so many times, easier to just make temp question object
        Question question = questionArray.get(index);
        //loading picture
        //Log.d("Image:", image.getMaxHeight() + " " + image.getMaxWidth() );

        if( question.getImageURL() != null){
            Picasso.with(Trivia.this).load(question.getImageURL()).fit().centerInside().into(image, new Callback() {
                @Override
                public void onSuccess() {
                    image.setVisibility(View.VISIBLE);
                    loadImage.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onError() {

                }
            });
        }else{
            loadImage.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        }
        //set the question number
        questionText.setText(question.getText());
        questionNum.setText("Q"+(questionArray.get(index).getID()+1));
        radioGroup.clearCheck();
        radioGroup.removeAllViews();
        ArrayList<String> choices = question.getChoices();
        for(int i = 0; i < choices.size(); i ++){
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(choices.get(i));
            radioButton.setId(i);
            radioGroup.addView(radioButton);
        }
        if(question.getUserGuess() != -1){
            radioGroup.check(question.getUserGuess() - 1);
        }

    }
    public void setUserAnswer(){
        questionArray.get(index).setUserGuess(radioGroup.getCheckedRadioButtonId()+ 1);
        Log.d("User:", questionArray.get(index).getUserGuess() + "");
    }

}
