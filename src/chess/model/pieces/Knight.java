package chess.model.pieces;

/**
 * Projekt: Szachy
 * Reprezentacja skoczka
 * Micha� Rapacz
 * 2015-03-26
 */

import java.util.ArrayList;
import java.util.List;

import chess.model.board.Board;
import chess.model.board.Color;
import chess.model.board.Field;

public class Knight extends Piece {
	
	private final static Knight whiteKnight = new Knight(Color.WHITE);
	private final static Knight blackKnight = new Knight(Color.BLACK);
	
	public static Knight getInstance(Color color) {
		return color == Color.WHITE ? whiteKnight : blackKnight;
	}
	
	private final static Field[] moveOptions = {
		new Field(-2, -1),
		new Field(-1, -2),
		new Field(1, 2),
		new Field(2, 1),
		new Field(-1, 2),
		new Field(-2, 1),
		new Field(1, -2),
		new Field(2, -1)
	};

	public Knight(Color color) {
		super(color);
	}

	@Override
	public String getUnicodeString() {
		return "\u265e";
	}
	
	@Override
	public List<Field> getAllPotentialMoves(Board board,
			int startX, int startY) {
		List<Field> movesList = new ArrayList<Field>();
		for(Field move : moveOptions) {
			int x = startX + move.getX();
			int y = startY + move.getY();
			if(isPositionInsideTheBoard(x, y)) {
				Piece piece = board.getPiece(x, y);
				if(piece == null || piece.getColor() != getColor()) {
					movesList.add(new Field(x, y));
				}
			}
		}
		return movesList;
	}
}
