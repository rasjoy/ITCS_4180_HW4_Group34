package com.example.joyrasmussen.hw4_group34;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable{

    int ID;
    String text;
    int answer;
    String imageURL;
    ArrayList<String> choices;

    public Question(int ID, String text, int answer, String imageURL, ArrayList<String> choices) {
        this.ID = ID;
        this.text = text;
        this.answer = answer;
        this.imageURL = imageURL;
        this.choices = choices;
    }

    @Override
    public String toString() {
        return "Question{" +
                "ID=" + ID +
                ", text='" + text + '\'' +
                ", answer=" + answer +
                ", imageURL='" + imageURL + '\'' +
                ", choices=" + choices +
                '}';
    }



}