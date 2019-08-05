package com.github.martingaston.tictactoe;

import com.github.martingaston.tictactoe.player.Player;

public class Messages {
    public static String setupInstructions() {
        return "Select a game mode:\n1. Human v Human\n2. Human v CPU (EASY)\n3. Human v CPU (HARD)\n4. CPU v CPU (EASY)\n5. CPU v CPU (HARD)";
    }

    public static String boardSetupInstructions() {
        return "Select a board size:\n1. 3x3 (default)\n2. 4x4";
    }

    public static String invalidMove() {
        return "Sorry, that move is invalid.\nPlease choose an unoccupied square.";
    }

    public static String playerWin(Player player) {
        return String.format("Player %s wins!", player.symbol().toString());
    }

    public static String playersDraw() {
        return "Bad luck! It's a draw!";
    }

    public static String announcePlayerTurn(Player player) {
        return String.format("Player %s's turn", player.symbol().toString());
    }

    public static String getInstructions(int sideLength) {
        String gridShape = sideLength + "x" + sideLength;
        int totalCells = sideLength * sideLength;
        return String.format("Input numbers between 1-%s on alternative turns to place your mark in the %s grid.", totalCells, gridShape);
    }

    public static String getIntro() {
        return "TIC TAC TOE" + "\n" +
                "The classic game of noughts and crosses!\nTurn friends into enemies as 2 players take turns marking spaces in a grid.\nWin short-lived glory by succeeding in placing a complete line in any horizontal, vertical or diagonal direction." + "\n";
    }
}
