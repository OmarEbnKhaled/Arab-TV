package com.omar.arabtv.ui.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.omar.arabtv.R;
import com.omar.arabtv.ui.Activities.ShowContentActivity;
import com.omar.arabtv.ui.Models.VideoModel;

import java.util.List;

public class GridAdapter extends BaseAdapter  {

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

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowContentActivity.class);
                intent.putExtra("imageURL",list.get(position).getImageURL());
                intent.putExtra("trailerURL",list.get(position).getTrailerURL());
                intent.putExtra("title",list.get(position).getTitle());
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
