package com.github.martingaston.tictactoe.player;

import com.github.martingaston.tictactoe.board.Board;
import com.github.martingaston.tictactoe.board.Symbol;

public interface Player {
    Symbol symbol();

    int getNextMove(Board board);
}