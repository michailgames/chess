package chess.model.pieces;

/**
 * Projekt: Szachy
 * Reprezentacja biskupa
 * Micha� Rapacz
 * 2015-03-26
 */

import chess.model.Color;
import chess.model.Piece;

public class Bishop extends AbstractStraightMovingPiece {
	
	private static final Direction[] availableDirections = {
		new Direction(-1, -1),
		new Direction(1, -1),
		new Direction(-1, 1),
		new Direction(1, 1)
	};

	public Bishop(Color color, int x, int y) {
		super(color, x, y);
	}

	@Override
	public String getUnicodeString() {
		return "\u265d";
	}
	
	@Override
	protected Direction[] getAvailableDirections() {
		return availableDirections;
	}
	
	@Override
	public Piece copy() {
		return new Bishop(getColor(), getX(), getY())
				.allowedToPerformCastling(canParticipateInCastling());
	}
}
