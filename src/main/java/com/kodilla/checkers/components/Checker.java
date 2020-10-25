package com.kodilla.checkers.components;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

import static com.kodilla.checkers.Checkers.tileSize;

public class Checker extends StackPane {

    private CheckerType type;

    private double finalMouseX, finalMouseY;
    private double initialMouseX, initialMouseY;

    public Checker(CheckerType type, int x, int y){
        this.type = type;

        move(x, y);

        Ellipse bg = new Ellipse(tileSize * 0.3125, tileSize * 0.26);
        bg.setFill(Color.BLACK);

        bg.setStroke(Color.BLACK);
        bg.setStrokeWidth(tileSize * 0.03);

        bg.setTranslateX((tileSize - tileSize * 0.3125 * 2) / 2);
        bg.setTranslateY((tileSize - tileSize * 0.26 * 2) / 2 + tileSize * 0.07);

        Ellipse ellipse = new Ellipse(tileSize * 0.3125, tileSize * 0.26);
        ellipse.setFill(type == CheckerType.Grey ? Color.valueOf("#404040") : Color.valueOf("#fff"));

        ellipse.setStroke(Color.BLACK);
        ellipse.setStrokeWidth(tileSize * 0.03);

        ellipse.setTranslateX((tileSize - tileSize * 0.3125 * 2) / 2);
        ellipse.setTranslateY((tileSize - tileSize * 0.26 * 2) / 2);

        getChildren().addAll(bg, ellipse);

        setOnMousePressed(e -> {
            finalMouseX = e.getSceneX();
            finalMouseY = e.getSceneY();
        });
        setOnMouseDragged(e -> {
            relocate(e.getSceneX() - finalMouseX + initialMouseX, e.getSceneY() - finalMouseY + initialMouseY);
        });
    }

    public void move(int x, int y) {
        initialMouseX = x * tileSize;
        initialMouseY = y * tileSize;
        relocate(initialMouseX, initialMouseY);
    }

    public double getFinalMouseX() {
        return finalMouseX;
    }

    public double getFinalMouseY() {
        return finalMouseY;
    }

    public double getInitialMouseX() {
        return initialMouseX;
    }

    public double getInitialMouseY() {
        return initialMouseY;
    }

    public CheckerType getType() {
        return type;
    }
    public void cancelMove() {
        relocate(initialMouseX, initialMouseY);
    }
}
