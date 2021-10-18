package com.omar.arabtv.ui.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;
import com.omar.arabtv.R;

import org.jetbrains.annotations.NotNull;


public class ShowContentActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;
    private String imageURL, trailerURL, title;
    private ImageView imageView;
    private boolean likeStatus = false;
    private boolean dislikeStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_content);

        Bundle extras;
        if (savedInstanceState == null) {
            extras = getIntent().getExtras();
            if(extras != null) {
                imageURL = extras.getString("imageURL");
                trailerURL = extras.getString("trailerURL");
                title = extras.getString("title");
                TextView contentTitle = findViewById(R.id.content_title);
                contentTitle.setText(title);
            }else
                finish();
        } else {
            finish();
        }

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_show_content);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);

        navigationView = (NavigationView)findViewById(R.id.nav_view_content);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_content);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        imageView = (ImageView)findViewById(R.id.poster);
        Glide.with(this).load(imageURL).into(imageView);

        LinearLayout rating = findViewById(R.id.btn_rating);
        rating.setOnClickListener(v -> Rating());

        LinearLayout like = findViewById(R.id.btn_like);
        like.setOnClickListener(v -> Like());

        LinearLayout dislike = findViewById(R.id.btn_dislike);
        dislike.setOnClickListener(v -> disLike());

    }

    private void disLike() {
        ImageView image = findViewById(R.id.dislike_image);

        if (dislikeStatus){
            image.setImageResource(R.drawable.ic_dislike_outline);
            dislikeStatus = false;
        }else {
            image.setImageResource(R.drawable.ic_dislike_fill);
            dislikeStatus = true;
            likeStatus = true;
            Like();
        }
    }

    private void Like() {
        ImageView image = findViewById(R.id.like_image);
        if (likeStatus){
            image.setImageResource(R.drawable.ic_like_outline);
            likeStatus = false;
        }else {
            image.setImageResource(R.drawable.ic_like_fill);
            likeStatus = true;
            dislikeStatus = true;
            disLike();
        }
    }

    private void Rating() {
        Dialog ratingDialog = new Dialog(ShowContentActivity.this);
        ratingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ratingDialog.setContentView(R.layout.dialog_rating);
        ratingDialog.show();

        RatingBar ratingBar = ratingDialog.findViewById(R.id.ratingBar);
        TextView liveRating = ratingDialog.findViewById(R.id.liveRating);
        MaterialButton btn_submit = ratingDialog.findViewById(R.id.btn_submitRating);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                liveRating.setText(String.format("%s",rating));
            }
        });

        btn_submit.setOnClickListener(v -> {
            String sRating = String.valueOf(ratingBar.getRating());
            TextView ratingShow = findViewById(R.id.rating_show);
            ratingShow.setText(sRating);

            ratingDialog.dismiss();
        });
    }

    public void show_video(View view) {
        Intent intent = new Intent(this, MediaPlayerActivity.class);
        intent.putExtra("videoURl",trailerURL);
        startActivity(intent);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {

        Intent intent = new Intent(ShowContentActivity.this, MainActivity.class);

        switch(item.getItemId()){

            case R.id.nav_home:
                intent.putExtra("layout",R.id.nav_home);
                break;

            case R.id.nav_gallery:
                intent.putExtra("layout",R.id.nav_gallery);
                break;

            case R.id.nav_slideshow:
                intent.putExtra("layout",R.id.nav_slideshow);
                break;
        }

        startActivity(intent);
        finish();
        return true;
    }

}