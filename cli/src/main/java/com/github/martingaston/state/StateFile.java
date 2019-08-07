package com.github.martingaston.state;

import com.github.martingaston.tictactoe.GameModes;
import com.github.martingaston.tictactoe.state.State;
import com.github.martingaston.tictactoe.board.Board;
import com.github.martingaston.tictactoe.board.BoardModes;
import com.github.martingaston.tictactoe.board.PopulatedBoard;
import com.github.martingaston.tictactoe.board.Symbol;
import com.github.martingaston.IO;
import com.github.martingaston.player.CLIPlayers;

import java.util.List;
import java.util.Scanner;

public class StateFile implements State {
    private GameModes mode;
    private BoardModes boardMode;
    private Board board;
    private Symbol playerCross;
    private Symbol playerNought;
    private List<String> contents;
    private CLIPlayers CLIPlayers;
    private String lastMove;

    public StateFile(List<String> variables) {
        this(variables, new IO(new Scanner(System.in)));
    }

    public StateFile(List<String> variables, IO io) {
        boardMode = BoardModes.nameOf(Integer.parseInt(variables.get(0)));
        mode = GameModes.nameOf(Integer.parseInt(variables.get(1)));
        playerCross = new Symbol(variables.get(2));
        playerNought = new Symbol(variables.get(3));
        contents = variables.subList(5, variables.size());
        CLIPlayers = CLIPlayers.create(mode, io);
        lastMove = variables.get(4);

        if (lastMove().equals("X")) {
            CLIPlayers.nextTurn();
        }

        board = PopulatedBoard.from(this);
    }

    public GameModes mode() {
        return mode;
    }

    public BoardModes boardMode() {
        return boardMode;
    }

    public Symbol playerCross() {
        return playerCross;
    }

    public Symbol playerNought() {
        return playerNought;
    }

    public List<String> contents() {
        return contents;
    }

    public CLIPlayers players() {
        return CLIPlayers;
    }

    public Board board() {
        return board;
    }

    public String lastMove() {
        return lastMove;
    }

    // need a test for what happens if person quits game on FIRST turn 
}
