package com.example.admin.daily2;

import android.content.Intent;
import android.media.MediaActionSound;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

public class DetailsOfAnimalActivity extends AppCompatActivity {

    private TextView detailsOfAnimal;
    private String sound;

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_of_animal);

        detailsOfAnimal = findViewById(R.id.detailsOfAnimal);

        Intent intent=getIntent();

        String details=intent.getStringExtra(ListOfAnimalsActivity.ANIMALDETAILSKEY);
        sound = intent.getStringExtra(ListOfAnimalsActivity.ANIMALSOUNDKEY);

        detailsOfAnimal.setText(details);

        mediaPlayer = new MediaPlayer();




    }

    public void PlaySound(View view) {

        switch(sound){
            case "Roar":
                try {
                    mediaPlayer.setDataSource("raw/lion_roaring_sound1tamilnadu178.mp3");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.start();

                break;
            case "Wheek":
                try {
                    mediaPlayer.setDataSource("raw/guinea_pig_queaking.mp3");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.start();
                break;
        }
    }
}
