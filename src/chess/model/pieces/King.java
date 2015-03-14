package chess.model.pieces;

import chess.model.Color;
import chess.model.Piece;

public class King extends Piece {

	public King(Color color) {
		super(color);
	}

	@Override
	protected String getWhiteUnicodeString() {
		return "\u2654";
	}

	@Override
	protected String getBlackUnicodeString() {
		return "\u265a";
	}

}
