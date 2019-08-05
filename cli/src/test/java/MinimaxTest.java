import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinimaxTest {
    private Board board;
    private Symbol playerMaxi;
    private Symbol playerMini;
    private Minimax minimax;

    @Before
    public void setUp() {
        board = new Board();
        playerMaxi = new Symbol("Maxi");
        playerMini = new Symbol("Mini");
        minimax = new Minimax(board, playerMaxi, playerMini);
    }

    @Test
    public void minimaxPlaysForWin() {
        board.add(0, playerMaxi);
        board.add(1, playerMaxi);
        int result = minimax.optimal();
        assertEquals(2, result);
    }

    @Test
    public void minimaxBlocksOpponentWin() {
        board.add(3, playerMini);
        board.add(4, playerMini);
        int result = minimax.optimal();
        assertEquals(5, result);
    }

    @Test
    public void minimaxGoesForEarliestWin() {
        board.add(0, playerMini);
        board.add(1, playerMini);
        board.add(2, playerMaxi);
        board.add(3, playerMini);
        board.add(4, playerMaxi);
        board.add(5, playerMaxi);
        int result = minimax.optimal();
        assertEquals(6, result);
    }
}