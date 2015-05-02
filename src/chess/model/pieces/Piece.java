package chess.model.pieces;

/**
 * Projekt: Szachy
 * Abstrakcyjna klasa bazowa dla wszystkich figur szachowych
 * Micha� Rapacz
 * 2015-03-26
 */

import java.util.ArrayList;
import java.util.List;

import chess.model.board.Board;
import chess.model.board.Color;
import chess.model.board.Field;

public abstract class Piece {
	
	private final Color color;
	private int x;
	private int y;
	private boolean canPerformCastling = false;
	
	public Piece(Color color, int x, int y) {
		this.color = color;
		this.x = x;
		this.y = y;
	}
	
	public final Color getColor() {
		return color;
	}
	
	public final Color getOppositeColor() {
		return color.getOppositeColor();
	}
	
	public abstract String getUnicodeString();
	
	public final int getX() {
		return x;
	}
	
	public final int getY() {
		return y;
	}
	
	public final Field getField() {
		return new Field(x, y);
	}
	
	protected final boolean isPositionInsideTheBoard(int x, int y) {
		return x >= 0 && x < Board.BOARD_SIZE && y >= 0 && y < Board.BOARD_SIZE;
	}
	
	public final List<Field> getAllLegalMoves(Board board) {
		List<Field> possibleMoves = getAllPotentialMoves(board);
		List<Field> legalMoves = new ArrayList<Field>(possibleMoves.size());
		for(Field move : possibleMoves) {
			if(isMoveSafeForKing(move, board)) {
				legalMoves.add(move);
			}
		}
		return legalMoves;
	}
	
	public final boolean isMoveSafeForKing(Field move, Board board) {
		Board boardCopy = new Board(board, new Field(getX(), getY()), move);
		return isBoardSafeForKing(boardCopy);
	}

	private boolean isBoardSafeForKing(Board board) {
		King king = board.getKing(getColor());
		if(king == null) {
			return false;
		}
		Field kingField = new Field(king.getX(), king.getY());
		
		for(Piece piece : board.getAllPieces(getOppositeColor())) {
			if(piece.getAllPotentialMoves(board)
					.contains(kingField)) {
				return false;
			}
		}
		return true;
	}

	public abstract List<Field> getAllPotentialMoves(Board board);

	public Piece move(Board board, int x, int y) {
		canPerformCastling = false;
		this.x = x;
		this.y = y;
		return this;
	}
	
	public final boolean canParticipateInCastling() {
		return canPerformCastling;
	}
	
	public final Piece allowedToPerformCastling() {
		canPerformCastling  = true;
		return this;
	}
	
	protected final Piece allowedToPerformCastling(boolean allowed) {
		if(allowed) {
			return allowedToPerformCastling();
		}
		return this;
	}

	public abstract Piece copy();
}
