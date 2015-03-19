package chess.model.players;

import chess.controller.GameController;
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
	public void fieldClicked(int x, int y, Board board) {
		Piece piece = board.getPiece(x, y);
		if(selectedPiece != null && selectedPiece.getAllLegalMoves(board)
				.contains(new Field(x, y))) {
			makeMove(selectedPiece, x, y);
			selectedPiece = null;
		} else if(piece != null && piece.getColor() == getColor()) {
			selectedPiece = piece; 
		} else {
			selectedPiece = null;
		}
	}
	
	@Override
	public Piece getSelectedPiece() {
		return selectedPiece;
	}

}
