package shaker.maker.chanfamily.tapper;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {

    static final String STATE_SCORE = "STATE_SCORE";
    static final String STATE_SPREE = "STATE_SPREE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            // Restore value of members from saved state
            setScore(savedInstanceState.getInt(STATE_SCORE));
            setSpree(savedInstanceState.getInt(STATE_SPREE));
        } else {

            setContentView(shaker.maker.chanfamily.tapper.R.layout.activity_end);

            Intent intent = getIntent();

            String score = intent.getStringExtra(MainActivity.SCORE);
            String spree = intent.getStringExtra(MainActivity.SPREE);

            TextView end_score_value = (TextView) findViewById(shaker.maker.chanfamily.tapper.R.id.end_score_value);
            TextView end_spree_value = (TextView) findViewById(shaker.maker.chanfamily.tapper.R.id.end_spree_value);

            end_score_value.setText(score);
            end_spree_value.setText(spree);
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
        int state_spree = getSpree();

        // Save the user's current game state
        savedInstanceState.putInt(STATE_SCORE, state_score);
        savedInstanceState.putInt(STATE_SPREE, state_spree);


        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    private int getScore(){
        TextView score = (TextView) findViewById(R.id.end_score_value);
        int score_val = Integer.parseInt(score.getText().toString());
        return score_val;
    }

    private int getSpree(){
        TextView spree = (TextView) findViewById(R.id.end_spree_value);
        int spree_val = Integer.parseInt(spree.getText().toString());
        return spree_val;
    }

    private void setScore(int score_val){
        TextView score = (TextView) findViewById(R.id.end_spree_value);
        score.setText(score_val);
    }

    private void setSpree(int spree_val){
        TextView spree = (TextView) findViewById(R.id.end_spree_value);
        spree.setText(spree_val);
    }


}
