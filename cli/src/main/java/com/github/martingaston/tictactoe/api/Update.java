package com.github.martingaston.tictactoe.api;

import com.github.martingaston.tictactoe.board.Board;
import com.github.martingaston.tictactoe.board.PopulatedBoard;
import com.github.martingaston.tictactoe.board.Symbol;

import java.util.List;
import java.util.stream.Collectors;

public class Update {
    static GameJSON from(int position, GameJSON previousMove) {

       Board nextBoard = PopulatedBoard.from(previousMove.board(), new Symbol("X"), new Symbol("O"));
       nextBoard.add(oneIndexedToZeroIndexed(position), previousMove.currentPlayer());

       List<String> returnedBoard = nextBoard
               .toList()
               .stream()
               .map(cell -> cell.equals(" ") ? null : cell)
               .collect(Collectors.toList());

       return new GameJSON.Builder()
               .isActive(!nextBoard.isGameOver())
               .board(returnedBoard)
               .currentPlayer(swapPlayer(previousMove.currentPlayer()))
               .messages(previousMove.messages())
               .mode(previousMove.mode())
               .build();
    }

    private static Symbol swapPlayer(Symbol currentPlayer) {
        return currentPlayer.equals(new Symbol("X")) ? new Symbol("O") : new Symbol("X");
    }

    private static int oneIndexedToZeroIndexed(int i) {
        return i - 1;
    }
}
