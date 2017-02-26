package shaker.maker.tapper;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import shaker.maker.chanfamily.tapper.R;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Creating App");
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            setContentView(R.layout.activity_start_lan);
        } else {
            setContentView(R.layout.activity_start);
        }
    }

    public void startGame(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onStart(){
        super.onStart();
        System.out.println("Starting App");
    }

    @Override
    public void onStop(){
        super.onStop();
        System.out.println("Stopping App");
    }

    @Override
    public void onResume(){
        super.onResume();
        System.out.println("Resuming App");
    }

    @Override
    public void onPause(){
        super.onPause();
        System.out.println("Pausing App");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        System.out.println("Destroying App");
    }

}
