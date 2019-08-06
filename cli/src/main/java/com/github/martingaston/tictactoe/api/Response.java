package com.github.martingaston.tictactoe.api;

import com.github.martingaston.tictactoe.Messages;
import com.github.martingaston.tictactoe.board.Board;
import com.github.martingaston.tictactoe.board.Symbol;

import java.util.*;

class Response {
    static GameJSON initial(String mode) {
        List<String> board = new ArrayList<>(Arrays.asList(null, null, null, null, null, null, null, null, null));

        Map<String, String> messages = new HashMap<>();
        messages.put("title", "TIC TAC TOE");
        messages.put("intro", Messages.getIntro());
        messages.put("instructions", Messages.getInstructions(3));
        messages.put("turn", "Player X's turn");

        Symbol currentPlayer = new Symbol("X");

        return new GameJSON.Builder()
                .isActive(true)
                .board(board)
                .messages(messages)
                .currentPlayer(currentPlayer)
                .mode(mode)
                .build();
    }

    static GameJSON updatedMove(GameJSON previousMove, Board nextBoard) {
        return new GameJSON.Builder()
                .isActive(Referee.gameIsActive(nextBoard))
                .board(Referee.formatBoard(nextBoard))
                .currentPlayer(Referee.swapPlayer(previousMove.currentPlayer()))
                .messages(previousMove.messages())
                .mode(previousMove.mode())
                .build();
    }

    static GameJSON gameOver(Board board, Symbol currentPlayer) {
        return new GameJSON.Builder()
                .isActive(false)
                .board(Referee.formatBoard(board))
                .messages(Referee.getEndingMessage(board, currentPlayer))
                .build();
    }
}