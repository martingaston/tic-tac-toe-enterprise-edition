import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessagesTest {
    private Player player;

    private boolean isString(String message) {
        return message.getClass() == String.class;
    }

    @Before
    public void setUp() {
        Board board = new Board();
        player = new PlayerHuman("X");
    }

    @Test
    public void playerWinsIsFormattedBasedOnPlayerClass() {
        String expectedResult = "Player X wins!";
        String message = Messages.playerWin(player);
        assertEquals(expectedResult, message);
    }

    @Test
    public void announcePlayerTurnIsFormattedBasedOnPlayerClass() {
        String expectedResult = "Player X's turn";
        String message = Messages.announcePlayerTurn(player);
        assertEquals(expectedResult, message);
    }

    @Test
    public void playersDrawReturnsString() {
        String message = Messages.playersDraw();
        assertTrue(isString(message));
    }

    @Test
    public void playersDrawStringIsNotEmpty() {
        String message = Messages.playersDraw();
        assertFalse(message.isEmpty());
    }

    @Test
    public void getIntroReturnsString() {
        String message = Messages.getIntro();
        assertTrue(isString(message));
    }

    @Test
    public void getIntroStringIsNotEmpty() {
        String message = Messages.getIntro();
        assertFalse(message.isEmpty());
    }

    @Test
    public void getInstructionsReturnsString() {
        int boardSize = 9;
        String message = Messages.getInstructions(boardSize);
        assertTrue(isString(message));
    }

    @Test
    public void getInstructionsStringIsNotEmpty() {
        int boardSize = 9;
        String message = Messages.getInstructions(boardSize);
        assertFalse(message.isEmpty());
    }
}