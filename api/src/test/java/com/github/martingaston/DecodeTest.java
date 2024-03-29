package com.github.martingaston;

import com.github.martingaston.tictactoe.board.Symbol;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class DecodeTest {
    private String game = "{\"position\": 1, \"isActive\":true,\"board\":[null,null,null,null,null,null,null,null,null],\"messages\":{\"title\":\"Tic Tac Toe\"},\"currentPlayer\":\"X\",\"mode\":\"ai\"}";

    @Test
    public void hasPosition() throws IOException {
        var decoder = Decode.from(game);

        assertEquals(decoder.position(), 1);
    }
    @Test
    public void hasIsActiveValue() throws IOException {
        var decoder = Decode.from(game);

        assertTrue(decoder.isActive());
    }

    @Test
    public void hasMode() throws IOException {
        var decoder = Decode.from(game);

        assertEquals("ai", decoder.mode());
    }

    @Test
    public void hasCurrentPlayer() throws IOException {
        var decoder = Decode.from(game);

        assertEquals(new Symbol("X"), decoder.currentPlayer());
    }

    @Test
    public void hasBoard() throws IOException {
        var decoder = Decode.from(game);
        List<String> expectedList = new ArrayList<>(Arrays.asList(null, null, null, null, null, null, null, null, null));

        assertEquals(expectedList, decoder.board());
    }

    @Test
    public void hasMessages() throws IOException {
        var decoder = Decode.from(game);
        HashMap<String, String> expectedMap = new HashMap<>();
        expectedMap.put("title", "Tic Tac Toe");

        assertEquals(expectedMap, decoder.messages());
    }
}