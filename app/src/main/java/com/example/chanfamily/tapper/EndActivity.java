package com.example.chanfamily.tapper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        Intent intent = getIntent();

        String score = intent.getStringExtra(MainActivity.SCORE);
        String spree = intent.getStringExtra(MainActivity.SPREE);

        TextView end_score_value = (TextView) findViewById(R.id.end_score_value);
        TextView end_spree_value = (TextView) findViewById(R.id.end_spree_value);

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


}
