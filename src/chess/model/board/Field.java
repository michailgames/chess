package chess.model.board;

public class Field {

    private final static Field[][] fields;

    private final static int MAX_OFFSET_TO_REMEMBER = 2;

    static {
        fields = new Field[Board.BOARD_SIZE + 4][Board.BOARD_SIZE + 4];
        for (int x = 0; x < Board.BOARD_SIZE + 4; x++) {
            for (int y = 0; y < Board.BOARD_SIZE + 4; y++) {
                fields[x][y] = new Field(x - MAX_OFFSET_TO_REMEMBER, y - MAX_OFFSET_TO_REMEMBER);
            }
        }
    }

    private final int x;

    private final int y;

    private Field(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Field get(int x, int y) {
        return fields[x + MAX_OFFSET_TO_REMEMBER][y + MAX_OFFSET_TO_REMEMBER];
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Field == false) {
            return false;
        }
        Field other = (Field) obj;
        return this.x == other.x && this.y == other.y;
    }
}
