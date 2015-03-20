package chess.model.players;

import chess.controller.GameController.IllegalMoveException;
import chess.model.Board;
import chess.model.Color;
import chess.model.Piece;
import chess.model.Player;

public class HumanPlayer extends Player {
	
	private Piece selectedPiece = null;

	public HumanPlayer(Color color) {
		super(color);
	}

	@Override
	public void fieldClicked(int x, int y, Board board) {
		Piece piece = board.getPiece(x, y);
		if(piece != null && piece.getColor() == getColor()) {
			selectedPiece = piece;
			return;
		}
		try {
			makeMove(selectedPiece, x, y);
		} catch(IllegalMoveException ex) {}
		finally {
			selectedPiece = null;
		}
	}
	
	@Override
	public Piece getSelectedPiece() {
		return selectedPiece;
	}

}
