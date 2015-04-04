package chess.model.players;

import chess.model.board.Board;
import chess.model.board.Color;
import chess.model.board.Field;
import chess.model.board.Move;
import chess.model.pieces.Piece;

/**
 * Projekt: Szachy
 * Abstrakcyjna klasa bazowa dla komputerowych graczy
 * Micha³ Rapacz
 * 2015-04-02
 */

public abstract class AbstractAIPlayer extends Player {
	
	private long minimumMoveTime = 1000;

	public AbstractAIPlayer(Color color) {
		super(color);
	}

	@Override
	public synchronized void startCalculatingNextMove(final Board board) {
		Thread calculationThread = new Thread(new Runnable() {
			@Override
			public void run() {
				long startTime = System.currentTimeMillis();
				Move move = calculateNextMove(board);
				long calculationTime = System.currentTimeMillis() - startTime;
				if(calculationTime < minimumMoveTime) {
					try {
						Thread.sleep(minimumMoveTime - calculationTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} 
				}
				makeMove(move.getSourceField(), move.getTargetField());
			}
		});
		calculationThread.start();
	}
	
	protected abstract Move calculateNextMove(Board board);

	@Override
	public void fieldClicked(Field field, Board board) { }

	@Override
	public Piece getSelectedPiece() {
		return null;
	}
}
