package chess.model.players;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import chess.model.board.Color;
import chess.model.players.PlayerFactory.PlayerMaker;

public class PlayerFactoryTest {

    private PlayerFactory factory;

    @Before
    public void before() {
        factory = new PlayerFactory();
    }

    @Test
    public void returnsListOfAvailablePlayers() {
        factory.registerPlayerMaker("aaa", new TestPlayerMaker());
        factory.registerPlayerMaker("bbb", new TestPlayerMaker());
        factory.registerPlayerMaker("ccc", new TestPlayerMaker());
        List<String> availablePlayers = factory.getAvailablePlayers();
        assertEquals(3, availablePlayers.size());
        assertTrue(availablePlayers.contains("aaa"));
        assertTrue(availablePlayers.contains("bbb"));
        assertTrue(availablePlayers.contains("ccc"));
    }

    @Test
    public void canCreateDefinedPlayer() {
        factory.registerPlayerMaker("test", new TestPlayerMaker());
        Player player = factory.makePlayer("test", Color.WHITE);
        assertTrue(player instanceof TestPlayer);
    }

    @Test
    public void createsNonPlayingPlayerIfNoMatchingMakersFound() {
        Player player = factory.makePlayer("non-existing-maker", Color.WHITE);
        assertEquals(NonPlayingPlayer.class, player.getClass());
        assertEquals(Color.WHITE, player.getColor());
    }

    private class TestPlayer extends NonPlayingPlayer {

        public TestPlayer(Color color) {
            super(color);
        }
    }

    private class TestPlayerMaker extends PlayerMaker {

        @Override
        public Player makePlayer(Color color) {
            return new TestPlayer(color);
        }

    }
}
