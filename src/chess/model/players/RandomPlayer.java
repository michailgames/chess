package chess.model.players;

import java.util.List;
import java.util.Random;

import chess.model.Board;
import chess.model.Color;
import chess.model.Move;
import chess.utils.MoveUtils;

/**
 * Projekt: Szachy
 * Komputerowy gracz wykonuj�cy losowe posuni�cia
 * Micha� Rapacz
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
