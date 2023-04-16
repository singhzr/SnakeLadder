package com.example.snakeladder;

import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Board {
    // Map Co-ordinated to the number
    ArrayList<Pair<Integer, Integer>> positionCoordinates;
    ArrayList<Integer> SnakeLadderPosition;


    public Board(){
        positionCoordinates = new ArrayList<>();
        populatePositionCoordinates();
        populateSnakeLadder();
    }

    // Adding values into the number (1-100)
    private void populatePositionCoordinates(){
        //Putting dummy values of 0th index
        positionCoordinates.add(new Pair<>(0,0));
        for (int i = 0; i < SnakeLadder.height; i++) {
            for (int j = 0; j < SnakeLadder.width; j++) {
                // Create X coordinates
                int xCord = 0;

                if(i % 2 == 0){
                    xCord = j * SnakeLadder.tileSize + SnakeLadder .tileSize/2;
                }
                else{
                    xCord = SnakeLadder.tileSize * SnakeLadder.height - (j * SnakeLadder.tileSize) - SnakeLadder.tileSize / 2;
                }
                // Create Y coordinates
                int yCord = SnakeLadder.tileSize * SnakeLadder.height - (i * SnakeLadder.tileSize) - SnakeLadder.tileSize / 2;
                positionCoordinates.add(new Pair<>(xCord,yCord));
            }
        }
    }

    private void populateSnakeLadder(){
        SnakeLadderPosition = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            // Mapped each index with same value
            SnakeLadderPosition.add(i);
        }

        // Mapping Snake and ladder
//        SnakeLadderPosition.set(1,38);
//        SnakeLadderPosition.set(4,14);
//        SnakeLadderPosition.set(9,31);
//        SnakeLadderPosition.set(21,42);
//        SnakeLadderPosition.set(28,84);
//        SnakeLadderPosition.set(51,67);
//        SnakeLadderPosition.set(71,91);
//        SnakeLadderPosition.set(80,100);
//
//        //For snake bite
//        SnakeLadderPosition.set(17,7);
//        SnakeLadderPosition.set(54,34);
//        SnakeLadderPosition.set(64,60);
//        SnakeLadderPosition.set(62,19);
//        SnakeLadderPosition.set(93,73);
//        SnakeLadderPosition.set(95,75);
//        SnakeLadderPosition.set(98,79);


        SnakeLadderPosition.set(38,1);
        SnakeLadderPosition.set(14,4);
        SnakeLadderPosition.set(31,9);
        SnakeLadderPosition.set(42,21);
        SnakeLadderPosition.set(84,28);
        SnakeLadderPosition.set(67,51);
        SnakeLadderPosition.set(91,71);
        SnakeLadderPosition.set(100,80);

        SnakeLadderPosition.set(7,17);
        SnakeLadderPosition.set(34,54);
        SnakeLadderPosition.set(60,64);
        SnakeLadderPosition.set(19,62);
        SnakeLadderPosition.set(73,93);
        SnakeLadderPosition.set(75,95);
        SnakeLadderPosition.set(79,98);
    }

    public int getNewPosition(int currentPosition){
        if(currentPosition > 0 && currentPosition <= 100){
            return SnakeLadderPosition.get(currentPosition);
        }
        return -1;
    }

    // Get Co-ordinates to move the position of player
    int getXCoordinates(int position){
        if(position>=1 && position<=100)
            return positionCoordinates.get(position).getKey();
        return -1;
    }

    int getYCoordinates(int position){
        if(position>=1 && position<=100)
            return positionCoordinates.get(position).getValue();
        return -1;
    }


    public static void main(String[] args) {
        Board board = new Board();
        for (int i = 0; i < board.positionCoordinates.size(); i++) {
            System.out.println(i + "  $ x : " + board.positionCoordinates.get(i).getKey() +
                    " y : " + board.positionCoordinates.get(i).getValue()
            );
        }
    }
}