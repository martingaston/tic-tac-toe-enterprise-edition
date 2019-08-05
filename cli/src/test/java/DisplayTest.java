import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class DisplayTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setupStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void resetStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testEmptyBoardStateOutputPrintsCorrectly() {
        String boardOutput =
                "+-----------+\n" +
                        "| [38;5;242m1[0m | [38;5;242m2[0m | [38;5;242m3[0m |\n" +
                        "+-----------+\n" +
                        "| [38;5;242m4[0m | [38;5;242m5[0m | [38;5;242m6[0m |\n" +
                        "+-----------+\n" +
                        "| [38;5;242m7[0m | [38;5;242m8[0m | [38;5;242m9[0m |\n" +
                        "+-----------+\n";

        Board board = new Board();
        Display.showBoard(board);
        assertEquals(boardOutput, outContent.toString());
    }

    @Test
    public void testFullBoardStateOutputPrintsCorrectly() {

        String boardOutput =
                "+-----------+\n" +
                        "| X | O | X |\n" +
                        "+-----------+\n" +
                        "| O | X | O |\n" +
                        "+-----------+\n" +
                        "| X | O | X |\n" +
                        "+-----------+\n";

        Board board = new Board();
        Symbol playerCross = new Symbol("X");
        Symbol playerNought = new Symbol("O");
        board.add(0, playerCross);
        board.add(2, playerCross);
        board.add(4, playerCross);
        board.add(6, playerCross);
        board.add(8, playerCross);
        board.add(1, playerNought);
        board.add(3, playerNought);
        board.add(5, playerNought);
        board.add(7, playerNought);
        Display.showBoard(board);
        assertEquals(boardOutput, outContent.toString());
    }
}