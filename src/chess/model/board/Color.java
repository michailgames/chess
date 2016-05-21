package chess.model.board;

public enum Color {
    WHITE, BLACK;

    public Color getOppositeColor() {
        return (this == WHITE) ? BLACK : WHITE;
    }
}