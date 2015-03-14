package chess.model.pieces;

import chess.model.Color;
import chess.model.Piece;

public class Rook extends Piece {

	public Rook(Color color) {
		super(color);
	}

	@Override
	protected String getWhiteUnicodeString() {
		return "\u2656";
	}

	@Override
	protected String getBlackUnicodeString() {
		return "\u265c";
	}

}
