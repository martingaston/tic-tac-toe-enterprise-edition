package com.github.martingaston;

import com.github.martingaston.tictactoe.Messages;
import com.github.martingaston.tictactoe.board.Board;
import com.github.martingaston.tictactoe.board.Symbol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Referee {
    static Symbol swapPlayer(JsonIncoming previousMove) {
        if(previousMove.mode().equals("ai")) {
            return new Symbol("X");
        }

        return previousMove.currentPlayer().equals(new Symbol("X")) ? new Symbol("O") : new Symbol("X");
    }

    static boolean aiShouldMakeMove(JsonIncoming previousMove) {
        return previousMove.mode().equals("ai");
    }

    static List<String> formatBoard(Board nextBoard) {
        return nextBoard
                .toList()
                .stream()
                .map(cell -> cell.equals(" ") ? null : cell)
                .collect(Collectors.toList());
    }

    static Map<String, String> getEndingMessage(Board board) {
        var messages = new HashMap<String, String>();

        if(board.hasWon(new Symbol("X"))) {
            messages.put("ending", "Player X wins!");
        } else if (board.hasWon(new Symbol("O"))) {
            messages.put("ending", "Player O wins!");
        } else {
            messages.put("ending", Messages.playersDraw());
        }

        return messages;
    }

    static boolean gameIsActive(Board board) {
        return !board.isGameOver();
    }
}
