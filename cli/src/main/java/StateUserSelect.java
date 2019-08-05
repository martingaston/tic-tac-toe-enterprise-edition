import java.util.List;

public class StateUserSelect implements State {
    private final IO io;
    private GameModes mode;
    private BoardModes boardMode;
    private Board board;
    private List<String> contents;
    private Players players;
    private String lastMove;

    public StateUserSelect(IO io) {
        this.io = io;
        boardMode = getBoardSize();
        mode = getGameMode();
        board = createBoard(boardMode);
        players = Players.create(mode, io);
    }

    public BoardModes getBoardSize() {
        Display.outMessage(Messages.boardSetupInstructions());
        int boardNumber = io.nextInt();
        return BoardModes.nameOf(boardNumber);
    }

    public GameModes getGameMode() {
        Display.outMessage(Messages.setupInstructions());
        int modeNumber = io.nextInt();
        return GameModes.nameOf(modeNumber);
    }

    private Board createBoard(BoardModes boardMode) {
        switch (boardMode) {
            case BOARD_3X3:
            default:
                return new Board(3);
            case BOARD_4X4:
                return new Board(4);
        }
    }

    @Override
    public GameModes mode() {
        return mode;
    }

    @Override
    public BoardModes boardMode() {
        return boardMode;
    }

    @Override
    public List<String> contents() {
        return null;
    }

    @Override
    public Players players() {
        return players;
    }

    @Override
    public Board board() {
        return board;
    }

    @Override
    public String lastMove() {
        return null;
    }

    @Override
    public Symbol playerCross() {
        return new Symbol("X");
    }

    @Override
    public Symbol playerNought() {
        return new Symbol("O");
    }
}
