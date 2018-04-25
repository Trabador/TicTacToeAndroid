package com.alexis.tictactoe;

/**
 * Created by Alexis on 24-Apr-18.
 */

public class GameLogic {

    private Byte players;
    private Byte level;
    public Byte currentPlayer;
    public Byte[] gameArray = {0,0,0,0,0,0,0,0,0};

    GameLogic(Byte players, Byte level){
        this.players = players;
        this.level = level;
        this.currentPlayer = 1;
    }


}
