package com.example.json.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.json.Adapt.BWDAdap;
import com.example.json.Adapt.BWDRecAdapter;
import com.example.json.DATA.BWDCelebty;
import com.example.json.R;
import com.example.json.Rest.ApiClient;
import com.example.json.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParsingRetrofitActivity extends AppCompatActivity {

    private String TAG = ParsingRetrofitActivity.class.getSimpleName();
    private String gender;
    TextView txt;
   RecyclerView recyclerView;
    GridView grid;
    List<BWDCelebty> BWCelebList;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
 //   private ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parsing_retrofit);
       grid =  findViewById(R.id.cakegrid);
       TextView txt =(TextView) findViewById(R.id.textview99);

      //  recyclerView=findViewById(R.id.reclervw);
      //  showProgDig();
        getBWCeleb();
    }



  /*  private void showProgDig() {
        pDialog = new ProgressDialog(ParsingRetrofitActivity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();
    }
*/
    private void getBWCeleb() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<List<BWDCelebty>> call = apiService.getCelebList();

        call.enqueue(new Callback<List<BWDCelebty>>() {
            @Override
            public void onResponse(Call<List<BWDCelebty>> call, Response<List<BWDCelebty>> response) {
          //      if(pDialog.isShowing());
          //      pDialog.dismiss();

                BWCelebList = response.body();
            /*   recyclerView = findViewById(R.id.reclervw);
                layoutManager = new LinearLayoutManager(ParsingRetrofitActivity.this);
                adapter = new BWDRecAdapter(ParsingRetrofitActivity.this,BWCelebList);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);*/





             if(BWCelebList != null);
                {
                    Log.d(TAG,"Number of Celeb received:" + BWCelebList.size());

                    BWDAdap adapter = new BWDAdap(ParsingRetrofitActivity.this, BWCelebList);
                    grid.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down));
                    grid.setAdapter(adapter);
                    grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(ParsingRetrofitActivity.this, ParsingsActivity.class);
                            intent.putExtra("Images", BWCelebList.get(position).getId());
                            ParsingRetrofitActivity.this.startActivity(intent);


//                           Toast.makeText(ParsingRetrofitActivity.this,
//                                    "You clicked at"+BWCelebList.get(position).getCat_name(),
//                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }


            }

            @Override
            public void onFailure(Call<List<BWDCelebty>> call, Throwable t) {
                Log.e(TAG, t.toString());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }
    public void onBackPressed () {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to Exit?")
                .setCancelable(false)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ParsingRetrofitActivity.super.onBackPressed();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }

}
