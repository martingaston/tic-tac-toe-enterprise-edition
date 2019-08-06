package com.github.martingaston.tictactoe.api;

import org.junit.Test;

import static org.junit.Assert.*;

public class APITest {
    @Test
    public void initCreatesRequiredResponse() {
        String result = API.init();
        String expected = "{\"board\":[null,null,null,null,null,null,null,null,null],\"currentPlayer\":\"X\",\"isActive\":true,\"messages\":{\"instructions\":\"Input numbers between 1-9 on alternative turns to place your mark in the 3x3 grid.\",\"intro\":\"TIC TAC TOE\\nThe classic game of noughts and crosses!\\nTurn friends into enemies as 2 players take turns marking spaces in a grid.\\nWin short-lived glory by succeeding in placing a complete line in any horizontal, vertical or diagonal direction.\\n\",\"turn\":\"Player X's turn\",\"title\":\"TIC TAC TOE\"},\"mode\":\"ai\"}";

        assertEquals(expected, result);
    }

}