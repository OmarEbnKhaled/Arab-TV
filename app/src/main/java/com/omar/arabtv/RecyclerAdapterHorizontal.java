package com.omar.arabtv;

import android.content.Context;
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

public class RecyclerAdapterHorizontal extends RecyclerView.Adapter<RecyclerAdapterHorizontal.ViewHolder> {

    private Context context;
    private List<VideoModel> list;
    private onItemListener onItemListener;

    public RecyclerAdapterHorizontal(Context context, List<VideoModel> list, onItemListener onItemListener) {
        this.context = context;
        this.list = list;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_horizantal, parent, false), onItemListener);
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

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView title;
        onItemListener onItemListener;

        public ViewHolder(@NonNull @NotNull View itemView , onItemListener onItemListener) {
            super(itemView);
            title = itemView.findViewById(R.id.title_h);
            imageView = itemView.findViewById(R.id.card_image_h);
            this.onItemListener = onItemListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemListener.onItemClick(getAdapterPosition());
        }
    }

    public interface onItemListener{
        void onItemClick(int position);
    }
}
