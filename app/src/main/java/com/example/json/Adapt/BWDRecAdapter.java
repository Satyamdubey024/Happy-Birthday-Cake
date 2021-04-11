package com.example.json.Adapt;

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
import com.example.json.DATA.BWDClebtys;
import com.example.json.R;
import com.example.json.ui.NextPageActivity;
import com.example.json.ui.ParsingsActivity;

import java.util.List;

public class BWDRecAdapter extends RecyclerView.Adapter<BWDRecAdapter.myViewHolder>{
    Context mcontext;
    List<BWDClebtys> body;

    public BWDRecAdapter(Context mcontext, List<BWDClebtys> body) {
        this.mcontext = mcontext;
        this.body = body;
    }

    @NonNull

    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_grid, null);
        myViewHolder myview = new myViewHolder(view);

        return myview;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        ImageView imgv;
        imgv = holder.imgview;
       RelativeLayout relativeLayout;
        final String imgid = body.get(position).getImage();
        Glide.with(mcontext).load(imgid).into(imgv);
      relativeLayout = holder.relativeLayout;
     final String id = body.get(position).getId();

       relativeLayout.setOnClickListener(new View.OnClickListener() {
           @Override
        public void onClick(View v) {
                Intent intent = new Intent(mcontext, NextPageActivity.class);
//                intent.putExtra("idnum",id);
               intent.putExtra("imagename",imgid);
              mcontext.startActivity(intent);
            }
       });
    }

    @Override
    public int getItemCount() {
        return body.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        ImageView imgview;
        RelativeLayout relativeLayout;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            this.imgview = itemView.findViewById(R.id.bWDimage);
            this.relativeLayout = itemView.findViewById(R.id.singlevwrelative);

        }
    }
}
