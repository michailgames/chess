package chess.model.pieces;

import chess.model.Color;
import chess.model.Piece;

public class Bishop extends Piece {

	public Bishop(Color color) {
		super(color);
	}

	@Override
	protected String getWhiteUnicodeString() {
		return "\u2657";
	}

	@Override
	protected String getBlackUnicodeString() {
		return "\u265d";
	}

}
