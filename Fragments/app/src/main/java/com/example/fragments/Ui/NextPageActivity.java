package com.example.fragments.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fragments.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class NextPageActivity extends AppCompatActivity {
    String  imageid;
    ImageView largeImage;
    Bitmap bitmap;
    URL url;
    URI uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_page);
        Intent getting_values = getIntent();
        imageid = getting_values.getStringExtra("imagename");
        String id = getting_values.getStringExtra("idnum");
        largeImage = findViewById(R.id.nxtpgeimage);
        Glide.with(NextPageActivity.this).load(imageid).into(largeImage);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custom_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.downloadBtn){

            bitmap =((BitmapDrawable)largeImage.getDrawable()).getBitmap();
            String time = new SimpleDateFormat("yyyyMMdd_HHss", Locale.getDefault()).format(System.currentTimeMillis());
            File pathtogo = Environment.getExternalStorageDirectory();
            File dir = new File(pathtogo+"/DCIM");
            dir.mkdirs();
            String imageName =time+".PNG";
            File file = new File(dir,imageName);
            OutputStream out ;
            try {
                out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG,100,out);
                out.flush();
                out.close();
                Toast.makeText(getApplicationContext(),"Downloaded",Toast.LENGTH_LONG).show();

            }catch (Exception all){
                Toast.makeText(getApplicationContext(),all.getMessage(),Toast.LENGTH_LONG).show();
            }




        }
        if(id == R.id.sharebtn){


            //this will share Image url


            Uri path = Uri.parse(imageid);

            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(Intent.EXTRA_TEXT,imageid);

            startActivity(Intent.createChooser(sharingIntent,"sending Image"));


            // this will share Image Directly

            //   Uri path  =Uri.parse();



        }
        return super.onOptionsItemSelected(item);
    }
    }
