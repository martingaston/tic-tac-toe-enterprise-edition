package com.github.martingaston.tictactoe.player;

import com.github.martingaston.tictactoe.board.Board;
import com.github.martingaston.tictactoe.board.Symbol;

import java.util.List;

public class PlayerCPU implements com.github.martingaston.tictactoe.player.Player {
    private final Symbol symbol;

    PlayerCPU(String symbol) {
        this.symbol = new Symbol(symbol);
    }

    @Override
    public Symbol symbol() {
        return symbol;
    }

    @Override
    public int getNextMove(Board board) {
        oneSecondSleep();
        return pickRandomCell(board.available());
    }

    private int pickRandomCell(List<Integer> available) {
        int cellIndex = (int) Math.floor(Math.random() * available.size());
        return available.get(cellIndex);
    }

    private void oneSecondSleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
