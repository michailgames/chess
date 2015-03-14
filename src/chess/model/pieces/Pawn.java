package chess.model.pieces;

import chess.model.Color;
import chess.model.Piece;

public class Pawn extends Piece {
	
	public Pawn(Color color) {
		super(color);
	}

	@Override
	protected String getWhiteUnicodeString() {
		return "\u2659";
	}

	@Override
	protected String getBlackUnicodeString() {
		return "\u265f";
	}

}
