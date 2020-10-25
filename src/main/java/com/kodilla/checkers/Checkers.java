package com.kodilla.checkers;

import com.kodilla.checkers.components.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import static javafx.scene.paint.RadialGradient.valueOf;

public class Checkers extends Application implements EventHandler<ActionEvent> {

    // Define these for adding additional functionality (Different game types with different board size)
    public static final int tileSize = 100;
    public static int width = 8;
    public static int height = 8;

    private Tile[][] board = new Tile[width][height];

    private Group tileGroup = new Group();
    private Group checkerGroup = new Group();
    private Image imageback = new Image("file:src/main/resources/WoodBackground.png");
    private GridPane grid = new GridPane();

    private Parent createcontent(GridPane grid) {

        grid.setPrefSize(tileSize * width, tileSize * height);
        grid.getChildren().addAll(tileGroup ,checkerGroup);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j< height; j++) {
                Tile tile = new Tile((i + j) % 2 == 0, i, j);
                board[i][j] = tile;

                tileGroup.getChildren().add(tile);

                Checker checker = null;

                if (j <= 2 && (i + j) % 2 != 0) {
                    checker = makeChecker(CheckerType.Grey, i, j);
                }
                if (j >= 5 && (i + j) % 2 != 0) {
                    checker = makeChecker(CheckerType.White, i, j);
                }
                if (checker != null) {
                    tile.setChecker(checker);
                    checkerGroup.getChildren().add(checker);
                }
            }
        }
        return grid;
    }

    private Checker makeChecker(CheckerType type, int x, int y) {
        Checker checker = new Checker(type, x, y);

        checker.setOnMouseReleased(e -> {
            int finalX = toBoard(checker.getLayoutX());
            int finalY = toBoard(checker.getLayoutY());

            //MoveResult result = attemptMove(checker, finalX, finalY);
            MoveResult result;

            if (finalX < 0 || finalY < 0 || finalX >= width || finalY >= height) {
                result = new MoveResult(MoveType.NONE);
            } else {
                result = attemptMove(checker, finalX, finalY);
            }

            int x0 = toBoard(checker.getInitialMouseX());
            int y0 = toBoard(checker.getInitialMouseY());

            switch (result.getType()) {
                case NONE:

                    checker.cancelMove();
                    break;

                case NORMAL:

                    checker.move(finalX,finalY);
                    board[x0][y0].setChecker(null);
                    board[finalX][finalY].setChecker(checker);

                    break;

                case KILL:

                    checker.move(finalX,finalY);
                    board[x0][y0].setChecker(null);
                    board[finalX][finalY].setChecker(checker);

                    Checker hostileChecker = result.getChecker();
                    board[toBoard(hostileChecker.getInitialMouseX())][toBoard(hostileChecker.getInitialMouseY())].setChecker(null);
                    checkerGroup.getChildren().remove(hostileChecker);

                    break;
            }
        });
        return checker;
    }

    private MoveResult attemptMove(Checker checker, int finalX, int finalY) {
        if (board[finalX][finalY].hasChecker() || (finalX + finalY) % 2 == 0) {
            return new MoveResult(MoveType.NONE);
        }
        int x0 = toBoard(checker.getInitialMouseX());
        int y0 = toBoard(checker.getInitialMouseY());

        if (Math.abs(finalX - x0) == 1 && finalY - y0 == checker.getType().moveDir) {

            return new MoveResult(MoveType.NORMAL);

        } else if (Math.abs(finalX - x0) == 2) {

            int x1 = x0 + (finalX - x0) / 2;
            int y1 = y0 + (finalY - y0) / 2;

            if (board[x1][y1].hasChecker() && board[x1][y1].getChecker().getType() != checker.getType()) {

                return new MoveResult(MoveType.KILL, board[x1][y1].getChecker());
            }
        }
        return new MoveResult(MoveType.NONE);
    }

    private int toBoard(double pixel) {
        return (int)(pixel + tileSize / 2) / tileSize;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        grid.setAlignment(Pos.CENTER);
        grid.setBackground(background);
        Button button = new Button("Change game mode to 10x10");
        button.setOnAction(this);

        Scene scene = new Scene(createcontent(grid),1000, 1000);
        grid.getChildren().add(button);

        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    @Override
    public void handle(ActionEvent event) {
        width = 10;
        height = 10;
        Scene scene = new Scene(createcontent(grid), 1000, 1000);
    }
}