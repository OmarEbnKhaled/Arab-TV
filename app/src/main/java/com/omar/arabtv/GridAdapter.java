package com.omar.arabtv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class GridAdapter extends BaseAdapter {

    private Context context;
    private List<VideoModel> list;

    private LayoutInflater inflater;

    public GridAdapter(Context context, List<VideoModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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

        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_gridview,null);
        }

        ImageView imageView = convertView.findViewById(R.id.image_grid);
        TextView textView = convertView.findViewById(R.id.title_grid);

        Glide.with(context).load(list.get(position).getImageURL()).into(imageView);
        textView.setText(list.get(position).getTitle());

        return convertView;
    }
}
