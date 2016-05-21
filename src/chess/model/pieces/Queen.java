package chess.model.pieces;

import chess.model.board.Color;

public class Queen extends AbstractStraightMovingPiece {

    private final static Queen whiteQueen = new Queen(Color.WHITE);

    private final static Queen blackQueen = new Queen(Color.BLACK);

    public static Queen getInstance(Color color) {
        return color == Color.WHITE ? whiteQueen : blackQueen;
    }

    private static final Direction[] availableDirections = { new Direction(-1, -1), new Direction(-1, 0),
            new Direction(-1, 1), new Direction(0, -1), new Direction(0, 1), new Direction(1, -1), new Direction(1, 0),
            new Direction(1, 1) };

    public Queen(Color color) {
        super(color);
    }

    @Override
    public String getUnicodeString() {
        return "\u265b";
    }

    @Override
    protected Direction[] getAvailableDirections() {
        return availableDirections;
    }
}
