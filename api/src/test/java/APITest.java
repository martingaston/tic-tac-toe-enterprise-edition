import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class APITest {
    @Test
    public void initWithZeroArgsReturnsAIGame() throws IOException {
        String result = API.startGame();
        String expected = "{\"board\":[null,null,null,null,null,null,null,null,null],\"currentPlayer\":\"X\",\"isActive\":true,\"messages\":{\"instructions\":\"Input numbers between 1-9 on alternative turns to place your mark in the 3x3 grid.\",\"intro\":\"TIC TAC TOE\\nThe classic game of noughts and crosses!\\nTurn friends into enemies as 2 players take turns marking spaces in a grid.\\nWin short-lived glory by succeeding in placing a complete line in any horizontal, vertical or diagonal direction.\\n\",\"turn\":\"Player X's turn\",\"title\":\"TIC TAC TOE\"},\"mode\":\"ai\"}";

        assertEquals(expected, result);
    }

    @Test
    public void initWithHumanModeReturnsNoughtPlayerMove() throws IOException {
        String input = "{\"position\": 1, \"board\":[null,null,null,null,null,null,null,null,null],\"currentPlayer\":\"X\",\"isActive\":true,\"messages\":{\"instructions\":\"Input numbers between 1-9 on alternative turns to place your mark in the 3x3 grid.\",\"intro\":\"TIC TAC TOE\\nThe classic game of noughts and crosses!\\nTurn friends into enemies as 2 players take turns marking spaces in a grid.\\nWin short-lived glory by succeeding in placing a complete line in any horizontal, vertical or diagonal direction.\\n\",\"turn\":\"Player X's turn\",\"title\":\"TIC TAC TOE\"},\"mode\":\"human\"}";
        String expected = "{\"board\":[\"X\",null,null,null,null,null,null,null,null],\"currentPlayer\":\"O\",\"isActive\":true,\"messages\":{\"instructions\":\"Input numbers between 1-9 on alternative turns to place your mark in the 3x3 grid.\",\"intro\":\"TIC TAC TOE\\nThe classic game of noughts and crosses!\\nTurn friends into enemies as 2 players take turns marking spaces in a grid.\\nWin short-lived glory by succeeding in placing a complete line in any horizontal, vertical or diagonal direction.\\n\",\"turn\":\"Player O's turn\",\"title\":\"TIC TAC TOE\"},\"mode\":\"human\"}";

        String moveOne = API.getNextTurn(input);

        assertEquals(expected, moveOne);
    }

    @Test
    public void playerNoughtCanWinGame() throws IOException {
        String input = "{\"position\":7,\"board\":[\"X\",\"X\",\"O\",null,\"O\",null,null,\"X\",null],\"currentPlayer\":\"O\",\"isActive\":true,\"messages\":{\"instructions\":\"Input numbers between 1-9 on alternative turns to place your mark in the 3x3 grid.\",\"intro\":\"TIC TAC TOE\\nThe classic game of noughts and crosses!\\nTurn friends into enemies as 2 players take turns marking spaces in a grid.\\nWin short-lived glory by succeeding in placing a complete line in any horizontal, vertical or diagonal direction.\\n\",\"turn\":\"Player O's turn\",\"title\":\"TIC TAC TOE\"},\"mode\":\"human\"}";
        String expected = "{\"board\":[\"X\",\"X\",\"O\",null,\"O\",null,\"O\",\"X\",null],\"currentPlayer\":\"\",\"isActive\":false,\"messages\":{\"ending\":\"Player O wins!\"},\"mode\":null}";

        String move = API.getNextTurn(input);

        assertEquals(expected, move);
    }

    @Test
    public void finalMoveAgainstAIOpponentShouldResultInDraw() throws IOException {
        String input = "{\"position\":9,\"board\":[\"X\",\"X\",\"O\",\"O\",\"O\",\"X\",\"X\",\"O\",null],\"currentPlayer\":\"X\",\"isActive\":true,\"messages\":{\"instructions\":\"Input numbers between 1-9 on alternative turns to place your mark in the 3x3 grid.\",\"intro\":\"TIC TAC TOE\\nThe classic game of noughts and crosses!\\nTurn friends into enemies as 2 players take turns marking spaces in a grid.\\nWin short-lived glory by succeeding in placing a complete line in any horizontal, vertical or diagonal direction.\\n\",\"turn\":\"Player X's turn\",\"title\":\"TIC TAC TOE\"},\"mode\":\"ai\"}";
        String expected = "{\"board\":[\"X\",\"X\",\"O\",\"O\",\"O\",\"X\",\"X\",\"O\",\"X\"],\"currentPlayer\":\"\",\"isActive\":false,\"messages\":{\"ending\":\"Bad luck! It's a draw!\"},\"mode\":null}";

        String move = API.getNextTurn(input);


        assertEquals(expected, move);
    }
}