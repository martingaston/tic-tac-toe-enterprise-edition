package com.github.martingaston.tictactoe.api;

import java.io.IOException;

public class API {
    public static String init() throws IOException {
        return API.init("ai");
    }

    public static String init(String mode) throws IOException {
        return Encode.from(Response.initial(mode));
    }

    public static String update(int position, String jsonString) throws IOException {
        JsonIncoming previousGame = Decode.from(jsonString);
        JsonOutgoing updated = Update.from(position, previousGame);
        return Encode.from(updated);
    }
}
