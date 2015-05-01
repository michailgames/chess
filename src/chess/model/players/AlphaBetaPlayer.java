package chess.model.players;

import java.util.Collections;
import java.util.List;

import chess.model.board.Board;
import chess.model.board.Color;
import chess.model.board.EvaluatedMove;
import chess.model.board.Move;
import chess.model.players.strategies.IBoardEvaluationStrategy;
import chess.utils.MoveUtils;

/**
 * Projekt: Szachy
 * Komputerowy gracz u¿ywaj¹cy algorytmu minimax z optymalizacj¹ odciêæ
 * alpha-beta
 * Micha³ Rapacz
 * 2015-05-01
 */

public class AlphaBetaPlayer extends AbstractAIPlayer {
	
	private IBoardEvaluationStrategy evaluationStrategy;
	private int depth;

	public AlphaBetaPlayer(Color color,
			IBoardEvaluationStrategy boardEvaluationStrategy, int depth) {
		super(color);
		this.evaluationStrategy = boardEvaluationStrategy;
		this.depth = depth;
	}

	@Override
	protected Move calculateNextMove(Board board) {
		int lowerBound = Integer.MIN_VALUE;
		int upperBound = Integer.MAX_VALUE;
		return max(board, depth, lowerBound, upperBound).getMove();
	}
	
	private EvaluatedMove max(Board board, int depth,
			int lowerBound, int upperBound) {
		List<Move> availableMoves = MoveUtils.allLegalMoves(board, getColor());
		Collections.shuffle(availableMoves);
		Move bestMove = null;
		int bestScore = Integer.MIN_VALUE;
		for(Move move : availableMoves) {
			Board newBoard = new Board(board, move.getSourceField(), 
					move.getTargetField());
			int score;
			if(depth == 1 || !MoveUtils.hasAnyLegalMove(newBoard,
					getColor().getOppositeColor())) {
				score = evaluationStrategy.evaluateBoard(newBoard,
						getColor().getOppositeColor());
			} else {
				score = min(newBoard, depth - 1,
						lowerBound, upperBound).getScore();
			}
			if(score >= upperBound) {
				return new EvaluatedMove(move, upperBound);
			}
			lowerBound = score > lowerBound ? score : lowerBound;
			if(score > bestScore) {
				bestMove = move;
				bestScore = score; 
			}
		}
		return new EvaluatedMove(bestMove, bestScore);
	}
	
	private EvaluatedMove min(Board board, int depth,
			int lowerBound, int upperBound) {
		List<Move> availableMoves = MoveUtils.allLegalMoves(board, 
				getColor().getOppositeColor());
		Collections.shuffle(availableMoves);
		Move worstMove = null;
		int worstScore = Integer.MAX_VALUE;
		for(Move move : availableMoves) {
			Board newBoard = new Board(board, move.getSourceField(), 
					move.getTargetField());
			int score;
			if(depth == 1 || !MoveUtils.hasAnyLegalMove(newBoard, getColor())) {
				score = evaluationStrategy.evaluateBoard(newBoard, getColor());
			} else {
				score = max(newBoard, depth - 1,
						lowerBound, upperBound).getScore();
			}
			if(score <= lowerBound) {
				return new EvaluatedMove(move, lowerBound);
			}
			upperBound = score < upperBound ? score : upperBound;
			if(score < worstScore) {
				worstMove = move;
				worstScore = score; 
			}
		}
		return new EvaluatedMove(worstMove, worstScore);
	}
}
