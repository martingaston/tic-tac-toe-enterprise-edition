package com.github.martingaston.state;

import com.github.martingaston.tictactoe.GameModes;
import com.github.martingaston.tictactoe.state.State;
import com.github.martingaston.tictactoe.board.Board;
import com.github.martingaston.tictactoe.board.BoardModes;
import com.github.martingaston.tictactoe.board.Symbol;
import com.github.martingaston.IO;
import com.github.martingaston.player.CLIPlayers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StateCLI implements State {

    private GameModes mode;
    private BoardModes boardMode;
    private Board board;
    private List<String> contents;
    private CLIPlayers CLIPlayers;
    private String lastMove;

    public StateCLI(String[] args) {
        this(args, new IO(new Scanner(System.in)));
    }

    public StateCLI(String[] args, IO io) {
        Map<String, String> parsedArgs = parseArgs(args);

        String boardArg = parsedArgs.getOrDefault("board", "");
        String modeArg = parsedArgs.getOrDefault("mode", "");
        boardMode = BoardModes.nameOf(boardArg);
        board = createBoard(boardMode);
        mode = GameModes.nameOf(modeArg);
        CLIPlayers = CLIPlayers.create(mode, io);
    }

    private Map<String, String> parseArgs(String[] args) {
        String argNameEqualValueRegex = "^--(\\w+)=([\\w|-]+)$"; // --name=value
        Pattern argStructure = Pattern.compile(argNameEqualValueRegex);
        Map<String, String> argMap = new HashMap<>();

        for (String arg : args) {
            Matcher matchedArg = argStructure.matcher(arg);
            if (matchedArg.matches()) {
                String argName = matchedArg.group(1);
                String argValue = matchedArg.group(2);
                argMap.put(argName, argValue);
            }
        }

        return argMap;
    }

    private Board createBoard(BoardModes boardMode) {
        switch (boardMode) {
            case BOARD_3X3:
            default:
                return new Board(3);
            case BOARD_4X4:
                return new Board(4);
        }
    }

    @Override
    public GameModes mode() {
        return mode;
    }

    @Override
    public List<String> contents() {
        return null;
    }

    @Override
    public BoardModes boardMode() {
        return boardMode;
    }

    @Override
    public CLIPlayers players() {
        return CLIPlayers;
    }

    @Override
    public Board board() {
        return board;
    }

    @Override
    public String lastMove() {
        return null;
    }

    @Override
    public Symbol playerCross() {
        return new Symbol("X");
    }

    @Override
    public Symbol playerNought() {
        return new Symbol("O");
    }
}
