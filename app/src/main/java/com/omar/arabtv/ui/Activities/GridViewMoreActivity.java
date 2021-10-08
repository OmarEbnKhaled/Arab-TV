package com.omar.arabtv.ui.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.omar.arabtv.ui.Models.VideoModel;
import com.omar.arabtv.databinding.ActivityGridViewMoreBinding;
import com.omar.arabtv.ui.Adapters.GridAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GridViewMoreActivity extends AppCompatActivity {

    private ActivityGridViewMoreBinding binding;
    private FirebaseFirestore dp;
    private List<VideoModel> list;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGridViewMoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dp = FirebaseFirestore.getInstance();

        getDataGridView();
    }

    public List<VideoModel> getDataGridView(){
        list = new ArrayList<>();

        dp.collection("مسلسلات رمضان 2021").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        VideoModel videoModel = document.toObject(VideoModel.class);
                        list.add(videoModel);
                        setGridView();
                        Log.d("DataOB", "onComplete: "+document.toString());
                    }
                }else {
                    Log.d("DataOB", "onComplete: ERROR");
                }
            }
        });

        return list;
    }

    private void setGridView() {
        GridAdapter gridAdapter = new GridAdapter(GridViewMoreActivity.this,list);
        binding.gridView.setAdapter(gridAdapter);
    }
}