package com.github.martingaston.tictactoe.player;

public interface Players {
    void nextTurn();
    Player playerCross();
    Player playerNought();
    Player getCurrentPlayer();
}
