package com.example.fragments.Rest;

import com.example.fragments.Data.RoomData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    //http://mapi.trycatchtech.com/v1/room_painting_ideas/get_post

    @GET("v1/room_painting_ideas/get_post")
    Call<List<RoomData>> getRoomList();
}
