package com.github.martingaston.tictactoe.state;

import com.github.martingaston.tictactoe.GameModes;
import com.github.martingaston.tictactoe.board.Board;
import com.github.martingaston.tictactoe.board.BoardModes;
import com.github.martingaston.tictactoe.board.Symbol;
import com.github.martingaston.tictactoe.player.Players;

import java.util.List;

public interface State {
    GameModes mode();

    BoardModes boardMode();

    List<String> contents();

    Players players();

    Symbol playerCross();

    Symbol playerNought();

    Board board();

    String lastMove();
}
