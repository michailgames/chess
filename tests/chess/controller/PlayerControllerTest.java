package chess.controller;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import chess.model.board.Color;
import chess.model.players.AdaptiveAlphaBetaPlayer;
import chess.model.players.AlphaBetaPlayer;
import chess.model.players.GreedyPlayer;
import chess.model.players.HumanPlayer;
import chess.model.players.MinimaxPlayer;
import chess.model.players.Player;
import chess.model.players.RandomPlayer;
import static org.junit.Assert.*;

/**
 * Projekt: Szachy
 * Test kontrolera graczy
 * Micha� Rapacz
 * 2015-04-09
 */

public class PlayerControllerTest {

	@Test
	public void availablePlayers() {
		List<String> availablePlayers = PlayerController.getInstance()
				.getAvailablePlayers();
		assertEquals(8, availablePlayers.size());
		assertTrue(availablePlayers.contains("Cz�owiek"));
		assertTrue(availablePlayers.contains("Komputer - losowy"));
		assertTrue(availablePlayers.contains("Komputer - zach�anny"));
		assertTrue(availablePlayers.contains("Komputer - minimax (3)"));
		assertTrue(availablePlayers.contains("Komputer - minimax (4)"));
		assertTrue(availablePlayers.contains("Komputer - alfa-beta (4)"));
		assertTrue(availablePlayers.contains("Komputer - alfa-beta (5)"));
		assertTrue(availablePlayers.contains("Komputer - alfa-beta (6)"));
//		assertTrue(availablePlayers.contains(
//				"Komputer - adaptacyjny alpha-beta (3-10s)"));
	}
	
	@Test
	public void creatingHumanPlayer() {
		Player player = PlayerController.getInstance()
				.makePlayer("Cz�owiek", Color.WHITE);
		assertTrue(player instanceof HumanPlayer
				&& player.getColor() == Color.WHITE);
	}
	
	@Test
	public void creatingRandomPlayer() {
		Player player = PlayerController.getInstance()
				.makePlayer("Komputer - losowy", Color.WHITE);
		assertTrue(player instanceof RandomPlayer
				&& player.getColor() == Color.WHITE);
	}
	
	@Test
	public void creatingGreedyPlayer() {
		Player player = PlayerController.getInstance()
				.makePlayer("Komputer - zach�anny", Color.WHITE);
		assertTrue(player instanceof GreedyPlayer
				&& player.getColor() == Color.WHITE);
	}
	
	@Test
	public void creatingMinimax3Player() {
		Player player = PlayerController.getInstance()
				.makePlayer("Komputer - minimax (3)", Color.WHITE);
		assertTrue(player instanceof MinimaxPlayer
				&& player.getColor() == Color.WHITE);
	}

	@Test
	public void creatingMinimax4Player() {
		Player player = PlayerController.getInstance()
				.makePlayer("Komputer - minimax (4)", Color.WHITE);
		assertTrue(player instanceof MinimaxPlayer
				&& player.getColor() == Color.WHITE);
	}
	
	@Test
	public void creatingAlphaBeta4Player() {
		Player player = PlayerController.getInstance()
				.makePlayer("Komputer - alfa-beta (4)", Color.WHITE);
		assertTrue(player instanceof AlphaBetaPlayer
				&& player.getColor() == Color.WHITE);
	}
	
	@Test
	public void creatingAlphaBeta5Player() {
		Player player = PlayerController.getInstance()
				.makePlayer("Komputer - alfa-beta (5)", Color.WHITE);
		assertTrue(player instanceof AlphaBetaPlayer
				&& player.getColor() == Color.WHITE);
	}
	
	@Test
	public void creatingAlphaBeta6Player() {
		Player player = PlayerController.getInstance()
				.makePlayer("Komputer - alfa-beta (6)", Color.WHITE);
		assertTrue(player instanceof AlphaBetaPlayer
				&& player.getColor() == Color.WHITE);
	}
	
	@Ignore
	@Test
	public void creatingAdaptiveAlphaBeta5to10Player() {
		Player player = PlayerController.getInstance().makePlayer(
				"Komputer - adaptacyjny alfa-beta (3-10s)", Color.WHITE);
		assertTrue(player instanceof AdaptiveAlphaBetaPlayer
				&& player.getColor() == Color.WHITE);
	}
}
