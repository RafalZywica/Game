package com.kodilla.tictactoe.components;


import com.kodilla.checkers.Checkers;
import com.kodilla.tictactoe.TicTacToe;
import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import org.w3c.dom.*;
import javafx.scene.text.Text;

import java.awt.*;

public class Tile extends StackPane {
    private Text text = new Text();
    private boolean playerXTurn = true;

    public Tile(int x, int y) {
        Rectangle border = new Rectangle(TicTacToe.tileSize, TicTacToe.tileSize);

        //border.relocate(x * TicTacToe.tileSize, y * TicTacToe.tileSize);

        border.setStroke(Color.BLACK);
        border.setFill(Color.WHITE);
        text.setFont(Font.font(200));

        setAlignment(Pos.CENTER);
        getChildren().addAll(border, text);

        setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                if (playerXTurn == false || text.getText().isEmpty() == false)
                    return;

                drawX();
                playerXTurn = false;

            } else if (event.getButton() == MouseButton.SECONDARY) {
                if (playerXTurn == true || text.getText().isEmpty() == false)
                    return;

                drawO();
                playerXTurn = true;
            }
        });

    }
    private void drawX() {
        text.setText("X");
    }
    private void drawO() {
        text.setText("O");
    }
}
