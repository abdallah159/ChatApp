package com.example.abdallahmohammed.chatapplication.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Abdallah Mohammed on 12/12/2017.
 */

public class ApiLogin {
    public static final String BASE_URL = "http://foodservice.4rera.com/api/";
    private static Retrofit retrofit = null ;

    public static Retrofit getClient(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
