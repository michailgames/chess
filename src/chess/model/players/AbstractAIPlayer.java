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

	public static final long MINIMUM_MOVE_TIME = 1000;
	private long lastCalculationTime = 0;
	private boolean firstMoveMade = false;
	private CalculatingMoveAction currentThreadAction = null;

	public AbstractAIPlayer(Color color) {
		super(color);
	}

	@Override
	public synchronized void startCalculatingNextMove(final Board board) {
		currentThreadAction = new CalculatingMoveAction(board);
		Thread calculationThread = new Thread(currentThreadAction);
		calculationThread.start();
	}
	
	protected abstract Move calculateNextMove(Board board);

	@Override
	public void fieldClicked(Field field, Board board) { }

	@Override
	public Field getSelectedField() {
		return currentThreadAction == null ? null :
			currentThreadAction.getSelectedField();
	}
	
	@Override
	public synchronized void interrupt() {
		if(currentThreadAction != null) {
			currentThreadAction.interrupt();
		}
	}
	
	public long getLastCalculationTime() {
		return lastCalculationTime;
	}
	
	public boolean hasMadeFirstMove() {
		return firstMoveMade;
	}
	
	private final class CalculatingMoveAction implements Runnable {
		
		private final Board board;
		private boolean interrupted = false;
		private Field selectedField = null;

		private CalculatingMoveAction(Board board) {
			this.board = board;
		}

		@Override
		public void run() {
			long startTime = System.currentTimeMillis();
			Move move = calculateNextMove(board);
			lastCalculationTime = System.currentTimeMillis() - startTime;
			try {
				Thread.sleep(Math.max(
						MINIMUM_MOVE_TIME - lastCalculationTime, 100));
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			
			synchronized(this) {
				if(interrupted) {
					return;
				}
				selectedField = move.getSourceField();
			}
			
			ApplicationController.getInstance().refreshView();
			try {
				Thread.sleep(MINIMUM_MOVE_TIME);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			selectedField = null;
			firstMoveMade = true;
			
			synchronized(this) {
				if(interrupted) {
					return;
				}
				makeMove(move.getSourceField(), move.getTargetField());
			}
		}
		
		public synchronized Field getSelectedField() {
			return selectedField;
		}
		
		public synchronized void interrupt() {
			selectedField = null;
			interrupted = true;
		}
	}
}
