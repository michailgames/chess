package chess.model.players;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.model.board.Color;

public class PlayerFactory {
	
	private Map<String, PlayerMaker> playerMakers =
			new HashMap<String, PlayerFactory.PlayerMaker>();
	
	public void registerPlayerMaker(String name, PlayerMaker maker) {
		playerMakers.put(name, maker);
	}
	
	public List<String> getAvailablePlayers() {
		return new ArrayList<String>(playerMakers.keySet());
	}

	public Player makePlayer(String name, Color color) {
		PlayerMaker maker = playerMakers.get(name);
		if(maker != null) {
			return maker.makePlayer(color);
		}
		return new NonPlayingPlayer(color);
	}
	
	public static abstract class PlayerMaker {
		public abstract Player makePlayer(Color color);
	}
}
