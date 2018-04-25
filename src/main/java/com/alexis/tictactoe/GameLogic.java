package com.alexis.tictactoe;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Alexis on 24-Apr-18.
 */

public class GameLogic {

    private Byte players;
    private Byte level;
    public Byte currentPlayer;
    public Byte[] gameArray = {0,0,0,0,0,0,0,0,0};
    private Dictionary<Integer,Integer> tiles = new Hashtable();
    private List<int[]> winCondition = new ArrayList();

    GameLogic(Byte players, Byte level){
        this.players = players;
        this.level = level;
        this.currentPlayer = 1;
        fillTilesReference();
        fillWinConditionList();
    }

    private void fillTilesReference(){
        tiles.put(R.id.a1,0);
        tiles.put(R.id.a2,1);
        tiles.put(R.id.a3,2);
        tiles.put(R.id.b1,3);
        tiles.put(R.id.b2,4);
        tiles.put(R.id.b3,5);
        tiles.put(R.id.c1,6);
        tiles.put(R.id.c2,7);
        tiles.put(R.id.c3,8);
    }

    private void fillWinConditionList(){

        winCondition.add(new int[]{0,1,2});
        winCondition.add(new int[]{3,4,5});
        winCondition.add(new int[]{6,7,8});
        winCondition.add(new int[]{0,3,6});
        winCondition.add(new int[]{1,4,7});
        winCondition.add(new int[]{2,5,8});
        winCondition.add(new int[]{0,4,8});
        winCondition.add(new int[]{2,4,6});
    }

    public void markInGameTile(int key){
        int position = tiles.get(key);
        gameArray[position] = currentPlayer;
    }

    public void changePlayerTurn(){
        if(currentPlayer == 1)
            currentPlayer = 2;
        else
            currentPlayer = 1;
    }

    public boolean isWinner(){
        int markedTiles = 0;
        for(int[] elem:winCondition){
            for(int i=0;i<3;i++){
                if(gameArray[elem[i]] == currentPlayer){
                    markedTiles++;
                }
            }
            if(markedTiles==3){
                return true;
            }
            markedTiles = 0;
        }
        return false;
    }


}
