package chess.model;

/**
 * Projekt: Szachy
 * Reprezentacja koloru figury
 * Micha³ Rapacz
 * 2015-03-26
 */

public enum Color {
	WHITE, BLACK;

	public Color getOppositeColor() {
		return (this == WHITE) ? BLACK : WHITE;
	}
}