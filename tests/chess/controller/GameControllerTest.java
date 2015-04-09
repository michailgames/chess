package chess.controller;

import org.junit.Before;
import org.junit.Test;

import chess.controller.GameController.IllegalMoveException;
import chess.controller.GameController.UnauthorizedMoveException;
import chess.model.board.Color;
import chess.model.board.Field;
import chess.model.players.NonPlayingPlayer;
import chess.model.players.Player;

/**
 * Projekt: Szachy
 * Test kontrolera gry
 * Micha³ Rapacz
 * 2015-03-26
 */

public class GameControllerTest {
	
	private Player whitePlayer;
	private Player blackPlayer;
	
	@Before
	public void setup() {
		whitePlayer = new NonPlayingPlayer(Color.WHITE);
		blackPlayer = new NonPlayingPlayer(Color.BLACK);
		GameController.getInstance().startNewGame(whitePlayer, blackPlayer);
	}

	@Test(expected=UnauthorizedMoveException.class)
	public void doesNotAcceptMoveFromOtherPlayer() {
		GameController.getInstance().reportNewMove(blackPlayer, new Field(0, 1),
				new Field(0, 2));
	}
	
	@Test
	public void acceptsMoveFromCurrentPlayer() {
		GameController.getInstance().reportNewMove(whitePlayer, new Field(0, 6),
				new Field(0, 5));
	}
	
	@Test(expected=UnauthorizedMoveException.class)
	public void doesNotAcceptTwoMovesFromOnePlayeInRow() {
		GameController.getInstance().reportNewMove(whitePlayer, new Field(0, 6),
				new Field(0, 5));
		GameController.getInstance().reportNewMove(whitePlayer, new Field(1, 6),
				new Field(1, 5));
	}
	
	@Test
	public void acceptsMovesFromPlayersInTurns() {
		GameController.getInstance().reportNewMove(whitePlayer, new Field(0, 6),
				new Field(0, 5));
		GameController.getInstance().reportNewMove(blackPlayer, new Field(1, 1),
				new Field(1, 2));
	}
	
	@Test(expected=IllegalMoveException.class)
	public void doesNotAcceptIllegalMoves() {
		GameController.getInstance().reportNewMove(whitePlayer, new Field(0, 6),
				new Field(3, 5));
	}
	
	@Test(expected=IllegalMoveException.class)
	public void doesNotAcceptMovingOpponentPieces() {
		GameController.getInstance().reportNewMove(whitePlayer, new Field(0, 1),
				new Field(0, 2));
	}

}
