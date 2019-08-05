package com.github.martingaston.tictactoe.api;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class DecodedTest {
    private String game = "{\"isActive\":true,\"board\":[null,null,null,null,null,null,null,null,null],\"messages\":{\"title\":\"Tic Tac Toe\"},\"currentPlayer\":\"X\",\"mode\":\"ai\"}";

    @Test
    public void hasIsActiveValue() throws IOException {
        var decoder = Decoded.from(game);

        assertTrue(decoder.isActive());
    }

    @Test
    public void hasMode() throws IOException {
        var decoder = Decoded.from(game);

        assertEquals("ai", decoder.mode());
    }

    @Test
    public void hasCurrentPlayer() throws IOException {
        var decoder = Decoded.from(game);

        assertEquals("X", decoder.currentPlayer());
    }

    @Test
    public void hasBoard() throws IOException {
        var decoder = Decoded.from(game);


        System.out.println(decoder.board());
    }

    @Test
    public void hasMessages() throws IOException {
        var decoder = Decoded.from(game);

        System.out.println(decoder.messages());
    }
}