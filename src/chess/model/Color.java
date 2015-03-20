package chess.model;

public enum Color {
	WHITE, BLACK;

	public Color getOppositeColor() {
		return (this == WHITE) ? BLACK : WHITE;
	}
}