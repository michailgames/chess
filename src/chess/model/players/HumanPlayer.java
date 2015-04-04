package chess.model.players;

/**
 * Projekt: Szachy
 * Klasa reprezentuj¹ca gracza kontrolowanego przez cz³owieka
 * Micha³ Rapacz
 * 2015-03-26
 */

import chess.controller.GameController.IllegalMoveException;
import chess.model.board.Board;
import chess.model.board.Color;
import chess.model.board.Field;
import chess.model.pieces.Piece;

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

	@Override
	public void startCalculatingNextMove(Board board) { }
}
