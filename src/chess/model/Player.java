package chess.model;

import chess.controller.GameController;
import chess.controller.GameController.IllegalMoveException;

public abstract class Player {
	
	private final Color color;

	public Player(Color color) {
		this.color = color;
	}

	public final Color getColor() {
		return color;
	}
	
	public final void makeMove(Field sourceField, Field targetField)
			throws IllegalMoveException{
		GameController.getInstance().reportNewMove(this, sourceField,
				targetField);
	}
	
	public abstract void fieldClicked(Field field, Board board);
	public abstract Piece getSelectedPiece();
}
