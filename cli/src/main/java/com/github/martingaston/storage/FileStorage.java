package com.github.martingaston.storage;

import com.github.martingaston.tictactoe.GameModes;
import com.github.martingaston.tictactoe.board.BoardModes;
import com.github.martingaston.tictactoe.state.State;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FileStorage implements Storage {
    @Override
    public List<String> in() {
        String content = "";

        File f = new File("game.txt");
        if (!f.exists()) {
            return new LinkedList<>();
        }

        try {
            content = new String(Files.readAllBytes(Paths.get("game.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Arrays.asList(content.split(","));
    }

    @Override
    public void out(State state) throws IOException {
        String boardMode = Integer.toString(BoardModes.id(state.boardMode()));
        String gameMode = Integer.toString(GameModes.id(state.mode()));
        String playerCrossSymbol = state.playerCross().toString();
        String playerNoughtSymbol = state.playerNought().toString();
        String lastPlayer = state.lastMove();
        List<String> boardAsList = state.board().toList();

        List<String> boardState = new LinkedList<>(Arrays.asList(
                boardMode,
                gameMode,
                playerCrossSymbol,
                playerNoughtSymbol,
                lastPlayer
        ));
        boardState.addAll(boardAsList);

        String boardCSV = String.join(",", boardState);
        BufferedWriter writer = new BufferedWriter(new FileWriter("game.txt"));
        writer.write(boardCSV);
        writer.close();
    }

    @Override
    public void close() {
        File file = new File("game.txt");
        file.delete();
    }
}
