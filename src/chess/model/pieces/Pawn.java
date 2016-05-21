package chess.model.pieces;

import java.util.ArrayList;
import java.util.List;

import chess.model.board.Board;
import chess.model.board.Color;
import chess.model.board.Field;

public class Pawn extends Piece {

    private final static Pawn whitePawn = new Pawn(Color.WHITE);

    private final static Pawn blackPawn = new Pawn(Color.BLACK);

    public static Pawn getInstance(Color color) {
        return color == Color.WHITE ? whitePawn : blackPawn;
    }

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public String getUnicodeString() {
        return "\u265f";
    }

    @Override
    public List<Field> getAllPotentialMoves(Board board, int startX, int startY) {
        List<Field> movesList = new ArrayList<Field>(4);
        int direction = (getColor() == Color.WHITE) ? -1 : 1;
        addStraightMoves(board, movesList, startX, startY, direction);
        addDiagonalMoves(board, movesList, startX, startY, direction);
        addEnPassantMoves(board, movesList, startX, startY, direction);
        return movesList;
    }

    @Override
    public boolean canAttackKing(int startX, int startY, int kingX, int kingY) {
        int direction = (getColor() == Color.WHITE) ? -1 : 1;
        if (startY + direction == kingY) {
            if (startX + 1 == kingX || startX - 1 == kingX) {
                return true;
            }
        }
        return false;
    }

    private void addEnPassantMoves(Board board, List<Field> movesList, int startX, int startY, int direction) {
        for (int i = -1; i <= 1; i += 2) {
            if (isPositionInsideTheBoard(startX + i, startY) == false) {
                continue;
            }
            Piece piece = board.getPiece(startX + i, startY);
            if (piece instanceof Pawn) {
                if (board.canPawnBeTakenEnPassant(Field.get(startX + i, startY))) {
                    movesList.add(Field.get(startX + i, startY + direction));
                }
            }
        }
    }

    private void addDiagonalMoves(Board board, List<Field> movesList, int startX, int startY, int direction) {
        for (int i = -1; i <= 1; i += 2) {
            if (isPositionInsideTheBoard(startX + i, startY + direction)) {
                Piece piece = board.getPiece(startX + i, startY + direction);
                if (piece != null && piece.getColor() != getColor()) {
                    movesList.add(Field.get(startX + i, startY + direction));
                }
            }
        }
    }

    private void addStraightMoves(Board board, List<Field> movesList, int startX, int startY, int direction) {
        if (board.getPiece(startX, startY + direction) == null) {
            movesList.add(Field.get(startX, startY + direction));
            boolean isAtStartPosition = (getColor() == Color.WHITE && startY == 6)
                    || (getColor() == Color.BLACK && startY == 1);
            if (isAtStartPosition && board.getPiece(startX, startY + 2 * direction) == null) {
                movesList.add(Field.get(startX, startY + 2 * direction));
            }
        }
    }

    @Override
    public Piece move(Board board, int x1, int y1, int x2, int y2) {
        if ((getColor() == Color.WHITE && y2 == 0) || (getColor() == Color.BLACK && y2 == Board.BOARD_SIZE - 1)) {
            return Queen.getInstance(getColor());
        }
        if (x1 != x2 && board.getPiece(x2, y2) == null) {
            board.clearField(x2, y1);
        }
        return super.move(board, x1, y1, x2, y2);
    }
}
