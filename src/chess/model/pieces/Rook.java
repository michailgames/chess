package chess.model.pieces;

import chess.model.board.Color;

public class Rook extends AbstractStraightMovingPiece {

    private final static Rook whiteRook = new Rook(Color.WHITE);

    private final static Rook blackRook = new Rook(Color.BLACK);

    public static Rook getInstance(Color color) {
        return color == Color.WHITE ? whiteRook : blackRook;
    }

    private static final Direction[] availableDirections = { new Direction(-1, 0), new Direction(1, 0),
            new Direction(0, 1), new Direction(0, -1) };

    public Rook(Color color) {
        super(color);
    }

    @Override
    public String getUnicodeString() {
        return "\u265c";
    }

    @Override
    protected Direction[] getAvailableDirections() {
        return availableDirections;
    }
}
