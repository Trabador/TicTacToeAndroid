package com.alexis.tictactoe;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

/**
 * Created by Alexis on 24-Apr-18.
 */

public class GameLogic {

    private Byte players;
    private Byte level;
    public Byte currentPlayer;
    public Byte[] gameArray = {0,0,0,0,0,0,0,0,0};
    /* Game board abstraction to array positions:
       0|1|2
       ------
       3|4|5
       ------
       6|7|8
     */
    private Dictionary<Integer,Integer> tiles = new Hashtable();//Tile maping from GUI to array positions
    private List<int[]> winCondition = new ArrayList();//List of every win condition in game

    GameLogic(Byte players, Byte level, Dictionary<Integer,Integer> tiles){
        this.players = players;
        this.level = level;
        this.currentPlayer = 1;
        this.tiles = tiles;
        fillWinConditionList();
    }

    private void fillWinConditionList(){
        winCondition.add(new int[]{0,1,2});//first row positions for win
        winCondition.add(new int[]{3,4,5});//second row positions for win
        winCondition.add(new int[]{6,7,8});//third row positions for win
        winCondition.add(new int[]{0,3,6});//first column positions for win
        winCondition.add(new int[]{1,4,7});//second column positions for win
        winCondition.add(new int[]{2,5,8});//third column positions for win
        winCondition.add(new int[]{0,4,8});//diagonal positions for win
        winCondition.add(new int[]{2,4,6});//inverse diagonal positions for win
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

    public boolean isTie(){
        for(int i=0;i<gameArray.length;i++){
            if(gameArray[i] == 0){
                return false;
            }
        }
        return true;
    }

    public boolean isWinner(){
        int markedTiles = 0;
        for(int[] elem:winCondition){
            for(int i=0;i<elem.length;i++){
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

    public int IA(){
        boolean isValid = false;
        int position;
        int id = 0;
        do{
            Random randomTile = new Random();
            position = randomTile.nextInt(gameArray.length);
            isValid = isValidPosition(position);
        }while(!isValid);

        Enumeration enumeration = tiles.keys();

        while(enumeration.hasMoreElements()){
            Object element = enumeration.nextElement();
            if(tiles.get(element) == position ){
               id = (Integer) element;
               break;
            }
        }
        return id;
    }

    private boolean isValidPosition(int pos){
        if(gameArray[pos] != 0){return false;}
        else {return true;}
    }
}
