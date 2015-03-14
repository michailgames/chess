package chess.controller;

import chess.model.Board;

public class GameController {

	private static GameController instance;
	
	private Board board = new Board();
	
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
	}
	
	public Board getBoard() {
		return board;
	}
}
