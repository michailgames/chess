package chess.controller;

import java.util.List;

import org.junit.Test;

import chess.model.board.Color;
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
		assertEquals(5, availablePlayers.size());
		assertTrue(availablePlayers.contains("Cz�owiek"));
		assertTrue(availablePlayers.contains("Komputer - losowy"));
		assertTrue(availablePlayers.contains("Komputer - zach�anny"));
		assertTrue(availablePlayers.contains("Komputer - minimax (3)"));
		assertTrue(availablePlayers.contains("Komputer - minimax (4)"));
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
}
