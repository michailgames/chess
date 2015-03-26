package chess.model.pieces;

/**
 * Projekt: Szachy
 * Reprezentacja wie¿y
 * Micha³ Rapacz
 * 2015-03-26
 */

import chess.model.Color;
import chess.model.Piece;

public class Rook extends AbstractStraightMovingPiece {
	
	private static final Direction[] availableDirections = {
		new Direction(-1, 0),
		new Direction(1, 0),
		new Direction(0, 1),
		new Direction(0, -1)
	};

	public Rook(Color color, int x, int y) {
		super(color, x, y);
	}

	@Override
	public String getUnicodeString() {
		return "\u265c";
	}
	
	@Override
	protected Direction[] getAvailableDirections() {
		return availableDirections;
	}
	
	@Override
	public Piece copy() {
		return new Rook(getColor(), getX(), getY())
				.allowedToPerformCastling(canParticipateInCastling());
	}
}
