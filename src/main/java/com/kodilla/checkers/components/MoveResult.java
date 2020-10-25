package com.kodilla.checkers.components;

import static com.kodilla.checkers.components.MoveType.NONE;

public class MoveResult {

    private Checker checker;
    private MoveType type;

    public MoveResult(MoveType type, Checker checker) {
        this.type = type;
        this.checker = checker;
    }
    public MoveResult(MoveType type) {
        this(type, null);
    }

    public Checker getChecker() {
        return checker;
    }

    public MoveType getType() {
        return type;
    }
}
