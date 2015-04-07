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
import chess.utils.MoveUtils;

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
		board = new Board();
		board.setup();
		whitePlayer = PlayerController.getInstance()
				.makePlayer(white, Color.WHITE);
		whitePlayer.registerMoveListener(getMoveListener());
		blackPlayer = PlayerController.getInstance()
				.makePlayer(black, Color.BLACK);
		blackPlayer.registerMoveListener(getMoveListener());
		currentPlayer = whitePlayer;
		currentPlayer.startCalculatingNextMove(getBoard());
	}
	
	public synchronized Board getBoard() {
		return new Board(board);
	}
	
	public synchronized void clickField(Field field) {
		currentPlayer.fieldClicked(field, board);
	}
	
	public synchronized Piece getSelectedPiece() {
		return currentPlayer.getSelectedPiece();
	}

	public synchronized void reportNewMove(Player player, Field sourceField,
			Field targetField) throws UnauthorizedMoveException {
		if(player != currentPlayer) {
			throw new UnauthorizedMoveException();
		}
		
		Piece piece = board.getPiece(sourceField);
		if(isMoveLegal(piece, targetField) == false) {
			throw new IllegalMoveException();
		}
		
		movePiece(piece, targetField);
	}

	private void movePiece(Piece piece, Field field) {
		board.movePiece(piece, field);
		nextTurn();
		ApplicationController.getInstance().refreshView();
	}

	private boolean isMoveLegal(Piece piece, Field field) {
		return piece != null && piece.getColor() == currentPlayer.getColor() && 
				piece.getAllLegalMoves(board).contains(field);
	}

	private void nextTurn() {
		currentPlayer = (currentPlayer == whitePlayer) ?
				blackPlayer : whitePlayer;
		if(MoveUtils.hasAnyLegalMove(getBoard(), currentPlayer.getColor())) {
			currentPlayer.startCalculatingNextMove(board);
		} else {
			whitePlayer = new NonPlayingPlayer(Color.WHITE);
			blackPlayer = new NonPlayingPlayer(Color.BLACK);
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
}
