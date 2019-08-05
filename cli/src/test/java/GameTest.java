import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class GameTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testCrossWinsHVHGameOn3x3Board() throws IOException {
        String gameInputs = "1\n1\n1\n4\n2\n5\n3";
        InputStream in = new ByteArrayInputStream(gameInputs.getBytes());
        System.setIn(in);

        Main.main(new String[] {});

        String gameOutput = outContent.toString();

        Pattern playerCrossWins = Pattern.compile("^.*(Player X wins!)$", Pattern.MULTILINE);
        Matcher gameMatch = playerCrossWins.matcher(gameOutput);
        assertTrue(gameMatch.find());
    }

    @Test
    public void testDrawStateEndsInDraw() throws IOException{
        String gameInputs = "1\n1\n1\n5\n2\n3\n7\n4\n6\n9\n8";
        InputStream in = new ByteArrayInputStream(gameInputs.getBytes());
        System.setIn(in);

        Main.main(new String[] {});

        String gameOutput = outContent.toString();

        Pattern drawState = Pattern.compile("^.*(Bad luck! It's a draw!)$", Pattern.MULTILINE);
        Matcher gameMatch = drawState.matcher(gameOutput);
        assertTrue(gameMatch.find());
    }

    @Test
    public void testNoughtWinsHVHGameOn4x4BoardAfterCrossPlaysIncorrectMove() throws IOException {
        String gameInputs = "2\n1\n16\n2\n11\n3\n6\n1\n1\n5\n4";
        InputStream in = new ByteArrayInputStream(gameInputs.getBytes());
        System.setIn(in);

        Main.main(new String[] {});

        String gameOutput = outContent.toString();

        Pattern playerCrossWins = Pattern.compile("^.*(Player O wins!)$", Pattern.MULTILINE);
        Matcher gameMatch = playerCrossWins.matcher(gameOutput);
        assertTrue(gameMatch.find());
    }
}