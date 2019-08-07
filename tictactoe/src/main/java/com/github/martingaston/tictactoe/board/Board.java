package com.github.martingaston.tictactoe.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Board {
    private final List<Cell> board = new ArrayList<>();
    private int sideLength;
    private int totalCells;

    public Board() {
        this(3);
    }

    public Board(int sideLength) {
        generate(sideLength);
    }

    private void generate(int sideLength) {
        this.sideLength = sideLength;
        this.totalCells = sideLength * sideLength;
        for (int i = 0; i < this.totalCells; i++) {
            board.add(new Cell());
        }
    }

    public Cell get(int position) {
        return board.get(position);
    }

    public int getTotalCells() {
        return this.totalCells;
    }

    public int sideLength() {
        return this.sideLength;
    }

    public List<Integer> available() {
        List<Integer> moves = new LinkedList<>();

        for (int i = 0; i < this.totalCells; i++) {
            if (!get(i).isOccupied()) {
                moves.add(i);
            }
        }

        return moves;
    }

    public void add(int position, Symbol symbol) {
        Cell requestedCell = get(position);
        if (!requestedCell.isOccupied()) {
            requestedCell.mark(symbol);
        }
    }

    public void remove(int position) {
        Cell requestedCell = get(position);
        if (requestedCell.isOccupied()) {
            requestedCell.unmark();
        }
    }

    public List<Line> lines() {
        List<Line> lines = new LinkedList<>();
        lines.addAll(rows());
        lines.addAll(columns());
        lines.addAll(diagonals());
        return lines;
    }

    private List<Line> diagonals() {
        List<Cell> diagonalLeft = new LinkedList<>();
        List<Cell> diagonalRight = new LinkedList<>();

        for (int row = 0; row < this.sideLength; row++) {
            diagonalLeft.add(leftDiagonalAt(row));
            diagonalRight.add(rightDiagonalAt(row));
        }

        return Arrays.asList(
                new Line(diagonalLeft),
                new Line(diagonalRight)
        );
    }

    private Cell rightDiagonalAt(int row) {
        return get(this.sideLength - 1 + (row * this.sideLength) - row);
    }

    private Cell leftDiagonalAt(int row) {
        return get(row + (row * this.sideLength));
    }

    private List<Line> columns() {
        List<Line> verticalMoves = new LinkedList<>();

        for (int column = 0; column < this.sideLength; column++) {
            verticalMoves.add(columnAt(column));
        }

        return verticalMoves;
    }

    private Line columnAt(int column) {
        List<Cell> populatedColumn = new LinkedList<>();
        for (int i = 0; i < sideLength; i++) {
            populatedColumn.add(get(column + sideLength * i));
        }

        return new Line(populatedColumn);
    }

    private List<Line> rows() {
        List<Line> horizontalMoves = new LinkedList<>();

        for (int row = 0; row < this.sideLength; row++) {
            horizontalMoves.add(rowAt(row));
        }

        return horizontalMoves;
    }

    private Line rowAt(int row) {
        int startIndex = row * sideLength;

        List<Cell> populatedRow = new LinkedList<>();
        for (int i = startIndex; i < startIndex + sideLength; i++) {
            populatedRow.add(get(i));
        }

        return new Line(populatedRow);
    }

    public boolean hasWinner() {
        return lines().stream().anyMatch(Line::hasWinner);
    }

    public boolean hasWon(Symbol symbol) {
        return lines().stream().anyMatch(line -> line.hasWinner(symbol));
    }

    public boolean isGameOver() {
        return isBoardFull() || hasWinner();
    }

    private boolean isBoardFull() {
        return this.board.stream().allMatch(Cell::isOccupied);
    }

    public List<String> toList() {
        return board.stream()
                .map(cell -> cell.occupant().toString())
                .collect(Collectors.toList());
    }
}
