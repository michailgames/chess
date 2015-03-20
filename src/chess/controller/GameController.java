package chess.controller;

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
		return board;
	}
	
	public void clickField(int x, int y) {
		currentPlayer.fieldClicked(x, y, board);
	}
	
	public Piece getSelectedPiece() {
		return currentPlayer.getSelectedPiece();
	}

	public void reportNewMove(Piece piece, int x, int y)
			throws IllegalMoveException {
		Field field = new Field(x, y);
		if(piece == null || piece.getColor() != currentPlayer.getColor() || 
				piece.getAllLegalMoves(board).contains(field) == false) {
			throw new IllegalMoveException();
		}
		board.movePiece(piece, x, y);
		nextTurn();
		ApplicationController.getInstance().refreshView();
	}

	private void nextTurn() {
		currentPlayer = (currentPlayer == whitePlayer) ?
				blackPlayer : whitePlayer;
	}
	
	public class IllegalMoveException extends Exception {
		private static final long serialVersionUID = 1L;	
	}
}
