package com.example.snakeladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeLadder extends Application { //entry point of JavaFX applications is the Application class

    public static final int tileSize=40, width=10, height=10;
    // Adding Button to Pane & display(label) line
    public static final int buttonLine = height*tileSize + 20,  infoLine = buttonLine - 20;

    private static Dice dice = new Dice();
    private Player playerOne, playerTwo;

    private boolean gameStarted = false, playerOneTurn = false, playerTwoTurn = false;

    private Pane createContent(){ //creates canvas or board
        Pane root = new Pane();
        root.setPrefSize(width*tileSize, height*tileSize + 50);//defines size

        // To create a Tile
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tile tile = new Tile(tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                root.getChildren().add(tile);
            }
        }

        Image img = new Image("C:\\Users\\yousu\\IdeaProjects\\SnakeLadder\\src\\main\\img.png"); //load image from file

        ImageView board = new ImageView(); //to display image on UI
        board.setImage(img);
        board.setFitHeight(height*tileSize);

        board.setFitWidth(width*tileSize);


        // To create Buttons
        // Button is component that can control the behaviour of app
        // An event is generated whenever the button is clicked
        Button playerOneButton = new Button("Suraj");
        Button playerTwoButton = new Button("Ayush");
        Button startButton = new Button("Start");

        //Translate all the buttons to buttonLine : Adding controls
        playerOneButton.setTranslateY(buttonLine);
        playerOneButton.setTranslateX(20);
        //playerOneButton.setDisable(true);
        playerTwoButton.setTranslateY(buttonLine);
        playerTwoButton.setTranslateX(300);
        // playerTwoButton.setDisable(true);
        startButton.setTranslateY(buttonLine);
        startButton.setTranslateX(160);


        //Info Display
        Label playerOneLabel = new Label("  Your turn");
        Label playerTwoLabel = new Label("Your turn");
        Label diceLabel = new Label("Start the Game");

        // Label translation
        playerOneLabel.setTranslateY(infoLine);
        playerOneLabel.setTranslateX(10);
        playerTwoLabel.setTranslateY(infoLine);
        playerTwoLabel.setTranslateX(300);
        diceLabel.setTranslateY(infoLine);
        diceLabel.setTranslateX(160);


        // Initializing players
        playerOne = new Player(tileSize, Color.BLACK, "Suraj");

        // When coins overlap, to recognize the players reducing the tile size of player 2
        playerTwo = new Player(tileSize - 5, Color.RED, "Ayush");

        // Player Action
        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerOneTurn){
                        int diceValue = dice.getRolledDiceValue();
                        diceLabel.setText("Dice Value : " + diceValue);
                        //Move the player 1
                        playerOne.movePlayer(diceValue);
                        // Winning condition
                        if(playerOne.isWinner()){
                            diceLabel.setText("Winner is " + playerOne.getName());
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            playerTwoTurn = true;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");

                            startButton.setDisable(false);
                            startButton.setText("Restart");
                            gameStarted = false;
                        } else {
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            playerTwoTurn = true;
                            playerTwoButton.setDisable(false);
                            playerTwoLabel.setText("Your turn");
                        }
                    }
                }

            }
        });

        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerTwoTurn){
                        int diceValue = dice.getRolledDiceValue();
                        diceLabel.setText("Dice Value : " + diceValue);
                        //Move the player 1
                        playerTwo.movePlayer(diceValue);
                        // Winning condition
                        if(playerTwo.isWinner()){
                            diceLabel.setText("Winner is " + playerTwo.getName());
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            playerTwoTurn = true;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");

                            startButton.setDisable(false);
                            startButton.setText("Restart");
                        } else {
                            playerOneTurn = true;
                            playerOneButton.setDisable(false);
                            playerOneLabel.setText("  Your Turn");

                            playerTwoTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");
                        }

                    }
                }

            }
        });

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted = true;
                diceLabel.setText("Game Started");
                startButton.setDisable(true);
                playerOneTurn = true;
                playerOneLabel.setText("  Your Turn");
                // Enable player one button
                playerOneButton.setDisable(false);
                playerOne.startingPosition();

                playerTwoTurn = false;
                playerTwoLabel.setText("");
                playerTwoButton.setDisable(true);
                playerTwo.startingPosition();
            }
        });

        root.getChildren().addAll(board, playerOneButton, playerTwoButton, startButton,
                playerOneLabel, playerTwoLabel, diceLabel,
                playerOne.getCoin(), playerTwo.getCoin());




        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();

    }
}
