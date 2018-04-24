package com.alexis.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onPlayButton(View view){
        Intent gotoSetupIntent =  new Intent(this, setupGame.class);
        this.startActivity(gotoSetupIntent);
    }

    public void onExitBtn(View view){
        System.exit(1);
    }
}
