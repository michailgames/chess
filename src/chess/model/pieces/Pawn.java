package chess.model.pieces;

/**
 * Projekt: Szachy
 * Reprezentacja pionka
 * Micha³ Rapacz
 * 2015-03-26
 */

import java.util.ArrayList;
import java.util.List;

import chess.model.board.Board;
import chess.model.board.Color;
import chess.model.board.Field;

public class Pawn extends Piece {

	public Pawn(Color color, int x, int y) {
		super(color, x, y);
	}

	@Override
	public String getUnicodeString() {
		return "\u265f";
	}

	@Override
	public List<Field> getAllPotentialMoves(Board board,
			int startX, int startY) {
		List<Field> movesList = new ArrayList<Field>(4);
		int direction = (getColor() == Color.WHITE) ? -1 : 1;
		addStraightMoves(board, movesList, startX, startY, direction);
		addDiagonalMoves(board, movesList, startX, startY, direction);
		addEnPassantMoves(board, movesList, startX,startY, direction);
		return movesList;
	}

	private void addEnPassantMoves(Board board, List<Field> movesList,
			int startX, int startY, int direction) {
		for(int i = -1; i <= 1; i += 2) {
			if(isPositionInsideTheBoard(startX + i, startY) == false) {
				continue;
			}
			Piece piece = board.getPiece(startX + i, startY);
			if(piece instanceof Pawn) {
				if(board.canPawnBeTakenEnPassant(new Field(startX + i, startY))) {
					movesList.add(new Field(startX + i, startY + direction));
				}
			}
		}
	}

	private void addDiagonalMoves(Board board, List<Field> movesList,
			int startX, int startY, int direction) {
		for(int i = -1; i <= 1; i += 2) {
			if(isPositionInsideTheBoard(startX + i, startY + direction)) {
				Piece piece = board.getPiece(startX + i, startY + direction);
				if(piece != null && piece.getColor() != getColor()) {
					movesList.add(new Field(startX + i, startY + direction));
				}
			}
		}
	}

	private void addStraightMoves(Board board, List<Field> movesList,
			int startX, int startY, int direction) {
		if(board.getPiece(startX, startY + direction) == null) {
			movesList.add(new Field(startX, startY + direction));
			boolean isAtStartPosition =
				(getColor() == Color.WHITE && startY == 6) ||
				(getColor() == Color.BLACK && startY == 1);
			if(isAtStartPosition &&
					board.getPiece(startX, startY +  2 * direction) == null) {
				movesList.add(new Field(startX, startY + 2 * direction));
			}
		}
	}
	
	@Override
	public Piece copy() {
		return new Pawn(getColor(), getX(), getY())
				.allowedToPerformCastling(canParticipateInCastling());
	}
	
	@Override
	public Piece move(Board board, int x, int y) {
		if((getColor() == Color.WHITE && y == 0) ||
				(getColor() == Color.BLACK && y == Board.BOARD_SIZE - 1)) {
			return new Queen(getColor(), x, y);
		}
		if(getX() != x && board.getPiece(x, y) == null) {
			board.clearField(x, getY());
		}
		return super.move(board, x, y);
	}
}
