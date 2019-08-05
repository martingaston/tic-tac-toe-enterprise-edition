class Players {
    private final Player playerCross;
    private final Player playerNought;
    private Player currentPlayer;

    public Players(Player playerCross, Player playerNought) {
        this.playerCross = playerCross;
        this.playerNought = playerNought;
        this.currentPlayer = this.playerCross;
    }

    public static Players create(GameModes mode, IO io) {
        Player playerCross;
        Player playerNought;
        switch (mode) {
            case MODE_HVH:
            default:
                playerCross = new PlayerHuman("X", io);
                playerNought = new PlayerHuman("O", io);
                break;
            case MODE_HVC_EASY:
                playerCross = new PlayerHuman("X", io);
                playerNought = new PlayerCPU("O");
                break;
            case MODE_HVC_HARD:
                playerCross = new PlayerHuman("X", io);
                playerNought = new PlayerMinimax("O", new Symbol("X"));
                break;
            case MODE_CVC_EASY:
                playerCross = new PlayerCPU("X");
                playerNought = new PlayerCPU("O");
                break;
            case MODE_CVC_HARD:
                playerCross = new PlayerMinimax("X", new Symbol("O"));
                playerNought = new PlayerMinimax("O", new Symbol("X"));
        }

        return new Players(playerCross, playerNought);
    }

    public void nextTurn() {
        if (currentPlayer == playerCross) {
            currentPlayer = playerNought;
        } else {
            currentPlayer = playerCross;
        }
    }

    public Player playerCross() {
        return playerCross;
    }

    public Player playerNought() {
        return playerNought;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
