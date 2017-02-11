package com.example.joyrasmussen.hw4_group34;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable{

    int ID;
    String text;
    int answer;
    String imageURL;
    ArrayList<String> choices;
    int userGuess;

    public int getID() {
        return ID;
    }

    public String getText() {
        return text;
    }

    public int getAnswer() {
        return answer;
    }

    public String getImageURL() {
        return imageURL;
    }

    public ArrayList<String> getChoices() {
        return choices;
    }

    public int getUserGuess() {
        return userGuess;
    }

    public void setUserGuess(int userGuess) {

        this.userGuess = userGuess;
    }

    public Question(int ID, String text, int answer, String imageURL, ArrayList<String> choices) {
        this.ID = ID;
        this.text = text;
        this.answer = answer;
        this.imageURL = imageURL;
        this.choices = choices;
        userGuess= -1;
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
