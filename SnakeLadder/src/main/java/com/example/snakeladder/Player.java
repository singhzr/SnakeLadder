package com.example.snakeladder;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {
    private Circle coin;
    private int currentPosition;
    private String name;

    static Board gameBoard = new Board();

    public Player(int tileSize, Color coinColor, String playerName) {
        coin = new Circle(tileSize / 2);
        coin.setFill(coinColor);
        currentPosition = 0;  // you can set 0, out of board
        movePlayer(1);
        name = playerName;
    }

    // To move our player
    public void movePlayer(int diceValue){
        if(currentPosition + diceValue <= 100) {
            currentPosition += diceValue;

            translateAnimation(diceValue);
            TranslateTransition secondMove = null, firstMove = translateAnimation(diceValue);

            //translateAnimation(diceValue) ;
            int newPosition = gameBoard.getNewPosition(currentPosition);
            if(newPosition != currentPosition && newPosition != -1){
                currentPosition = newPosition;
                secondMove = translateAnimation(6);
            }

            if(secondMove == null){
                firstMove.play();
            }
            else {
                SequentialTransition sequentialTransition = new SequentialTransition(firstMove,
                        new PauseTransition(Duration.millis(1000)), secondMove);
                sequentialTransition.play();
            }
        }
//        int x = gameBoard.getXCoordinates(currentPosition);
//        int y = gameBoard.getYCoordinates(currentPosition);
//        coin.setTranslateX(x);
//        coin.setTranslateY(y);
    }

    // Animation
    private TranslateTransition translateAnimation(int diceValue){
        TranslateTransition animate = new TranslateTransition(Duration.millis(200*diceValue), coin);
        animate.setToX(gameBoard.getXCoordinates(currentPosition));
        animate.setToY(gameBoard.getYCoordinates(currentPosition));
        animate.setAutoReverse(false);
        return animate;
    }

    // Bringing players to starting point
    public void startingPosition(){
        currentPosition = 0;
        movePlayer(1);
    }

    boolean isWinner(){
        if(currentPosition == 100)
            return true;
        return false;
    }

    public Circle getCoin() {
        return coin;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public String getName() {
        return name;
    }
}