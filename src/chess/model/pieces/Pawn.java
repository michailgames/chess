package chess.model.pieces;

/**
 * Projekt: Szachy
 * Reprezentacja pionka
 * Micha³ Rapacz
 * 2015-03-26
 */

import java.util.ArrayList;
import java.util.List;

import chess.model.Board;
import chess.model.Color;
import chess.model.Field;
import chess.model.Piece;

public class Pawn extends Piece {

	public Pawn(Color color, int x, int y) {
		super(color, x, y);
	}

	@Override
	public String getUnicodeString() {
		return "\u265f";
	}

	@Override
	protected List<Field> getAllPossibleMoves(Board board) {
		List<Field> movesList = new ArrayList<Field>(4);
		int direction = (getColor() == Color.WHITE) ? -1 : 1;
		addStraightMoves(board, movesList, direction);
		addDiagonalMoves(board, movesList, direction);
		addEnPassantMoves(board, movesList, direction);
		return movesList;
	}

	private void addEnPassantMoves(Board board, List<Field> movesList,
			int direction) {
		for(int i = -1; i <= 1; i += 2) {
			if(isPositionInsideTheBoard(getX() + i, getY()) == false) {
				continue;
			}
			Piece piece = board.getPiece(getX() + i, getY());
			if(piece instanceof Pawn) {
				if(((Pawn) piece).canBeTakenEnPassant(board)) {
					movesList.add(new Field(getX() + i, getY() + direction));
				}
			}
		}
	}
	
	private boolean canBeTakenEnPassant(Board board) {
		return board.canPawnBeTakenEnPassant(this);
	}

	private void addDiagonalMoves(Board board, List<Field> movesList,
			int direction) {
		for(int i = -1; i <= 1; i += 2) {
			if(isPositionInsideTheBoard(getX() + i, getY() + direction)) {
				Piece piece = board.getPiece(getX() + i, getY() + direction);
				if(piece != null && piece.getColor() != getColor()) {
					movesList.add(new Field(getX() + i, getY() + direction));
				}
			}
		}
	}

	private void addStraightMoves(Board board, List<Field> movesList,
			int direction) {
		if(board.getPiece(getX(), getY() + direction) == null) {
			movesList.add(new Field(getX(), getY() + direction));
			boolean isAtStartPosition =
				(getColor() == Color.WHITE && getY() == 6) ||
				(getColor() == Color.BLACK && getY() == 1);
			if(isAtStartPosition &&
					board.getPiece(getX(), getY() +  2 * direction) == null) {
				movesList.add(new Field(getX(), getY() + 2 * direction));
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
			makeEnPassantMagic(board, x, y);
		}
		return super.move(board, x, y);
	}

	private void makeEnPassantMagic(Board board, int x, int y) {
		Pawn pawn = (Pawn) board.getPiece(x, getY());
		board.movePiece(pawn, x, y);
	}
}
