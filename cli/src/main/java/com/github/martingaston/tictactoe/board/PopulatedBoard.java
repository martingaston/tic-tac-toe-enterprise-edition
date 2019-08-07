package com.github.martingaston.tictactoe.board;

import com.github.martingaston.tictactoe.state.State;

import java.util.List;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class PopulatedBoard {
    public static Board from(State state) {
        return from(state.contents(), state.players().playerCross().symbol(), state.players().playerNought().symbol());
    }

    public static Board from(List<String> boardList, Symbol playerCross, Symbol playerNought) {
        if(boardList.size() != 9 && boardList.size() != 16) {
            return new Board();
        }

        int sideLength = (int) Math.sqrt(boardList.size());
        Board newBoard = new Board(sideLength);

        IntStream
                .range(0, boardList.size())
                .forEach(checkOccupant(boardList, newBoard, playerCross.toString(), playerNought.toString()));

        return newBoard;
    }

    private static IntConsumer checkOccupant(List<String> boardList, Board newBoard, String playerCross, String playerNought) {
        return i -> {
            if(hasValidOccupant(boardList, playerCross, playerNought, i)) {
               newBoard.add(i, new Symbol(boardList.get(i)));
            }
        };
    }

    private static boolean hasValidOccupant(List<String> boardList, String playerCross, String playerNought, int i) {
        return positionIsOccupied(boardList, i) && symbolIsValid(boardList, i, playerCross, playerNought);
    }

    private static boolean positionIsOccupied(List<String> boardList, int i) {
        return boardList.get(i) != null && !boardList.get(i).isBlank();
    }

    private static boolean symbolIsValid(List<String> boardList, int i, String playerCross, String playerNought) {
        return boardList.get(i).equals(playerCross) || boardList.get(i).equals(playerNought);
    }
}