public enum Tile {
    N_S('|'),
    E_W('-'),
    N_E('L'),
    N_W('J'),
    S_W('7'),
    S_E('F'),
    GND('.'),
    START('S');

    private final char tileChar;

    Tile(char tileChar) {
        this.tileChar = tileChar;
    }

    public static Tile getByChar(char c) {
        for (Tile type : values()) {
            if (type.tileChar == c) {
                return type;
            }
        }
        throw new IllegalArgumentException("No such type with char: " + c);
    }
}