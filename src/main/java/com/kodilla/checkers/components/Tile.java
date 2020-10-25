package com.kodilla.checkers.components;

import com.kodilla.checkers.Checkers;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

    private Checker checker;
    public boolean hasChecker() {
        return checker != null;
    }

    public Checker getChecker() {
        return checker;
    }

    public void setChecker(Checker checker) {
        this.checker = checker;
    }

    public Tile (boolean type, int x, int y) {
        setWidth(Checkers.tileSize);
        setHeight(Checkers.tileSize);

        relocate(x * Checkers.tileSize, y * Checkers.tileSize);

        setFill(type ? Color.valueOf("#d6d6d6") : Color.valueOf("#702963"));

    }

}
