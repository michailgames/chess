package chess.controller;

/**
 * Projekt: Szachy
 * Kontroler bie¿¹cej rozgrywki
 * Micha³ Rapacz
 * 2015-03-26
 */

import chess.model.Board;
import chess.model.Color;
import chess.model.Field;
import chess.model.Piece;
import chess.model.Player;
import chess.model.players.HumanPlayer;
import chess.model.players.NonPlayingPlayer;

public class GameController {

	private static GameController instance;
	
	private Board board = new Board();
	private Player whitePlayer = new NonPlayingPlayer(Color.WHITE);
	private Player blackPlayer = new NonPlayingPlayer(Color.BLACK);
	private Player currentPlayer = whitePlayer;
	
	private GameController() { }
	
	public static GameController getInstance() {
		if(instance == null) {
			instance = new GameController();
		}
		return instance;
	}
	
	public void startNewGame() {
		board = new Board();
		board.setup();
		whitePlayer = new HumanPlayer(Color.WHITE);
		blackPlayer = new HumanPlayer(Color.BLACK);
		currentPlayer = whitePlayer;
	}
	
	public Board getBoard() {
		return new Board(board);
	}
	
	public void clickField(Field field) {
		currentPlayer.fieldClicked(field, board);
	}
	
	public Piece getSelectedPiece() {
		return currentPlayer.getSelectedPiece();
	}

	public void reportNewMove(Player player, Field sourceField,
			Field targetField) throws IllegalMoveException {
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
	}
	
	public class IllegalMoveException extends Exception {
		private static final long serialVersionUID = 1L;	
	}
	
	public class UnauthorizedMoveException extends RuntimeException {
		private static final long serialVersionUID = 1L;	
	}
}
