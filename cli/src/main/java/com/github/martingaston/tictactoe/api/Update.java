package com.github.martingaston.tictactoe.api;

import com.github.martingaston.tictactoe.board.Board;
import com.github.martingaston.tictactoe.board.PopulatedBoard;
import com.github.martingaston.tictactoe.board.Symbol;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Update {
    public static GameJSON fromJson(int position, String jsonString) throws IOException {
       GameJSON previousMove = Decode.from(jsonString);

       Board nextBoard = PopulatedBoard.from(previousMove.board(), new Symbol("X"), new Symbol("O"));
       nextBoard.add(position - 1, previousMove.currentPlayer());

       List<String> returnedBoard = nextBoard
               .toList()
               .stream()
               .map(cell -> cell.equals(" ") ? null : cell)
               .collect(Collectors.toList());

       return new GameJSON.Builder()
               .isActive(true)
               .board(returnedBoard)
               .currentPlayer(swapPlayer(previousMove.currentPlayer()))
               .messages(previousMove.messages())
               .mode(previousMove.mode())
               .build();
    }

    private static Symbol swapPlayer(Symbol currentPlayer) {
        return currentPlayer.equals(new Symbol("X")) ? new Symbol("O") : new Symbol("X");
    }
}
