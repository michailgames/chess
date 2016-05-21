package chess.model.players;

import java.util.Collections;
import java.util.List;

import chess.model.board.Board;
import chess.model.board.Color;
import chess.model.board.Move;
import chess.model.players.strategies.IBoardEvaluationStrategy;
import chess.model.utils.MoveUtils;

public class GreedyPlayer extends AbstractAIPlayer {

    private IBoardEvaluationStrategy evaluationStrategy;

    public GreedyPlayer(Color color, IBoardEvaluationStrategy boardEvaluationStrategy) {
        super(color);
        this.evaluationStrategy = boardEvaluationStrategy;
    }

    @Override
    protected Move calculateNextMove(Board board) {
        List<Move> availableMoves = MoveUtils.allLegalMoves(board, getColor());
        Collections.shuffle(availableMoves);
        return determineBestMove(board, availableMoves);
    }

    private Move determineBestMove(Board board, List<Move> availableMoves) {
        Move bestMove = null;
        int bestScore = Integer.MIN_VALUE;
        for (Move move : availableMoves) {
            Board newBoard = new Board(board, move.getSourceField(), move.getTargetField());
            int score = evaluationStrategy.evaluateBoard(newBoard, getColor().getOppositeColor());
            if (score > bestScore) {
                bestMove = move;
                bestScore = score;
            }
        }
        return bestMove;
    }

}
