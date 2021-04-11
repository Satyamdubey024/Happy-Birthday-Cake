package com.example.fragments.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fragments.Data.RoomData;
import com.example.fragments.R;
import com.example.fragments.Ui.NextPageActivity;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    Context mcontext;
    List<RoomData> body;

    public CustomAdapter(Context mcontext, List<RoomData>body){
        this.mcontext = mcontext;
        this.body = body;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_block, null);
        MyViewHolder mvh = new MyViewHolder(view);
               return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ImageView imgv;
        imgv =holder.img;
        RelativeLayout relativeLayout;
        final String imgid = body.get(position).image;
        Glide.with(mcontext).load(imgid).into(imgv);
        relativeLayout = holder.relativeLayout;
        final String id = body.get(position).id;
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, NextPageActivity.class);
                intent.putExtra("idnum", id);
                intent.putExtra("imagename", imgid);
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return body.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

         ImageView img;
         RelativeLayout relativeLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.img = itemView.findViewById(R.id.ImgRecycle);
            this.relativeLayout = itemView.findViewById(R.id.Relative);
        }
    }

}
