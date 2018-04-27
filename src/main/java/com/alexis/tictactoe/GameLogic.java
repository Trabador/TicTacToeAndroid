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

    GameLogic(Byte level){
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
        winCondition.add(new int[]{0,1,2});//first row positions for win
        winCondition.add(new int[]{3,4,5});//second row positions for win
        winCondition.add(new int[]{6,7,8});//third row positions for win
        winCondition.add(new int[]{0,3,6});//first column positions for win
        winCondition.add(new int[]{1,4,7});//second column positions for win
        winCondition.add(new int[]{2,5,8});//third column positions for win
        winCondition.add(new int[]{0,4,8});//diagonal positions for win
        winCondition.add(new int[]{2,4,6});//inverse diagonal positions for win
    }

    protected void markInGameTile(int key){
        int position = tiles.get(key);
        gameArray[position] = currentPlayer;
    }

    protected void changePlayerTurn(){
        if(currentPlayer == 1)
            currentPlayer = 2;
        else
            currentPlayer = 1;
    }

    protected boolean isTie(){
        //check is every tile is filled and nobody has wined
        for(int i=0;i<gameArray.length;i++){
            if(gameArray[i] == 0){
                return false;
            }
        }
        return true;
    }

    protected boolean isWinner(){
        //checks for every possible win condition if one player has reached the requirements for win
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

    protected int IA(){
        boolean isValid = false;
        int position;
        //in level 0 just checks for a free random tile
        do{
            Random randomTile = new Random();
            position = randomTile.nextInt(gameArray.length);
            isValid = isValidPosition(position);
        }while(!isValid);

        //in level 2 check if the corners are occupied, if so , overwrite the position
        if(level == 2){
            if(gameArray[0] == 0){position = 0;}
            else if(gameArray[2] == 0){position = 2;}
            else if(gameArray[6] == 0){position = 6;}
            else if(gameArray[8] == 0){position = 8;}
        }

        //in level 1 and 2, if the player makes 2 in a row try to block the third for the win
        if(level >= 1){
            int aux = hasPossibleWin();
            if(aux > -1){
                position = aux;
            }
        }
        //finally returns the ID from the mapping ViewId -> Position in array logic
        return getIdFromTiles(position);
    }

    private int getIdFromTiles(int pos){
        /*Returns the ID from the mapping View ID -> Position in array logic
          (Inverse operation from getting the id of the view clicked and putting the mark on the
           equivalent position)*/
        int id = 0;
        Enumeration enumeration = tiles.keys();

        while(enumeration.hasMoreElements()){
            Object element = enumeration.nextElement();
            if(tiles.get(element) == pos){
                id = (Integer) element;
                break;
            }
        }

        return id;
    }

    private int hasPossibleWin(){
        //Check if there are 2 marks of the same an returns if there are free space to block the win
        // Or try to win
        int counter = 0;
        int keyPosition = -1;
        int sample = -1;
        for(int[] elem:winCondition){
            for(int i=0;i<elem.length;i++){
                if(gameArray[elem[i]] == 0){//check for free space
                    keyPosition = elem[i];
                }
                else{
                    if(sample > -1){//if there is already a sample,
                        if(gameArray[elem[i]] == sample){counter++;}//checks if the sample is the same
                    }
                    else {
                        //there is no sample and set the sample
                        sample = gameArray[elem[i]];
                        counter++;
                    }
                }
            }
            if(counter == 2 && keyPosition>-1){//if meet requirements,returns the key position to block or win
                return keyPosition;
            }
            counter = 0;
            keyPosition = -1;
            sample = -1;
        }
        return -1;
    }


    private boolean isValidPosition(int pos){
        if(gameArray[pos] != 0){return false;}
        else {return true;}
    }
}
