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
 * Komputerowy gracz u¿ywaj¹cy algorytmu minimax
 * Micha³ Rapacz
 * 2015-04-30
 */

public class MinimaxPlayer extends AbstractAIPlayer {
	
	private IBoardEvaluationStrategy evaluationStrategy;
	private int depth;

	public MinimaxPlayer(Color color,
			IBoardEvaluationStrategy boardEvaluationStrategy, int depth) {
		super(color);
		this.evaluationStrategy = boardEvaluationStrategy;
		this.depth = depth;
	}

	@Override
	protected Move calculateNextMove(Board board) {
		List<Move> availableMoves = MoveUtils.allLegalMoves(board, getColor());
		Collections.shuffle(availableMoves);
		return max(board, depth, availableMoves).getMove();
	}
	
	private EvaluatedMove max(Board board, int depth,
			List<Move> availableMoves) {
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
				score = min(newBoard, depth - 1, MoveUtils.allLegalMoves(
						newBoard, getColor().getOppositeColor())).getScore();
			}
			if(score > bestScore) {
				bestMove = move;
				bestScore = score; 
			}
		}
		return new EvaluatedMove(bestMove, bestScore);
	}
	
	private EvaluatedMove min(Board board, int depth,
			List<Move> availableMoves) {
		Move worstMove = null;
		int worstScore = Integer.MAX_VALUE;
		for(Move move : availableMoves) {
			Board newBoard = new Board(board, move.getSourceField(), 
					move.getTargetField());
			int score;
			if(depth == 1 || !MoveUtils.hasAnyLegalMove(newBoard, getColor())) {
				score = evaluationStrategy.evaluateBoard(newBoard, getColor());
			} else {
				score = max(newBoard, depth - 1, MoveUtils.allLegalMoves(
						newBoard, getColor())).getScore();
			}
			if(score < worstScore) {
				worstMove = move;
				worstScore = score; 
			}
		}
		return new EvaluatedMove(worstMove, worstScore);
	}
}
