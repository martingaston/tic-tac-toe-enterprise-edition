package com.github.martingaston.tictactoe.api;

import com.github.martingaston.tictactoe.board.Symbol;

import java.util.List;
import java.util.Map;

public interface Json {
    boolean isActive();
    String mode();
    Symbol currentPlayer();
    List<String> board();
    Map<String, String> messages();
}
