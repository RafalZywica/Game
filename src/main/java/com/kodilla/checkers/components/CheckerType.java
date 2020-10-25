package com.kodilla.checkers.components;

public enum CheckerType {
    Grey(1), White(-1);

    public final int moveDir;

    CheckerType(int moveDir) {
        this.moveDir = moveDir;
    }
}
