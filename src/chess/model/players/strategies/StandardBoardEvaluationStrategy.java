package chess.model.players.strategies;

import chess.model.board.Board;
import chess.model.board.Color;
import chess.model.board.GameState;
import chess.model.pieces.Bishop;
import chess.model.pieces.Knight;
import chess.model.pieces.Pawn;
import chess.model.pieces.Piece;
import chess.model.pieces.Queen;
import chess.model.pieces.Rook;

/**
 * Projekt: Szachy
 * Klasa implementuj¹ca standardow¹ strategiê oceny planszy
 * Micha³ Rapacz
 * 2015-04-05
 */

public class StandardBoardEvaluationStrategy implements
		IBoardEvaluationStrategy {
	
	private Color representedColor;
	
	public StandardBoardEvaluationStrategy(Color representedColor) {
		this.representedColor = representedColor;
	}

	@Override
	public int evaluateBoard(Board board, Color nextPlayerColor) {
		GameState gameState = board.getGameState(nextPlayerColor);
		if(gameState == GameState.MATE) {
			return nextPlayerColor == representedColor ?
					Integer.MIN_VALUE + 1000 : Integer.MAX_VALUE - 1000;
		} else if(gameState == GameState.STALEMATE) {
			return 0;
		}
		
		return openPositionScore(board);
	}

	private int openPositionScore(Board board) {
		int score = 0;
		for(int x = 0; x < Board.BOARD_SIZE; x++) {
			for(int y = 0; y < Board.BOARD_SIZE; y++) {
				Piece piece = board.getPiece(x, y);
				if(piece == null) {
					continue;
				} else if(piece.getColor() == representedColor) {
					score += getPieceValue(piece, x, y);
				} else {
					score -= getPieceValue(piece, x, y);
				}
			}
		}
		return score;
	}

	public int getPieceValue(Piece piece, int x, int y) {
		if(piece instanceof Pawn) {
			if(piece.getColor() == Color.WHITE && y == 2 ||
					piece.getColor() == Color.BLACK && y == 5) {
				return 110;
			}
			if(piece.getColor() == Color.WHITE && y == 1 ||
					piece.getColor() == Color.BLACK && y == 6) {
				return 130;
			}
			return 100;
		} else if(piece instanceof Knight) {
			return 300;
		} else if(piece instanceof Bishop) {
			return 300;
		} else if(piece instanceof Rook) {
			return 500;
		} else if(piece instanceof Queen) {
			return 900;
		}
		return 0;
	}

}
