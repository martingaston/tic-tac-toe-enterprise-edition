import java.util.List;

class Line {
    private final List<Cell> cells;

    public Line(List<Cell> cells) {
        this.cells = cells;
    }

    public boolean hasWinner() {
        Symbol occupant = cells.get(0).occupant();
        return hasWinner(occupant);
    }

    public boolean hasWinner(Symbol occupant) {
        if (occupant.equals(new Symbol(" "))) {
            return false;
        }

        return cells.stream().allMatch(cell -> cell.occupant().equals(occupant));
    }

    @Override
    public String toString() {
        StringBuilder stringify = new StringBuilder("Cell: ");
        for (Cell cell : cells) {
            stringify.append("| isOccupied: ").append(cell.isOccupied());
            if (cell.isOccupied()) {
                stringify.append(", occupant: ").append(cell.occupant());
            }
            stringify.append(" | ");
        }
        return stringify.toString();
    }
}