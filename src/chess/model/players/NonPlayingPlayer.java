package chess.model.players;

import chess.model.Board;
import chess.model.Color;
import chess.model.Piece;
import chess.model.Player;

public class NonPlayingPlayer extends Player {

	public NonPlayingPlayer(Color color) {
		super(color);
	}

	@Override
	public void fieldClicked(int x, int y, Board board) { }

	@Override
	public Piece getSelectedPiece() {
		return null;
	}

}
