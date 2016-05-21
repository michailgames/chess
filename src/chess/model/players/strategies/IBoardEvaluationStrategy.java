package chess.model.players.strategies;

import chess.model.board.Board;
import chess.model.board.Color;
import chess.model.pieces.Piece;

public interface IBoardEvaluationStrategy {

    public int evaluateBoard(Board board, Color nextPlayerColor);

    public int getPieceValue(Piece piece, int x, int y);
}
