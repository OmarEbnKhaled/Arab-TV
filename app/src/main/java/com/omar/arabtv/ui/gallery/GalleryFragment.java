package com.omar.arabtv.ui.gallery;

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
import com.omar.arabtv.RecyclerAdapterVertical;
import com.omar.arabtv.VideoModel;
import com.omar.arabtv.databinding.FragmentGalleryBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;
    private RecyclerView recyclerView;
    private FirebaseFirestore dp;
    private List<VideoModel> list;
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

        /*final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

    private void getData(){
        list = new ArrayList<>();

        dp.collection("مسلسلات رمضان 2021").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        VideoModel videoModel = document.toObject(VideoModel.class);
                        list.add(videoModel);
                        setRecyclerViewVertical();
                        //Log.d("DataOB", "onComplete: "+document.toString());
                    }
                }else {
                    Log.d("DataOB", "onComplete: ERROR");
                }
            }
        });
    }

    private void setRecyclerViewVertical() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        RecyclerAdapterVertical recyclerAdapterVertical = new RecyclerAdapterVertical(getContext(),list);
        recyclerView.setAdapter(recyclerAdapterVertical);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}