package chess.model.players.strategies;

import java.util.Random;

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
	
	private final static int baseScore = 100;
	private final static int randomizationRange = 150;
	private Random random = new Random();

	@Override
	public int evaluateBoard(Board board, Color color) {
		GameState gameState = board.getGameState(color.getOppositeColor());
		if(gameState == GameState.MATE) {
			return Integer.MAX_VALUE;
		} else if(gameState == GameState.STALEMATE) {
			return baseScore;
		}
		
		int score = baseScore;
		for(Piece piece : board.getAllPieces(color)) {
			score += getPieceValue(piece);
		}
		for(Piece piece : board.getAllPieces(color.getOppositeColor())) {
			score -= getPieceValue(piece);
		}
		return randomize(score);
	}

	private int randomize(int score) {
		return score + random.nextInt(randomizationRange);
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
