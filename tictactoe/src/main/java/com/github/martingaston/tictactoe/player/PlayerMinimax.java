package com.github.martingaston.tictactoe.player;

import com.github.martingaston.tictactoe.Minimax;
import com.github.martingaston.tictactoe.board.Board;
import com.github.martingaston.tictactoe.board.Symbol;

public class PlayerMinimax implements com.github.martingaston.tictactoe.player.Player {
    private final Symbol symbol;
    private final Symbol opponent;

    public PlayerMinimax(String symbol, Symbol opponent) {
        this.symbol = new Symbol(symbol);
        this.opponent = opponent;
    }

    @Override
    public Symbol symbol() {
        return symbol;
    }

    @Override
    public int getNextMove(Board board) {
        oneSecondSleep();
        return new Minimax(board, this.symbol(), opponent).optimal();
    }

    private void oneSecondSleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
