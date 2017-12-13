package com.example.abdallahmohammed.chatapplication.model;

/**
 * Created by Abdallah Mohammed on 12/12/2017.
 */

public class MessagesList {
    private String message;
    private String date ;

    public MessagesList() {
    }

    public MessagesList(String message, String date) {
        this.message = message;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
