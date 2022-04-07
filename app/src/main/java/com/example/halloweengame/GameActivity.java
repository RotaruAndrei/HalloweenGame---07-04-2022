package com.example.halloweengame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private TextView score, gameInstructions;
    private ImageView heart1, heart2, heart3, hero, reward1, reward2, enemy1, enemy2, enemy3;
    private RelativeLayout relativeLayout;
    private boolean screenTouched = false;
    private boolean flowControl = false;
    private int screenWidthX, screenHeightY, heroX, heroY, enemy1X, enemy1Y, enemy2X, enemy2Y, enemy3X, enemy3Y, reward1X, reward1Y, reward2X, reward2Y;
    private Handler handler, handler2;
    private Runnable runnable, runnable2;
    private int playerLife = 3;
    private int playerScore = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initUI();

        relativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                gameInstructions.setVisibility(View.INVISIBLE);

                if (!screenTouched){

                    screenTouched = true;

                    //get screen dimensions
                    screenWidthX  = relativeLayout.getWidth();
                    screenHeightY = relativeLayout.getHeight();

                    //get hero position
                    heroX         = (int) hero.getX();
                    heroY         = (int) hero.getY();

                    handler       = new Handler();
                    runnable      = new Runnable() {
                        @Override
                        public void run() {
                            heroMovement();
                            enemyMovement();
                            collisionControl();
                        }
                    };

                    handler.post(runnable);

                }else {

                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                        flowControl = true;
                    }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                        flowControl = false;
                    }
                }

                return true;
            }
        });

    }


    //init UI components
    private void initUI () {
        score            = findViewById(R.id.game_scoreTitle);
        gameInstructions = findViewById(R.id.game_instructions);
        heart1           = findViewById(R.id.game_heart1);
        heart2           = findViewById(R.id.game_heart2);
        heart3           = findViewById(R.id.game_heart3);
        hero             = findViewById(R.id.game_heroImg);
        reward1          = findViewById(R.id.game_reward1Img);
        reward2          = findViewById(R.id.game_reward2Img);
        enemy1           = findViewById(R.id.game_enemy1Img);
        enemy2           = findViewById(R.id.game_enemy2Img);
        enemy3           = findViewById(R.id.game_enemy3Img);
        relativeLayout   = findViewById(R.id.game_relativeLayoutId);

    }

    //hero movement controls
    private void heroMovement () {


        // control the hero
        // on Y Axis
        if (!flowControl){

            heroY = heroY + (screenHeightY / 50);

            if (heroY >= (screenHeightY - hero.getHeight())){
                heroY = screenHeightY - hero.getHeight();
            }

            hero.setY(heroY);

        }else {

            heroY = heroY - (screenHeightY / 50);

            if (heroY <= 0){
                heroY = 0;
            }

            hero.setY(heroY);
        }



    }

    //control enemy movement
    private void enemyMovement (){

        // set visibility for enemy and reward
        enemy1.setVisibility(View.VISIBLE);
        enemy2.setVisibility(View.VISIBLE);
        enemy3.setVisibility(View.VISIBLE);
        reward1.setVisibility(View.VISIBLE);
        reward2.setVisibility(View.VISIBLE);


        //set movement for enemy1
        enemy1X = enemy1X - (screenWidthX / 150);


        //challenge the player :D
      if (playerScore >= 50 && playerScore < 100){
          enemy1X = enemy1X - (screenWidthX / 130);
        }

      if (playerScore >= 100 && playerScore < 150){
          enemy1X = enemy1X - (screenWidthX / 120);
      }

      if (playerScore >=150){
          enemy1X = enemy1X - (screenWidthX / 100);
      }


        if (enemy1X <= 0){

            enemy1X = screenWidthX + 200;
            enemy1Y = (int) Math.floor(Math.random() * screenHeightY);

            if (enemy1Y <= 0){
                enemy1Y = 0;
            }

            if (enemy1Y >= (screenHeightY - enemy1.getHeight())){
                enemy1Y = (screenHeightY - enemy1.getHeight());
            }
        }
        enemy1.setX(enemy1X);
        enemy1.setY(enemy1Y);

        //set movement for enemy2
        enemy2X = enemy2X - (screenWidthX / 140);
        if (enemy2X <= 0){

            enemy2X = screenWidthX + 200;
            enemy2Y = (int) Math.floor(Math.random() * screenHeightY);

            if (enemy2Y <= 0){
                enemy2Y = 0;
            }

            if (enemy2Y >= (screenHeightY - enemy2.getHeight())){
                enemy2Y = (screenHeightY - enemy2.getHeight());
            }
        }
        enemy2.setX(enemy2X);
        enemy2.setY(enemy2Y);

        //set movement for enemy3
        enemy3X = enemy3X - (screenWidthX / 130);

        //challenge the player :D
        if (playerScore >= 50 && playerScore < 100){
            enemy3X = enemy3X - (screenWidthX / 130);
        }

        if (playerScore >= 100 && playerScore < 150){
            enemy3X = enemy3X - (screenWidthX / 120);
        }

        if (playerScore >=150){
            enemy3X = enemy3X - (screenWidthX / 100);
        }

        if (enemy3X <= 0){

            enemy3X = screenWidthX + 200;
            enemy3Y = (int) Math.floor(Math.random() * screenHeightY);

            if (enemy3Y <= 0){
                enemy3Y = 0;
            }

            if (enemy3Y >= (screenHeightY - enemy3.getHeight())){
                enemy3Y = (screenHeightY - enemy3.getHeight());
            }
        }

        enemy3.setX(enemy3X);
        enemy3.setY(enemy3Y);

        //set movement for reward1
        reward1X = reward1X - (screenWidthX / 150);
        if (reward1X <= 0){

            reward1X = screenWidthX + 200;
            reward1Y = (int) Math.floor(Math.random() * screenHeightY);

            if (reward1Y <= 0){
                reward1Y = 0;
            }

            if (reward1Y >= (screenHeightY - reward1.getHeight())){
                reward1Y = (screenHeightY - reward1.getHeight());
            }
        }

        reward1.setX(reward1X);
        reward1.setY(reward1Y);

        //set movement for reward2
        reward2X = reward2X - (screenWidthX / 150);
        if (reward2X <= 0){

            reward2X = screenWidthX + 200;
            reward2Y = (int) Math.floor(Math.random() * screenHeightY);

            if (reward2Y <= 0){
                reward2Y = 0;
            }

            if (reward2Y >= (screenHeightY - reward2.getHeight())){
                reward2Y = (screenHeightY - reward2.getHeight());
            }
        }
        reward2.setX(reward2X);
        reward2.setY(reward2Y);
    }

    //control collision
    private void collisionControl (){

        //get enemy1 center point

        int centerEnemy1X = enemy1X + (enemy1.getWidth() / 2);
        int centerEnemy1Y = enemy1Y + (enemy1.getHeight() / 2);


        if ((centerEnemy1X >= heroX)
        && (centerEnemy1X <= heroX + hero.getWidth())
        && (centerEnemy1Y >= heroY)
        && (centerEnemy1Y <= heroY + hero.getHeight())){

            enemy1X = screenWidthX + 200;
            playerLife--;
        }

        //get enemy2 center point

        int centerEnemy2X = enemy2X + (enemy2.getWidth() / 2);
        int centerEnemy2Y = enemy2Y + (enemy2.getHeight() / 2);


        if (centerEnemy2X >= heroX
                && centerEnemy2X <= (heroX + hero.getWidth())
                && centerEnemy2Y >= heroY
                && centerEnemy2Y <= (heroY + hero.getHeight())){


            enemy2X = screenWidthX + 200;
            playerLife--;
        }

        //get enemy3 center point

        int centerEnemy3X = enemy3X + (enemy3.getWidth() / 2);
        int centerEnemy3Y = enemy3Y + (enemy3.getHeight() / 2);


        if (centerEnemy3X >= heroX
                && centerEnemy3X <= (heroX + hero.getWidth())
                && centerEnemy3Y >= heroY
                && centerEnemy3Y <= (heroY + hero.getHeight())){


            enemy3X = screenWidthX + 200;
            playerLife--;
        }

        //get reward1 center point

        int centerReward1X = reward1X + (reward1.getWidth() / 2);
        int centerReward1Y = reward1Y + (reward1.getHeight() / 2);


        if (centerReward1X >= heroX
                && centerReward1X <= (heroX + hero.getWidth())
                && centerReward1Y >= heroY
                && centerReward1Y <= (heroY + hero.getHeight())){


            playerScore += 10;
            reward1X = screenWidthX + 200;
            score.setText(String.valueOf(playerScore));
        }

        //get reward2 center point

        int centerReward2X = reward2X + (reward2.getWidth() / 2);
        int centerReward2Y = reward2Y + (reward2.getHeight() / 2);


        if (centerReward2X >= heroX
                && centerReward2X <= (heroX + hero.getWidth())
                && centerReward2Y >= heroY
                && centerReward2Y <= (heroY + hero.getHeight())){


            playerScore += 10;
            reward2X = (screenWidthX + 200);
            score.setText(String.valueOf(playerScore));
        }


        handler.postDelayed(runnable,20);


        if (playerLife > 0 && playerScore < 200){

            if (playerLife == 2 ){
                heart3.setImageResource(R.drawable.ic_heart1_empty);
            }

            if (playerLife == 1){
                heart2.setImageResource(R.drawable.ic_heart1_empty);
            }

        }else if (playerScore >= 200){

            handler.removeCallbacks(runnable);
            relativeLayout.setEnabled(false);
            enemy1.setVisibility(View.INVISIBLE);
            enemy2.setVisibility(View.INVISIBLE);
            enemy3.setVisibility(View.INVISIBLE);
            reward1.setVisibility(View.INVISIBLE);
            reward2.setVisibility(View.INVISIBLE);

            gameInstructions.setVisibility(View.VISIBLE);
            gameInstructions.setText("Congratulations !");



            handler2 = new Handler();
            runnable2 = new Runnable() {
                @Override
                public void run() {
                    heroX = heroX + (screenWidthX / 300);
                    hero.setX(heroX);
                    hero.setY(screenHeightY / 2f);

                    if (heroX <= screenWidthX){

                        handler2.postDelayed(runnable2,20);

                    }else {

                        handler2.removeCallbacks(runnable2);
                        Intent intent = new Intent(GameActivity.this,ResultActivity.class);
                        intent.putExtra("score",playerScore);
                        startActivity(intent);
                        finish();
                    }
                }
            };
            handler2.post(runnable2);


        }else if (playerLife == 0){

            handler.removeCallbacks(runnable);
            heart3.setImageResource(R.drawable.ic_heart1_empty);
            Intent intent = new Intent(GameActivity.this,ResultActivity.class);
            intent.putExtra("score",playerScore);
            startActivity(intent);
            finish();
        }

    }
}