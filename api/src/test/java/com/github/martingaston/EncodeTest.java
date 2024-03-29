package com.github.martingaston;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.martingaston.tictactoe.board.Symbol;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.*;

public class EncodeTest {
   @Test
   public void encodesGameJsonObject() throws JsonProcessingException {
       JsonOutgoing jsonOutgoing = new JsonOutgoing.Builder()
               .mode("ai")
               .currentPlayer(new Symbol("O"))
               .isActive(false)
               .board(new ArrayList<>(Arrays.asList("X", "O", "X", null, null, null, null, null, null)))
               .messages(new HashMap<>())
               .build();

       String expected = "{\"board\":[\"X\",\"O\",\"X\",null,null,null,null,null,null],\"currentPlayer\":\"O\",\"isActive\":false,\"messages\":{},\"mode\":\"ai\"}";

       String encoded = Encode.from(jsonOutgoing);

       assertEquals(expected, encoded);
   }
}
