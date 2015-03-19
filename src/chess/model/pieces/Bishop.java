package chess.model.pieces;

import java.util.ArrayList;
import java.util.List;

import chess.model.Board;
import chess.model.Color;
import chess.model.Field;
import chess.model.Piece;

public class Bishop extends Piece {

	public Bishop(Color color, int x, int y) {
		super(color, x, y);
	}

	@Override
	protected String getWhiteUnicodeString() {
		return "\u2657";
	}

	@Override
	protected String getBlackUnicodeString() {
		return "\u265d";
	}

	@Override
	protected List<Field> getAllPossibleMoves(Board board) {
		return new ArrayList<Field>();
	}
}
