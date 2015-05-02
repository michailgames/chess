package chess.model.pieces;

/**
 * Projekt: Szachy
 * Abstrakcyjna klasa reprezentuj¹ca figurê poruszaj¹c¹ siê po liniach prostych
 * Micha³ Rapacz
 * 2015-03-26
 */

import java.util.ArrayList;
import java.util.List;

import chess.model.board.Board;
import chess.model.board.Color;
import chess.model.board.Field;

public abstract class AbstractStraightMovingPiece extends Piece {

	public AbstractStraightMovingPiece(Color color, int x, int y) {
		super(color, x, y);
	}

	@Override
	public List<Field> getAllPotentialMoves(Board board) {
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
