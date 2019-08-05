public enum BoardModes {
    BOARD_3X3(1, "3x3"),
    BOARD_4X4(2, "4x4");

    private final Integer id;
    private final String arg;

    BoardModes(Integer id, String arg) {
        this.id = id;
        this.arg = arg;
    }

    public static BoardModes nameOf(int id) {
        for (BoardModes option : values()) {
            if (option.id == id) {
                return option;
            }
        }

        return BoardModes.BOARD_3X3;
    }

    public static BoardModes nameOf(String arg) {
        for (BoardModes option : values()) {
            if (option.arg.equals(arg)) {
                return option;
            }
        }

        return BoardModes.BOARD_3X3;
    }

    public static int id(BoardModes mode) {
        return mode.id;
    }
}
