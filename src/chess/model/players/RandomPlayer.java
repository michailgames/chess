package chess.model.players;

import java.util.List;
import java.util.Random;

import chess.model.board.Board;
import chess.model.board.Color;
import chess.model.board.Move;
import chess.model.utils.MoveUtils;

/**
 * Projekt: Szachy
 * Komputerowy gracz wykonuj¹cy losowe posuniêcia
 * Micha³ Rapacz
 * 2015-04-02
 */

public class RandomPlayer extends AbstractAIPlayer {
	
	private Random randomGenerator = new Random();

	public RandomPlayer(Color color) {
		super(color);
	}

	@Override
	protected Move calculateNextMove(Board board) {
		List<Move> availableMoves = MoveUtils.allLegalMoves(board, getColor());
		int randomIndex = randomGenerator.nextInt(availableMoves.size());
		return availableMoves.get(randomIndex);
	}

}
