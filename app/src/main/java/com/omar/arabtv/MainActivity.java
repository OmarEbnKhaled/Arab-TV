package com.omar.arabtv;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.omar.arabtv.databinding.ActivityMainBinding;
import com.omar.arabtv.ui.gallery.GalleryFragment;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private int layout;
    private SliderView sliderView;
    public FirebaseFirestore dp;
    public List<VideoModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbarMainActivity);

        dp = FirebaseFirestore.getInstance();
        getData();

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();

        //Set fragment Layout in mainActivity
        Bundle extras;
        if (savedInstanceState == null) {
            extras = getIntent().getExtras();
            if(extras == null) {
                layout= R.id.nav_home;
            } else {
                layout = extras.getInt("layout");
            }
        } else {
            layout = R.id.nav_home;
        }

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavGraph navGraph = navController.getNavInflater().inflate(R.navigation.mobile_navigation);
        navGraph.setStartDestination(layout);
        navController.setGraph(navGraph);

        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        binding.appBarMain.toolbarMainActivity.setTitle("");
    }

    private void setSlider() {
        sliderView = (SliderView)findViewById(R.id.slider);

        SliderAdapter sliderAdapter = new SliderAdapter(this,list);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

    }

    public void getData(){
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
                    setSlider();
                }else {
                    Log.d("DataOB", "onComplete: ERROR");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}