import org.junit.Test;

import static org.junit.Assert.*;

public class SymbolTest {
    @Test
    public void SymbolForCrossReturnsStringCross() {
        Symbol symbol = new Symbol("X");
        assertEquals("X", symbol.toString());
    }

    @Test
    public void SymbolForNoughtReturnsStringNought() {
        Symbol symbol = new Symbol("O");
        assertEquals("O", symbol.toString());
    }

    @Test
    public void twoSymbolsWithSameValueAreEqual() {
        Symbol symbolOne = new Symbol("O");
        Symbol symbolTwo = new Symbol("O");
        assertEquals(symbolOne, symbolTwo);
    }

    @Test
    public void twoSymbolsWithDifferentValueAreNotEquals() {
        Symbol symbolOne = new Symbol("O");
        Symbol symbolTwo = new Symbol("X");
        assertNotEquals(symbolOne, symbolTwo);
    }
}