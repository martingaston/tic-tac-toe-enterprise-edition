package com.github.martingaston.player;

import com.github.martingaston.tictactoe.GameModes;
import com.github.martingaston.tictactoe.board.Symbol;
import com.github.martingaston.IO;
import com.github.martingaston.tictactoe.player.Players;
import com.github.martingaston.tictactoe.player.Player;

public class CLIPlayers implements Players {
    private final com.github.martingaston.tictactoe.player.Player playerCross;
    private final com.github.martingaston.tictactoe.player.Player playerNought;
    private com.github.martingaston.tictactoe.player.Player currentPlayer;

    public CLIPlayers(com.github.martingaston.tictactoe.player.Player playerCross, com.github.martingaston.tictactoe.player.Player playerNought) {
        this.playerCross = playerCross;
        this.playerNought = playerNought;
        this.currentPlayer = this.playerCross;
    }

    public static CLIPlayers create(GameModes mode, IO io) {
        com.github.martingaston.tictactoe.player.Player playerCross;
        com.github.martingaston.tictactoe.player.Player playerNought;
        switch (mode) {
            case MODE_HVH:
            default:
                playerCross = new PlayerHuman("X", io);
                playerNought = new PlayerHuman("O", io);
                break;
            case MODE_HVC_EASY:
                playerCross = new PlayerHuman("X", io);
                playerNought = new com.github.martingaston.tictactoe.player.PlayerCPU("O");
                break;
            case MODE_HVC_HARD:
                playerCross = new PlayerHuman("X", io);
                playerNought = new com.github.martingaston.tictactoe.player.PlayerMinimax("O", new Symbol("X"));
                break;
            case MODE_CVC_EASY:
                playerCross = new com.github.martingaston.tictactoe.player.PlayerCPU("X");
                playerNought = new com.github.martingaston.tictactoe.player.PlayerCPU("O");
                break;
            case MODE_CVC_HARD:
                playerCross = new com.github.martingaston.tictactoe.player.PlayerMinimax("X", new Symbol("O"));
                playerNought = new com.github.martingaston.tictactoe.player.PlayerMinimax("O", new Symbol("X"));
        }

        return new CLIPlayers(playerCross, playerNought);
    }

    @Override
    public void nextTurn() {
        if (currentPlayer == playerCross) {
            currentPlayer = playerNought;
        } else {
            currentPlayer = playerCross;
        }
    }

    @Override
    public Player playerCross() {
        return playerCross;
    }

    @Override
    public Player playerNought() {
        return playerNought;
    }

    @Override
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
