package chess.model.pieces;

import java.util.ArrayList;
import java.util.List;

import chess.model.Board;
import chess.model.Color;
import chess.model.Field;
import chess.model.Piece;

public class Knight extends Piece {

	public Knight(Color color, int x, int y) {
		super(color, x, y);
	}

	@Override
	protected String getWhiteUnicodeString() {
		return "\u2658";
	}

	@Override
	protected String getBlackUnicodeString() {
		return "\u265e";
	}
	
	@Override
	protected List<Field> getAllPossibleMoves(Board board) {
		return new ArrayList<Field>();
	}
}
