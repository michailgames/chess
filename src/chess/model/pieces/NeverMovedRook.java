package chess.model.pieces;

import chess.model.board.Board;
import chess.model.board.Color;

public class NeverMovedRook extends Rook {

    private final static NeverMovedRook whiteRook = new NeverMovedRook(Color.WHITE);

    private final static NeverMovedRook blackRook = new NeverMovedRook(Color.BLACK);

    public static NeverMovedRook getInstance(Color color) {
        return color == Color.WHITE ? whiteRook : blackRook;
    }

    public NeverMovedRook(Color color) {
        super(color);
    }

    @Override
    public Piece move(Board board, int x1, int y1, int x2, int y2) {
        super.move(board, x1, y1, x2, y2);
        return Rook.getInstance(getColor());
    }

    @Override
    public boolean canParticipateInCastling() {
        return true;
    }
}
