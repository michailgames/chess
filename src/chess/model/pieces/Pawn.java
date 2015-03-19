package chess.model.pieces;

import java.util.ArrayList;
import java.util.List;

import chess.model.Board;
import chess.model.Color;
import chess.model.Field;
import chess.model.Piece;

public class Pawn extends Piece {

	public Pawn(Color color, int x, int y) {
		super(color, x, y);
	}

	@Override
	protected String getWhiteUnicodeString() {
		return "\u2659";
	}

	@Override
	protected String getBlackUnicodeString() {
		return "\u265f";
	}

	@Override
	protected List<Field> getAllPossibleMoves(Board board) {
		List<Field> movesList = new ArrayList<Field>(4);
		int direction = (getColor() == Color.WHITE) ? -1 : 1;
		if(board.getPiece(getX(), getY() + direction) == null) {
			movesList.add(new Field(getX(), getY() + direction));
		}
		return movesList;
	}
}
