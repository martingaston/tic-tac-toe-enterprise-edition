package com.github.martingaston.tictactoe.api;

import com.github.martingaston.tictactoe.board.Symbol;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UpdateTest {
    @Test
    public void updateCanAddMoveToBoard() throws IOException {
        String init = API.init();
        int movePosition = 1;
        GameJSON updatedGame = Update.fromJson(movePosition, init);
        List<String> expectedBoard = new ArrayList<>(Arrays.asList("X", null, null, null, null, null, null, null, null));

        assertEquals(expectedBoard, updatedGame.board());
    }

    @Test
    public void updateWillSwapCurrentPlayer() throws IOException {
        String init = API.init();
        int movePosition = 1;
        GameJSON updatedGame = Update.fromJson(movePosition, init);

        assertEquals(new Symbol("O"), updatedGame.currentPlayer());
    }
}
