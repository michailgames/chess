package chess.model.players;

import chess.model.board.Board;
import chess.model.board.Color;
import chess.model.board.Field;

public class NonPlayingPlayer extends Player {

    public NonPlayingPlayer(Color color) {
        super(color);
    }

    @Override
    public void fieldClicked(Field field, Board board) {}

    @Override
    public Field getSelectedField() {
        return null;
    }

    @Override
    public void interrupt() {}

    @Override
    public void startCalculatingNextMove(Board board) {}

}
