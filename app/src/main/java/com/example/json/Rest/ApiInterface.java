package com.example.json.Rest;

import com.example.json.DATA.BWDCelebty;
import com.example.json.DATA.BWDClebtys;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("v1/happy_birthday_cake/get_categories")
    Call<List<BWDCelebty>> getCelebList();

    @GET("v1/happy_birthday_cake/get_post")
    Call<List<BWDClebtys>> getCelebLt(@Query("category_id") String imageId);


}
