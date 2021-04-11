package com.example.json.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.json.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler abc = new Handler();



        abc.postDelayed(new Runnable() {
            @Override
            public void run() {


                Intent intent = new Intent(SplashActivity.this, ParsingRetrofitActivity.class);
                startActivity(intent);


                finish();//To close this activity so that login activity won't go back to splash screen
            }
        },5000);

    }
}
