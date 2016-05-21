package chess.model.board;

public class Field {

    private final int x;

    private final int y;

    public Field(int x, int y) {
        this.x = x;
        this.y = y;
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
