package chess.model.pieces;

import chess.model.Color;
import chess.model.Piece;

public class Knight extends Piece {

	public Knight(Color color) {
		super(color);
	}

	@Override
	protected String getWhiteUnicodeString() {
		return "\u2658";
	}

	@Override
	protected String getBlackUnicodeString() {
		return "\u265e";
	}

}
