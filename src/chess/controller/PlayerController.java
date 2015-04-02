package chess.controller;

import java.util.List;

import chess.model.Color;
import chess.model.Player;
import chess.model.PlayerFactory;
import chess.model.PlayerFactory.PlayerMaker;
import chess.model.players.HumanPlayer;
import chess.model.players.RandomPlayer;

public class PlayerController {

	private static PlayerController instance;
	
	private PlayerFactory playerFactory = new PlayerFactory();
	
	private PlayerController() { }
	
	public static PlayerController getInstance() {
		if(instance == null) {
			instance = new PlayerController();
			instance.registerAvailablePlayers();
		}
		return instance;
	}

	private void registerAvailablePlayers() {
		playerFactory.registerPlayerMaker("Cz³owiek", new PlayerMaker() {
			@Override
			public Player makePlayer(Color color) {
				return new HumanPlayer(color);
			}
		});
		playerFactory.registerPlayerMaker("Komputer - losowy", new PlayerMaker() {
			@Override
			public Player makePlayer(Color color) {
				return new RandomPlayer(color);
			}
		});
	}
	
	public List<String> getAvailablePlayers() {
		return playerFactory.getAvailablePlayers();
	}
	
	public Player makePlayer(String name, Color color) {
		return playerFactory.makePlayer(name, color);
	}

}
