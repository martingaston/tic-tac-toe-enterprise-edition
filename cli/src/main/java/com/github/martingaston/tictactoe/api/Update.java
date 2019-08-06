package com.github.martingaston.tictactoe.api;

import com.github.martingaston.tictactoe.Messages;
import com.github.martingaston.tictactoe.Minimax;
import com.github.martingaston.tictactoe.board.Board;
import com.github.martingaston.tictactoe.board.PopulatedBoard;
import com.github.martingaston.tictactoe.board.Symbol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Update {
    static GameJSON from(int position, GameJSON previousMove) {
       Board nextBoard = PopulatedBoard.from(previousMove.board(), new Symbol("X"), new Symbol("O"));
       nextBoard.add(oneIndexedToZeroIndexed(position), previousMove.currentPlayer());

       if (gameIsOver(nextBoard)) {
           return gameOver(nextBoard, previousMove.currentPlayer());
       }

       if (aiShouldMakeMove(previousMove)) {
           int cpuMove = new Minimax(nextBoard, new Symbol("O"), new Symbol("X")).optimal();
           return Update.from(cpuMove, updatedGameJson(previousMove, nextBoard));
       }

        return updatedGameJson(previousMove, nextBoard);
    }

    private static List<String> formatBoard(Board nextBoard) {
        return nextBoard
                   .toList()
                   .stream()
                   .map(cell -> cell.equals(" ") ? null : cell)
                   .collect(Collectors.toList());
    }

    private static GameJSON updatedGameJson(GameJSON previousMove, Board nextBoard) {
        return new GameJSON.Builder()
                .isActive(gameIsActive(nextBoard))
                .board(formatBoard(nextBoard))
                .currentPlayer(swapPlayer(previousMove.currentPlayer()))
                .messages(previousMove.messages())
                .mode(previousMove.mode())
                .build();
    }

    private static boolean gameIsActive(Board board) {
        return !gameIsOver(board);
    }

    private static boolean gameIsOver(Board board) {
        return board.isGameOver();
    }

    private static GameJSON gameOver(Board board, Symbol currentPlayer) {
        return new GameJSON.Builder()
                .isActive(false)
                .board(formatBoard(board))
                .messages(getEndingMessage(board, currentPlayer))
                .build();
    }

    private static Symbol swapPlayer(Symbol currentPlayer) {
        return currentPlayer.equals(new Symbol("X")) ? new Symbol("O") : new Symbol("X");
    }

    private static boolean aiShouldMakeMove(GameJSON previousMove) {
        return previousMove.mode().equals("ai") && nextPlayerIsNought(previousMove.currentPlayer());
    }

    private static boolean nextPlayerIsNought(Symbol currentPlayer) {
        return swapPlayer(currentPlayer).equals(new Symbol("O"));
    }

    private static Map<String, String> getEndingMessage(Board board, Symbol currentPlayer) {
        var messages = new HashMap<String, String>();

        if(board.hasWon(currentPlayer)) {
            messages.put("ending", String.format("Player %s wins!", currentPlayer.toString()));
        } else {
            messages.put("ending", Messages.playersDraw());
        }

        return messages;
    }

    private static int oneIndexedToZeroIndexed(int i) {
        return i - 1;
    }
}
