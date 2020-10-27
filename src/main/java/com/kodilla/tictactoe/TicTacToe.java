package com.kodilla.tictactoe;

import com.kodilla.tictactoe.components.Combination;
import com.kodilla.tictactoe.components.RandomBot;
import com.kodilla.tictactoe.components.Tile;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class TicTacToe extends Application {

    public static final int tileSize = 200, width = 3, height = 3;
    public static boolean playerXTurn = true, playable = true, playerOneIsX = true;
    public static int numberOfMoves = 0;
    private static String playerSide = new String("X");
    private static Text whichSideIsPlayerOn = new Text("You are playing as " + playerSide);
    private static Text winning = new Text(null);
    private static Text drawing = new Text();
    private Image imageback = new Image("file:src/main/resources/TicTacToeTexture.png");
    public static Group tileGroup = new Group();
    private VBox buttonBox = new VBox(5);
    private static GridPane root = new GridPane();
    public static Tile[][] board = new Tile[3][3];
    private static List<Combination> combinations = new ArrayList<>();
    public static List<Tile> tilesList = new ArrayList<>();

    private Parent createContent() {

        root.setPrefSize(width *tileSize, height * tileSize);
        root.add(tileGroup, 1, 1);

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                Tile tile = new Tile(i, j);
                tile.setTranslateX(i * tileSize);
                tile.setTranslateY(j * tileSize);

                tileGroup.getChildren().add(tile);
                tilesList.add(tile);

                board[i][j] = tile;

            }
        }
        for (int y = 0; y < 3; y++) {
            combinations.add(new Combination(board[0][y],board[1][y], board[2][y]));
        }
        for (int x = 0; x < 3; x++) {
            combinations.add(new Combination(board[x][0],board[x][1], board[x][2]));
        }
        combinations.add(new Combination(board[0][0], board[1][1], board[2][2]));
        combinations.add(new Combination(board[2][0], board[1][1], board[0][2]));

        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }
    public static void checkState() {
        for (Combination combination: combinations) {
            if (combination.isComplete()) {
                playable = false;
                playWinAnimation(combination);
                break;
            }
        }
        for (Combination combination: combinations) {
            if (numberOfMoves == 9 && playable == true) {
                playDrawAnimation(combination);
                break;
            }
        }
    }
    private static void playWinAnimation(Combination combination) {

        System.out.println(combination.getTiles()[0].getValue());

        winning.setFont(Font.font(200));
        winning.setY(200);
        winning.setStroke(Color.BLACK);
        winning.setStrokeWidth(5);

        if (combination.getTiles()[0].getValue() == "X") {
            winning.setText("X Wins");
            winning.setFill(Color.GREEN);
        } else if (combination.getTiles()[0].getValue() == "O"){
            winning.setText("O Wins");
            winning.setFill(Color.RED);
        }
        System.out.println(numberOfMoves);

        root.add(winning,1, 0);

    }
    private static void playDrawAnimation(Combination combination) {

        drawing.setFont(Font.font(200));
        drawing.setY(200);
        drawing.setFill(Color.BLUE);
        drawing.setStroke(Color.BLACK);
        drawing.setStrokeWidth(5);
        drawing.setText("DRAW");
        root.add(drawing,1, 0);
    }
    public static void botMoves() {
        RandomBot randomBot = new RandomBot();
        randomBot.botPlay();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BackgroundSize backgroundSize =
                new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage =
                new BackgroundImage(imageback, BackgroundRepeat.REPEAT,
                        BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        root.setBackground(background);
        root.setAlignment(Pos.CENTER);
        root.setVgap(10);
        root.setHgap(10);

        whichSideIsPlayerOn.setStroke(Color.BLACK);
        whichSideIsPlayerOn.setStrokeWidth(1);
        whichSideIsPlayerOn.setFill(Color.WHITE);
        whichSideIsPlayerOn.setFont(Font.font(50));

        root.add(whichSideIsPlayerOn, 0, 1);

        Button newGameButton = new Button("New Game");
        newGameButton.setFont(Font.font(20));
        newGameButton.setMinWidth(200);
        newGameButton.setOnAction(value -> {
            /*if (playerOneIsX == true) {
                playerXTurn = true;
            } else {
                playerXTurn = false;
            }*/
            playerXTurn = true;
            playable = true;
            numberOfMoves = 0;
            winning.setText(null);
            drawing.setText(null);
            for (int i = 0; i < 9; i++){
                ((Tile) TicTacToe.tileGroup.getChildren().get(i)).cleanTile();
            }

            System.out.println("NGB playerXturn before botMoves " + playerXTurn);
            System.out.println("NGB playerSide before botMoves " + playerSide);

            if (!playerOneIsX) {
                botMoves();
            }
            System.out.println("NGB playerXturn after botMoves " + playerXTurn);
            System.out.println("NGB playerSide after botMoves " + playerSide);
            System.out.println("");
        });

        Button changeSidesButton = new Button("Change Sides");
        changeSidesButton.setFont(Font.font(20));
        changeSidesButton.setMinWidth(200);
        changeSidesButton.setOnAction(value -> {
            if (playerOneIsX == true) {
                playerOneIsX = false;
                playerSide = "O";
            } else {
                playerOneIsX = true;
                playerSide = "X";
            }
            whichSideIsPlayerOn.setText("You are playing as " + playerSide);
            System.out.println("CSB playerXturn before botMoves " + playerXTurn);
            System.out.println("CSB playerSide before botMoves " + playerSide);
            System.out.println("");

            if (!playerOneIsX && playerXTurn) {
                botMoves();
            }
            if (playerOneIsX && !playerXTurn) {
                botMoves();
            }

            System.out.println("CSB playerXturn after botMoves " + playerXTurn);
            System.out.println("CSB playerSide after botMoves " + playerSide);
        });

        buttonBox.getChildren().addAll(newGameButton, changeSidesButton);
        buttonBox.setAlignment(Pos.CENTER);

        root.add(buttonBox, 2, 1);

        Scene scene = new Scene(createContent(), 1600, 1200);

        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}