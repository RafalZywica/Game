package com.kodilla.tictactoe.components;


import com.kodilla.tictactoe.TicTacToe;
import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Tile extends StackPane {
    private final Text text = new Text();

    public Tile() {
        Rectangle border = new Rectangle(TicTacToe.TILE_SIZE, TicTacToe.TILE_SIZE);

        border.setStroke(Color.BLACK);
        border.setFill(Color.WHITE);
        border.setStrokeWidth(5);
        text.setFont(Font.font(200));
        text.setStroke(Color.BLACK);
        text.setStrokeWidth(2);
        setAlignment(Pos.CENTER);
        getChildren().addAll(border, text);


        setOnMouseClicked(event -> {
            if (!TicTacToe.playable)
                return;
            if (event.getButton() == MouseButton.PRIMARY) {
                if (TicTacToe.playerXTurn && text.getText().isEmpty() && TicTacToe.playerOneIsX) {
                    drawX();
                    TicTacToe.playerXTurn = false;
                } else if (!TicTacToe.playerXTurn && text.getText().isEmpty() && !TicTacToe.playerOneIsX) {
                    drawO();
                    TicTacToe.playerXTurn = true;
                }
                TicTacToe.numberOfMoves++;
                TicTacToe.checkState();
                TicTacToe.botMoves();
            } /*else if (event.getButton() == MouseButton.SECONDARY) {
                if (TicTacToe.playerXTurn ||!text.getText().isEmpty())
                    return;

                drawO();
                TicTacToe.playerXTurn = true;
                TicTacToe.numberOfMoves++;
                TicTacToe.checkState();
            }*/
        });
    }

    public String getValue() {
        return text.getText();
    }

    public void drawX() {
        text.setText("X");
        text.setFill(Color.GREEN);
    }

    public void drawO() {
        text.setText("O");
        text.setFill(Color.RED);
    }

    public void cleanTile() {
        text.setText("");
    }
}
