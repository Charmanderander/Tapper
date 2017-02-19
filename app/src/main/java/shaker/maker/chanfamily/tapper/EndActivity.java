package shaker.maker.chanfamily.tapper;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(shaker.maker.chanfamily.tapper.R.layout.activity_end);

        Intent intent = getIntent();

        String score = intent.getStringExtra(MainActivity.SCORE);
        String spree = intent.getStringExtra(MainActivity.SPREE);

        TextView end_score_value = (TextView) findViewById(shaker.maker.chanfamily.tapper.R.id.end_score_value);
        TextView end_spree_value = (TextView) findViewById(shaker.maker.chanfamily.tapper.R.id.end_spree_value);

        end_score_value.setText(score);
        end_spree_value.setText(spree);

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
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main_lan);
            System.out.println("landscape");
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            System.out.println("portrait");
            setContentView(R.layout.activity_main);
        }
    }


}
