package chess.model.pieces;

import java.util.ArrayList;
import java.util.List;

import chess.model.Board;
import chess.model.Color;
import chess.model.Field;
import chess.model.Piece;

public class Rook extends Piece {

	public Rook(Color color, int x, int y) {
		super(color, x, y);
	}

	@Override
	protected String getWhiteUnicodeString() {
		return "\u2656";
	}

	@Override
	protected String getBlackUnicodeString() {
		return "\u265c";
	}

	@Override
	protected List<Field> getAllPossibleMoves(Board board) {
		return new ArrayList<Field>();
	}
}
