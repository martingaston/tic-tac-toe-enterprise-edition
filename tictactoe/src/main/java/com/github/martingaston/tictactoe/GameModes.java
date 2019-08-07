package com.github.martingaston.tictactoe;

public enum GameModes {
    MODE_HVH(1, "hvh"),
    MODE_HVC_EASY(2, "hvc-easy"),
    MODE_HVC_HARD(3, "hvc-hard"),
    MODE_CVC_EASY(4, "cvc-easy"),
    MODE_CVC_HARD(5, "cvc-hard");

    private final Integer id;
    private final String arg;

    GameModes(Integer id, String arg) {
        this.id = id;
        this.arg = arg;
    }

    public static GameModes nameOf(int id) {
        for (GameModes option : values()) {
            if (option.id == id) {
                return option;
            }
        }

        return GameModes.MODE_HVH;
    }

    public static GameModes nameOf(String arg) {
        for (GameModes option : values()) {
            if (option.arg.equals(arg)) {
                return option;
            }
        }

        return GameModes.MODE_HVH;
    }

    public static int id(GameModes mode) {
        return mode.id;
    }
}
