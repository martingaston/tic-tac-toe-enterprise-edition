import com.github.martingaston.tictactoe.Minimax;
import com.github.martingaston.tictactoe.board.Board;
import com.github.martingaston.tictactoe.board.PopulatedBoard;
import com.github.martingaston.tictactoe.board.Symbol;

class Update {
    static JsonOutgoing from(JsonIncoming currentGame) {
       Board board = PopulatedBoard.from(currentGame.board(), new Symbol("X"), new Symbol("O"));
       board.add(oneIndexedToZeroIndexed(currentGame.position()), currentGame.currentPlayer());

       if (board.isGameOver()) {
           return gameOver(board);
       }

       if (Referee.aiShouldMakeMove(currentGame)) {
           makeAiMove(board);
       }

       return updateGame(currentGame, board);
    }

    private static void makeAiMove(Board board) {
        var aiSymbol = new Symbol("O");
        int cpuMove = new Minimax(board, aiSymbol, new Symbol("X")).optimal();
        board.add(cpuMove, aiSymbol);
    }

    private static JsonOutgoing updateGame(JsonIncoming currentGame, Board updatedBoard) {
        return Responses.updatedMove(currentGame, updatedBoard);
    }

    private static JsonOutgoing gameOver(Board finalBoard) {
        return Responses.gameOver(finalBoard);

    }

    private static int oneIndexedToZeroIndexed(int i) {
        return i - 1;
    }
}
