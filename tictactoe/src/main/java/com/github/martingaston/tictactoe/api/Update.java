package com.github.martingaston.tictactoe.api;

import com.github.martingaston.tictactoe.Minimax;
import com.github.martingaston.tictactoe.board.Board;
import com.github.martingaston.tictactoe.board.PopulatedBoard;
import com.github.martingaston.tictactoe.board.Symbol;

class Update {
    static JsonOutgoing from(JsonIncoming previousMove) {
       Board board = PopulatedBoard.from(previousMove.board(), new Symbol("X"), new Symbol("O"));
       board.add(oneIndexedToZeroIndexed(previousMove.position()), previousMove.currentPlayer());

       if (board.isGameOver()) {
           return gameOver(board, previousMove.currentPlayer());
       }

       if (Referee.aiShouldMakeMove(previousMove)) {
           makeAiMove(board);
       }

       return updatedGameJson(previousMove, board);
    }

    private static void makeAiMove(Board board) {
        var aiSymbol = new Symbol("O");
        int cpuMove = new Minimax(board, aiSymbol, new Symbol("X")).optimal();
        board.add(cpuMove, aiSymbol);
    }

    private static JsonOutgoing updatedGameJson(JsonIncoming previousMove, Board nextBoard) {
        return Response.updatedMove(previousMove, nextBoard);
    }

    private static JsonOutgoing gameOver(Board board, Symbol currentPlayer) {
        return Response.gameOver(board, currentPlayer);

    }

    private static int oneIndexedToZeroIndexed(int i) {
        return i - 1;
    }
}
