package chess.controller;

/**
 * Projekt: Szachy
 * Kontroler bie¿¹cej rozgrywki
 * Micha³ Rapacz
 * 2015-03-26
 */

import chess.model.board.Board;
import chess.model.board.Color;
import chess.model.board.Field;
import chess.model.board.GameState;
import chess.model.listeners.IMoveListener;
import chess.model.pieces.Piece;
import chess.model.players.NonPlayingPlayer;
import chess.model.players.Player;
import chess.model.utils.MoveUtils;

public class GameController {

	private static GameController instance;
	
	private Board board = new Board();
	private Player whitePlayer = new NonPlayingPlayer(Color.WHITE);
	private Player blackPlayer = new NonPlayingPlayer(Color.BLACK);
	private Player currentPlayer = whitePlayer;
	
	private GameController() { }
	
	public synchronized static GameController getInstance() {
		if(instance == null) {
			instance = new GameController();
		}
		return instance;
	}
	
	public synchronized void startNewGame(String white, String black) {
		startNewGame(PlayerController.getInstance()
				.makePlayer(white, Color.WHITE),
				PlayerController.getInstance()
				.makePlayer(black, Color.BLACK));
	}

	synchronized void startNewGame(Player white, Player black) {
		whitePlayer = white;
		blackPlayer = black;
		whitePlayer.registerMoveListener(getMoveListener());
		blackPlayer.registerMoveListener(getMoveListener());
		board = new Board();
		board.setup();
		LogController.getInstance().clearLogs();
		currentPlayer = whitePlayer;
		currentPlayer.startCalculatingNextMove(getBoard());
	}
	
	public synchronized Board getBoard() {
		return new Board(board);
	}
	
	public synchronized void clickField(Field field) {
		currentPlayer.fieldClicked(field, board);
	}
	
	public synchronized Field getSelectedPiece() {
		return currentPlayer.getSelectedField();
	}

	public synchronized void reportNewMove(Player player, Field sourceField,
			Field targetField) throws UnauthorizedMoveException {
		if(player != currentPlayer) {
			throw new UnauthorizedMoveException();
		}
		
		if(isMoveLegal(sourceField, targetField) == false) {
			throw new IllegalMoveException();
		}
		
		LogController.getInstance().reportMove(board, sourceField, targetField);
		movePiece(sourceField, targetField);
	}

	private void movePiece(Field sourceField, Field field) {
		board.movePiece(sourceField, field);
		nextTurn();
		ApplicationController.getInstance().refreshLogs();
		ApplicationController.getInstance().refreshView();
	}

	private boolean isMoveLegal(Field sourceField, Field targetField) {
		Piece piece = board.getPiece(sourceField);
		if(piece == null || piece.getColor() != currentPlayer.getColor()) {
			return false;
		}
		return piece.getAllLegalMoves(board, sourceField.getX(),
				sourceField.getY()).contains(targetField);
	}

	private void nextTurn() {
		currentPlayer = (currentPlayer == whitePlayer) ?
				blackPlayer : whitePlayer;
		if(MoveUtils.hasAnyLegalMove(getBoard(), currentPlayer.getColor())) {
			currentPlayer.startCalculatingNextMove(board);
		} else {
			currentPlayer = (currentPlayer.getColor() == Color.BLACK) ?
					blackPlayer : whitePlayer;
		}
	}
	
	public GameState getGameState() {
		return board.getGameState(currentPlayer.getColor());
	}
	
	public Color getCurrentPlayerColor() {
		return currentPlayer.getColor();
	}
	
	public class IllegalMoveException extends RuntimeException {
		private static final long serialVersionUID = 1L;	
	}
	
	public class UnauthorizedMoveException extends RuntimeException {
		private static final long serialVersionUID = 1L;	
	}
	
	private IMoveListener getMoveListener() {
		return new IMoveListener() {
			@Override
			public void reportNewMove(Player player, Field sourceField,
					Field targetField) throws UnauthorizedMoveException {
				GameController.getInstance()
				.reportNewMove(player, sourceField, targetField);
			}
		};
	}

	public synchronized void undoMove() {
		if(LogController.getInstance().getLogsNumber() == 0) {
			return;
		}
		currentPlayer.interrupt();
		board = LogController.getInstance().getPreviousBoardState();
		LogController.getInstance().reportUndo();
		nextTurn();
		
		ApplicationController.getInstance().refreshView();
		ApplicationController.getInstance().refreshLogs();
	}
}
