package com.example.fragments.Rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    // http://mapi.trycatchtech.com/v1/room_painting_ideas/get_post

    public static final String BASE_URL ="http://mapi.trycatchtech.com/";
    private static Retrofit retrofit = null;

    //Creating retrofit object
    public static Retrofit getClient(){
        if (retrofit == null){
            retrofit  = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
                    GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

}
