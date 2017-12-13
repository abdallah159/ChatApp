package com.example.abdallahmohammed.chatapplication.model;

/**
 * Created by Abdallah Mohammed on 12/12/2017.
 */

public class LoginData {
    private String email ;
    private String password;
    private String fbid ;

    public LoginData(String email, String password, String fbid) {
        this.email = email;
        this.password = password;
        this.fbid = fbid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFbid() {
        return fbid;
    }

    public void setFbid(String fbid) {
        this.fbid = fbid;
    }
}
