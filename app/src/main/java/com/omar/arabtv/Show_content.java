package com.omar.arabtv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.MediaController;

import com.google.android.material.navigation.NavigationView;
import com.omar.arabtv.databinding.ActivityMainBinding;


public class Show_content extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_content);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_show_content);
        setSupportActionBar(toolbar);



    }

    public void show_video(View view) {
        startActivity(new Intent(this, MediaPlayer.class));
    }

}