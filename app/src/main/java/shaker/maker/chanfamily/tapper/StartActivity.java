package shaker.maker.chanfamily.tapper;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(shaker.maker.chanfamily.tapper.R.layout.activity_start);
    }

    public void startGame(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_start_lan);
            System.out.println("landscape");
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            System.out.println("portrait");
            setContentView(R.layout.activity_start);
        }
    }
}
