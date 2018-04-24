package com.alexis.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Alexis on 24-Apr-18.
 */

public class GameScreen extends Activity {

    Byte players;
    Byte level;

    @Override
    protected  void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.game);

        Bundle extraData = getIntent().getExtras();
        this.players = extraData.getByte("playerNumber");
        this.level = extraData.getByte("level");

        TextView test = (TextView) findViewById(R.id.textView);
        String text = "Players: "+Byte.toString(players)+" Level "+Byte.toString(level);
        test.setText(text);

    }
}
