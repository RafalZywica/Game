package com.kodilla.tictactoe.components;

import com.kodilla.tictactoe.TicTacToe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomBot {
    private final List<Integer> possibleMovesLocations = new ArrayList<>();
    private static final Random random = new Random();

    public void botPlay() {
        for (int i = 0; i < 9; i++) {
            if (!((Tile) TicTacToe.tileGroup.getChildren().get(i)).getValue().equals("X")
                    && !((Tile) TicTacToe.tileGroup.getChildren().get(i)).getValue().equals("O")) {
                possibleMovesLocations.add(i);
            }
        }
        if (possibleMovesLocations.size() != 0 && TicTacToe.playable) {
            int randomPossibleMovePosition
                    = random.nextInt(possibleMovesLocations.size());
            if (!TicTacToe.playerXTurn && TicTacToe.playerOneIsX) {
                ((Tile) TicTacToe.tileGroup.getChildren().get(possibleMovesLocations.get(randomPossibleMovePosition))).drawO();
                TicTacToe.playerXTurn = true;
                TicTacToe.numberOfMoves ++;
            } else if (TicTacToe.playerXTurn && !TicTacToe.playerOneIsX) {
                ((Tile) TicTacToe.tileGroup.getChildren().get(possibleMovesLocations.get(randomPossibleMovePosition))).drawX();
                TicTacToe.playerXTurn = false;
                TicTacToe.numberOfMoves ++;
            }
            TicTacToe.checkState();

        }
    }
}