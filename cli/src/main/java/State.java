import java.util.List;

public interface State {
    GameModes mode();

    BoardModes boardMode();

    List<String> contents();

    Players players();

    Symbol playerCross();

    Symbol playerNought();

    Board board();

    String lastMove();
}
