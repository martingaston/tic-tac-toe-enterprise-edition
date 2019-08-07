package com.github.martingaston.tictactoe.api;

import com.github.martingaston.tictactoe.board.Symbol;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class UpdateTest {
    @Test
    public void canAddMoveToBoard() {
        var jsonInput = new JsonIncoming.Builder()
                .position(1)
                .board(new ArrayList<>(Arrays.asList(null, null, null, null, null, null, null, null, null)))
                .currentPlayer(new Symbol("X"))
                .isActive(true)
                .messages(new HashMap<>())
                .mode("human")
                .build();
        List<String> expectedBoard = new ArrayList<>(Arrays.asList("X", null, null, null, null, null, null, null, null));

        JsonOutgoing updatedGame = Update.from(jsonInput);

        assertEquals(expectedBoard, updatedGame.board());
    }

    @Test
    public void willSwapCurrentPlayer() {
        var jsonInput = new JsonIncoming.Builder()
                .position(1)
                .board(new ArrayList<>(Arrays.asList(null, null, null, null, null, null, null, null, null)))
                .currentPlayer(new Symbol("X"))
                .isActive(true)
                .messages(new HashMap<>())
                .mode("human")
                .build();

        JsonOutgoing updatedGame = Update.from(jsonInput);

        assertEquals(new Symbol("O"), updatedGame.currentPlayer());
    }

    @Test
    public void isActiveIsFalseWhenGameIsOver() throws IOException {
        var jsonInput = new JsonIncoming.Builder()
                .position(3)
                .board(new ArrayList<>(Arrays.asList("X", "X", null, "O", "O", null, null, null, null)))
                .currentPlayer(new Symbol("X"))
                .isActive(true)
                .messages(new HashMap<>())
                .mode("human")
                .build();

        JsonOutgoing playedTurn = Update.from(jsonInput);

        assertFalse(playedTurn.isActive());
        assertEquals("Player X wins!", playedTurn.messages().get("ending"));
    }

    @Test
    public void aDrawReturnsADrawMessage() {
        var jsonInput = new JsonIncoming.Builder()
                .position(6)
                .board(new ArrayList<>(Arrays.asList("X", "X", "O", "O", "O", null, "X", "X", "O")))
                .currentPlayer(new Symbol("X"))
                .isActive(true)
                .messages(new HashMap<>())
                .mode("human")
                .build();

        JsonOutgoing playedTurn = Update.from(jsonInput);

        assertFalse(playedTurn.isActive());
        assertEquals("Bad luck! It's a draw!", playedTurn.messages().get("ending"));
    }

    @Test
    public void aiModeTakesNoughtTurns() {
        var jsonInput = new JsonIncoming.Builder()
                .position(1)
                .board(new ArrayList<>(Arrays.asList(null, null, null, null, null, null, null, null, null)))
                .currentPlayer(new Symbol("X"))
                .isActive(true)
                .messages(new HashMap<>())
                .mode("ai")
                .build();

        JsonOutgoing playedTurn = Update.from(jsonInput);
        int movesPlayed = (int) playedTurn.board().stream().filter(Objects::nonNull).count();

        assertEquals(playedTurn.currentPlayer(), new Symbol("X"));
        assertEquals(2, movesPlayed);
    }
}
