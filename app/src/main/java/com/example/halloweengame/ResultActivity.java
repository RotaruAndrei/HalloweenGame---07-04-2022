package com.example.halloweengame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView congratulations, score, highestScore;
    private Button playAgain_btn;
    private int incomingScore = 0, incomingHighestScore = 0;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // init UI components
        initUI();
        sharedPreferences    = this.getSharedPreferences("HalloweenGame",MODE_PRIVATE);
        incomingHighestScore = sharedPreferences.getInt("score",0);
        //receive user score and display it
        incomingScore        = getIntent().getIntExtra("score",0);
        score.setText("Your score: " + incomingScore);
        highestScore.setText("Highest score: " + incomingHighestScore);

        if (incomingScore > incomingHighestScore){
            sharedPreferences.edit().putInt("score",incomingScore).apply();
            congratulations.setText("Congratulations, you achieved a new record!");

        }else if (incomingScore == incomingHighestScore){
            congratulations.setText("Congratulations, you achieve the pinnacle");
        } else if (incomingHighestScore - incomingScore >= 30){
            congratulations.setText("Never give up! Keep training and you will be better!");

        } else if (incomingHighestScore - incomingScore <= 20 && incomingHighestScore - incomingScore > 0){
            congratulations.setText("You are so close to achieve a new record!");

        }else {
            congratulations.setText("Keep playing!");
        }

        playAgain_btn.setOnClickListener(onClick ->{
            startActivity(new Intent(ResultActivity.this,GameActivity.class));
            finish();
        });


    }

    private void initUI (){
        congratulations = findViewById(R.id.result_congratulations);
        score           = findViewById(R.id.result_score);
        highestScore    = findViewById(R.id.result_highestScore);
        playAgain_btn   = findViewById(R.id.result_playAgain_btn);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Halloween Game")
                .setMessage("Are you sure you want to quit the game?")
                .setCancelable(false)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Quit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(0);
                    }
                });

        builder.create().show();
    }
}