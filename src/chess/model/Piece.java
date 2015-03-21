package chess.model;

import java.util.ArrayList;
import java.util.List;

import chess.model.pieces.King;

public abstract class Piece {
	
	private final Color color;
	private int x;
	private int y;
	
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
		List<Field> possibleMoves = getAllPossibleMoves(board);
		List<Field> legalMoves = new ArrayList<Field>(possibleMoves.size());
		for(Field move : possibleMoves) {
			if(moveIsSafeForKing(move, board)) {
				legalMoves.add(move);
			}
		}
		return legalMoves;
	}
	
	private boolean moveIsSafeForKing(Field move, Board board) {
		Board boardCopy = new Board(board, new Field(getX(), getY()), move);
		King king = boardCopy.getKing(getColor());
		Field kingField = new Field(king.getX(), king.getY());
		
		for(Piece piece : boardCopy.getAllPieces(getOppositeColor())) {
			if(piece.getAllPossibleMoves(boardCopy)
					.contains(kingField)) {
				return false;
			}
		}
		return true;
	}

	protected abstract List<Field> getAllPossibleMoves(Board board);

	public Piece move(int x, int y) {
		this.x = x;
		this.y = y;
		return this;
	}

	public abstract Piece copy();
}
