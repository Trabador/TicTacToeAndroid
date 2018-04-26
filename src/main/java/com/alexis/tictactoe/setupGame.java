package com.alexis.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by Alexis on 23-Apr-18.
 */

public class setupGame extends Activity {

    @Override
    protected  void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.setup);
        RadioGroup playerNumGroup = (RadioGroup)findViewById (R.id.playerNum);
        final RadioGroup levelModeGroup = (RadioGroup)findViewById(R.id.difficultylevel);
        final TextView levelModeText = (TextView) findViewById(R.id.difLvl);
        playerNumGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {
                if(checkedID==R.id.twoPMode){
                    //if two player mode is selected hide level selection for cpu
                    levelModeGroup.setEnabled(false);
                    levelModeGroup.setVisibility(View.INVISIBLE);
                    levelModeText.setVisibility(View.INVISIBLE);
                }
                else {
                    //otherwise 1p mode is selected, re enable cpu level selection
                    levelModeGroup.setEnabled(true);
                    levelModeText.setVisibility(View.VISIBLE);
                    levelModeGroup.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void onStartButton(View view){
        byte players = getPlayerNumber();
        byte level;
        if(players == 1){
            level = getDifficultyLevel();
        }
        else {
            level = -1;
        }

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

    private byte getPlayerNumber(){
        RadioGroup configPlayerNum = (RadioGroup) findViewById(R.id.playerNum);
        int id = configPlayerNum.getCheckedRadioButtonId();
        Byte playerNum = 1;
        if(id==R.id.onePMode){
            playerNum = 1;
        }
        else{
            playerNum = 2;
        }
        return playerNum;
    }
}
