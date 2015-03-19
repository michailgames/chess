package chess.model.pieces;

import java.util.ArrayList;
import java.util.List;

import chess.model.Board;
import chess.model.Color;
import chess.model.Field;
import chess.model.Piece;

public class King extends Piece {

	public King(Color color, int x, int y) {
		super(color, x, y);
	}

	@Override
	protected String getWhiteUnicodeString() {
		return "\u2654";
	}

	@Override
	protected String getBlackUnicodeString() {
		return "\u265a";
	}

	@Override
	protected List<Field> getAllPossibleMoves(Board board) {
		List<Field> possibleMoves = new ArrayList<Field>(10);
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				int x = getX() + i;
				int y = getY() + j;
				if((i != 0 || j != 0) && isPositionInsideTheBoard(x, y)) {
					Piece piece = board.getPiece(x, y);
					if(piece == null || piece.getColor() != getColor()) {
						possibleMoves.add(new Field(x, y));
					}
				}
			}
		}
		return possibleMoves;
	}
}
