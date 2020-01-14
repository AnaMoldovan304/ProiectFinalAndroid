package com.example.finalandroid;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    private static int nrOfMessage = 1;

    private String username;

    private String message;

    private String currentTime;

    private boolean favorite;


    public Message() {
        this.username = "Computer";
        this.message = "Automated message: " + nrOfMessage;
        this.currentTime = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss").format(new Date());
        favorite = false;
        nrOfMessage++;
    }


    public Message(String username, String message) {
        this.username = username;
        this.message = message;
        this.currentTime = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss").format(new Date());
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public void changeFavorite(){
        this.favorite = !this.favorite;
    }

}
