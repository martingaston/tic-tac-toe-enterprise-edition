package com.github.martingaston.state;

import com.github.martingaston.tictactoe.GameModes;
import com.github.martingaston.tictactoe.board.Board;
import com.github.martingaston.tictactoe.board.BoardModes;
import com.github.martingaston.tictactoe.board.Cell;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class StateFileTest {

    @Test
    public void GameStateReturnsModeHVHEnumWithOne() {
        List<String> stateVariables = Arrays.asList(
                "0", "1", "", "", "",
                "", "", "",
                "", "", "",
                "", "", "");
        StateFile stateFile = new StateFile(stateVariables);
        assertEquals(GameModes.MODE_HVH, stateFile.mode());
    }

    @Test
    public void GameStateReturnsModeHVCHardEnumWithThree() {
        List<String> stateVariables = Arrays.asList(
                "0", "3", "", "", "",
                "", "", "",
                "", "", "",
                "", "", "");
        StateFile stateFile = new StateFile(stateVariables);
        assertEquals(GameModes.MODE_HVC_HARD, stateFile.mode());
    }

    @Test
    public void GameStateReturnsModeHVHEnumIfInputIsInvalid() {
        List<String> stateVariables = Arrays.asList(
                "0", "15", "", "", "",
                "", "", "",
                "", "", "",
                "", "", "");
        StateFile stateFile = new StateFile(stateVariables);
        assertEquals(GameModes.MODE_HVH, stateFile.mode());
    }

    @Test
    public void GameStateReturnsBoard3X3EnumWithOne() {
        List<String> stateVariables = Arrays.asList(
                "1", "0", "", "", "",
                "", "", "",
                "", "", "",
                "", "", "");
        StateFile stateFile = new StateFile(stateVariables);
        assertEquals(BoardModes.BOARD_3X3, stateFile.boardMode());
    }

    @Test
    public void GameStateReturnsBoard4X4EnumWithTwo() {
        List<String> stateVariables = Arrays.asList(
                "2", "0", "", "", "",
                "", "", "",
                "", "", "",
                "", "", "");
        StateFile stateFile = new StateFile(stateVariables);
        assertEquals(BoardModes.BOARD_4X4, stateFile.boardMode());
    }

    @Test
    public void GameStateReturnsBoard3X3EnumWithInvalidInput() {
        List<String> stateVariables = Arrays.asList(
                "15", "0", "", "", "",
                "", "", "",
                "", "", "",
                "", "", "");
        StateFile stateFile = new StateFile(stateVariables);
        assertEquals(BoardModes.BOARD_3X3, stateFile.boardMode());
    }
    private String[] createStringArrayFromBoard(Board board) {
        ArrayList<String> boardAsArrayList = new ArrayList<>();
        int totalCells = board.getTotalCells();
        Cell currentCell;
        for (int i = 0; i < totalCells; i++) {
            currentCell = board.get(i);
            boardAsArrayList.add(currentCell.occupant().toString());
        }
        return boardAsArrayList.toArray(new String[]{});
    }

    @Test
    public void boardFromListCreatesEmpty3x3Board() {
        StateFile state = new StateFile(Arrays.asList(
                "1", "1", "X", "O", "O",
                "", "", "",
                "", "", "",
                "", "", ""
        ));
        Board board = state.board();
        String[] updatedBoard = createStringArrayFromBoard(board);
        String[] expectedBoard = {
                " ", " ", " ",
                " ", " ", " ",
                " ", " ", " "
        };

        assertArrayEquals(expectedBoard, updatedBoard);
    }

    @Test
    public void boardFromListCreatesHorizontalPopulated3x3Board() {
        StateFile state = new StateFile(Arrays.asList(
                "1", "1", "X", "O", "O",
                "X", "X", "X",
                "", "", "",
                "", "", ""
        ));
        Board board = state.board();
        String[] updatedBoard = createStringArrayFromBoard(board);
        String[] expectedBoard = {
                "X", "X", "X",
                " ", " ", " ",
                " ", " ", " "
        };

        assertArrayEquals(expectedBoard, updatedBoard);
    }

    @Test
    public void boardFromListCreatesVerticalPopulated3x3Board() {
        StateFile state = new StateFile(Arrays.asList(
                "1", "1", "X", "O", "O",
                "O", "", "",
                "O", "", "",
                "O", "", ""
        ));
        Board board = state.board();
        String[] updatedBoard = createStringArrayFromBoard(board);
        String[] expectedBoard = {
                "O", " ", " ",
                "O", " ", " ",
                "O", " ", " "
        };

        assertArrayEquals(expectedBoard, updatedBoard);
    }

    @Test
    public void boardFromListDoesNotPlaceNonMatchingSymbols() {
        StateFile state = new StateFile(Arrays.asList(
                "1", "1", "X", "O", "O",
                "Q", "Q", "Q",
                "", "", "",
                "", "", ""
        ));
        Board board = state.board();
        String[] updatedBoard = createStringArrayFromBoard(board);
        String[] expectedBoard = {
                " ", " ", " ",
                " ", " ", " ",
                " ", " ", " "
        };

        assertArrayEquals(expectedBoard, updatedBoard);
    }

    @Test
    public void boardFromListCreates4x4Board() {
        StateFile state = new StateFile(Arrays.asList(
                "1", "1", "X", "O", "O",
                "", "", "", "",
                "", "", "", "",
                "", "", "", "",
                "", "", "", ""
        ));

        Board board = state.board();
        String[] updatedBoard = createStringArrayFromBoard(board);
        String[] expectedBoard = {
                " ", " ", " ", " ",
                " ", " ", " ", " ",
                " ", " ", " ", " ",
                " ", " ", " ", " "
        };

        assertArrayEquals(expectedBoard, updatedBoard);
    }

    @Test
    public void ListsNotNineOrSixteenLengthWillReturnEmpty3x3Board() {
        StateFile state = new StateFile(Arrays.asList(
                "1", "1", "X", "O", "O",
                "X", "X", "X",
                "", "", "",
                "", "", "", ""
        ));

        Board board = state.board();
        String[] updatedBoard = createStringArrayFromBoard(board);
        String[] expectedBoard = {
                " ", " ", " ",
                " ", " ", " ",
                " ", " ", " "
        };

        assertArrayEquals(expectedBoard, updatedBoard);
    }
}