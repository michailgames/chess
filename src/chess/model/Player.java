package chess.model;

import chess.controller.GameController;

public abstract class Player {
	
	private final Color color;

	public Player(Color color) {
		this.color = color;
	}

	public final Color getColor() {
		return color;
	}
	
	public final void makeMove(Piece selectedPiece, int x, int y) {
		GameController.getInstance().reportNewMove(selectedPiece, x, y);
	}
	
	public abstract void fieldClicked(int x, int y, Board board);
	public abstract Piece getSelectedPiece();
}
