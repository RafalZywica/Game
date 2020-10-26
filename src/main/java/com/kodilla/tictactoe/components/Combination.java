package com.kodilla.tictactoe.components;

public class Combination {
    private Tile[] tiles;
    public Combination(Tile...tiles){
        this.tiles = tiles;
    }

    public Tile[] getTiles() {
        return tiles;
    }

    public boolean isComplete() {
        if (tiles[0].getValue().isEmpty())
            return false;
        return tiles[0].getValue().equals(tiles[1].getValue())
                && tiles[0].getValue().equals(tiles[2].getValue());
    }
}
