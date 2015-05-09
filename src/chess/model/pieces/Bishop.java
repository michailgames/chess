package chess.model.pieces;

/**
 * Projekt: Szachy
 * Reprezentacja biskupa
 * Micha³ Rapacz
 * 2015-03-26
 */

import chess.model.board.Color;

public class Bishop extends AbstractStraightMovingPiece {
	
	private final static Bishop whiteBishop = new Bishop(Color.WHITE);
	private final static Bishop blackBishop = new Bishop(Color.BLACK);
	
	public static Bishop getInstance(Color color) {
		return color == Color.WHITE ? whiteBishop : blackBishop;
	}
	
	private static final Direction[] availableDirections = {
		new Direction(-1, -1),
		new Direction(1, -1),
		new Direction(-1, 1),
		new Direction(1, 1)
	};

	public Bishop(Color color) {
		super(color);
	}

	@Override
	public String getUnicodeString() {
		return "\u265d";
	}
	
	@Override
	protected Direction[] getAvailableDirections() {
		return availableDirections;
	}
}
