package chess.model.pieces;

import chess.model.board.Board;
import chess.model.board.Color;

public class NeverMovedKing extends King {

    private final static NeverMovedKing whiteKing = new NeverMovedKing(Color.WHITE);

    private final static NeverMovedKing blackKing = new NeverMovedKing(Color.BLACK);

    public static King getInstance(Color color) {
        return color == Color.WHITE ? whiteKing : blackKing;
    }

    public NeverMovedKing(Color color) {
        super(color);
    }

    @Override
    public Piece move(Board board, int x1, int y1, int x2, int y2) {
        super.move(board, x1, y1, x2, y2);
        return King.getInstance(getColor());
    }

    @Override
    public boolean canParticipateInCastling() {
        return true;
    }
}
