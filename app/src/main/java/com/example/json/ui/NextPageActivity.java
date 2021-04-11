package com.example.json.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.json.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class NextPageActivity extends AppCompatActivity {
    String imageid;
    ImageView largeImage;
    Bitmap bitmap;
    Button ext;
    private static final int WRITE_EXTERNAL_STORAGE_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_page);

        ext=findViewById(R.id.exit);


        Intent getting_values = getIntent();
        imageid = getting_values.getStringExtra("imagename");
//       String id = getting_values.getStringExtra("idnum");
        largeImage = findViewById(R.id.nextpageimage);
        Glide.with(NextPageActivity.this).load(imageid).into(largeImage);
        largeImage.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down));
//        Picasso.get().load(imageid).into(largeImage);
        //   Picasso.get(NextPageActivity.this).load(imageid).into(largeImage);

    }

    private void chekSelfPermission(String writeExternalStorage) {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case WRITE_EXTERNAL_STORAGE_CODE:
                if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                }else {
                    Toast.makeText(this, "Permission Enable ", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_db, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.download){


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_DENIED) {
                    String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    requestPermissions(permission, WRITE_EXTERNAL_STORAGE_CODE);
                }else {
                    saveImage();
                }
            }


        }
        if(id == R.id.share){
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT,imageid);
            shareIntent.setType("text/plain");
            startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.send_to)));
        }
        return super.onOptionsItemSelected(item);
    }
    public void saveImage() {

        bitmap = ((BitmapDrawable) largeImage.getDrawable()).getBitmap();
        String time = new SimpleDateFormat("yyyyMMdd_HHss", Locale.getDefault()).format(System.currentTimeMillis());
        File pathtogo = Environment.getExternalStorageDirectory();
        File dir = new File(pathtogo + "/DCIM");
        dir.mkdirs();
        String imageName = time + ".PNG";
        File file = new File(dir, imageName);
        OutputStream out;
        try {
            out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
            Toast.makeText(getApplicationContext(), "Downloaded", Toast.LENGTH_LONG).show();

        } catch (Exception all) {
            Toast.makeText(getApplicationContext(), all.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


}
