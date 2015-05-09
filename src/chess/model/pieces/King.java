package chess.model.pieces;

/**
 * Projekt: Szachy
 * Reprezentcaja króla
 * Micha³ Rapacz
 * 2015-03-26
 */

import java.util.ArrayList;
import java.util.List;

import chess.model.board.Board;
import chess.model.board.Color;
import chess.model.board.Field;

public class King extends Piece {

	public King(Color color, int x, int y) {
		super(color, x, y);
	}

	@Override
	public String getUnicodeString() {
		return "\u265a";
	}

	@Override
	public List<Field> getAllPotentialMoves(Board board,
			int startX, int startY) {
		List<Field> possibleMoves = new ArrayList<Field>(10);
		addNormalMoves(board, startX, startY, possibleMoves);
		addCastlingMoves(board, startX, startY, possibleMoves);
		return possibleMoves;
	}

	private void addNormalMoves(Board board, int startX, int startY,
			List<Field> possibleMoves) {
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				int x = startX + i;
				int y = startY + j;
				if((i != 0 || j != 0) && isPositionInsideTheBoard(x, y)) {
					Piece piece = board.getPiece(x, y);
					if(piece == null || piece.getColor() != getColor()) {
						possibleMoves.add(new Field(x, y));
					}
				}
			}
		}
	}
	
	private void addCastlingMoves(Board board, int startX, int startY,
			List<Field> possibleMoves) {
		if(canParticipateInCastling() == false || isUnderAttack(board)) {
			return;
		}
		
		if(isRookSideCastlingPossible(board, startX, startY)) {
			possibleMoves.add(new Field(startX + 2, startY));
		}
		
		if(isQueenSideCastlingPossible(board, startX, startY)) {
			possibleMoves.add(new Field(startX - 2, startY));
		}
	}

	public boolean isQueenSideCastlingPossible(Board board,
			int startX, int startY) {
		Piece rook = board.getPiece(startX - 4, startY);
		if(rook != null && canPassThroughField(board, startX - 1, startY) &&
				board.getPiece(startX - 2, startY) == null &&
				board.getPiece(startX - 3, startY) == null &&
				rook.canParticipateInCastling()) {
			return true;
		}
		return false;
	}
	
	private boolean isRookSideCastlingPossible(Board board,
			int startX, int startY) {
		Piece rook = board.getPiece(startX + 3, startY);
		if(rook != null && canPassThroughField(board, startX + 1, startY) &&
				board.getPiece(startX + 2, startY) == null &&
				rook.canParticipateInCastling()) {
			return true;
		}
		return false;
	}

	public boolean isUnderAttack(Board board) {
		return isMoveSafeForKing(getField(), board) == false;
	}
	
	private boolean canPassThroughField(Board board, int x, int y) {
		return board.getPiece(x, y) == null &&
				isMoveSafeForKing(new Field(x, y), board);
	}
	
	@Override
	public Piece move(Board board, int x, int y) {
		if(x == getX() + 2) {
			Piece rook = board.getPiece(getX() + 3, getY());
			board.movePiece(rook.getField(), getX() + 1, getY());
		}
		else if(x == getX() - 2) {
			Piece rook = board.getPiece(getX() - 4, getY());
			board.movePiece(rook.getField(), getX() - 1, getY());
		}
		return super.move(board, x, y);
	}
	
	@Override
	public Piece copy() {
		return new King(getColor(), getX(), getY())
				.allowedToPerformCastling(canParticipateInCastling());
	}	
}
