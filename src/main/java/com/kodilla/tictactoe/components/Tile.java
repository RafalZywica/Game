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
    private Text text = new Text();

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public Tile(int x, int y) {
        Rectangle border = new Rectangle(TicTacToe.tileSize, TicTacToe.tileSize);

        border.setStroke(Color.BLACK);
        border.setFill(Color.WHITE);
        text.setFont(Font.font(200));
        setAlignment(Pos.CENTER);
        getChildren().addAll(border, text);


        setOnMouseClicked(event -> {
            if (!TicTacToe.playable)
                return;
            if (event.getButton() == MouseButton.PRIMARY) {
                if (!TicTacToe.playerXTurn || !text.getText().isEmpty())
                    return;

                drawX();
                TicTacToe.playerXTurn = false;
                TicTacToe.checkState();

            } else if (event.getButton() == MouseButton.SECONDARY) {
                if (TicTacToe.playerXTurn ||!text.getText().isEmpty())
                    return;

                drawO();
                TicTacToe.playerXTurn = true;
                TicTacToe.checkState();
            }
        });

    }
    public String getValue() {
        return text.getText();
    }
    public Double getCenterX() {
        return getTranslateX() + 50;
    }
    public Double getCenterY() {
        return getTranslateY();
    }
    private void drawX() {
        text.setText("X");
    }
    private void drawO() {
        text.setText("O");
    }
}
