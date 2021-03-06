package chess.model.pieces;

import java.util.ArrayList;
import java.util.List;

import chess.model.board.Board;
import chess.model.board.Color;
import chess.model.board.Field;

public abstract class Piece {

    private final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public final Color getColor() {
        return color;
    }

    public final Color getOppositeColor() {
        return color.getOppositeColor();
    }

    public abstract String getUnicodeString();

    protected final boolean isPositionInsideTheBoard(int x, int y) {
        return x >= 0 && x < Board.BOARD_SIZE && y >= 0 && y < Board.BOARD_SIZE;
    }

    public final List<Field> getAllLegalMoves(Board board, int startX, int startY) {
        List<Field> possibleMoves = getAllPotentialMoves(board, startX, startY);
        List<Field> legalMoves = new ArrayList<Field>(possibleMoves.size());
        for (Field move : possibleMoves) {
            if (isMoveSafeForKing(startX, startY, move, board)) {
                legalMoves.add(move);
            }
        }
        return legalMoves;
    }

    public final boolean isMoveSafeForKing(int startX, int startY, Field move, Board board) {
        Board boardCopy = new Board(board, Field.get(startX, startY), move);
        return isBoardSafeForKing(boardCopy);
    }

    public boolean isBoardSafeForKing(Board board) {
        Field kingField = board.getKingField(color);
        board.movePiece(kingField, kingField);

        List<Field> potentialAttackers = getPotentialAttackers(board, kingField);

        for (Field field : potentialAttackers) {
            Piece attacker = board.getPiece(field);
            if (attacker != null
                    && attacker.canAttackKing(field.getX(), field.getY(), kingField.getX(), kingField.getY())) {
                return false;
            }
        }
        return true;
    }

    private List<Field> getPotentialAttackers(Board board, Field field) {
        List<Field> potentialAttackers = new ArrayList<Field>();
        potentialAttackers
                .addAll(Queen.getInstance(getColor()).getAllPotentialMoves(board, field.getX(), field.getY()));
        potentialAttackers.addAll(Knight.getInstance(getColor())
                .getAllPotentialMoves(board, field.getX(), field.getY()));
        return potentialAttackers;
    }

    public abstract List<Field> getAllPotentialMoves(Board board, int startX, int startY);

    /*
     * This method assumes: 1. there's nothing between king and the piece 2. the
     * piece's and king's field are in straight line or in range of knight's
     * move
     */
    public abstract boolean canAttackKing(int startX, int startY, int kingX, int kingY);

    public Piece move(Board board, int x1, int y1, int x2, int y2) {
        return this;
    }

    public boolean canParticipateInCastling() {
        return false;
    }
}
