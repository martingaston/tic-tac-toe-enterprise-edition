package com.github.martingaston.tictactoe.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class Decode {
    public static GameJSON from(String game) throws IOException {
        var objectMapper = new ObjectMapper();
        return objectMapper.readValue(game, GameJSON.class);
    }
}
