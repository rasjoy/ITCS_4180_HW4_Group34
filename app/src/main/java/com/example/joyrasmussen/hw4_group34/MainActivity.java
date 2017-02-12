package com.example.joyrasmussen.hw4_group34;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String URL = "http://dev.theappsdr.com/apis/trivia_json/index.php";
    ArrayList<Question> questions;
    Button button;

    public void start(View view){
        Intent intent = new Intent("com.exampe.joy.hw4_group34.intent.action.View");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra("QUESTIONS", questions);
         startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questions = new ArrayList<Question>();
        button = (Button) findViewById(R.id.startButton);
        button.setEnabled(false);
        DownloadTask downloadTask = new DownloadTask(this);
        downloadTask.execute(URL);

    }

    public void changeUI(){
        TextView loadingText = (TextView) findViewById(R.id.loadingTextView);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        ImageView image = (ImageView) findViewById(R.id.imageView);

        button.setEnabled(true);
        button.setBackgroundColor(Color.rgb(51, 181, 229));
        loadingText.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        image.setVisibility(View.VISIBLE);



    }

    public void setQuestions(ArrayList<Question> questions){
        this.questions = questions;
    }

    public void finish(View view){
       finish();
    }
}
