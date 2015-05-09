package chess.model.pieces;

/**
 * Projekt: Szachy
 * Abstrakcyjna klasa bazowa dla wszystkich figur szachowych
 * Micha³ Rapacz
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
	
	public final List<Field> getAllLegalMoves(Board board, int startX,
			int startY) {
		List<Field> possibleMoves = getAllPotentialMoves(board, startX, startY);
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

	public boolean isBoardSafeForKing(Board board) {
		Field kingField = board.getKingField(color);
		board.movePiece(kingField, kingField);
		
		List<Field> potentialAttackers = getPotentialAttackers(board, kingField);
		
		for(Field field : potentialAttackers) {
			Piece attacker = board.getPiece(field);
			if(attacker != null && attacker.getAllPotentialMoves(board,
					field.getX(), field.getY()).contains(kingField)) {
				return false;
			}
		}
		return true;
	}

	private List<Field> getPotentialAttackers(Board board, Field field) {
		List<Field> potentialAttackers = new ArrayList<Field>();
		potentialAttackers.addAll(new Queen(getColor(), field.getX(),
				field.getY()).getAllPotentialMoves(board, field.getX(),
						field.getY()));
		potentialAttackers.addAll(new Knight(getColor(), field.getX(),
				field.getY()).getAllPotentialMoves(board, field.getX(),
						field.getY()));
		return potentialAttackers;
	}

	public abstract List<Field> getAllPotentialMoves(Board board,
			int startX, int startY);

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
