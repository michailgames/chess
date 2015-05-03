package chess.model.players;

import chess.model.board.Board;
import chess.model.board.Color;
import chess.model.board.Move;
import chess.model.players.strategies.IBoardEvaluationStrategy;

/**
 * Projekt: Szachy
 * Rozszerzenie gracza alfa-beta o adaptacjê g³êbokoœci przeszukiwania
 * do czasu zu¿ytego na obliczenia w poprzednich ruchach
 * Micha³ Rapacz
 * 2015-05-01
 */

public class AdaptiveAlphaBetaPlayer extends AlphaBetaPlayer {
	
	private long calculationTimeLowerBound, calculationTimeUpperBound;

	public AdaptiveAlphaBetaPlayer(Color color,
			IBoardEvaluationStrategy boardEvaluationStrategy, int initialDepth,
			long calculationTimeLowerBound, long calculationTimeUpperBound) {
		super(color, boardEvaluationStrategy, initialDepth);
		this.calculationTimeLowerBound = calculationTimeLowerBound;
		this.calculationTimeUpperBound = calculationTimeUpperBound;
	}
	
	@Override
	protected Move calculateNextMove(Board board) {
		if(hasMadeFirstMove()) {
			if(getLastCalculationTime() < calculationTimeLowerBound) {
				setDepth(getDepth() + 1);
			} else if(getLastCalculationTime() > calculationTimeUpperBound) {
				setDepth(getDepth() - 1);
			}
		}
		System.out.println(getLastCalculationTime() + ", " + getDepth());
		return super.calculateNextMove(board);
	}

}
