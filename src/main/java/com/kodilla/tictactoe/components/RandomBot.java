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
        System.out.println(k);

        if (k.size() != 0) {

            int r = rand.nextInt(k.size());
            System.out.println("random " + r);
            System.out.println("random position" + k.get(r));
            if (TicTacToe.playerOneTurn)
                return;
            ((Tile) TicTacToe.tileGroup.getChildren().get(k.get(r))).drawO();
            TicTacToe.playerOneTurn = true;
            TicTacToe.numberOfMoves++;
            TicTacToe.checkState();

        }
        System.out.println();
        System.out.println(k.size());
        System.out.println(TicTacToe.numberOfMoves);
    }
}
