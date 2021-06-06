package com.omar.arabtv;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.holder> {

    private List<VideoModel> list;
    private Context context;

    public SliderAdapter(Context context, List<VideoModel> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public holder onCreateViewHolder(ViewGroup parent) {
        return new holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider, parent, false));
    }

    @Override
    public void onBindViewHolder(holder viewHolder, int position) {

        Glide.with(context).load(list.get(position).getImageURL()).into(viewHolder.imageView);
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Show_content.class);
                intent.putExtra("imageURL",list.get(position).getImageURL());
                intent.putExtra("trailerURL",list.get(position).getTrailerURL());
                intent.putExtra("title",list.get(position).getTitle());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getCount() {
        return 7;
    }

    public class holder extends SliderViewAdapter.ViewHolder {

        ImageView imageView;

        public holder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_slider);

        }

    }

}
