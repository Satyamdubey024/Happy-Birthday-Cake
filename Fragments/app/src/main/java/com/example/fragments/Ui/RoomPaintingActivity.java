package com.example.fragments.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.fragments.Adapter.CustomAdapter;
import com.example.fragments.Data.RoomData;
import com.example.fragments.R;
import com.example.fragments.Rest.ApiClient;
import com.example.fragments.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomPaintingActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    List<RoomData> Body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_painting);
        apiget();
    }

    private void apiget() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        retrofit2.Call<List<RoomData>> call = apiInterface.getRoomList();
        call.enqueue(new Callback<List<RoomData>>() {
            @Override
            public void onResponse(Call<List<RoomData>> call, Response<List<RoomData>> response) {

                Body = response.body();
                recyclerView = findViewById(R.id.RoomPainting);
                layoutManager = new LinearLayoutManager(RoomPaintingActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                adapter = new CustomAdapter(RoomPaintingActivity.this,Body);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<RoomData>> call, Throwable t) {

            }

        });
    }
}

