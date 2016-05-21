package chess.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import chess.model.board.Color;
import chess.model.players.AlphaBetaPlayer;
import chess.model.players.GreedyPlayer;
import chess.model.players.HumanPlayer;
import chess.model.players.Player;
import chess.model.players.RandomPlayer;

public class PlayerControllerTest {

    @Test
    public void availablePlayers() {
        List<String> availablePlayers = PlayerController.getInstance().getAvailablePlayers();
        assertEquals(8, availablePlayers.size());
        assertTrue(availablePlayers.contains("Human"));
        assertTrue(availablePlayers.contains("CPU - level 0"));
        assertTrue(availablePlayers.contains("CPU - level 1"));
        assertTrue(availablePlayers.contains("CPU - level 2"));
        assertTrue(availablePlayers.contains("CPU - level 3"));
        assertTrue(availablePlayers.contains("CPU - level 4"));
        assertTrue(availablePlayers.contains("CPU - level 5"));
        assertTrue(availablePlayers.contains("CPU - level 6"));
    }

    @Test
    public void creatingHumanPlayer() {
        Player player = PlayerController.getInstance().makePlayer("Human", Color.WHITE);
        assertTrue(player instanceof HumanPlayer && player.getColor() == Color.WHITE);
    }

    @Test
    public void creatingLevel0Player() {
        Player player = PlayerController.getInstance().makePlayer("CPU - level 0", Color.WHITE);
        assertTrue(player instanceof RandomPlayer && player.getColor() == Color.WHITE);
    }

    @Test
    public void creatingLevel1Player() {
        Player player = PlayerController.getInstance().makePlayer("CPU - level 1", Color.WHITE);
        assertTrue(player instanceof GreedyPlayer && player.getColor() == Color.WHITE);
    }

    @Test
    public void creatingLevel2Player() {
        Player player = PlayerController.getInstance().makePlayer("CPU - level 2", Color.WHITE);
        assertTrue(player instanceof AlphaBetaPlayer && player.getColor() == Color.WHITE
                && ((AlphaBetaPlayer) player).getDepth() == 2);
    }

    @Test
    public void creatingLevel3Player() {
        Player player = PlayerController.getInstance().makePlayer("CPU - level 3", Color.WHITE);
        assertTrue(player instanceof AlphaBetaPlayer && player.getColor() == Color.WHITE
                && ((AlphaBetaPlayer) player).getDepth() == 3);
    }

    @Test
    public void creatingLevel4Player() {
        Player player = PlayerController.getInstance().makePlayer("CPU - level 4", Color.WHITE);
        assertTrue(player instanceof AlphaBetaPlayer && player.getColor() == Color.WHITE
                && ((AlphaBetaPlayer) player).getDepth() == 4);
    }

    @Test
    public void creatingLevel5Player() {
        Player player = PlayerController.getInstance().makePlayer("CPU - level 5", Color.WHITE);
        assertTrue(player instanceof AlphaBetaPlayer && player.getColor() == Color.WHITE
                && ((AlphaBetaPlayer) player).getDepth() == 5);
    }

    @Test
    public void creatingLevel6Player() {
        Player player = PlayerController.getInstance().makePlayer("CPU - level 6", Color.WHITE);
        assertTrue(player instanceof AlphaBetaPlayer && player.getColor() == Color.WHITE
                && ((AlphaBetaPlayer) player).getDepth() == 6);
    }
}
