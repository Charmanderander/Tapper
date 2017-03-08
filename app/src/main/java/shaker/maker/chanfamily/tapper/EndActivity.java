package shaker.maker.chanfamily.tapper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

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

            TextView highscore_value = (TextView) findViewById(R.id.highscore_value);

            saveHighScore(Integer.parseInt(score));

            highscore_value.setText(Integer.toString(getHighScore()));
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

    private void saveHighScore(int score){
        SharedPreferences prefs = this.getSharedPreferences("HighScore", Context.MODE_PRIVATE);
        int highscore = prefs.getInt("highscore", 0); //0 is the default value

        if (score > highscore){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("highscore", score);
            editor.commit();
        }

    }

    private int getHighScore(){
        SharedPreferences prefs = this.getSharedPreferences("HighScore", Context.MODE_PRIVATE);
        int highscore = prefs.getInt("highscore", 0); //0 is the default value
        return highscore;
    }

}
