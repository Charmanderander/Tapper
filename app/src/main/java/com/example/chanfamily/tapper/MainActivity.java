package com.example.chanfamily.tapper;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public final static String SCORE = "SCORE";
    public final static String SPREE = "SPREE";

    private final String[] nextInst = {"LEFT", "RIGHT"};

    private int highestSpree = 0;

    Random random = new Random();
    int index = random.nextInt(nextInst.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setInst();

        TextView timer = (TextView) findViewById(R.id.timer_value);
        startTimer(10, timer);
    }

    public void leftClick(View view){
        validateScore("LEFT");
    }

    public void rightClick(View view){
        validateScore("RIGHT");
    }

    private void validateScore(String inst){
        TextView currInst = (TextView) findViewById(R.id.instruction);
        TextView score = (TextView) findViewById(R.id.score_value);
        TextView spree = (TextView) findViewById(R.id.spree_value);

        int currScore = Integer.parseInt(score.getText().toString());
        int currSpree = Integer.parseInt(spree.getText().toString());

        if (currInst.getText().toString().equals(inst)){
            currScore += 1;
            currSpree += 1;
            updateSpree(currSpree);

            score.setText(Integer.toString(currScore));
            spree.setText(Integer.toString(currSpree));
        } else {
            updateSpree(currSpree);
            currSpree = 0;
            spree.setText(Integer.toString(currSpree));
        }



        setInst();
    }

    private void updateSpree(int currSpree){
        if (currSpree > highestSpree) {
            highestSpree = currSpree;
        }
    }

    private void setInst(){
        Random random = new Random();
        int index = random.nextInt(nextInst.length);
        TextView inst = (TextView) findViewById(R.id.instruction);
        inst.setText(nextInst[index]);
    }

    public void startTimer(int Seconds,final TextView timer){


        new CountDownTimer(Seconds* 1000+1000, 1000) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                timer.setText("TIME : " + String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));
            }

            public void onFinish() {
                TextView score = (TextView) findViewById(R.id.score_value);
                endScreen(score.getText().toString(), Integer.toString(highestSpree));
            }
        }.start();
    }

    private void endScreen(String score, String spree){
        Intent intent = new Intent(this, EndActivity.class);
        intent.putExtra(SCORE,score);
        intent.putExtra(SPREE,spree);
        startActivity(intent);
    }
}
