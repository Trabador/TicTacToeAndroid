package com.alexis.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


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
        showCurrentPlayerText();
        //**************************NEEDS TO BE DELETED , debug purpose only*******
        Toast test = Toast.makeText(getApplicationContext(),"players "+players+" level "+level,Toast.LENGTH_LONG);
        test.show();
        //*******************************************
    }

    private void showCurrentPlayerText(){
        TextView currentPlayerText = (TextView) findViewById(R.id.currentPlytxt);
        if(game.currentPlayer == 1){
            currentPlayerText.setText(R.string.player_circle);
        }
        else {
            currentPlayerText.setText(R.string.player_cross);
        }
    }

    public void onTile(View v){
        if(!isOver){
            ImageView aux = (ImageView) findViewById(v.getId());
            setMarkOnTile(aux);
        }
    }

    private void endGame(){
        isOver = true;
        TextView resultText = (TextView) findViewById(R.id.currentPlytxt);
        if(game.isTie()){
            resultText.setText(R.string.tie);
        }
        else {
            if(game.currentPlayer ==1){
                resultText.setText(R.string.circle_win);
            }
            else{
                resultText.setText(R.string.cross_win);
            }
        }
    }

    private void setMarkOnTile(ImageView tile){
        game.markInGameTile(tile.getId());
        if(game.currentPlayer == 1){
            tile.setImageResource(R.drawable.circle);
            tile.setClickable(false);
        }
        else {
            tile.setImageResource(R.drawable.cross);
            tile.setClickable(false);
        }
        if(game.isWinner()){
            Toast toast;
            if(game.currentPlayer == 1){
                toast = Toast.makeText(getApplicationContext(),R.string.circle_win,Toast.LENGTH_LONG);
            }
            else{
                toast = Toast.makeText(getApplicationContext(),R.string.cross_win,Toast.LENGTH_LONG);
            }
            toast.show();
            endGame();
        }
        else if(game.isTie()){
            Toast toast = Toast.makeText(getApplicationContext(),R.string.tie,Toast.LENGTH_LONG);
            toast.show();
            endGame();
        }
        else {
            game.changePlayerTurn();
            showCurrentPlayerText();
        }
    }
}
