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

import java.util.ArrayList;
import java.util.List;

public class TicTacToe extends Application {

    public static final int tileSize = 200, width = 3, height = 3;
    public static boolean playerXTurn = true, playable =true;
    public static int numberOfMoves = 0;

    private Image imageback = new Image("file:src/main/resources/TicTacToeTexture.png");
    public static Group tileGroup = new Group();
    private static GridPane root = new GridPane();
    public static Tile[][] board = new Tile[3][3];
    private static List<Combination> combinations = new ArrayList<>();
    public static List<Tile> tilesList = new ArrayList<>();

    private Parent createContent() {

        root.setPrefSize(width *tileSize, height * tileSize);
        root.add(tileGroup, 0, 1);

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
        /*Line line = new Line();
        line.setStrokeWidth(5);
        line.setStartX(combination.getTiles()[0].getCenterX());
        line.setStartY(combination.getTiles()[0].getCenterY());
        line.setEndX(combination.getTiles()[0].getCenterX());
        line.setEndY(combination.getTiles()[0].getCenterY());*/

        System.out.println(combination.getTiles()[0].getValue());

        Text winning = new Text();
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


        root.add(winning,0, 0);
        /*root.add(line, 0, 1);

        Timeline timeline = new Timeline();

        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1),
                new KeyValue(line.endXProperty(), combination.getTiles()[2].getCenterX()),
                new KeyValue(line.endYProperty(), combination.getTiles()[2].getCenterY())));
        timeline.play();*/
    }
    private static void playDrawAnimation(Combination combination) {
        Text drawing= new Text();
        drawing.setFont(Font.font(200));
        drawing.setY(200);
        drawing.setFill(Color.BLUE);
        drawing.setStroke(Color.BLACK);
        drawing.setStrokeWidth(5);
        drawing.setText("DRAW");
        root.add(drawing,0, 0);
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

        Scene scene = new Scene(createContent(), 1000, 1000);

        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}