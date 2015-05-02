package chess.model.players.strategies;

import chess.model.board.Board;
import chess.model.board.Color;
import chess.model.pieces.Piece;

/**
 * Projekt: Szachy
 * Interfejs dla implementacji funkcji oceny sytuacji na planszy 
 * Micha³ Rapacz
 * 2015-04-05
 */

public interface IBoardEvaluationStrategy {

	public int evaluateBoard(Board board, Color nextPlayerColor);
	public int getPieceValue(Piece piece);
}
