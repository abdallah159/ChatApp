package com.example.abdallahmohammed.chatapplication.rest;

import com.example.abdallahmohammed.chatapplication.model.LoginData;
import com.example.abdallahmohammed.chatapplication.model.LoginResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Abdallah Mohammed on 12/12/2017.
 */

public interface ApiInterfaceLogin {
    @POST("v1/signin")
    Call<LoginResult>getStatus(@Body LoginData loginData);

}
