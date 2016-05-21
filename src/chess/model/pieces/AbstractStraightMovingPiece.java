package chess.model.pieces;

import java.util.ArrayList;
import java.util.List;

import chess.model.board.Board;
import chess.model.board.Color;
import chess.model.board.Field;

public abstract class AbstractStraightMovingPiece extends Piece {

    public AbstractStraightMovingPiece(Color color) {
        super(color);
    }

    @Override
    public List<Field> getAllPotentialMoves(Board board, int startX, int startY) {
        List<Field> movesList = new ArrayList<Field>();
        for (Direction direction : getAvailableDirections()) {
            boolean canMoveFurther = true;
            int i = 1;
            while (canMoveFurther) {
                int x = startX + i * direction.getX();
                int y = startY + i * direction.getY();
                if (!isPositionInsideTheBoard(x, y)) {
                    canMoveFurther = false;
                } else {
                    Piece piece = board.getPiece(x, y);
                    if (piece == null) {
                        movesList.add(Field.get(x, y));
                    } else if (piece.getColor() == getColor()) {
                        canMoveFurther = false;
                    } else {
                        movesList.add(Field.get(x, y));
                        canMoveFurther = false;
                    }
                }
                i++;
            }
        }
        return movesList;
    }

    @Override
    public boolean canAttackKing(int startX, int startY, int kingX, int kingY) {
        int xDiff = kingX - startX;
        int yDiff = kingY - startY;
        if (!isStraightLine(xDiff, yDiff)) {
            return false;
        }
        for (Direction direction : getAvailableDirections()) {
            if (direction.matches(xDiff, yDiff)) {
                return true;
            }
        }
        return false;
    }

    private boolean isStraightLine(int xDiff, int yDiff) {
        return Math.abs(xDiff) == Math.abs(yDiff) || xDiff * yDiff == 0;
    }

    protected abstract Direction[] getAvailableDirections();

    public static class Direction {

        private int x;

        private int y;

        public Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public boolean matches(int xDiff, int yDiff) {
            return Math.signum(xDiff) == Math.signum(x) && Math.signum(yDiff) == Math.signum(y);
        }
    }

}
