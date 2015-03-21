package chess.model.players;

import chess.model.Board;
import chess.model.Color;
import chess.model.Field;
import chess.model.Piece;
import chess.model.Player;

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

}
