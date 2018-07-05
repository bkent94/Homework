package com.example.admin.weekend;

import android.media.MediaPlayer;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;

import java.io.IOException;

public class Media extends MainActivity {

    private DrawerLayout navigationDrawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private MediaPlayer mediaPlayer;
    private ImageButton playSong;
    private TextView songTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        navigationDrawerLayout = (DrawerLayout) findViewById(R.id.nav_drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, navigationDrawerLayout, R.string.open, R.string.close);
        navigationDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().findItem(R.id.songs).setChecked(true);
        mediaPlayer = MediaPlayer.create(this,R.raw.official_national_anthem);
        songTitle = findViewById(R.id.songTitle);
        songTitle.setText("Now Playing: Official_National_Anthem");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void clickPlayPause(View view) {
        playSong = findViewById(R.id.playSong);
            if(mediaPlayer.isPlaying()){
                mediaPlayer.pause();
                playSong.setImageResource(R.drawable.pause_icon);
            }
            else {
                mediaPlayer.start();

                playSong.setImageResource(R.drawable.button_play_icon);
            }
    }
}
