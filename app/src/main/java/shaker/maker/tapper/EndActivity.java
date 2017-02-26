package shaker.maker.tapper;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import shaker.maker.chanfamily.tapper.R;

public class EndActivity extends AppCompatActivity {

    static final String STATE_SCORE = "STATE_SCORE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            setContentView(R.layout.activity_end_lan);
        } else {
            setContentView(R.layout.activity_end);
        }

        if (savedInstanceState != null) {

            // Restore value of members from saved state
            setScore(savedInstanceState.getInt(STATE_SCORE));
        } else {

            Intent intent = getIntent();

            String score = intent.getStringExtra(MainActivity.SCORE);

            TextView end_score_value = (TextView) findViewById(R.id.end_score_value);

            end_score_value.setText(score);
        }
    }
    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(this,StartActivity.class);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(setIntent);
    }

    public void playAgain(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void returnMainMenu(View view){
        Intent intent = new Intent(this,StartActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Getting current state parameters
        int state_score = getScore();

        // Save the user's current game state
        savedInstanceState.putInt(STATE_SCORE, state_score);


        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    private int getScore(){
        TextView score = (TextView) findViewById(R.id.end_score_value);
        int score_val = Integer.parseInt(score.getText().toString());
        return score_val;
    }


    private void setScore(int score_val){
        TextView score = (TextView) findViewById(R.id.end_score_value);
        score.setText(Integer.toString(score_val));
    }

}
