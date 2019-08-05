public interface Player {
    Symbol symbol();

    int getNextMove(Board board);
}