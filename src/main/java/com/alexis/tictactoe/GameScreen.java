package com.alexis.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Dictionary;
import java.util.Hashtable;


/**
 * Created by Alexis on 24-Apr-18.
 */

public class GameScreen extends Activity {

    private Byte players;
    private Byte level;
    private GameLogic game;
    private Boolean isOver;

    @Override
    protected  void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.game);

        Bundle extraData = getIntent().getExtras();
        this.players = extraData.getByte("playerNumber");
        this.level = extraData.getByte("level");
        this.game = new GameLogic(players, level);
        this.isOver = false;
    }



    public void onTile(View v){
        if(!isOver){
            ImageView aux = (ImageView) findViewById(v.getId());
            setMarkOnTile(aux);
        }
    }

    private void endGame(){
        isOver = true;
    }

    private void setMarkOnTile(ImageView tile){
        if(game.currentPlayer == 1){
            game.markInGameTile(tile.getId());
            tile.setImageResource(R.drawable.circle);
            tile.setClickable(false);
            if(game.isWinner()){
                Toast toast = Toast.makeText(getApplicationContext(),R.string.circle_win,Toast.LENGTH_LONG);
                toast.show();
                endGame();
            }
            else {game.changePlayerTurn();}
        }
        else {
            game.markInGameTile(tile.getId());
            tile.setImageResource(R.drawable.cross);
            tile.setClickable(false);
            if(game.isWinner()){
                Toast toast = Toast.makeText(getApplicationContext(),R.string.cross_win,Toast.LENGTH_LONG);
                toast.show();
                endGame();
            }
            else {game.changePlayerTurn();}
        }
    }
}
