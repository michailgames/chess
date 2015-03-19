package chess.controller;

import chess.model.Board;
import chess.model.Color;
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

	public void reportNewMove(Piece piece, int x, int y) {
		board.movePiece(piece, x, y);
		nextTurn();
		ApplicationController.getInstance().refreshView();
	}

	private void nextTurn() {
		currentPlayer = (currentPlayer == whitePlayer) ?
				blackPlayer : whitePlayer;
	}
}
