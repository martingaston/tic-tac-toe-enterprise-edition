package com.github.martingaston.tictactoe.api;

import com.github.martingaston.tictactoe.board.Symbol;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class UpdateTest {
    @Test
    public void canAddMoveToBoard() throws IOException {
        JsonIncoming game = Decode.from(API.init("human"));
        int movePosition = 1;
        List<String> expectedBoard = new ArrayList<>(Arrays.asList("X", null, null, null, null, null, null, null, null));

        JsonOutgoing updatedGame = Update.from(movePosition, game);

        assertEquals(expectedBoard, updatedGame.board());
    }

    @Test
    public void willSwapCurrentPlayer() throws IOException {
        JsonIncoming game = Decode.from(API.init("human"));
        int movePosition = 1;

        JsonOutgoing updatedGame = Update.from(movePosition, game);

        assertEquals(new Symbol("O"), updatedGame.currentPlayer());
    }

    @Test
    public void isActiveIsFalseWhenGameIsOver() throws IOException {
        JsonIncoming initialGame = Decode.from(API.init("human"));

        JsonOutgoing moveOne = Update.from(1, initialGame);
        JsonOutgoing moveTwo = Update.from(4, moveOne);
        JsonOutgoing moveThree = Update.from(2, moveTwo);
        JsonOutgoing moveFour = Update.from(5, moveThree);
        JsonOutgoing moveFive = Update.from(3, moveFour);

        assertTrue(moveFour.isActive());
        assertFalse(moveFive.isActive());
        assertEquals("Player X wins!", moveFive.messages().get("ending"));
    }

    @Test
    public void aDrawReturnsADrawMessage() throws IOException {
        Json game = Decode.from(API.init("human"));
        List<Integer> movesList = new ArrayList<>(Arrays.asList(1, 5, 2, 3, 7, 4, 8, 9, 6));

        for (Integer movePosition : movesList) {
            game = Update.from(movePosition, game);
        }

        assertFalse(game.isActive());
        assertEquals("Bad luck! It's a draw!", game.messages().get("ending"));
    }

    @Test
    public void aiModeTakesNoughtTurns() throws IOException {
        JsonIncoming initialGame = Decode.from(API.init("ai"));

        JsonOutgoing moveOne = Update.from(1, initialGame);
        int movesPlayed = (int) moveOne.board().stream().filter(Objects::nonNull).count();

        assertEquals(moveOne.currentPlayer(), new Symbol("X"));
        assertEquals(2, movesPlayed);
    }
}
