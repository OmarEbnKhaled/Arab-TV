package com.omar.arabtv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;


public class Show_content extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;
    private String imageURL, trailerURL, title;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_content);

        Bundle extras;
        if (savedInstanceState == null) {
            extras = getIntent().getExtras();
            if(extras != null) {
                imageURL = extras.getString("imageURL");
                trailerURL = extras.getString("trailerURL");
                title = extras.getString("title");
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

    }

    public void show_video(View view) {
        Intent intent = new Intent(this, MediaPlayer.class);
        intent.putExtra("videoURl",trailerURL);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {

        Intent intent = new Intent(Show_content.this,MainActivity.class);

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