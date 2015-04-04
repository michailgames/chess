package chess.model.pieces;

/**
 * Projekt: Szachy
 * Reprezentacja królowej
 * Micha³ Rapacz
 * 2015-03-26
 */

import chess.model.board.Color;

public class Queen extends AbstractStraightMovingPiece {
	
	private static final Direction[] availableDirections = {
		new Direction(-1, -1),
		new Direction(-1, 0),
		new Direction(-1, 1),
		new Direction(0, -1),
		new Direction(0, 1),
		new Direction(1, -1),
		new Direction(1, 0),
		new Direction(1, 1)
	};

	public Queen(Color color, int x, int y) {
		super(color, x, y);
	}

	@Override
	public String getUnicodeString() {
		return "\u265b";
	}

	@Override
	protected Direction[] getAvailableDirections() {
		return availableDirections;
	}
	
	@Override
	public Piece copy() {
		return new Queen(getColor(), getX(), getY())
				.allowedToPerformCastling(canParticipateInCastling());
	}
}
