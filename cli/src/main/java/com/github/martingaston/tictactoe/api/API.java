package com.github.martingaston.tictactoe.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.martingaston.tictactoe.Messages;
import com.github.martingaston.tictactoe.board.Symbol;

import java.util.*;

public class API {
    public static String init() {
        List<String> board = new ArrayList<>(Arrays.asList(null, null, null, null, null, null, null, null, null));

        Map<String, String> messages = new HashMap<>();
        messages.put("title", "TIC TAC TOE");
        messages.put("intro", Messages.getIntro());
        messages.put("instructions", Messages.getInstructions(3));
        messages.put("turn", "Player X's turn");

        Symbol currentPlayer = new Symbol("X");
        String mode = "ai";

        var gameJson = new GameJSON.Builder()
                .isActive(true)
                .board(board)
                .messages(messages)
                .currentPlayer(currentPlayer)
                .mode(mode)
                .build();

        try {
            return Encode.from(gameJson);
        } catch (JsonProcessingException error) {
            return "{}";
        }
    }
}
