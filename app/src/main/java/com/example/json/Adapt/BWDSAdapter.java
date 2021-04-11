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
import com.example.json.DATA.BWDClebtys;
import com.example.json.R;

import java.util.List;


public class BWDSAdapter extends BaseAdapter {

    private Context mcontext;
    List<BWDClebtys> BWDCelebList;
    ImageView imageV;

    public BWDSAdapter(Context c, List<BWDClebtys> BWDCelebList) {
        this.mcontext = c;
        this.BWDCelebList = BWDCelebList;
    }


    @Override
    public int getCount() {
        return BWDCelebList.size();
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
        LayoutInflater inflater = (LayoutInflater) mcontext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(mcontext);
            grid = inflater.inflate(R.layout.single_grid, null);


            imageV = (ImageView)grid.findViewById(R.id.bWDimage);

            String imageurl = BWDCelebList.get(position).getImage();

            Glide.with(mcontext).load(imageurl).into(imageV);

        } else {
            grid = (View) convertView;
        }


        return grid;
    }
}
