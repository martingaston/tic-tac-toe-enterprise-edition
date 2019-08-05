public class PopulatedBoard {
    public static Board from(State state) {
        if (state.contents().size() != 9 && state.contents().size() != 16) {
            return new Board();
        }

        int sideLength = (int) Math.sqrt(state.contents().size());
        Board newBoard = new Board(sideLength);

        for (int i = 0; i < state.contents().size(); i++) {
            Symbol currentSymbol = new Symbol(state.contents().get(i));
            if (currentSymbol.equals(state.playerCross())) {

                newBoard.add(i, state.players().playerCross().symbol());
            } else if (currentSymbol.equals(state.playerNought())) {
                newBoard.add(i, state.players().playerNought().symbol());
            }
        }

        return newBoard;
    }
}