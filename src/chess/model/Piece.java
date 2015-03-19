package chess.model;

import java.util.List;

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
	
	public String getUnicodeString() {
		if(color == Color.WHITE) {
			return getWhiteUnicodeString();
		} else {
			return getBlackUnicodeString();
		}
	}
	
	public final int getX() {
		return x;
	}
	
	public final int getY() {
		return y;
	}
	
	protected boolean isPositionInsideTheBoard(int x, int y) {
		return x >= 0 && x < Board.BOARD_SIZE && y >= 0 && y < Board.BOARD_SIZE;
	}
	
	public final List<Field> getAllLegalMoves(Board board) {
		return getAllPossibleMoves(board);
	}
	
	protected abstract List<Field> getAllPossibleMoves(Board board);
	
	protected abstract String getWhiteUnicodeString();
	
	protected abstract String getBlackUnicodeString();

	public final Piece move(int x, int y) {
		this.x = x;
		this.y = y;
		return this;
	}

}
