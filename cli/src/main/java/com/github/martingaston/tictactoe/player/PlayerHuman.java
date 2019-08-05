package com.github.martingaston.tictactoe.player;

import com.github.martingaston.tictactoe.Messages;
import com.github.martingaston.tictactoe.board.Board;
import com.github.martingaston.tictactoe.board.Symbol;
import com.github.martingaston.tictactoe.cli.Display;
import com.github.martingaston.tictactoe.cli.IO;

import java.util.Scanner;

public class PlayerHuman implements com.github.martingaston.tictactoe.player.Player {
    private final Symbol symbol;
    private final IO io;

    public PlayerHuman(String symbol, IO io) {
        this.symbol = new Symbol(symbol);
        this.io = io;
    }

    public PlayerHuman(String symbol) {
        this(symbol, new IO(new Scanner(System.in)));
    }

    public Symbol symbol() {
        return symbol;
    }

    public int getNextMove(Board board) {
        int desiredCell = getAnInteger();

        while (!board.available().contains(desiredCell)) {
            invalidMove();
            desiredCell = getAnInteger();
        }

        return desiredCell;
    }

    private int getAnInteger() {
        while (!io.hasNextInt()) {
            invalidMove();
            io.next();
        }
        return computerise(io.nextInt());
    }

    private int computerise(int oneIndexedNumber) {
        return oneIndexedNumber - 1;
    }

    private void invalidMove() {
        Display.outMessage(Messages.invalidMove());
    }
}