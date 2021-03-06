package chess.model.pieces;

import java.util.ArrayList;
import java.util.List;

import chess.model.board.Board;
import chess.model.board.Color;
import chess.model.board.Field;

public class King extends Piece {

    private final static King whiteKing = new King(Color.WHITE);

    private final static King blackKing = new King(Color.BLACK);

    public static King getInstance(Color color) {
        return color == Color.WHITE ? whiteKing : blackKing;
    }

    public King(Color color) {
        super(color);
    }

    @Override
    public String getUnicodeString() {
        return "\u265a";
    }

    @Override
    public List<Field> getAllPotentialMoves(Board board, int startX, int startY) {
        List<Field> possibleMoves = new ArrayList<Field>(10);
        addNormalMoves(board, startX, startY, possibleMoves);
        addCastlingMoves(board, startX, startY, possibleMoves);
        return possibleMoves;
    }

    @Override
    public boolean canAttackKing(int startX, int startY, int kingX, int kingY) {
        return Math.abs(kingX - startX) <= 1 && Math.abs(kingY - startY) <= 1;
    }

    private void addNormalMoves(Board board, int startX, int startY, List<Field> possibleMoves) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int x = startX + i;
                int y = startY + j;
                if ((i != 0 || j != 0) && isPositionInsideTheBoard(x, y)) {
                    Piece piece = board.getPiece(x, y);
                    if (piece == null || piece.getColor() != getColor()) {
                        possibleMoves.add(Field.get(x, y));
                    }
                }
            }
        }
    }

    private void addCastlingMoves(Board board, int startX, int startY, List<Field> possibleMoves) {
        if (canParticipateInCastling() == false || isUnderAttack(board, startX, startY)) {
            return;
        }

        if (isRookSideCastlingPossible(board, startX, startY)) {
            possibleMoves.add(Field.get(startX + 2, startY));
        }

        if (isQueenSideCastlingPossible(board, startX, startY)) {
            possibleMoves.add(Field.get(startX - 2, startY));
        }
    }

    public boolean isQueenSideCastlingPossible(Board board, int x, int y) {
        Piece rook = board.getPiece(x - 4, y);
        if (rook != null && canPassThroughField(board, x, y, x - 1, y) && board.getPiece(x - 2, y) == null
                && board.getPiece(x - 3, y) == null && rook.canParticipateInCastling()) {
            return true;
        }
        return false;
    }

    private boolean isRookSideCastlingPossible(Board board, int x, int y) {
        Piece rook = board.getPiece(x + 3, y);
        if (rook != null && canPassThroughField(board, x, y, x + 1, y) && board.getPiece(x + 2, y) == null
                && rook.canParticipateInCastling()) {
            return true;
        }
        return false;
    }

    public boolean isUnderAttack(Board board, int x, int y) {
        return isMoveSafeForKing(x, y, Field.get(x, y), board) == false;
    }

    private boolean canPassThroughField(Board board, int x1, int y1, int x2, int y2) {
        return board.getPiece(x2, y2) == null && isMoveSafeForKing(x1, y1, Field.get(x2, y2), board);
    }

    @Override
    public Piece move(Board board, int x1, int y1, int x2, int y2) {
        if (x2 == x1 + 2) {
            board.movePiece(x1 + 3, y1, x1 + 1, y1);
        } else if (x2 == x1 - 2) {
            board.movePiece(x1 - 4, y1, x1 - 1, y1);
        }
        return super.move(board, x1, y1, x2, y2);
    }
}
