package com.omar.arabtv.Category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.omar.arabtv.R;
import com.omar.arabtv.RecyclerAdapterHorizontal;
import com.omar.arabtv.VideoModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private Context context;
    private List<CategoryModel> listCategory;

    public CategoryAdapter(Context context, List<CategoryModel> listCategory) {
        this.context = context;
        this.listCategory = listCategory;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

        holder.nameCategory.setText(listCategory.get(position).getName());

        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));
        RecyclerAdapterHorizontal recyclerAdapterHorizontal = new RecyclerAdapterHorizontal(context,listCategory.get(position).getVideoModels());
        holder.recyclerView.setAdapter(recyclerAdapterHorizontal);

    }

    @Override
    public int getItemCount() {
        return listCategory.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView nameCategory;
        RecyclerView recyclerView;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            nameCategory = itemView.findViewById(R.id.name_category);
            recyclerView = itemView.findViewById(R.id.recycle_horizontal);

        }
    }
}
