package chess.model.pieces;

import java.util.ArrayList;
import java.util.List;

import chess.model.Board;
import chess.model.Color;
import chess.model.Field;
import chess.model.Piece;

public class King extends Piece {

	public King(Color color, int x, int y) {
		super(color, x, y);
	}

	@Override
	public String getUnicodeString() {
		return "\u265a";
	}

	@Override
	protected List<Field> getAllPossibleMoves(Board board) {
		List<Field> possibleMoves = new ArrayList<Field>(10);
		addNormalMoves(board, possibleMoves);
		addCastlingMoves(board, possibleMoves);
		return possibleMoves;
	}

	private void addNormalMoves(Board board, List<Field> possibleMoves) {
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				int x = getX() + i;
				int y = getY() + j;
				if((i != 0 || j != 0) && isPositionInsideTheBoard(x, y)) {
					Piece piece = board.getPiece(x, y);
					if(piece == null || piece.getColor() != getColor()) {
						possibleMoves.add(new Field(x, y));
					}
				}
			}
		}
	}
	
	private void addCastlingMoves(Board board, List<Field> possibleMoves) {
		if(canParticipateInCastling() == false) {
			return;
		}
		if(board.getPiece(getX() + 1, getY()) == null &&
				board.getPiece(getX() + 2, getY()) == null) {
			Piece rook = board.getPiece(getX() + 3, getY());
			if(rook != null && rook.canParticipateInCastling()) {
				possibleMoves.add(new Field(getX() + 2, getY()));
			}
		}
		if(board.getPiece(getX() - 1, getY()) == null &&
				board.getPiece(getX() - 2, getY()) == null &&
				board.getPiece(getX() - 3, getY()) == null) {
			Piece rook = board.getPiece(getX() - 4, getY());
			if(rook != null && rook.canParticipateInCastling()) {
				possibleMoves.add(new Field(getX() - 2, getY()));
			}
		}
	}
	
	@Override
	public Piece move(Board board, int x, int y) {
		if(x == getX() + 2) {
			Piece rook = board.getPiece(getX() + 3, getY());
			board.movePiece(rook, getX() + 1, getY());
		}
		else if(x == getX() - 2) {
			Piece rook = board.getPiece(getX() - 4, getY());
			board.movePiece(rook, getX() - 1, getY());
		}
		return super.move(board, x, y);
	}
	
	@Override
	public Piece copy() {
		return new King(getColor(), getX(), getY())
				.allowedToPerformCastling(canParticipateInCastling());
	}	
}
