package com.example.json.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.Toast;

import com.example.json.Adapt.BWDRecAdapter;
import com.example.json.Adapt.BWDSAdapter;
import com.example.json.DATA.BWDClebtys;
import com.example.json.R;
import com.example.json.Rest.ApiClient;
import com.example.json.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParsingsActivity extends AppCompatActivity {

    private String image;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
//    GridView grids;
    private ProgressDialog iDialog;
    List<BWDClebtys> BWCelebLt;
    private String TAGS = ParsingsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parsings);
//        grids = findViewById(R.id.cakenextgrid);
        recyclerView=findViewById(R.id.recycler);

        image = getIntent().getStringExtra("Images");
        layoutManager = new GridLayoutManager(ParsingsActivity.this,1);
        recyclerView.setLayoutManager(layoutManager);
      //  recyclerView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in));

        showProgDig();
        getBWCeleb();

    }



    private void showProgDig() {
        iDialog = new ProgressDialog(ParsingsActivity.this);
        iDialog.setMessage("Please wait...");
        iDialog.setCancelable(true);
        iDialog.show();
    }

    private void getBWCeleb() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<List<BWDClebtys>> call = apiService.getCelebLt(image);
        call.enqueue(new Callback<List<BWDClebtys>>() {
            @Override
            public void onResponse(Call<List<BWDClebtys>> call, Response<List<BWDClebtys>> response) {
                if(iDialog.isShowing());
                iDialog.dismiss();

                BWCelebLt = response.body();

                if(BWCelebLt != null);
                {
                    Log.d(TAGS,"Number of Celeb received:" + BWCelebLt.size());

                    adapter = new BWDRecAdapter(ParsingsActivity.this,BWCelebLt);

                    recyclerView.setAdapter(adapter);
                    recyclerView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down));


                }

        }

            @Override
            public void onFailure(Call<List<BWDClebtys>> call, Throwable t) {
                Log.e(TAGS, t.toString());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }
}