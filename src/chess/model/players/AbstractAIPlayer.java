package chess.model.players;

import chess.controller.ApplicationController;
import chess.model.board.Board;
import chess.model.board.Color;
import chess.model.board.Field;
import chess.model.board.Move;

/**
 * Projekt: Szachy
 * Abstrakcyjna klasa bazowa dla komputerowych graczy
 * Micha³ Rapacz
 * 2015-04-02
 */

public abstract class AbstractAIPlayer extends Player {
	
	private long minimumMoveTime = 1000;
	private long lastCalculationTime = 0;
	private boolean firstMoveMade = false;
	private Field selectedField = null;

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
				lastCalculationTime = System.currentTimeMillis() - startTime;
				try {
					Thread.sleep(Math.max(
							minimumMoveTime - lastCalculationTime, 100));
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				selectedField = move.getSourceField();
				ApplicationController.getInstance().refreshView();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				selectedField = null;
				firstMoveMade = true;
				makeMove(move.getSourceField(), move.getTargetField());
			}
		});
		calculationThread.start();
	}
	
	protected abstract Move calculateNextMove(Board board);

	@Override
	public void fieldClicked(Field field, Board board) { }

	@Override
	public Field getSelectedField() {
		return selectedField;
	}
	
	public long getLastCalculationTime() {
		return lastCalculationTime;
	}
	
	public boolean hasMadeFirstMove() {
		return firstMoveMade;
	}
}
