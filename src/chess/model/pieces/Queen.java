package chess.model.pieces;

import chess.model.Color;
import chess.model.Piece;

public class Queen extends Piece {

	public Queen(Color color) {
		super(color);
	}

	@Override
	protected String getWhiteUnicodeString() {
		return "\u2655";
	}

	@Override
	protected String getBlackUnicodeString() {
		return "\u265b";
	}

}
