package com.omar.arabtv.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.omar.arabtv.Category.CategoryAdapter;
import com.omar.arabtv.Category.CategoryModel;
import com.omar.arabtv.R;
import com.omar.arabtv.VideoModel;
import com.omar.arabtv.databinding.FragmentHomeBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private List<VideoModel> list;
    private List<CategoryModel> categoryList;
    private FirebaseFirestore dp;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dp = FirebaseFirestore.getInstance();
        progressBar = binding.progressCategory;
        progressBar.setVisibility(View.VISIBLE);
        recyclerView = binding.recycleCategory;

        getData();

        return root;
    }

    private void getData() {
        list = new ArrayList<>();

        dp.collection("مسلسلات رمضان 2021").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        VideoModel videoModel = document.toObject(VideoModel.class);
                        list.add(videoModel);
                        //Log.d("DataOB", "onComplete: "+document.toString());
                    }
                    setRecycleView();
                }else {
                    Log.d("DataOB", "onComplete: ERROR");
                }
            }
        });
    }

    private void setRecycleView() {

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(),getCategory());
        recyclerView.setAdapter(categoryAdapter);
        progressBar.setVisibility(View.GONE);

    }

    private List<CategoryModel> getCategory(){

        categoryList = new ArrayList<>();
        categoryList.add(new CategoryModel("مسلسلات عربي",list));
        categoryList.add(new CategoryModel("مسلسلات أجنبي",list));
        categoryList.add(new CategoryModel("أفلام عربي",list));
        categoryList.add(new CategoryModel("أفلام أجنبي",list));

        return categoryList;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}