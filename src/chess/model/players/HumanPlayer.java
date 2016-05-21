package chess.model.players;

import chess.controller.GameController.IllegalMoveException;
import chess.model.board.Board;
import chess.model.board.Color;
import chess.model.board.Field;
import chess.model.pieces.Piece;

public class HumanPlayer extends Player {

    private Field selectedField = null;

    public HumanPlayer(Color color) {
        super(color);
    }

    @Override
    public synchronized void fieldClicked(Field field, Board board) {
        Piece piece = board.getPiece(field);
        if (piece != null && piece.getColor() == getColor()) {
            selectedField = field;
        } else if (selectedField != null) {
            try {
                makeMove(selectedField, field);
            } catch (IllegalMoveException ex) {} finally {
                selectedField = null;
            }
        }
    }

    @Override
    public Field getSelectedField() {
        return selectedField;
    }

    @Override
    public synchronized void interrupt() {
        selectedField = null;
    }

    @Override
    public void startCalculatingNextMove(Board board) {}
}
