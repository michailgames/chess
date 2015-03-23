package chess.model.pieces;

import java.util.ArrayList;
import java.util.List;

import chess.model.Board;
import chess.model.Color;
import chess.model.Field;
import chess.model.Piece;

public class Knight extends Piece {
	
	private final static Field[] moveOptions = {
		new Field(-2, -1),
		new Field(-1, -2),
		new Field(1, 2),
		new Field(2, 1),
		new Field(-1, 2),
		new Field(-2, 1),
		new Field(1, -2),
		new Field(2, -1)
	};

	public Knight(Color color, int x, int y) {
		super(color, x, y);
	}

	@Override
	public String getUnicodeString() {
		return "\u265e";
	}
	
	@Override
	protected List<Field> getAllPossibleMoves(Board board) {
		List<Field> movesList = new ArrayList<Field>();
		for(Field move : moveOptions) {
			int x = getX() + move.getX();
			int y = getY() + move.getY();
			if(isPositionInsideTheBoard(x, y)) {
				Piece piece = board.getPiece(x, y);
				if(piece == null || piece.getColor() != getColor()) {
					movesList.add(new Field(x, y));
				}
			}
		}
		return movesList;
	}
	
	@Override
	public Piece copy() {
		return new Knight(getColor(), getX(), getY())
				.allowedToPerformCastling(canParticipateInCastling());
	}
}
