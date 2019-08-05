package com.github.martingaston.tictactoe.board;

public class Cell {
    private Symbol occupant;

    public Symbol occupant() {
        if (!isOccupied()) {
            return new Symbol(" ");
        }

        return occupant;
    }

    void mark(Symbol symbol) {
        this.occupant = symbol;
    }

    void unmark() {
        this.occupant = null;
    }

    public boolean isOccupied() {
        return occupant != null;
    }
}
