package com.github.martingaston.tictactoe.api;

import com.github.martingaston.tictactoe.Messages;

import java.util.*;

public class API {
    public String init() {
        boolean isActive = true;

        List<String> Board = new ArrayList<>(Arrays.asList(null, null, null, null, null, null, null, null, null));

        Map<String, String> messages = new HashMap<>();
        messages.put("title", "TIC TAC TOE");
        messages.put("intro", Messages.getIntro());
        messages.put("instructions", Messages.getInstructions(3));
        messages.put("turn", "Player X's turn");

        String currentPlayer = "X";

        return "X";
    }
}
