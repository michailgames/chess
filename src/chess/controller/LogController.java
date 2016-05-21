package chess.controller;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import chess.model.board.Board;
import chess.model.board.Field;
import chess.model.pieces.Piece;

public class LogController {

    private static LogController instance;

    private final String[] rankNames = { "a", "b", "c", "d", "e", "f", "g", "h" };

    private List<String> loggedMoves = new ArrayList<String>();

    private Deque<Board> previousBoardStates = new ArrayDeque<Board>();

    private LogController() {}

    public synchronized static LogController getInstance() {
        if (instance == null) {
            instance = new LogController();
        }
        return instance;
    }

    public synchronized void clearLogs() {
        loggedMoves.clear();
        previousBoardStates.clear();
    }

    public synchronized void reportMove(Board board, Field sourceField, Field targetField) {
        Piece piece = board.getPiece(sourceField);
        previousBoardStates.add(new Board(board));
        loggedMoves.add(piece.getUnicodeString() + " " + getFieldIdentifier(sourceField) + "\u2192"
                + getFieldIdentifier(targetField));
    }

    public synchronized int getLogsNumber() {
        return loggedMoves.size();
    }

    public synchronized String getLog(int moveIndex) {
        if (moveIndex < loggedMoves.size()) {
            return loggedMoves.get(moveIndex);
        }
        return "";
    }

    public synchronized Board getPreviousBoardState() {
        return previousBoardStates.getLast();
    }

    public synchronized void reportUndo() {
        if (loggedMoves.size() > 0) {
            loggedMoves.remove(loggedMoves.size() - 1);
            previousBoardStates.removeLast();
        }
    }

    private String getFieldIdentifier(Field field) {
        return rankNames[field.getX()] + Integer.toString(8 - field.getY());
    }
}
