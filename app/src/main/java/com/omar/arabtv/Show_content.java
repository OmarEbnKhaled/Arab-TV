package com.omar.arabtv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.content.Intent;
import android.os.Bundle;

import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;


public class Show_content extends AppCompatActivity {

    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_content);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_show_content);
        toolbar.setTitle("القاهرة كابول");
        setSupportActionBar(toolbar);

        navigationView = (NavigationView)findViewById(R.id.nav_view_content);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_content);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        /*navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_home:
                        startActivity(new Intent(Show_content.this,MediaPlayer.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_gallery:
                        startActivity(new Intent(Show_content.this,MainActivity.class));
                        break;

                }
                return true;
            }
        });*/

    }

    public void show_video(View view) {
        startActivity(new Intent(this, MediaPlayer.class));
    }

}