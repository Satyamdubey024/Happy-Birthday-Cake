package com.example.json.Adapt;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.json.DATA.BWDCelebty;
import com.example.json.R;

import java.util.List;


public class BWDAdap extends BaseAdapter{


   private Context icontext;
    List<BWDCelebty> BWCelebList;
    ImageView imageView;

    public BWDAdap(Context c, List<BWDCelebty> BWCelebList) {
        icontext = c;
        this.BWCelebList = BWCelebList;
    }

    @Override
    public int getCount() {
        return BWCelebList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) icontext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(icontext);
            grid = inflater.inflate(R.layout.bollywood_grid_single, null);

          TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            imageView = (ImageView)grid.findViewById(R.id.bWDimage);
      textView.setText(BWCelebList.get(position).getCat_name());

            String imageurl = BWCelebList.get(position).getImage();

            Glide.with(icontext).load(imageurl).into(imageView);

        } else {
            grid = (View) convertView;
        }

        return grid;


    }
}
