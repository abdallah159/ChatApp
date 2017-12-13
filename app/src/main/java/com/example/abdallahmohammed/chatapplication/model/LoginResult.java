package com.example.abdallahmohammed.chatapplication.model;

/**
 * Created by Abdallah Mohammed on 12/12/2017.
 */

public class LoginResult {
    private String success;
    private User user ;

    public LoginResult(String success, User user) {
        this.success = success;
        this.user = user;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
