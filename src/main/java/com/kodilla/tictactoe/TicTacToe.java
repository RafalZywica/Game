package com.kodilla.tictactoe;

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

public class TicTacToe extends Application {

    public static final int tileSize = 200, width = 3, height = 3;
    private Image imageback = new Image("file:src/main/resources/TicTacToeTexture.png");
    private Group tileGroup = new Group();
    private Group pieceGroup = new Group();
    private GridPane root = new GridPane();

    private Parent createContent() {

        root.setPrefSize(width *tileSize, height * tileSize);
        root.getChildren().addAll(tileGroup, pieceGroup);

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                Tile tile = new Tile(i, j);
                tile.setTranslateX(i * tileSize);
                tile.setTranslateY(j * tileSize);

                tileGroup.getChildren().add(tile);

            }
        }

        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);


        root.setBackground(background);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(createContent(), 1600, 900);

        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}