package chess.controller;

import java.util.List;

import org.junit.Test;

import chess.model.Color;
import chess.model.Player;
import chess.model.players.HumanPlayer;
import static org.junit.Assert.*;

public class PlayerControllerTest {

	@Test
	public void availablePlayers() {
		List<String> availablePlayers = PlayerController.getInstance()
				.getAvailablePlayers();
		assertEquals(1, availablePlayers.size());
		assertTrue(availablePlayers.contains("Cz³owiek"));
	}
	
	@Test
	public void creatingHumanPlayer() {
		Player player = PlayerController.getInstance()
				.makePlayer("Cz³owiek", Color.WHITE);
		assertTrue(player instanceof HumanPlayer
				&& player.getColor() == Color.WHITE);
	}

}
