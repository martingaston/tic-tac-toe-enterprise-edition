package com.github.martingaston.tictactoe.api;

import com.github.martingaston.tictactoe.Minimax;
import com.github.martingaston.tictactoe.board.Board;
import com.github.martingaston.tictactoe.board.PopulatedBoard;
import com.github.martingaston.tictactoe.board.Symbol;

public class Update {
    static GameJSON from(int position, GameJSON previousMove) {
       Board nextBoard = PopulatedBoard.from(previousMove.board(), new Symbol("X"), new Symbol("O"));
       nextBoard.add(oneIndexedToZeroIndexed(position), previousMove.currentPlayer());

       if (nextBoard.isGameOver()) {
           return gameOver(nextBoard, previousMove.currentPlayer());
       }

       if (aiShouldMakeMove(previousMove)) {
           int cpuMove = new Minimax(nextBoard, new Symbol("O"), new Symbol("X")).optimal();
           return Update.from(cpuMove, updatedGameJson(previousMove, nextBoard));
       }

       return updatedGameJson(previousMove, nextBoard);
    }

    private static GameJSON updatedGameJson(GameJSON previousMove, Board nextBoard) {
        return Response.updatedMove(previousMove, nextBoard);
    }

    private static GameJSON gameOver(Board board, Symbol currentPlayer) {
        return Response.gameOver(board, currentPlayer);

    }

    private static boolean aiShouldMakeMove(GameJSON previousMove) {
        return previousMove.mode().equals("ai") && Referee.nextPlayerIsNought(previousMove.currentPlayer());
    }

    private static int oneIndexedToZeroIndexed(int i) {
        return i - 1;
    }
}
