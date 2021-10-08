package com.omar.arabtv.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.omar.arabtv.ui.Adapters.RecyclerAdapterVertical;
import com.omar.arabtv.ui.Activities.ShowContentActivity;
import com.omar.arabtv.ui.Models.VideoModel;
import com.omar.arabtv.databinding.FragmentGalleryBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment implements RecyclerAdapterVertical.onItemListener {

    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;
    private RecyclerView recyclerView;
    public FirebaseFirestore dp;
    public List<VideoModel> list;
    private ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        progressBar = binding.progressVertical;
        progressBar.setVisibility(View.VISIBLE);
        dp = FirebaseFirestore.getInstance();
        recyclerView = binding.recycleVertical;

        getData();

        return root;
    }

    public List<VideoModel> getData(){
        list = new ArrayList<>();

        dp.collection("مسلسلات رمضان 2021").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        VideoModel videoModel = document.toObject(VideoModel.class);
                        list.add(videoModel);
                        setRecyclerViewVertical();
                        Log.d("DataOB", "onComplete: "+document.toString());
                    }
                }else {
                    Log.d("DataOB", "onComplete: ERROR");
                }
            }
        });

        return list;
    }

    private void setRecyclerViewVertical() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        RecyclerAdapterVertical recyclerAdapterVertical = new RecyclerAdapterVertical(getContext(),list, this);
        recyclerView.setAdapter(recyclerAdapterVertical);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), ShowContentActivity.class);
        intent.putExtra("imageURL",list.get(position).getImageURL());
        intent.putExtra("trailerURL",list.get(position).getTrailerURL());
        intent.putExtra("title",list.get(position).getTitle());
        startActivity(intent);
    }
}