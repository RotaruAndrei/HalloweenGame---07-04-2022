package com.example.halloweengame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private ImageView heroImg, enemy1Img, enemy2Img, enemy3Img, rewardImg, volumeImg;
    private MaterialButton play_btn;
    private MediaPlayer mediaPlayer;
    private Animation animation;
    private boolean isPlaying = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        // load the animation
        animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.animation_presentation);

        // add animation to the imges
        heroImg.setAnimation(animation);
        enemy1Img.setAnimation(animation);
        enemy2Img.setAnimation(animation);
        enemy3Img.setAnimation(animation);
        rewardImg.setAnimation(animation);

        // send user to game activity
        play_btn.setOnClickListener(onClick -> {
            mediaPlayer.reset();
            volumeImg.setImageResource(R.drawable.ic_volume_on);
            startActivity(new Intent(MainActivity.this, GameActivity.class));
        });

    }

    // init UI components
    private void initUI (){
        heroImg   = findViewById(R.id.main_heroImg);
        enemy1Img = findViewById(R.id.main_enemy1Img);
        enemy2Img = findViewById(R.id.main_enemy2Img);
        enemy3Img = findViewById(R.id.main_enemy3Img);
        rewardImg = findViewById(R.id.main_rewardImg);
        volumeImg = findViewById(R.id.main_volumeIconId);
        play_btn  = findViewById(R.id.main_play_btn);


    }

    //on resume method to start the soundtrack
    // and change volume icon
    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.soundtrack);
        mediaPlayer.start();

        // control the sound volume and changing the icon
        volumeImg.setOnClickListener(onClick ->{

            if (!isPlaying){

                isPlaying = true;
                volumeImg.setImageResource(R.drawable.ic_volume_off);
                mediaPlayer.setVolume(0,0);

            }else {

                isPlaying = false;
                volumeImg.setImageResource(R.drawable.ic_volume_on);
                mediaPlayer.setVolume(1,1);

            }
        });
//        mediaPlayer.setVolume(1,1);
//        mediaPlayer.start();
    }
}