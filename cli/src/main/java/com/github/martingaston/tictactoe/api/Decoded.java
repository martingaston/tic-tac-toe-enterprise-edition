package com.github.martingaston.tictactoe.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Decoded {
    private final String mode;
    private final String currentPlayer;
    private boolean isActive;
    private List<String> board;
    private Map<String, String> messages;

    public static Decoded from(String game) throws IOException {
        var objectMapper = new ObjectMapper();
        return objectMapper.readValue(game, Decoded.class);
    }

    @JsonCreator
    private Decoded(
            @JsonProperty("mode") String mode,
            @JsonProperty("currentPlayer") String currentPlayer,
            @JsonProperty("isActive") boolean isActive,
            @JsonProperty("board") List<String> board,
            @JsonProperty("messages") Map<String, String> messages
    ) {
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
