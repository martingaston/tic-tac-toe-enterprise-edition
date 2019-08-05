public class Cell {
    private Symbol occupant;

    Symbol occupant() {
        if (!isOccupied()) {
            return new Symbol(" ");
        }

        return occupant;
    }

    void mark(Symbol symbol) {
        this.occupant = symbol;
    }

    void unmark() {
        this.occupant = null;
    }

    boolean isOccupied() {
        return occupant != null;
    }
}
