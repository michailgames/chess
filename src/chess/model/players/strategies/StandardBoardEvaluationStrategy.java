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
					Integer.MIN_VALUE + 1  : Integer.MAX_VALUE - 1;
		} else if(gameState == GameState.STALEMATE) {
			return 0;
		}
		
		return openPositionScore(board);
	}

	private int openPositionScore(Board board) {
		int score = 0;
		for(Piece piece : board.getAllPieces(representedColor)) {
			score += getPieceValue(piece);
		}
		for(Piece piece : board.getAllPieces(representedColor
				.getOppositeColor())) {
			score -= getPieceValue(piece);
		}
		return score;
	}

	private int getPieceValue(Piece piece) {
		if(piece instanceof Pawn) {
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
