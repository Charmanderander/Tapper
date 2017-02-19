package shaker.maker.chanfamily.tapper;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public final static String SCORE = "SCORE";
    public final static String SPREE = "SPREE";

    static final String STATE_SCORE = "STATE_SCORE";
    static final String STATE_SPREE = "STATE_SPREE";
    static final String STATE_TIME = "STATE_TIME";
    static final String STATE_INST = "STATE_INST";

    private final String[] nextInst = {"LEFT", "RIGHT"};

    private CountDownTimer countDownTimer = null;

    private int remainingTime = 10;

    private int highestSpree = 0;

    Random random = new Random();
    int index = random.nextInt(nextInst.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check whether we're recreating a previously destroyed instance
        if (savedInstanceState != null) {
            System.out.println("restoring");
            // Restore value of members from saved state
            setScore(savedInstanceState.getInt(STATE_SCORE));
            setSpree(savedInstanceState.getInt(STATE_SPREE));
            setTime(savedInstanceState.getInt(STATE_TIME));
            setInst(savedInstanceState.getString(STATE_INST));
        } else {
            setContentView(shaker.maker.chanfamily.tapper.R.layout.activity_main);
            setInst();
        }

    }

    public void leftClick(View view){
        validateScore("LEFT");
    }

    public void rightClick(View view){
        validateScore("RIGHT");
    }

    private void validateScore(String inst){
        TextView currInst = (TextView) findViewById(shaker.maker.chanfamily.tapper.R.id.instruction);
        TextView score = (TextView) findViewById(shaker.maker.chanfamily.tapper.R.id.score_value);
        TextView spree = (TextView) findViewById(shaker.maker.chanfamily.tapper.R.id.spree_value);

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
        setInst(nextInst[index]);
    }

    public void makeTimer(int Seconds,final TextView timer){

        countDownTimer = new CountDownTimer(Seconds* 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                timer.setText("TIME : " + String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));
            }

            public void onFinish() {
                String score = Integer.toString(getScore());
                String spree = Integer.toString(highestSpree);
                endScreen(score, spree);
            }
        };

    }

    @Override
    public void onStop(){
        super.onPause();
        remainingTime = getTime();
        countDownTimer.cancel();
    }

    @Override
    public void onResume(){
        super.onResume();

        // On resuming the app, reinstate the remaining time
        setTime(remainingTime);
    }

    private void endScreen(String score, String spree){
        Intent intent = new Intent(this, EndActivity.class);

        // Passing Score and Spree to end screen
        intent.putExtra(SCORE,score);
        intent.putExtra(SPREE,spree);
        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Getting current state parameters
        String state_inst = getInst();
        int state_time = getTime();
        int state_score = getScore();
        int state_spree = getSpree();

        // Save the user's current game state
        savedInstanceState.putInt(STATE_SCORE, state_score);
        savedInstanceState.putInt(STATE_SPREE, state_spree);
        savedInstanceState.putInt(STATE_TIME, state_time);
        savedInstanceState.putString(STATE_INST, state_inst);


        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }


    // Getter and setter methods
    private void setScore(int score_val){
        TextView score = (TextView) findViewById(R.id.spree_value);
        score.setText(score_val);
    }

    private void setSpree(int spree_val){
        TextView spree = (TextView) findViewById(R.id.spree_value);
        spree.setText(spree_val);
    }

    private void setTime(int time_val){
        TextView timer = (TextView) findViewById(R.id.timer_value);
        makeTimer(time_val, timer);
        countDownTimer.start();
    }

    private void setInst(String inst_val){
        TextView inst = (TextView) findViewById(R.id.instruction);
        inst.setText(inst_val);
        Animation text_pop = AnimationUtils.loadAnimation(this, shaker.maker.chanfamily.tapper.R.anim.text_pop);
        inst.startAnimation(text_pop);
    }

    private int getScore(){
        TextView score = (TextView) findViewById(R.id.score_value);
        int score_val = Integer.parseInt(score.getText().toString());
        return score_val;
    }

    private int getSpree(){
        TextView spree = (TextView) findViewById(R.id.spree_value);
        int spree_val = Integer.parseInt(spree.getText().toString());
        return spree_val;
    }

    private int getTime(){
        TextView timer = (TextView) findViewById(R.id.timer_value);
        int time_val = Integer.parseInt(timer.getText().toString().split(":")[2]);
        return time_val;
    }


    private String getInst(){
        TextView inst = (TextView) findViewById(R.id.instruction);
        String inst_val = inst.getText().toString();
        return inst_val;
    }

}
