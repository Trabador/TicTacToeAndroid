package com.alexis.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * Created by Alexis on 24-Apr-18.
 */

public class GameScreen extends Activity {

    Byte players;
    Byte level;
    GameLogic game;

    @Override
    protected  void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.game);

        Bundle extraData = getIntent().getExtras();
        this.players = extraData.getByte("playerNumber");
        this.level = extraData.getByte("level");
        this.game = new GameLogic(players, level);

    }

    public void onTile(View v){
        ImageView aux = (ImageView) findViewById(v.getId());
        Toast toast = Toast.makeText(getApplicationContext(),""+v.getId(),Toast.LENGTH_LONG);
        toast.show();
        setMarkOnTile(aux);
    }

    private void setMarkOnTile(ImageView tile){
        if(game.currentPlayer == 1){
            tile.setImageResource(R.drawable.circle);
            tile.setClickable(false);
            game.currentPlayer = 2;
        }
        else {
            tile.setImageResource(R.drawable.cross);
            tile.setClickable(false);
            game.currentPlayer = 1;
        }
    }
}
