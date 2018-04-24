package com.alexis.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

/**
 * Created by Alexis on 23-Apr-18.
 */

public class setupGame extends Activity {

    @Override
    protected  void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.setup);
    }

    public void on1PlayerButton(View view){
        byte players = 1;
        byte level = getDifficultyLevel();

        Intent gotoGameIntent = new Intent(this,GameScreen.class);
        gotoGameIntent.putExtra("playerNumber",players);
        gotoGameIntent.putExtra("level",level);
        this.startActivity(gotoGameIntent);

    }

    public void on2PlayerButton(View view){
         byte players = 2;
         byte level = -1;

        Intent gotoGameIntent = new Intent(this,GameScreen.class);
        gotoGameIntent.putExtra("playerNumber",players);
        gotoGameIntent.putExtra("level",level);
        this.startActivity(gotoGameIntent);
    }

    private byte getDifficultyLevel(){
        RadioGroup configDifficulty = (RadioGroup) findViewById(R.id.difficultylevel);
        int id = configDifficulty.getCheckedRadioButtonId();
        byte level = -1;
        switch (id){
            case R.id.normalOpt:
                level = 1;
                break;
            case R.id.easyOpt:
                level = 0;
                break;
            case R.id.insaneOpt:
                level = 2;
                break;
        }
        return level;
    }
}
