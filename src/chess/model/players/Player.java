package chess.model.players;

import chess.controller.GameController.UnauthorizedMoveException;
import chess.model.board.Board;
import chess.model.board.Color;
import chess.model.board.Field;
import chess.model.listeners.IMoveListener;

public abstract class Player {

    private final Color color;

    private IMoveListener moveListener;

    public Player(Color color) {
        this.color = color;
    }

    public final Color getColor() {
        return color;
    }

    protected synchronized final void makeMove(Field sourceField, Field targetField) {
        try {
            moveListener.reportNewMove(this, sourceField, targetField);
        } catch (UnauthorizedMoveException ex) {}
        ;
    }

    public void registerMoveListener(IMoveListener listener) {
        this.moveListener = listener;
    }

    public abstract void startCalculatingNextMove(Board board);

    public abstract void fieldClicked(Field field, Board board);

    public abstract Field getSelectedField();

    public abstract void interrupt();
}
