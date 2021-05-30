package com.omar.arabtv;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RecyclerAdapterVertical extends RecyclerView.Adapter<RecyclerAdapterVertical.ViewHolder> {

    Context context;
    List<VideoModel> list;

    public RecyclerAdapterVertical(Context context, List<VideoModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_vertical, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImageURL()).into(holder.imageView);
        //Log.d("DataOB", "onComplete: "+list.get(position).getImageURL().toString());
        holder.title.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_v);
            imageView = itemView.findViewById(R.id.card_image_v);
        }
    }
}
