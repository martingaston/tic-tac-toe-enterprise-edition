package com.github.martingaston.tictactoe.api;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Encode {
    public static String from(GameJSON gameJson) throws JsonProcessingException {
        var mapper = new ObjectMapper();
        mapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
        return mapper.writeValueAsString(gameJson);
    }
}

