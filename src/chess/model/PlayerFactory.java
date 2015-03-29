package chess.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.model.players.HumanPlayer;
import chess.model.players.NonPlayingPlayer;

public class PlayerFactory {
	
	private Map<String, PlayerMaker> playerMakers =
			new HashMap<String, PlayerFactory.PlayerMaker>();
	
	public PlayerFactory() {
		playerMakers.put("Cz³owiek", new PlayerMaker() {
			@Override
			public Player makePlayer(Color color) {
				return new HumanPlayer(color);
			}
		});
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
	
	private abstract class PlayerMaker {
		public abstract Player makePlayer(Color color);
	}
}
