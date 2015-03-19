package chess.model.pieces;

import java.util.ArrayList;
import java.util.List;

import chess.model.Board;
import chess.model.Color;
import chess.model.Field;
import chess.model.Piece;

public abstract class AbstractStraightMovingPiece extends Piece {

	public AbstractStraightMovingPiece(Color color, int x, int y) {
		super(color, x, y);
	}

	@Override
	protected List<Field> getAllPossibleMoves(Board board) {
		List<Field> movesList = new ArrayList<Field>();
		for(Direction direction : getAvailableDirections()) {
			boolean canMoveFurther = true;
			int i = 1;
			while(canMoveFurther) {
				int x = getX() + i * direction.getX();
				int y = getY() + i * direction.getY();
				if(!isPositionInsideTheBoard(x, y)) {
					canMoveFurther = false;
				} else {
					Piece piece = board.getPiece(x, y);
					if(piece == null) {
						movesList.add(new Field(x, y));
					} else if(piece.getColor() == getColor()) {
						canMoveFurther = false;
					} else {
						movesList.add(new Field(x, y));
						canMoveFurther = false;
					}
				}
				i++;
			}
		}
		return movesList;
	}
	
	protected abstract Direction[] getAvailableDirections();
	
	public static class Direction {
		private int x;
		private int y;
		
		public Direction(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
	}

}
