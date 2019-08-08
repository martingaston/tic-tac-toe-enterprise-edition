package com.github.martingaston;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

class Decode {
    static JsonIncoming from(String game) throws IOException {
        var objectMapper = new ObjectMapper();
        return objectMapper.readValue(game, JsonIncoming.class);
    }
}
