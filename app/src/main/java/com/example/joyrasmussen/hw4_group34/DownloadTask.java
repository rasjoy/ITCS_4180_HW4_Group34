package com.example.joyrasmussen.hw4_group34;


import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class DownloadTask extends AsyncTask<String, Void, String> {

    MainActivity mainActivity;

    public DownloadTask(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected String doInBackground(String... params) {

        StringBuilder result = new StringBuilder();
        URL url;
        HttpURLConnection urlConnection = null;

        try {

            url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);

            int data = reader.read();

            while (data != -1) {

                char current = (char) data;
                result.append(current);

                data = reader.read();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return result.toString();
    }

    @Override
    protected void onPostExecute(String s) {

        ArrayList<Question> questions = new ArrayList<Question>();

        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray questionsJSONArray = jsonObject.getJSONArray("questions");

            for (int i = 0; i < questionsJSONArray.length(); i++) {

                int id, answer;
                String text, imageURL;
                ArrayList<String> choices = new ArrayList<String>();

                JSONObject current = (JSONObject) questionsJSONArray.get(i);

                id = current.getInt("id");
                text = current.getString("text");

                if(!current.isNull("image")) {
                    imageURL = current.getString("image");
                } else {
                    imageURL = null;
                }

                JSONObject choicesArray = current.getJSONObject("choices");

                answer = choicesArray.getInt("answer");

                JSONArray choiceOptions = choicesArray.getJSONArray("choice");

                for(int j = 0; j < choiceOptions.length(); j++){

                    String choice = choiceOptions.getString(j);
                    choices.add(choice);

                }

                Question q = new Question(id, text, answer, imageURL, choices);
                questions.add(q);
            }

            mainActivity.setQuestions(questions);
            mainActivity.changeUI();



        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onPostExecute(s);
    }
}
