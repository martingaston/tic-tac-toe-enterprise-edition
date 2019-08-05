import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class PlayerHumanTest {
    private Board board;

    @Before
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testPlayerSymbolCross() {
        Player player = new PlayerHuman("X");
        assertEquals("X", player.symbol().toString());
    }

    @Test
    public void testPlayerSymbolNought() {
        Player player = new PlayerHuman("O");
        assertEquals("O", player.symbol().toString());
    }

    @Test
    public void testPlayerInput() {
        int zeroIndexedResult = 4;
        String input = "5";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Player player = new PlayerHuman("O");

        assertEquals(zeroIndexedResult, player.getNextMove(board));

    }


}