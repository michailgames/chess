package chess.model.players;

/**
 * Projekt: Szachy
 * Reprezentacja gracza przed rozpoczêciem gry
 * Micha³ Rapacz
 * 2015-03-26
 */

import chess.model.board.Board;
import chess.model.board.Color;
import chess.model.board.Field;
import chess.model.pieces.Piece;

public class NonPlayingPlayer extends Player {

	public NonPlayingPlayer(Color color) {
		super(color);
	}

	@Override
	public void fieldClicked(Field field, Board board) { }

	@Override
	public Piece getSelectedPiece() {
		return null;
	}

	@Override
	public void startCalculatingNextMove(Board board) { }

}
