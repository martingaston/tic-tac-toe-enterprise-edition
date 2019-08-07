import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.martingaston.tictactoe.board.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class JsonIncoming implements Json {
    private final int position;
    private final String mode;
    private final Symbol currentPlayer;
    private final boolean isActive;
    private final List<String> board;
    private final Map<String, String> messages;

    @JsonCreator
    private static JsonIncoming from(
            @JsonProperty("position") int position,
            @JsonProperty("mode") String mode,
            @JsonProperty("currentPlayer") String currentPlayer,
            @JsonProperty("isActive") boolean isActive,
            @JsonProperty("board") ArrayList<String> board,
            @JsonProperty("messages") HashMap<String, String> messages
    ) {
        return new JsonIncoming(position, mode, new Symbol(currentPlayer), isActive, board, messages);
    }

    private JsonIncoming(int position, String mode, Symbol currentPlayer, boolean isActive, List<String> board, Map<String, String> messages) {
        this.position = position;
        this.mode = mode;
        this.currentPlayer = currentPlayer;
        this.isActive = isActive;
        this.board = board;
        this.messages = messages;
    }

    public static class Builder {
        private int position;
        private String mode;
        private Symbol currentPlayer;
        private boolean isActive;
        private List<String> board;
        private Map<String, String> messages;

        public Builder position(int position) {
            this.position = position;

            return this;
        }

        public Builder mode(String mode) {
            this.mode = mode;

            return this;
        }

        public Builder currentPlayer(Symbol playerSymbol) {
            this.currentPlayer = playerSymbol;

            return this;
        }

        public Builder isActive(boolean isActive) {
            this.isActive = isActive;

            return this;
        }

        public Builder board(List<String> board) {
            this.board = board;

            return this;
        }

        public Builder messages(Map<String, String> messages) {
            this.messages = messages;

            return this;
        }

        public JsonIncoming build() {
            return new JsonIncoming(this.position, this.mode, this.currentPlayer, this.isActive, this.board, this.messages);
        }
    }

    @JsonProperty("position")
    public int position() {
        return this.position;
    }

    @JsonProperty("isActive")
    public boolean isActive() {
        return this.isActive;
    }

    @JsonProperty("mode")
    public String mode() {
        return this.mode;
    }

    @JsonProperty("currentPlayer")
    private String currentPlayerString() {
        return this.currentPlayer.toString();
    }

    public Symbol currentPlayer() {
        return this.currentPlayer;
    }

    @JsonProperty("board")
    public List<String> board() {
        return this.board;
    }

    @JsonProperty("messages")
    public Map<String, String> messages() {
        return this.messages;
    }
}
