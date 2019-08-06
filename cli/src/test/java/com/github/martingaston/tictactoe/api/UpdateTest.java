package com.github.martingaston.tictactoe.api;

import com.github.martingaston.tictactoe.board.Symbol;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class UpdateTest {
    @Test
    public void canAddMoveToBoard() throws IOException {
        GameJSON game = Decode.from(API.init());
        int movePosition = 1;
        List<String> expectedBoard = new ArrayList<>(Arrays.asList("X", null, null, null, null, null, null, null, null));

        GameJSON updatedGame = Update.from(movePosition, game);

        assertEquals(expectedBoard, updatedGame.board());
    }

    @Test
    public void willSwapCurrentPlayer() throws IOException {
        GameJSON game = Decode.from(API.init());
        int movePosition = 1;

        GameJSON updatedGame = Update.from(movePosition, game);

        assertEquals(new Symbol("O"), updatedGame.currentPlayer());
    }

    @Test
    public void isActiveIsFalseWhenGameIsOver() throws IOException {
        GameJSON initialGame = Decode.from(API.init());

        GameJSON moveOne = Update.from(1, initialGame);
        GameJSON moveTwo = Update.from(4, moveOne);
        GameJSON moveThree = Update.from(2, moveTwo);
        GameJSON moveFour = Update.from(5, moveThree);
        GameJSON moveFive = Update.from(3, moveFour);

        assertTrue(moveFour.isActive());
        assertFalse(moveFive.isActive());
    }
}
