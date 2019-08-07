package com.github.martingaston.tictactoe.api;

import java.io.IOException;

public class API {
    public static String startGame() throws IOException {
        return API.startGame("ai");
    }

    public static String startGame(String mode) throws IOException {
        return Encode.from(Responses.initial(mode));
    }

    public static String getNextTurn(String json) throws IOException {
        JsonIncoming previousGame = Decode.from(json);
        JsonOutgoing updated = Update.from(previousGame);
        return Encode.from(updated);
    }
}
