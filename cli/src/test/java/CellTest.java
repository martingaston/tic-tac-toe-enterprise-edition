import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CellTest {
    private Symbol playerCross;
    private Symbol playerNought;
    private Cell cell;

    @Before
    public void setUp() {
        playerCross = new Symbol("X");
        playerNought = new Symbol("O");
        cell = new Cell();
    }

    @Test
    public void unoccupiedCellIsABlankSpace() {
        String unoccupiedCell = cell.occupant().toString();
        assertEquals(" ", unoccupiedCell);
    }

    @Test
    public void cellOccupiedByCrossReturnsCross() {
        cell.mark(playerCross);
        String crossOccupiedCell = cell.occupant().toString();
        assertEquals("X", crossOccupiedCell);
    }

    @Test
    public void cellOccupiedByNoughtReturnsNought() {
        cell.mark(playerNought);
        String noughtOccupiedCell = cell.occupant().toString();
        assertEquals("O", noughtOccupiedCell);
    }
}