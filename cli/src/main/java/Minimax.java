class Minimax {
    private final Symbol maximizer;
    private final Symbol minimizer;
    private final Board board;

    public Minimax(Board board, Symbol maximizer, Symbol minimizer) {
        this.board = board;
        this.maximizer = maximizer;
        this.minimizer = minimizer;
    }

    private Integer evaluateBoard(int depth) {
        final int WINNING_SCORE = 10;
        final int LOSING_SCORE = -10;
        final int DRAW_SCORE = 0;

        if (board.hasWon(this.maximizer)) {
            return WINNING_SCORE - depth;
        }

        if (board.hasWon(this.minimizer)) {
            return LOSING_SCORE + depth;
        }

        return DRAW_SCORE;
    }

    private Integer minimax(int depth, boolean isMax) {
        int score = evaluateBoard(depth);

        if (score != 0) {
            return score;
        }

        if (noMovesLeft()) {
            return 0;
        }

        if (isMax) {
            int best = Integer.MIN_VALUE;

            for (int index : board.available()) {
                board.add(index, maximizer);
                best = Integer.max(best,
                        minimax(depth + 1, false));
                board.remove(index);
            }

            return best;
        } else {
            int best = Integer.MAX_VALUE;

            for (int index : board.available()) {
                board.add(index, minimizer);
                best = Integer.min(best,
                        minimax(depth + 1, true));
                board.remove(index);
            }

            return best;
        }
    }

    public Integer optimal() {
        int bestValue = Integer.MIN_VALUE;
        int bestIndex = Integer.MIN_VALUE;

        for (int index : board.available()) {
            board.add(index, maximizer);
            int moveValue = minimax(0, false);
            board.remove(index);

            if (moveValue > bestValue) {
                bestValue = moveValue;
                bestIndex = index;
            }
        }

        return bestIndex;
    }

    private boolean noMovesLeft() {
        return board.isGameOver();
    }
}
