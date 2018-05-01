package com.alexis.tictactoe;

import android.app.Activity;
import android.media.MediaPlayer;
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
    private enum gameStates {CIRCLE_WIN,CROSS_WIN,TIE}
    private gameStates gameResult;
    private MediaPlayer endGameMedia;
    private MediaPlayer circleDrawMedia;
    private MediaPlayer crossDrawMedia;

    @Override
    protected  void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.game);

        Bundle extraData = getIntent().getExtras();
        this.players = extraData.getByte("playerNumber");
        this.level = extraData.getByte("level");
        this.game = new GameLogic(level);
        this.isOver = false;
        this.endGameMedia = MediaPlayer.create(getApplicationContext(),R.raw.endgamesound);
        this.circleDrawMedia = MediaPlayer.create(getApplicationContext(),R.raw.circledrawsound);
        this.circleDrawMedia.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                circleDrawMedia.stop();
                circleDrawMedia.reset();
            }
        });
        this.crossDrawMedia = MediaPlayer.create(getApplicationContext(), R.raw.crossdrawsound);
        this.crossDrawMedia.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                crossDrawMedia.stop();
                crossDrawMedia.reset();
            }
        });
        showCurrentPlayerText();
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
        if(players == 1){
            if (!isOver) {
                ImageView aux = (ImageView) findViewById(v.getId());
                setMarkOnTile(aux);
            }
            if(!isOver){
                ImageView aux = (ImageView) findViewById(game.IA());
                setMarkOnTile(aux);
            }
        }
        else {
            if (!isOver) {
                ImageView aux = (ImageView) findViewById(v.getId());
                setMarkOnTile(aux);
            }
        }
    }

    private void endGame(){
        isOver = true;
        TextView resultText = (TextView) findViewById(R.id.currentPlytxt);
        switch (gameResult){
            case CIRCLE_WIN:
                resultText.setText(R.string.circle_win);
                break;
            case CROSS_WIN:
                resultText.setText(R.string.cross_win);
                break;
            case TIE:
                resultText.setText(R.string.tie);
                break;
        }
        endGameMedia.start();
    }

    private void setMarkOnTile(ImageView tile){
        game.markInGameTile(tile.getId());
        if(game.currentPlayer == 1){
            tile.setImageResource(R.drawable.circle);
            tile.setClickable(false);
            circleDrawMedia = MediaPlayer.create(getApplicationContext(),R.raw.circledrawsound);
            circleDrawMedia.start();
        }
        else {
            tile.setImageResource(R.drawable.cross);
            tile.setClickable(false);
            crossDrawMedia = MediaPlayer.create(getApplicationContext(),R.raw.crossdrawsound);
            crossDrawMedia.start();
        }
        if(game.isWinner()){
            Toast toast;
            if(game.currentPlayer == 1){
                toast = Toast.makeText(getApplicationContext(),R.string.circle_win,Toast.LENGTH_LONG);
                toast.show();
                gameResult = gameStates.CIRCLE_WIN;//Player 1 Wins State
            }
            else{
                toast = Toast.makeText(getApplicationContext(),R.string.cross_win,Toast.LENGTH_LONG);
                toast.show();
                gameResult = gameStates.CROSS_WIN;//Player 2 Wins State
            }
            endGame();
        }
        else if(game.isTie()){
            Toast toast = Toast.makeText(getApplicationContext(),R.string.tie,Toast.LENGTH_LONG);
            toast.show();
            gameResult = gameStates.TIE; //The game is a Tie State
            endGame();
        }
        else {
            game.changePlayerTurn();
            showCurrentPlayerText();
        }
    }

    public void onResetGameButton(View view){
        this.game = new GameLogic(level);
        this.isOver = false;
        showCurrentPlayerText();
        ImageView aux;
        aux = (ImageView) findViewById(R.id.a1);
        aux.setImageResource(R.drawable.tile);
        aux.setClickable(true);
        aux = (ImageView) findViewById(R.id.a2);
        aux.setImageResource(R.drawable.tile);
        aux.setClickable(true);
        aux = (ImageView) findViewById(R.id.a3);
        aux.setImageResource(R.drawable.tile);
        aux.setClickable(true);
        aux = (ImageView) findViewById(R.id.b1);
        aux.setImageResource(R.drawable.tile);
        aux.setClickable(true);
        aux = (ImageView) findViewById(R.id.b2);
        aux.setImageResource(R.drawable.tile);
        aux.setClickable(true);
        aux = (ImageView) findViewById(R.id.b3);
        aux.setImageResource(R.drawable.tile);
        aux.setClickable(true);
        aux = (ImageView) findViewById(R.id.c1);
        aux.setImageResource(R.drawable.tile);
        aux.setClickable(true);
        aux = (ImageView) findViewById(R.id.c2);
        aux.setImageResource(R.drawable.tile);
        aux.setClickable(true);
        aux = (ImageView) findViewById(R.id.c3);
        aux.setImageResource(R.drawable.tile);
        aux.setClickable(true);
    }
}
