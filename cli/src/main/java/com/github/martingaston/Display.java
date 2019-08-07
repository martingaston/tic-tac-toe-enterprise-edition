package com.github.martingaston;

import com.github.martingaston.tictactoe.board.Board;
import com.github.martingaston.tictactoe.board.Cell;

public class Display {

    private static final String newline = "\n";

    private Display() {
    }

    public static void outMessage(String output) {
        System.out.println(output);
    }

    private static String faded(String message) {
        String ANSI_RESET = "\033[0m";
        String ANSI_DARK_GREY = "\033[38;5;242m";
        return ANSI_DARK_GREY + message + ANSI_RESET;
    }

    public static void showBoard(Board board) {
        System.out.print(renderRows(board));
    }

    private static String generateDivider(Board board) {
        int cellLength = getCellLength(board);

        StringBuilder divider = new StringBuilder("+");
        int lineLength = getLineLength(cellLength, board);
        divider.append("-".repeat(lineLength));
        divider.append("+");
        divider.append(newline);

        return divider.toString();
    }

    private static int getLineLength(int cellLength, Board board) {
        return cellLength * board.sideLength() - 1;
    }

    private static int getCellLength(Board board) {
        return board.getTotalCells() > 10 ? 5 : 4;
    }

    private static String renderRows(Board board) {
        int totalCells = board.getTotalCells();
        int cellsInRow = board.sideLength();

        StringBuilder grid = new StringBuilder(generateDivider(board));

        for (int i = 0; i < totalCells; i += cellsInRow) {
            String renderedRow = renderRow(board, i, i + cellsInRow);
            grid.append(renderedRow);
        }

        return grid.toString();
    }

    private static String renderRow(Board board, int startIndex, int endIndex) {
        StringBuilder renderedRowString = new StringBuilder();
        Cell currentBoardCell;
        renderedRowString.append("|");

        for (int i = startIndex; i < endIndex; i++) {
            renderedRowString.append(" ");

            currentBoardCell = board.get(i);

            String output;
            if (!currentBoardCell.isOccupied()) {
                int boardNumber = humanise(i);
                output = faded(renderOccupant(Integer.toString(boardNumber), board));
            } else {
                output = renderOccupant(currentBoardCell, board);
            }

            renderedRowString.append(output);
            renderedRowString.append(" |");
        }

        renderedRowString.append(newline);
        renderedRowString.append(generateDivider(board));

        return renderedRowString.toString();
    }

    private static String renderOccupant(Cell cell, Board board) {
        return renderOccupant(cell.occupant().toString(), board);
    }

    private static String renderOccupant(String occupant, Board board) {
        return occupantNeedsPadding(occupant, board) ? " " + occupant : occupant;
    }

    private static boolean occupantNeedsPadding(String occupant, Board board) {
        return board.getTotalCells() > 9 && occupant.length() == 1;
    }

    private static int humanise(int zeroIndexedNumber) {
        return zeroIndexedNumber + 1;
    }
}
