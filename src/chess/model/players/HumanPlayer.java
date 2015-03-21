package chess.model.players;

import chess.controller.GameController.IllegalMoveException;
import chess.model.Board;
import chess.model.Color;
import chess.model.Field;
import chess.model.Piece;
import chess.model.Player;

public class HumanPlayer extends Player {
	
	private Piece selectedPiece = null;

	public HumanPlayer(Color color) {
		super(color);
	}

	@Override
	public void fieldClicked(Field field, Board board) {
		Piece piece = board.getPiece(field);
		if(piece != null && piece.getColor() == getColor()) {
			selectedPiece = piece;
		}
		else if(selectedPiece != null) {
			try {
				makeMove(selectedPiece.getField(), field);
			} catch(IllegalMoveException ex) {}
			finally {
				selectedPiece = null;
			}
		}
	}
	
	@Override
	public Piece getSelectedPiece() {
		return selectedPiece;
	}

}
