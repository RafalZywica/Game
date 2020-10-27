package com.kodilla.tictactoe;

import com.kodilla.tictactoe.components.Combination;
import com.kodilla.tictactoe.components.RandomBot;
import com.kodilla.tictactoe.components.Tile;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class TicTacToe extends Application {

    public static final int TILE_SIZE = 200;
    public static final int WIDTH = 3;
    public static final int HEIGHT = 3;
    public static boolean playerXTurn = true;
    public static boolean playable = true;
    public static boolean playerOneIsX = true;
    public static int numberOfMoves = 0;
    private static String playerSide = "X";
    private static final Text WHICH_SIDE_IS_PLAYER_ON = new Text("You are playing as " + playerSide);
    private static final Text WINNING = new Text();
    private static final Text DRAWING = new Text();
    private final Image imageback = new Image("file:src/main/resources/TicTacToeTexture.png");
    public static Group tileGroup = new Group();
    private final VBox buttonBox = new VBox(5);
    private static final GridPane ROOT = new GridPane();
    public static Tile[][] board = new Tile[3][3];
    private static final List<Combination> COMBINATION_ARRAY_LIST = new ArrayList<>();
    public static List<Tile> tilesList = new ArrayList<>();

    private Parent createContent() {
        ROOT.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        ROOT.add(tileGroup, 1, 1);

        for (int j = 0; j < HEIGHT; j++) {
            for (int i = 0; i < WIDTH; i++) {
                Tile tile = new Tile();
                tile.setTranslateX(i * TILE_SIZE);
                tile.setTranslateY(j * TILE_SIZE);

                tileGroup.getChildren().add(tile);
                tilesList.add(tile);

                board[i][j] = tile;
            }
        }
        for (int y = 0; y < 3; y++) {
            COMBINATION_ARRAY_LIST.add(new Combination(board[0][y], board[1][y], board[2][y]));
        }
        for (int x = 0; x < 3; x++) {
            COMBINATION_ARRAY_LIST.add(new Combination(board[x][0], board[x][1], board[x][2]));
        }
        COMBINATION_ARRAY_LIST.add(new Combination(board[0][0], board[1][1], board[2][2]));
        COMBINATION_ARRAY_LIST.add(new Combination(board[2][0], board[1][1], board[0][2]));

        return ROOT;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void checkState() {
        for (Combination combination : COMBINATION_ARRAY_LIST) {
            if (combination.isComplete()) {
                playable = false;
                playWinAnimation(combination);
                break;
            }
        }
        if (numberOfMoves == 9 && playable) {
            playDrawAnimation();
        }
    }

    private static void playWinAnimation(Combination combination) {
        WINNING.setFont(Font.font(200));
        WINNING.setY(200);
        WINNING.setStroke(Color.BLACK);
        WINNING.setStrokeWidth(5);

        if (combination.getTiles()[0].getValue().equals("X")) {
            WINNING.setText("X Wins");
            WINNING.setFill(Color.GREEN);
        } else if (combination.getTiles()[0].getValue().equals("O")) {
            WINNING.setText("O Wins");
            WINNING.setFill(Color.RED);
        }
        System.out.println(numberOfMoves);

        ROOT.add(WINNING, 1, 0);

    }

    private static void playDrawAnimation() {
        DRAWING.setFont(Font.font(200));
        DRAWING.setY(200);
        DRAWING.setFill(Color.BLUE);
        DRAWING.setStroke(Color.BLACK);
        DRAWING.setStrokeWidth(5);
        DRAWING.setText("DRAW");
        ROOT.add(DRAWING, 1, 0);
    }

    public static void botMoves() {
        RandomBot randomBot = new RandomBot();
        randomBot.botPlay();
    }

    @Override
    public void start(Stage primaryStage) {
        BackgroundSize backgroundSize =
                new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage =
                new BackgroundImage(imageback, BackgroundRepeat.REPEAT,
                        BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        ROOT.setBackground(background);
        ROOT.setAlignment(Pos.CENTER);
        ROOT.setVgap(10);
        ROOT.setHgap(10);

        WHICH_SIDE_IS_PLAYER_ON_CONFIG();

        Button newGameButton = new Button("New Game");
        newGameButtonMethod(newGameButton);

        Button changeSidesButton = new Button("Change Sides");
        changeSidesButtonMethod(changeSidesButton);

        buttonBox.getChildren().addAll(newGameButton, changeSidesButton);
        buttonBox.setAlignment(Pos.CENTER);

        ROOT.add(buttonBox, 2, 1);

        Scene scene = new Scene(createContent(), 1600, 1200);

        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void WHICH_SIDE_IS_PLAYER_ON_CONFIG() {
        WHICH_SIDE_IS_PLAYER_ON.setStroke(Color.BLACK);
        WHICH_SIDE_IS_PLAYER_ON.setStrokeWidth(1);
        WHICH_SIDE_IS_PLAYER_ON.setFill(Color.WHITE);
        WHICH_SIDE_IS_PLAYER_ON.setFont(Font.font(50));

        ROOT.add(WHICH_SIDE_IS_PLAYER_ON, 0, 1);
    }

    private void changeSidesButtonMethod(Button changeSidesButton) {
        changeSidesButton.setFont(Font.font(20));
        changeSidesButton.setMinWidth(200);
        changeSidesButton.setOnAction(value -> {
            if (playerOneIsX) {
                playerOneIsX = false;
                playerSide = "O";
            } else {
                playerOneIsX = true;
                playerSide = "X";
            }
            WHICH_SIDE_IS_PLAYER_ON.setText("You are playing as " + playerSide);

            if (!playerOneIsX && playerXTurn) {
                botMoves();
            }
            if (playerOneIsX && !playerXTurn) {
                botMoves();
            }
        });
    }

    private void newGameButtonMethod(Button newGameButton) {
        newGameButton.setFont(Font.font(20));
        newGameButton.setMinWidth(200);
        newGameButton.setOnAction(value -> {
            playerXTurn = true;
            playable = true;
            numberOfMoves = 0;
            WINNING.setText(null);
            DRAWING.setText(null);
            for (int i = 0; i < 9; i++) {
                ((Tile) TicTacToe.tileGroup.getChildren().get(i)).cleanTile();
            }

            if (!playerOneIsX) {
                botMoves();
            }
        });
    }
}