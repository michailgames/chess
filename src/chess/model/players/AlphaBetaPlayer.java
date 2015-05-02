package chess.model.players;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import chess.model.board.Board;
import chess.model.board.Color;
import chess.model.board.EvaluatedMove;
import chess.model.board.Move;
import chess.model.players.strategies.IBoardEvaluationStrategy;
import chess.utils.MoveUtils;

/**
 * Projekt: Szachy
 * Komputerowy gracz u�ywaj�cy algorytmu minimax z optymalizacj� odci��
 * alpha-beta
 * Micha� Rapacz
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
		List<Move> availableMoves = getOrderedMoves(board, getColor());
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
		List<Move> availableMoves = getOrderedMoves(board,
				getColor().getOppositeColor());
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
	
	private List<Move> getOrderedMoves(final Board board, Color color) {
		List<Move> availableMoves = MoveUtils.allPotentialMoves(board, color);
		Collections.shuffle(availableMoves);
		List<Move> orderedMoves = new ArrayList<Move>(availableMoves.size());
		List<Move> capturingMoves = filterCapturingMoves(board, availableMoves);
		orderedMoves.addAll(capturingMoves);
		List<Move> nonCapturingMoves = filterNonCapturingMoves(board,
				availableMoves);
		orderedMoves.addAll(nonCapturingMoves);
		return orderedMoves;
	}

	private void sortMovesIncreasinglyByPieceValue(final Board board, List<Move> availableMoves) {
		Collections.sort(availableMoves, new Comparator<Move>() {

			@Override
			public int compare(Move move1, Move move2) {
				int pieceValue1 = evaluationStrategy.getPieceValue(
						board.getPiece(move1.getSourceField()));
				int pieceValue2 = evaluationStrategy.getPieceValue(
						board.getPiece(move2.getSourceField()));
				return pieceValue1 - pieceValue2;
			}
		});
	}

	private List<Move> filterNonCapturingMoves(final Board board,
			List<Move> availableMoves) {
		List<Move> nonCapturingMoves = new ArrayList<Move>();
		for(Move move : availableMoves) {
			if(moveCaptures(board, move) == false) {
				nonCapturingMoves.add(move);
			}
		}
		return nonCapturingMoves;
	}

	private List<Move> filterCapturingMoves(final Board board,
			List<Move> availableMoves) {
		List<Move> capturingMoves = new ArrayList<Move>();
		for(Move move : availableMoves) {
			if(moveCaptures(board, move)) {
				capturingMoves.add(move);
			}
		}
		sortMovesIncreasinglyByPieceValue(board, capturingMoves);
		sortMovesDecreasingleByCaptureValue(board, capturingMoves);
		return capturingMoves;
	}

	private void sortMovesDecreasingleByCaptureValue(final Board board,
			List<Move> capturingMoves) {
		Collections.sort(capturingMoves, new Comparator<Move>() {

			@Override
			public int compare(Move move1, Move move2) {
				int captureValue1 = evaluationStrategy.getPieceValue(
						board.getPiece(move1.getTargetField()));
				int captureValue2 = evaluationStrategy.getPieceValue(
						board.getPiece(move2.getTargetField()));
				return captureValue2 - captureValue1;
			}
		});
	}

	boolean moveCaptures(Board board, Move move) {
		return board.getPiece(move.getTargetField()) != null;
	}
}
