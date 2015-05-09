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
	
	public Piece(Color color) {
		this.color = color;
	}
	
	public final Color getColor() {
		return color;
	}
	
	public final Color getOppositeColor() {
		return color.getOppositeColor();
	}
	
	public abstract String getUnicodeString();
	
	protected final boolean isPositionInsideTheBoard(int x, int y) {
		return x >= 0 && x < Board.BOARD_SIZE && y >= 0 && y < Board.BOARD_SIZE;
	}
	
	public final List<Field> getAllLegalMoves(Board board, int startX,
			int startY) {
		List<Field> possibleMoves = getAllPotentialMoves(board, startX, startY);
		List<Field> legalMoves = new ArrayList<Field>(possibleMoves.size());
		for(Field move : possibleMoves) {
			if(isMoveSafeForKing(startX, startY, move, board)) {
				legalMoves.add(move);
			}
		}
		return legalMoves;
	}
	
	public final boolean isMoveSafeForKing(int startX, int startY,
			Field move, Board board) {
		Board boardCopy = new Board(board, new Field(startX, startY), move);
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
		potentialAttackers.addAll(Queen.getInstance(getColor())
				.getAllPotentialMoves(board, field.getX(), field.getY()));
		potentialAttackers.addAll(Knight.getInstance(getColor())
				.getAllPotentialMoves(board, field.getX(),field.getY()));
		return potentialAttackers;
	}

	public abstract List<Field> getAllPotentialMoves(Board board,
			int startX, int startY);

	public Piece move(Board board, int x1, int y1, int x2, int y2) {
		return this;
	}
	
	public boolean canParticipateInCastling() {
		return false;
	}
}
