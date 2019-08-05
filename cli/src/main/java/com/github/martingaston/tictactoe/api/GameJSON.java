package com.github.martingaston.tictactoe.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameJSON {
    private final String mode;
    private final String currentPlayer;
    private boolean isActive;
    private List<String> board;
    private Map<String, String> messages;

    @JsonCreator
    private static GameJSON from(
            @JsonProperty("mode") String mode,
            @JsonProperty("currentPlayer") String currentPlayer,
            @JsonProperty("isActive") boolean isActive,
            @JsonProperty("board") ArrayList<String> board,
            @JsonProperty("messages") HashMap<String, String> messages
    ) {
        return new GameJSON(mode, currentPlayer, isActive, board, messages);
    }

    private GameJSON(String mode, String currentPlayer, boolean isActive, ArrayList<String> board, HashMap<String, String> messages) {
        this.mode = mode;
        this.currentPlayer = currentPlayer;
        this.isActive = isActive;
        this.board = board;
        this.messages = messages;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public String mode() {
        return this.mode;
    }

    public String currentPlayer() {
        return this.currentPlayer;
    }

    public List<String> board() {
        return this.board;
    }

    public Map<String, String> messages() {
        return this.messages;
    }
}
