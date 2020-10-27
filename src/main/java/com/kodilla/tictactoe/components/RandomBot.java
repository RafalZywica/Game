package com.kodilla.tictactoe.components;

import com.kodilla.tictactoe.TicTacToe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomBot {
List<Integer> k = new ArrayList<>();
Random rand = new Random();

    public RandomBot() {
    }
    public void botPlay() {


        for (int i = 0; i < 9; i++) {
            if (((Tile) TicTacToe.tileGroup.getChildren().get(i)).getValue() != "X"
                    && ((Tile) TicTacToe.tileGroup.getChildren().get(i)).getValue() != "O") {
                k.add(i);
            }
        }

        if (k.size() != 0 && TicTacToe.playable == true) {

            int r = rand.nextInt(k.size());

            if (!TicTacToe.playerXTurn && TicTacToe.playerOneIsX) {
                ((Tile) TicTacToe.tileGroup.getChildren().get(k.get(r))).drawO();
                TicTacToe.playerXTurn = true;

            } else if (TicTacToe.playerXTurn && !TicTacToe.playerOneIsX) {
                ((Tile) TicTacToe.tileGroup.getChildren().get(k.get(r))).drawX();
                TicTacToe.playerXTurn = false;
            }

            TicTacToe.numberOfMoves++;
            TicTacToe.checkState();

        }
    }
}
