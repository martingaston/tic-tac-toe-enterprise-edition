package com.github.martingaston.player;

import com.github.martingaston.tictactoe.board.Board;
import com.github.martingaston.tictactoe.player.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CLIPlayersTest {
    private CLIPlayers players;

    @Before
    public void setUp() {
        Board board = new Board();
        Player playerCross = new PlayerHuman("X");
        Player playerNought = new PlayerHuman("O");
        players = new CLIPlayers(playerCross, playerNought);
    }

    @Test
    public void getCurrentPlayerReturnsCrossOnFirstTurn() {
        Player currentPlayer = players.getCurrentPlayer();
        String currentPlayerSymbol = currentPlayer.symbol().toString();
        assertEquals("X", currentPlayerSymbol);
    }

    @Test
    public void getCurrentPlayerReturnsNoughtAfterTurnChanges() {
        players.nextTurn();
        Player currentPlayer = players.getCurrentPlayer();
        String currentPlayerSymbol = currentPlayer.symbol().toString();
        assertEquals("O", currentPlayerSymbol);
    }

    @Test
    public void getCurrentPlayerReturnsCrossAfterTwoTurns() {
        players.nextTurn();
        players.nextTurn();
        Player currentPlayer = players.getCurrentPlayer();
        String currentPlayerSymbol = currentPlayer.symbol().toString();
        assertEquals("X", currentPlayerSymbol);
    }
}