public class Messages {
    static String setupInstructions() {
        return "Select a game mode:\n1. Human v Human\n2. Human v CPU (EASY)\n3. Human v CPU (HARD)\n4. CPU v CPU (EASY)\n5. CPU v CPU (HARD)";
    }

    static String boardSetupInstructions() {
        return "Select a board size:\n1. 3x3 (default)\n2. 4x4";
    }

    static String invalidMove() {
        return "Sorry, that move is invalid.\nPlease choose an unoccupied square.";
    }

    static String playerWin(Player player) {
        return String.format("Player %s wins!", player.symbol().toString());
    }

    static String playersDraw() {
        return "Bad luck! It's a draw!";
    }

    static String announcePlayerTurn(Player player) {
        return String.format("Player %s's turn", player.symbol().toString());
    }

    static String getInstructions(int sideLength) {
        String gridShape = sideLength + "x" + sideLength;
        int totalCells = sideLength * sideLength;
        return String.format("Input numbers between 1-%s on alternative turns to place your mark in the %s grid.", totalCells, gridShape);
    }

    static String getIntro() {
        return "TIC TAC TOE" + "\n" +
                "The classic game of noughts and crosses!\nTurn friends into enemies as 2 players take turns marking spaces in a grid.\nWin short-lived glory by succeeding in placing a complete line in any horizontal, vertical or diagonal direction." + "\n";
    }
}
