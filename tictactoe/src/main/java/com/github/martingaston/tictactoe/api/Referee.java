package com.github.martingaston.tictactoe.api;

import com.github.martingaston.tictactoe.Messages;
import com.github.martingaston.tictactoe.board.Board;
import com.github.martingaston.tictactoe.board.Symbol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Referee {
    static Symbol swapPlayer(Symbol currentPlayer) {
        return currentPlayer.equals(new Symbol("X")) ? new Symbol("O") : new Symbol("X");
    }

    static boolean aiShouldMakeMove(Json previousMove) {
        return previousMove.mode().equals("ai") && Referee.nextPlayerIsNought(previousMove.currentPlayer());
    }

    private static boolean nextPlayerIsNought(Symbol currentPlayer) {
        return Referee.swapPlayer(currentPlayer).equals(new Symbol("O"));
    }

    static List<String> formatBoard(Board nextBoard) {
        return nextBoard
                .toList()
                .stream()
                .map(cell -> cell.equals(" ") ? null : cell)
                .collect(Collectors.toList());
    }

    static Map<String, String> getEndingMessage(Board board, Symbol currentPlayer) {
        var messages = new HashMap<String, String>();

        if(board.hasWon(currentPlayer)) {
            messages.put("ending", String.format("Player %s wins!", currentPlayer.toString()));
        } else {
            messages.put("ending", Messages.playersDraw());
        }

        return messages;
    }

    static boolean gameIsActive(Board board) {
        return !board.isGameOver();
    }
}
