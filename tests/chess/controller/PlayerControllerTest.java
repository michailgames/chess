package chess.controller;

import java.util.List;

import org.junit.Test;

import chess.model.Color;
import chess.model.Player;
import chess.model.players.HumanPlayer;
import chess.model.players.RandomPlayer;
import static org.junit.Assert.*;

public class PlayerControllerTest {

	@Test
	public void availablePlayers() {
		List<String> availablePlayers = PlayerController.getInstance()
				.getAvailablePlayers();
		assertEquals(2, availablePlayers.size());
		assertTrue(availablePlayers.contains("Cz³owiek"));
		assertTrue(availablePlayers.contains("Komputer - losowy"));
	}
	
	@Test
	public void creatingHumanPlayer() {
		Player player = PlayerController.getInstance()
				.makePlayer("Cz³owiek", Color.WHITE);
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

}
